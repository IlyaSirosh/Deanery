package controllers.decorators;

import controllers.configs.MainController;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RequestPath {
    String value();
    MainController.RequestMethods method() default MainController.RequestMethods.GET;
}
