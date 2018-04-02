package ui;

import controllers.decorators.RequestPath;

import java.util.Map;

@RequestPath("/addCourse")
public class AddCourseView implements View{

    @Override
    public void renderView() {
        System.out.println("Trying to render view");
    }

    @Override
    public void handleSubmit(String path, Map<String, Object> params) {

    }

}
