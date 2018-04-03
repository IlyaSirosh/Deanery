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
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        JPanel panelDown = new JPanel();
        JButton okButton = new JButton("OK");
        panelDown.add(okButton);
        JButton cancelButton = new JButton("Cancel");
        panelDown.add(cancelButton);

        JPanel panelInputs = new JPanel();
        JTextField name = new JTextField();
        name.setColumns(20);
        JLabel nameLabel = new JLabel("Name: ", JLabel.LEFT);
        panelInputs.add(nameLabel);
        panelInputs.add(name);

        f.add(panelInputs, BorderLayout.CENTER);
        f.add(panelDown, BorderLayout.SOUTH);
        f.setVisible(true);
    }
}
