package bht.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();

        context.register(WebConfig.class);

        servletContext.addListener(new ContextLoaderListener(
                context
        ));

        AnnotationConfigWebApplicationContext dispatcherContext
                = new AnnotationConfigWebApplicationContext();

        dispatcherContext.register(WebConfig.class);

        ServletRegistration.Dynamic servlet =
                servletContext.addServlet("dispatcher",
                        new DispatcherServlet(dispatcherContext));

        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

    }
}