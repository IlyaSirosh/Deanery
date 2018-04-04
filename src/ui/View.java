package ui;

import controllers.configs.MainController;
import controllers.exceptions.UnsatisfiedDependencyException;

import java.util.Map;

public abstract class View {

    public abstract void renderView(Map<String, Object> params) throws UnsatisfiedDependencyException;
    public void handleSubmit(String path, Map<String, Object> params){
        MainController.getMainController().renderTemplate(path, params);
    };
}
