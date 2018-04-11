package com.ui;

import com.controllers.configs.MainController;
import com.controllers.decorators.RequestPath;
import com.controllers.decorators.View;
import com.controllers.exceptions.UnsatisfiedDependencyException;
import com.model.GroupStudent;
import com.model.Lesson;
import com.model.Semester;
import com.model.Student;
import com.model.enums.SemesterEnum;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@View
@RequestPath("/editStudentResult")
public class EditStudentResult extends com.ui.View{

    @Override
    public void renderView(Map<String, Object> params) throws UnsatisfiedDependencyException {
        JFrame f = new JFrame();
        f.setSize(400, 250);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(new BorderLayout());

        JPanel panelInputs = new JPanel();
        GroupLayout layout = new GroupLayout(panelInputs);
        panelInputs.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JPanel panelDown = new JPanel();
        JButton okButton = new JButton("OK");
        panelDown.add(okButton);
        JButton cancelButton = new JButton("Cancel");
        panelDown.add(cancelButton);

        JSpinner grade = new JSpinner(new SpinnerNumberModel(61,0,100,1));
        JLabel gradeLabel = new JLabel("Grade: ", JLabel.LEFT);



        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(gradeLabel).addComponent(grade))
        );

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(gradeLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(grade)));


        okButton.addActionListener(e -> {
            GroupStudent gs = new GroupStudent();
            System.out.println(params.get("lId"));
            Student s = new Student(Integer.parseInt(params.get("sId").toString()));
            Lesson l = new Lesson(Integer.parseInt(params.get("lId").toString()));
            gs.setStudent(s);
            gs.setLesson(l);
            gs.setGrade((Integer) grade.getValue());
            MainController.getMainController().renderTemplate("/updateResult", new HashMap<String, Object>() {{put("exam", gs);}});

            f.dispose();
        });

        cancelButton.addActionListener(e -> {
            f.dispose();
        });

        JLabel mainLabel = new JLabel("Edit Student's Result");
        mainLabel.setFont (mainLabel.getFont ().deriveFont (18.0f));
        mainLabel.setFont(mainLabel.getFont ().deriveFont(mainLabel.getFont ().getStyle() | Font.BOLD));
        mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
        Border border = mainLabel.getBorder();
        Border margin = new EmptyBorder(10,10,10,10);
        mainLabel.setBorder(new CompoundBorder(border, margin));

        f.add(mainLabel, BorderLayout.NORTH);
        f.add(panelInputs, BorderLayout.CENTER);
        f.add(panelDown, BorderLayout.SOUTH);
        f.setVisible(true);
    }
}
