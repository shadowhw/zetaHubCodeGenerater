package com.heyexi;

import com.heyexi.generater.dto.DataSourceConfig;
import com.heyexi.generater.dto.GlobalConfig;
import com.heyexi.generater.dto.PackageConfig;
import com.heyexi.generater.dto.ZetaHubCodeGenerator;
import com.heyexi.generater.service.SourceCodeGenerateService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@SpringBootTest(classes = {Application.class})
@RunWith(SpringRunner.class)
public class Test {

    private final String url = "jdbc:mysql://localhost:3306/admin?useSSL=false&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=GMT%2B8";
    private final String uname = "root";
    private final String pwd = "123456";


    @Autowired
    private SourceCodeGenerateService codeGenerateService;

    /**
     * 代码生成集成测试
     */
    @org.junit.jupiter.api.Test
    void codeGenerateTest() {
        ZetaHubCodeGenerator zetaHubCodeGenerator = new ZetaHubCodeGenerator();
        GlobalConfig globalConfig = new GlobalConfig();
        PackageConfig packageConfig = new PackageConfig();
        DataSourceConfig dataSourceConfig = new DataSourceConfig();

        // 全局配置
        globalConfig.setTablesName(Arrays.asList("iam_menu")).setAuthor("heyexi")
                .setTablePrefix(Arrays.asList("iam_"))
                .setPrimaryFieldName("id").setDelFiledName("delFlag")
                .setProjectPathModel(2)
                // 使用相对路径
                .setCustomProjectRootPath("zetaHubCodeGenerater");

        // 包配置
        packageConfig.setParentPackage("com.heyexi");
        packageConfig.setModuleName("sample");

        dataSourceConfig.setPassword(pwd);
        dataSourceConfig.setUrl(url);
        dataSourceConfig.setUsername(uname);

        zetaHubCodeGenerator.setGlobalConfig(globalConfig);
        zetaHubCodeGenerator.setPackageConfig(packageConfig);
        zetaHubCodeGenerator.setDataSourceConfig(dataSourceConfig);

        codeGenerateService.generate(zetaHubCodeGenerator);
    }

}
