package ui;

import java.util.Map;

public abstract class View {

    public abstract void renderView();
    public void handleSubmit(String path, Map<String, Object> params){

    };
}
