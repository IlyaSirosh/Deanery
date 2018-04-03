package ui;

import controllers.configs.MainController;

import java.util.Map;

public abstract class View {

    public abstract void renderView();
    public void handleSubmit(String path, Map<String, Object> params){
        MainController.getMainController().renderTemplate(path, params);
    };
}
