package ui;

import controllers.decorators.RequestPath;

import javax.swing.*;
import java.util.Map;

@RequestPath("/addCourse")
public class AddCourseView extends View{

    @Override
    public void renderView() {
        JFrame f = new JFrame();
        f.setSize(400, 200);
        JTextField textField = new JTextField("This is a text");
        f.add(textField);
        f.setVisible(true);
    }

    @Override
    public void handleSubmit(String path, Map<String, Object> params) {

    }

}
