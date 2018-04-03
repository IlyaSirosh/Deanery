<<<<<<< HEAD
package starters;

import controllers.configs.MainController;

import java.util.HashMap;

public class ControllerStarter {
    public static void main(String[] args) throws Exception {
        MainController.MAIN_CONTROLLER.linkEverything();
        MainController.MAIN_CONTROLLER.renderTemplate("/index", new HashMap(){{put("testValue", "Some value");}});

    }
}
=======
package starters;

import controllers.configs.MainController;
import controllers.configs.ServicesDispatcher;

import java.util.HashMap;

public class ControllerStarter {
    public static void main(String[] args) throws Exception {
        MainController.createMainController();
        ServicesDispatcher.createServiceDispatcher();
        MainController.getMainController().linkEverything();
        MainController.getMainController().renderTemplate("/index", new HashMap<>());

    }
}
>>>>>>> ui
