package com.controllers.exceptions;

import com.controllers.configs.MainController;

public class MappingNotFoundException extends Exception {
    public MappingNotFoundException(String path){
        super("The requested mapping "+path+" wasn't found in annotated controllers.");
    }
}
