package eltex.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Mvc конфигурация для быстрого написания шаблонных маппингов
 *
 * @author "Alexey Derevtsov"
 * @version 1.0.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /**
     * Метод для возвращения ссылок без серльеной логики
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/main").setViewName("main");
    }
    /**
     * Метод обработки ресурсов в определенных ссылках
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}