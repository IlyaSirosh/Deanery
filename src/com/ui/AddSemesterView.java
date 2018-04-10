package com.ui;

import com.controllers.configs.MainController;
import com.controllers.decorators.RequestPath;
import com.controllers.exceptions.UnsatisfiedDependencyException;
import com.model.Semester;
import com.model.enums.SemesterEnum;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@com.controllers.decorators.View
@RequestPath("/addSemester")
public class AddSemesterView extends View{
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

        JComboBox sem = new JComboBox(SemesterEnum.values());
        JLabel semLabel = new JLabel("Season: ", JLabel.LEFT);

        Calendar c = Calendar.getInstance();
        JSpinner year = new JSpinner(new SpinnerNumberModel(c.get(Calendar.YEAR), c.get(Calendar.YEAR), c.get(Calendar.YEAR)+10, 1));
        JLabel yearLabel = new JLabel("Year: ", JLabel.LEFT);


        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(semLabel).addComponent(sem))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(yearLabel).addComponent(year))
        );

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(semLabel).addComponent(yearLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sem).addComponent(year)));


        okButton.addActionListener(e -> {
            Semester newSem = new Semester();
            newSem.setSemester((SemesterEnum) sem.getSelectedItem());
            newSem.setYear((Integer) year.getValue());
            MainController.getMainController().renderTemplate("/saveSemester", new HashMap<String, Object>() {{put("semester", newSem);}});

            f.dispose();
        });

        cancelButton.addActionListener(e -> {
            f.dispose();
        });

        JLabel mainLabel = new JLabel("Add Semester");
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
