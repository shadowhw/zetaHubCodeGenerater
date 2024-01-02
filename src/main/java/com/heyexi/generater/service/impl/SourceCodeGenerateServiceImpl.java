package com.heyexi.generater.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.heyexi.generater.constant.SourceCodeGenerateConstant;
import com.heyexi.generater.dto.DataSourceConfig;
import com.heyexi.generater.dto.GlobalConfig;
import com.heyexi.generater.dto.PackageConfig;
import com.heyexi.generater.dto.RenderDataDTO;
import com.heyexi.generater.dto.TableFieldData;
import com.heyexi.generater.dto.TemplateInfoData;
import com.heyexi.generater.dto.ZetaHubCodeGenerator;
import com.heyexi.generater.service.CommonServiceAbstract;
import com.heyexi.generater.service.IZetaHubDataSourceService;
import com.heyexi.generater.service.SourceCodeGenerateService;
import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author heyexi
 * @Date 2023-07-28 17:0:9
 * @Description
 */
@Service
@Slf4j
public class SourceCodeGenerateServiceImpl extends CommonServiceAbstract implements SourceCodeGenerateService {

    private IZetaHubDataSourceService zetaHubDataSourceService;
    private static final Map<String, String> dbDataTypeMapping = new HashMap<>();
    private final Configuration freemarkerConfiguration;
    private final String SOURCE_BASE_DIR = "\\src\\main\\java\\";
    private final String SQL_TABLE_INFO = "select column_name,data_type,column_comment from information_schema.columns where table_name = ? order by ordinal_position;";

    /**
     * 当前默认只支持MySQL
     */
    private final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

    static {
        dbDataTypeMapping.put("bigint", "Long");
        dbDataTypeMapping.put("varchar", "String");
        dbDataTypeMapping.put("int", "Integer");
        dbDataTypeMapping.put("datetime", "LocalDateTime");
        dbDataTypeMapping.put("date", "LocalDate");
        dbDataTypeMapping.put("tinyint", "Integer");
        dbDataTypeMapping.put("decimal", "BigDecimal");
        dbDataTypeMapping.put("text", "String");
        dbDataTypeMapping.put("char", "String");
        dbDataTypeMapping.put("double", "Double");
        dbDataTypeMapping.put("float", "Float");
    }

    public SourceCodeGenerateServiceImpl(final Configuration freemarkerConfiguration) {
        this.freemarkerConfiguration = freemarkerConfiguration;
    }

    @Override
    public void generate(ZetaHubCodeGenerator zetaHubCodeGenerator) {
        // 检查非空信息
        validParam(zetaHubCodeGenerator);

        DataSourceConfig dataSourceConfig = zetaHubCodeGenerator.getDataSourceConfig();
        GlobalConfig globalConfig = zetaHubCodeGenerator.getGlobalConfig();
        PackageConfig packageConfig = zetaHubCodeGenerator.getPackageConfig();

        // 配置数据源
        buildDataSource(dataSourceConfig);

        List<String> tablesName = globalConfig.getTablesName();
        tablesName.forEach(tb -> {
            // 查询表信息
            List<TableFieldData> queryList = zetaHubDataSourceService.executeQueryList(SQL_TABLE_INFO, TableFieldData.class, ListUtil.of(tb));
            if (CollectionUtils.isEmpty(queryList)) {
                return;
            }
            List<TableFieldData> tableFieldData = queryList.stream()
                    .map(t -> t.setJavaDataType(dbDataTypeMapping.get(t.getDataType().toLowerCase())))
                    .map(t -> t.setEntityFieldName(underScoreCaseToCamelCase(t.getColumnName(), false)))
                    .collect(Collectors.toList());

            // 获取文件生成路径
            String urlPath = getSourceGeneratePath(globalConfig, packageConfig);
            // 获取实体名称
            String entityName = getEntityNameByPrefix(globalConfig.getTablePrefix(), tb);

            RenderDataDTO renderDataDTO = new RenderDataDTO();
            renderDataDTO.setTableName(tb);
            renderDataDTO.setJavaDataTypeList(tableFieldData.stream().map(TableFieldData::getJavaDataType).collect(Collectors.toSet()));
            renderDataDTO.setDate(new Date());
            renderDataDTO.setProjectRootUrlPath(urlPath);
            renderDataDTO.setEntityName(entityName);
            renderDataDTO.setTableFieldData(tableFieldData);
            renderDataDTO.setPackageConfig(packageConfig);
            renderDataDTO.setGlobalConfig(globalConfig);
            renderDataDTO.setHumpEntityName(underScoreCaseToCamelCase(entityName, false));

            try {
                List<TemplateInfoData> templateInfoDataList = buildTemplateInfo(urlPath, entityName, renderDataDTO);

                for (TemplateInfoData templateInfoData : templateInfoDataList) {
                    // 渲染模板
                    String renderedContent = FreeMarkerTemplateUtils.processTemplateIntoString(templateInfoData.getTemplate(),
                            templateInfoData.getRenderDataDTO());

                    // 创建目录
                    File file = new File(templateInfoData.getUrl());
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    // 创建Java文件
                    File outputFile = new File(templateInfoData.getFileName());
                    try (FileWriter writer = new FileWriter(outputFile)) {
                        writer.write(renderedContent);
                        writer.flush();
                    }
                }
            } catch (Exception e) {
                log.warn("获取模板文件信息异常:{}", e.getMessage(), e);
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 构造模板文件信息
     *
     * @param projectRootUrlPath
     * @param entityName
     * @param renderDataDTO
     * @return
     * @throws IOException
     */
    private List<TemplateInfoData> buildTemplateInfo(String projectRootUrlPath, String entityName, RenderDataDTO renderDataDTO) throws IOException {
        List<TemplateInfoData> list = new ArrayList<>();
        // 默认忽略字段
        List<String> ignoreFields = Arrays.asList("delFlag", "createById", "createByName", "createTime", "updateTime", "updateById", "updateByName", "orgId");

        // Controller
        TemplateInfoData controller = new TemplateInfoData();
        controller.setTemplate(freemarkerConfiguration.getTemplate(SourceCodeGenerateConstant.TEMPLATE_NAME.CONTROLLER));
        controller.setUrl(projectRootUrlPath + SourceCodeGenerateConstant.PACKAGE_NAME.CONTROLLER + "\\");
        controller.setFileName(controller.getUrl() + entityName + underScoreCaseToCamelCase(SourceCodeGenerateConstant.PACKAGE_NAME.CONTROLLER, true)
                + SourceCodeGenerateConstant.FILE_TYPE.JAVA);
        RenderDataDTO controllerDataDTO = renderDataDTO.cloneRenderDataDTO();
        controller.setRenderDataDTO(controllerDataDTO);

        // PageListDTO
        TemplateInfoData pageListDTO = new TemplateInfoData();
        pageListDTO.setTemplate(freemarkerConfiguration.getTemplate(SourceCodeGenerateConstant.TEMPLATE_NAME.PAGE_LIST_DTO));
        pageListDTO.setUrl(projectRootUrlPath + SourceCodeGenerateConstant.PACKAGE_NAME.DOMAIN + "\\"
                + SourceCodeGenerateConstant.PACKAGE_NAME.DTO + "\\");
        pageListDTO.setFileName(pageListDTO.getUrl() + entityName + SourceCodeGenerateConstant.STRING_POOL.PAGE_LIST_DTO
                + SourceCodeGenerateConstant.FILE_TYPE.JAVA);
        RenderDataDTO pageListDataDTO = renderDataDTO.cloneRenderDataDTO();
        List<TableFieldData> tableFieldData = pageListDataDTO.getTableFieldData().stream().filter(t -> !ignoreFields.contains(t.getEntityFieldName()))
                .collect(Collectors.toList());
        pageListDataDTO.setTableFieldData(tableFieldData);
        pageListDataDTO.setJavaDataTypeList(pageListDataDTO.getTableFieldData().stream().map(TableFieldData::getJavaDataType).collect(Collectors.toSet()));
        pageListDTO.setRenderDataDTO(pageListDataDTO);

        // PageListVO
        TemplateInfoData pageListVO = new TemplateInfoData();
        pageListVO.setTemplate(freemarkerConfiguration.getTemplate(SourceCodeGenerateConstant.TEMPLATE_NAME.PAGE_LIST_VO));
        pageListVO.setUrl(projectRootUrlPath + SourceCodeGenerateConstant.PACKAGE_NAME.DOMAIN + "\\"
                + SourceCodeGenerateConstant.PACKAGE_NAME.VO + "\\");
        pageListVO.setFileName(pageListVO.getUrl() + entityName + SourceCodeGenerateConstant.STRING_POOL.PAGE_LIST_VO
                + SourceCodeGenerateConstant.FILE_TYPE.JAVA);
        RenderDataDTO pageListVoData = renderDataDTO.cloneRenderDataDTO();
        pageListVO.setRenderDataDTO(pageListVoData);

        // DetailVO
        TemplateInfoData detailVO = new TemplateInfoData();
        detailVO.setTemplate(freemarkerConfiguration.getTemplate(SourceCodeGenerateConstant.TEMPLATE_NAME.DETAIL_VO));
        detailVO.setUrl(projectRootUrlPath + SourceCodeGenerateConstant.PACKAGE_NAME.DOMAIN + "\\"
                + SourceCodeGenerateConstant.PACKAGE_NAME.VO + "\\");
        detailVO.setFileName(pageListVO.getUrl() + entityName + SourceCodeGenerateConstant.STRING_POOL.DETAIL_VO
                + SourceCodeGenerateConstant.FILE_TYPE.JAVA);
        RenderDataDTO detailVOVoData = renderDataDTO.cloneRenderDataDTO();
        detailVO.setRenderDataDTO(detailVOVoData);

        // saveOrUpdateDTO
        TemplateInfoData saveOrUpdateDTO = new TemplateInfoData();
        saveOrUpdateDTO.setTemplate(freemarkerConfiguration.getTemplate(SourceCodeGenerateConstant.TEMPLATE_NAME.SAVE_OR_UPDATE_DTO));
        saveOrUpdateDTO.setUrl(projectRootUrlPath + SourceCodeGenerateConstant.PACKAGE_NAME.DOMAIN + "\\"
                + SourceCodeGenerateConstant.PACKAGE_NAME.DTO + "\\");
        saveOrUpdateDTO.setFileName(saveOrUpdateDTO.getUrl() + entityName + SourceCodeGenerateConstant.STRING_POOL.SAVE_OR_UPDATE_DTO
                + SourceCodeGenerateConstant.FILE_TYPE.JAVA);
        RenderDataDTO saveOrUpdateDataDTO = renderDataDTO.cloneRenderDataDTO();
        List<TableFieldData> saveOrUpdateTableFieldData = saveOrUpdateDataDTO.getTableFieldData().stream().filter(t -> !ignoreFields.contains(t.getEntityFieldName()))
                .collect(Collectors.toList());
        saveOrUpdateDataDTO.setTableFieldData(saveOrUpdateTableFieldData);
        saveOrUpdateDataDTO.setJavaDataTypeList(saveOrUpdateDataDTO.getTableFieldData().stream().map(TableFieldData::getJavaDataType).collect(Collectors.toSet()));
        saveOrUpdateDTO.setRenderDataDTO(saveOrUpdateDataDTO);

        // convert
        TemplateInfoData convert = new TemplateInfoData();
        convert.setTemplate(freemarkerConfiguration.getTemplate(SourceCodeGenerateConstant.TEMPLATE_NAME.CONVERT));
        convert.setUrl(projectRootUrlPath + SourceCodeGenerateConstant.PACKAGE_NAME.DOMAIN + "\\"
                + SourceCodeGenerateConstant.PACKAGE_NAME.CONVERT + "\\");
        convert.setFileName(convert.getUrl() + entityName + SourceCodeGenerateConstant.STRING_POOL.CONVERT
                + SourceCodeGenerateConstant.FILE_TYPE.JAVA);
        RenderDataDTO convertDataDTO = renderDataDTO.cloneRenderDataDTO();
        convert.setRenderDataDTO(convertDataDTO);

        // Entity
        TemplateInfoData entity = new TemplateInfoData();
        entity.setTemplate(freemarkerConfiguration.getTemplate(SourceCodeGenerateConstant.TEMPLATE_NAME.ENTITY));
        entity.setUrl(projectRootUrlPath + SourceCodeGenerateConstant.PACKAGE_NAME.DOMAIN + "\\"
                + SourceCodeGenerateConstant.PACKAGE_NAME.ENTITY + "\\");
        entity.setFileName(entity.getUrl() + entityName + SourceCodeGenerateConstant.FILE_TYPE.JAVA);
        RenderDataDTO entityDataDTO = renderDataDTO.cloneRenderDataDTO();
        entity.setRenderDataDTO(entityDataDTO);

        // Service
        TemplateInfoData service = new TemplateInfoData();
        service.setTemplate(freemarkerConfiguration.getTemplate(SourceCodeGenerateConstant.TEMPLATE_NAME.SERVICE));
        service.setUrl(projectRootUrlPath + SourceCodeGenerateConstant.PACKAGE_NAME.SERVICE + "\\");
        service.setFileName(service.getUrl() + entityName + SourceCodeGenerateConstant.STRING_POOL.SERVICE
                + SourceCodeGenerateConstant.FILE_TYPE.JAVA);
        RenderDataDTO serviceDataDTO = renderDataDTO.cloneRenderDataDTO();
        service.setRenderDataDTO(serviceDataDTO);

        // ServiceImpl
        TemplateInfoData serviceImpl = new TemplateInfoData();
        serviceImpl.setTemplate(freemarkerConfiguration.getTemplate(SourceCodeGenerateConstant.TEMPLATE_NAME.SERVICE_IMPL));
        serviceImpl.setUrl(projectRootUrlPath + SourceCodeGenerateConstant.PACKAGE_NAME.SERVICE + "\\"
                + SourceCodeGenerateConstant.PACKAGE_NAME.IMPL + "\\");
        serviceImpl.setFileName(serviceImpl.getUrl() + entityName + SourceCodeGenerateConstant.STRING_POOL.SERVICE_IMPL
                + SourceCodeGenerateConstant.FILE_TYPE.JAVA);
        RenderDataDTO serviceImplDTO = renderDataDTO.cloneRenderDataDTO();
        serviceImpl.setRenderDataDTO(serviceImplDTO);

        // Mapper
        TemplateInfoData mapper = new TemplateInfoData();
        mapper.setTemplate(freemarkerConfiguration.getTemplate(SourceCodeGenerateConstant.TEMPLATE_NAME.MAPPER));
        mapper.setUrl(projectRootUrlPath + SourceCodeGenerateConstant.PACKAGE_NAME.MAPPER + "\\");
        mapper.setFileName(mapper.getUrl() + entityName + SourceCodeGenerateConstant.STRING_POOL.MAPPER
                + SourceCodeGenerateConstant.FILE_TYPE.JAVA);
        RenderDataDTO mapperlDTO = renderDataDTO.cloneRenderDataDTO();
        mapper.setRenderDataDTO(mapperlDTO);

        // MapperImpl
        TemplateInfoData mapperImpl = new TemplateInfoData();
        mapperImpl.setTemplate(freemarkerConfiguration.getTemplate(SourceCodeGenerateConstant.TEMPLATE_NAME.MAPPER_IMPL));
        mapperImpl.setUrl(projectRootUrlPath + SourceCodeGenerateConstant.PACKAGE_NAME.MAPPER + "\\"
                + SourceCodeGenerateConstant.PACKAGE_NAME.XML + "\\");
        mapperImpl.setFileName(mapperImpl.getUrl() + entityName + SourceCodeGenerateConstant.STRING_POOL.MAPPER
                + SourceCodeGenerateConstant.FILE_TYPE.XML);
        RenderDataDTO mapperImplDTO = renderDataDTO.cloneRenderDataDTO();
        mapperImpl.setRenderDataDTO(mapperImplDTO);


        list.add(controller);
        list.add(pageListDTO);
        list.add(pageListVO);
        list.add(entity);
        list.add(service);
        list.add(serviceImpl);
        list.add(mapper);
        list.add(mapperImpl);
        list.add(saveOrUpdateDTO);
        list.add(convert);
        list.add(detailVO);
        return list;
    }

    /**
     * 获取源码文件生成路径
     *
     * @param config
     * @return
     */
    private String getSourceGeneratePath(GlobalConfig config, PackageConfig packageConfig) {
        String rootPath = System.getProperty("user.dir");
        String[] split = rootPath.split("/|\\\\");
        // 获取项目根目录
        String path = rootPath.substring(0, rootPath.length() - split[split.length - 1].length());
        // 获取Source目录
        StringBuilder sourcePath = new StringBuilder();
        sourcePath.append(SOURCE_BASE_DIR).append(packageConfig.getParentPackage().replace(".", File.separator));
        if (Objects.nonNull(packageConfig.getModuleName())) {
            sourcePath.append(File.separator).append(packageConfig.getModuleName()).append(File.separator);
        }
        if (Objects.isNull(config.getProjectPathModel())) {
            return rootPath + sourcePath;
        }
        if (SourceCodeGenerateConstant.NUM.NUM_1.equals(config.getProjectPathModel())) {
            return config.getCustomProjectRootPath() + sourcePath;
        } else if (SourceCodeGenerateConstant.NUM.NUM_2.equals(config.getProjectPathModel())) {
            return path + config.getCustomProjectRootPath() + sourcePath;
        } else {
            throw new IllegalStateException("项目代码生成路径模式非法值");
        }

    }

    /**
     * 获取实体名称
     *
     * @param tablePrefix
     * @param tableName
     * @return
     */
    private String getEntityNameByPrefix(List<String> tablePrefix, String tableName) {
        if (CollectionUtils.isEmpty(tablePrefix)) {
            return tableName;
        }
        for (String prefix : tablePrefix) {
            if (tableName.startsWith(prefix)) {
                return underScoreCaseToCamelCase(tableName.substring(prefix.length()), true);
            }
        }
        return underScoreCaseToCamelCase(tableName, true);
    }


    /**
     * 参数校验
     *
     * @param zetaHubCodeGenerator
     */
    private void validParam(ZetaHubCodeGenerator zetaHubCodeGenerator) {
        GlobalConfig globalConfig = zetaHubCodeGenerator.getGlobalConfig();
        PackageConfig packageConfig = zetaHubCodeGenerator.getPackageConfig();
        DataSourceConfig dataSourceConfig = zetaHubCodeGenerator.getDataSourceConfig();

        Assert.notNull(zetaHubCodeGenerator, "生成器不能为空!");
        Assert.notNull(globalConfig, "全局配置信息不能为空!");
        Assert.notNull(packageConfig, "包配置信息不能为空!");
        Assert.notNull(dataSourceConfig, "数据源配置信息不能为空!");

        // 校验项目路径
        if (Objects.nonNull(globalConfig.getProjectPathModel())) {
            Assert.notNull(globalConfig.getCustomProjectRootPath(), "项目根路径不能为空!");
        }

        Assert.notEmpty(globalConfig.getTablesName(), "表名不能为空!");
        Assert.notNull(dataSourceConfig.getUrl(), "数据库连接url不能为空!");
        Assert.notNull(dataSourceConfig.getUsername(), "数据库连接用户名不能为空!");
        Assert.notNull(dataSourceConfig.getPassword(), "数据库连接密码不能为空!");
        Assert.notNull(packageConfig.getParentPackage(), "包配置信息的父级包目录不能为空!");
    }

    /**
     * 设置数据源
     *
     * @param dataSourceConfig
     */
    private void buildDataSource(DataSourceConfig dataSourceConfig) {
        if (Objects.isNull(zetaHubDataSourceService)) {
            zetaHubDataSourceService = new ZetaHubDataSourceServiceImpl(DRIVER_NAME, dataSourceConfig.getUrl(), dataSourceConfig.getUsername(), dataSourceConfig.getPassword());
        }
    }

}
