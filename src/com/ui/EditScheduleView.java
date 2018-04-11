package com.ui;

import com.controllers.configs.MainController;
import com.controllers.decorators.RequestPath;
import com.controllers.exceptions.UnsatisfiedDependencyException;
import com.model.Schedule;
import com.model.Week;
import com.model.enums.Day;
import com.resources.BeansDispatcher;
import com.services.DeaneryService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@com.controllers.decorators.View
@RequestPath("/editSchedule")
public class EditScheduleView extends View{
    private class WeekItem
    {
        public Week week;

        public WeekItem(Week week) {
            this.week = week;
        }

        public String toString()
        {
            return week.getSemester().getSemester() + " " + week.getSemester().getYear() + " " + week.getNumber() + " week";
        }
    }

    @Override
    public void renderView(Map<String, Object> params) throws UnsatisfiedDependencyException {
        DeaneryService ds = (DeaneryService) BeansDispatcher.getBean(DeaneryService.class);


        JFrame f = new JFrame();
        f.setSize(400, 350);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLayout(new BorderLayout());

        JPanel panelInputs = new JPanel();
        GroupLayout layout = new GroupLayout(panelInputs);
        panelInputs.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        JPanel panelDown = new JPanel();
        JButton detailsButton = new JButton("See Details");
        panelDown.add(detailsButton);
        JButton okButton = new JButton("OK");
        panelDown.add(okButton);
        JButton cancelButton = new JButton("Cancel");
        panelDown.add(cancelButton);

        JComboBox day = new JComboBox(Day.values());
        JLabel dayLabel = new JLabel("Day: ", JLabel.LEFT);

        JLabel weekLabel = new JLabel("Week №: ", JLabel.LEFT);
        JComboBox week = new JComboBox();
        List<Week> weeks = ds.getAllWeeks();
        weeks.forEach(d -> {
            week.addItem(new WeekItem(d));
                });

        JSpinner lesson = new JSpinner(new SpinnerNumberModel(1, 1, 6, 1));
        JLabel lessonLabel = new JLabel("Lesson №: ", JLabel.LEFT);


        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(dayLabel).addComponent(day))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(weekLabel).addComponent(week))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lessonLabel).addComponent(lesson))
        );

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(dayLabel).addComponent(weekLabel).addComponent(lessonLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(day).addComponent(week).addComponent(lesson)));


        okButton.addActionListener(e -> {
            WeekItem wi = (WeekItem) week.getSelectedItem();
            Week w = wi.week;
            Schedule newSchedule = new Schedule ();
            newSchedule.setDay((Day) day.getSelectedItem());
            newSchedule.setWeek(w);
            newSchedule.setScheduleId(Integer.parseInt(params.get("id").toString()));
            newSchedule.setLessonNumber((Integer) lesson.getValue());
            MainController.getMainController().renderTemplate("/updateSchedule", new HashMap<String, Object>() {{put("schedule", newSchedule);}});

            f.dispose();
        });

        cancelButton.addActionListener(e -> {
            f.dispose();
        });

        detailsButton.addActionListener(e -> {
            MainController.getMainController().renderTemplate("/showScheduleUnits", new HashMap<String, Object>());
        });

        JLabel mainLabel = new JLabel("Edit schedule");
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
