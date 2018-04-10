package com.controllers.configs;

import java.util.HashMap;
import java.util.Map;

public class Model {
    private Map<String, Object> params;

    public Model(){
        params = new HashMap<>();
    }

    Map<String, Object> getAllParams(){
        return params;
    }

    public void addParam(String name, Object o){
        params.put(name, o);
    }
}
