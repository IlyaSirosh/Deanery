package com.ui;

import com.controllers.configs.MainController;
import com.controllers.decorators.RequestPath;
import com.controllers.exceptions.UnsatisfiedDependencyException;
import com.model.Student;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@com.controllers.decorators.View
@RequestPath("/addStudent")
public class AddStudentView extends View{

    @Override
    public void renderView(Map<String, Object> params) throws UnsatisfiedDependencyException {
        JFrame f = new JFrame();
        f.setSize(400, 400);
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

        JTextField name = new JTextField();
        JLabel nameLabel = new JLabel("Name: ", JLabel.LEFT);

        JTextField steciality = new JTextField();
        JLabel stecialityLabel = new JLabel("Speciality: ", JLabel.LEFT);

        JSpinner credits = new JSpinner(new SpinnerNumberModel(1, 1, 260, 1));
        JLabel creditsLabel = new JLabel("Credits: ", JLabel.LEFT);

        JTextField startDate = new JTextField("2018-04-04");
        JLabel startDateLabel = new JLabel("Start Date: ", JLabel.LEFT);

        JTextField endDate = new JTextField("2018-04-04");
        JLabel endDateLabel = new JLabel("End Date: ", JLabel.LEFT);

        JComboBox endReason = new JComboBox(Student.LeaveReason.values());
        JLabel endReasonLabel = new JLabel("End Reason: ", JLabel.LEFT);


        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel).addComponent(name))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(stecialityLabel).addComponent(steciality))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(creditsLabel).addComponent(credits))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(startDateLabel).addComponent(startDate))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(endDateLabel).addComponent(endDate))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(endReasonLabel).addComponent(endReason))
        );

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel).addComponent(stecialityLabel).addComponent(creditsLabel).addComponent(startDateLabel).addComponent(endDateLabel).addComponent(endReasonLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(name).addComponent(steciality).addComponent(credits).addComponent(startDate).addComponent(endDate).addComponent(endReason)));


        okButton.addActionListener(e -> {
            Student newStudent = new Student();
            newStudent.setSurname(name.getText());
            newStudent.setSpeciality(steciality.getText());
            newStudent.setCredits((Integer) credits.getValue());
            newStudent.setStartdate(Date.valueOf(startDate.getText()));
            newStudent.setEnddate(Date.valueOf(endDate.getText()));
            newStudent.setEnddateReason((Student.LeaveReason) endReason.getSelectedItem());
            MainController.getMainController().renderTemplate("/saveStudent", new HashMap<String, Object>() {{put("student", newStudent);}});

            f.dispose();
        });

        cancelButton.addActionListener(e -> {
            f.dispose();
        });

        JLabel mainLabel = new JLabel("Add student");
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
