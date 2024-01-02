package com.heyexi.config;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.Version;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @Author heyexi
 * @Date 2023-05-11 17:12:53
 * @Description freemaker配置信息
 */
@Component
public class FreeMarkerConfig {

    @Bean
    public Configuration freemarkerConfiguration() {
        Configuration configuration = new Configuration(new Version("2.3.31"));
        configuration.setDefaultEncoding("UTF-8");
        // 设置模板加载器
        configuration.setTemplateLoader(templateLoader());
        // 设置对象包装器
        configuration.setObjectWrapper(new DefaultObjectWrapperBuilder(new Version("2.3.31")).build());
        return configuration;
    }

    private TemplateLoader templateLoader() {
        // 使用 ClassTemplateLoader 加载 resources 目录下的模板文件
        return new ClassTemplateLoader(FreeMarkerConfig.class, "/template");
    }
}
