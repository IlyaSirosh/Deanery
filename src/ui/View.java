package ui;

import java.util.Map;

public interface View {

    void renderView();
    void handleSubmit(String path, Map<String, Object> params);
}
