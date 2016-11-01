package com.rdas;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    private static final String CONFIG_LOCATION = "com.rdas.config";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        System.out.println("***** Initializing Application for " + servletContext.getServerInfo() + " *****");
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.setConfigLocation(CONFIG_LOCATION);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        ServletRegistration.Dynamic servlet = servletContext.addServlet("mvc-dispatcher", dispatcherServlet);

        servlet.addMapping("/");
        servlet.setAsyncSupported(true);
        servlet.setLoadOnStartup(1);

        servletContext.setInitParameter("spring.profiles.default", "qa");
//        servletContext.setInitParameter("spring.profiles.active", "dev");
    }
}
