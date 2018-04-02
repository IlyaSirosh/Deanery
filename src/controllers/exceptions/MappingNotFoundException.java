package controllers.exceptions;

import controllers.configs.MainController;

public class MappingNotFoundException extends Exception {
    public MappingNotFoundException(String path, MainController.RequestMethods method){
        super("The requested mapping "+path+"("+method+") wasn't found in annotated controllers.");
    }
}
