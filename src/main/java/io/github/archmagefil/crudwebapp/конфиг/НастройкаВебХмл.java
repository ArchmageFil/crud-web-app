package io.github.archmagefil.crudwebapp.конфиг;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class НастройкаВебХмл extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{КонфигБд.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    } //{НастройкаПриложения.class}

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}