<<<<<<< HEAD
package ui;

import controllers.configs.MainController;

import java.util.Map;

public abstract class View {

    public abstract void renderView();
    public void handleSubmit(String path, Map<String, Object> params){
        MainController.MAIN_CONTROLLER.renderTemplate(path, params);
    };
}
=======
package ui;

import controllers.configs.MainController;
import controllers.exceptions.UnsatisfiedDependencyException;

import java.util.Map;

public abstract class View {

    public abstract void renderView() throws UnsatisfiedDependencyException;
    public void handleSubmit(String path, Map<String, Object> params){
        MainController.getMainController().renderTemplate(path, params);
    };
}
>>>>>>> ui
