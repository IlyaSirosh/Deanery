package starters;

import controllers.configs.MainController;

import java.util.HashMap;

public class ControllerStarter {
    public static void main(String[] args) throws Exception {
        MainController mainController = new MainController();
        mainController.linkEverything();
        mainController.renderTemplate("/try", MainController.RequestMethods.GET, new HashMap(){{put("testValue", "Some value");}});
    }
}
