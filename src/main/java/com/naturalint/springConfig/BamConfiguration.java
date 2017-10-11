package com.naturalint.springConfig;

import com.naturalint.exceptions.BamException;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

/**
 * Created by skn on 10/9/2017.
 */
@Configuration
@ComponentScan(basePackages = {"com.naturalint.*"})
@PropertySource("classpath:/application-${spring.profiles.active:default}.properties")
public class BamConfiguration  {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ProducerTemplate producerTemplate() {
        SimpleRegistry simpleRegistry = new SimpleRegistry();
        CamelContext camelContext = new DefaultCamelContext(simpleRegistry);
        ProducerTemplate template = null;
        try {
            camelContext.addRoutes(bamRoutes());
            PropertiesComponent propertiesComponent = new PropertiesComponent();
            if(System.getProperties().containsKey("spring.profiles.active"))
                propertiesComponent.setLocation("classpath:/application-" + System.getProperty("spring.profiles.active") + ".properties");
            else
                propertiesComponent.setLocation("classpath:/application-default.properties");
            camelContext.addComponent("properties", propertiesComponent);
            template = camelContext.createProducerTemplate();
            camelContext.start();
        } catch (Exception e) {
            throw new BamException(e);
        }
        return template;
    }

    @Bean
    public BamRoutes bamRoutes() {
        return new BamRoutes();
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(this.applicationContext);
        templateResolver.setPrefix("classpath:META-INF/resources/WEB-INF/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(true);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver(@Autowired SpringTemplateEngine templateEngine){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }
}
