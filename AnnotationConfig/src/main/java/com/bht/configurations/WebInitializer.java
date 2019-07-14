package com.bht.configurations;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {

        // Application Context Configure by Spring Annotation
        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();

        context.register(WebConfig.class);
        context.setServletContext(servletContext);


        // Servlet Dispatcher using for Spring MVC model
        ServletRegistration.Dynamic servlet = servletContext
                .addServlet("dispatcher",
                        new DispatcherServlet(context));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");


        // Register Encoding filter for UTF-8
        CharacterEncodingFilter characterEncodingFilter =
                new CharacterEncodingFilter(
                        "UTF-8",
                        true);

        // Make sure encoding filter is matched
        servletContext.addFilter(
                "myFilter", characterEncodingFilter)
                .addMappingForUrlPatterns(
                        null, false, "/*");
    }
}