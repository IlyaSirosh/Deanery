package ui;

import controllers.decorators.RequestPath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

@RequestPath("/addCourse")
public class AddCourseView extends View{

    @Override
    public void renderView() {
        JFrame f = new JFrame();
        f.setSize(400, 200);
        f.setLayout(new BorderLayout());
        JTextField textField = new JTextField("This is a text");
        JButton b = new JButton("GOGOGOGO");
        f.add(b, BorderLayout.SOUTH);
        b.addActionListener(e -> {
            HashMap<String, Object> params = new HashMap<>();
            params.put("testValue", textField.getText());
            handleSubmit("/try", params);
            f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
        });
        f.add(textField, BorderLayout.NORTH);
        f.setVisible(true);
    }
}
