package com.heyexi.generater.constant;

/**
 * @Author heyexi
 * @Date 2023-08-3 15:7:48
 * @Description 代码生成器常量
 */
public interface SourceCodeGenerateConstant {

    /**
     * 数字常量
     */
    interface NUM {
        Integer NUM_0 = 0;
        Integer NUM_1 = 1;
        Integer NUM_2 = 2;
        Integer NUM_3 = 3;
        Integer NUM_4 = 4;
        Integer NUM_5 = 5;
        Integer NUM_6 = 6;
        Integer NUM_7 = 7;
        Integer NUM_8 = 8;
        Integer NUM_9 = 9;
    }

    /**
     * 模板文件名称
     */
    interface TEMPLATE_NAME {
        String ENTITY = "entity.ftl";
        String CONTROLLER = "controller.ftl";
        String PAGE_LIST_DTO = "pageListDTO.ftl";
        String SAVE_OR_UPDATE_DTO = "saveOrUpdateDTO.ftl";
        String CONVERT = "convert.ftl";
        String PAGE_LIST_VO = "pageListVO.ftl";
        String DETAIL_VO = "detailVO.ftl";
        String SERVICE = "service.ftl";
        String SERVICE_IMPL = "serviceImpl.ftl";
        String MAPPER = "mapper.ftl";
        String MAPPER_IMPL = "mapperImpl.ftl";
        String TABLE_DDL = "tableDDL.ftl";
    }

    /**
     * 文件后缀类型
     */
    interface FILE_TYPE {
        String JAVA = ".java";
        String VUE = ".vue";
        String XML = ".xml";
        String SQL = ".sql";
    }

    /**
     * 包名称
     */
    interface PACKAGE_NAME {
        String ENTITY = "entity";
        String CONTROLLER = "controller";
        String SERVICE = "service";
        String IMPL = "impl";
        String XML = "xml";
        String MAPPER = "mapper";
        String DOMAIN = "domain";
        String DTO = "dto";
        String CONVERT = "convert";
        String VO = "vo";
    }

    /**
     * 字符串常量
     */
    interface STRING_POOL {
        String ID = "id";
        String PAGE_LIST_DTO = "PageListDTO";
        String PAGE_LIST_VO = "PageListVO";
        String DETAIL_VO = "DetailVO";
        String SERVICE = "Service";
        String SERVICE_IMPL = "ServiceImpl";
        String MAPPER = "Mapper";
        String SAVE_OR_UPDATE_DTO = "SaveOrUpdateDTO";
        String CONVERT = "Convert";
    }

}
