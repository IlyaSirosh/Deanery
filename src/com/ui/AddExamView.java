package com.ui;

import com.controllers.configs.MainController;
import com.controllers.decorators.RequestPath;
import com.controllers.decorators.View;
import com.controllers.exceptions.UnsatisfiedDependencyException;
import com.model.*;
import com.model.Class;
import com.model.enums.Day;
import com.resources.BeansDispatcher;
import com.services.DeaneryService;
import com.services.LessonService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.security.acl.Group;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@View
@RequestPath("/addSession")
public class AddExamView extends com.ui.View{
    private class GroupItem
    {
        public Lesson lesson;

        public GroupItem(Lesson lesson) {
            this.lesson = lesson;
        }

        public String toString()
        {
            return lesson.getThreadName() + " " + lesson.getGroupNumber();
        }

    }

    private class ClassItem
    {
        public com.model.Class classs;

        public ClassItem(com.model.Class classs) {
            this.classs = classs;
        }

        public String toString()
        {
            return classs.getBuilding() + "-" + classs.getNumber();
        }

    }

    @Override
    public void renderView(Map<String, Object> params) throws UnsatisfiedDependencyException {
        DeaneryService ds = (DeaneryService) BeansDispatcher.getBean(DeaneryService.class);
        LessonService ls = (LessonService) BeansDispatcher.getBean(LessonService.class);


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
        JButton okButton = new JButton("OK");
        panelDown.add(okButton);
        JButton cancelButton = new JButton("Cancel");
        panelDown.add(cancelButton);

        JTextField day = new JTextField();
        JLabel dayLabel = new JLabel("Day: ", JLabel.LEFT);

        JLabel classLabel = new JLabel("Class №: ", JLabel.LEFT);
        JComboBox classs = new JComboBox();
        List<com.model.Class> classes = ds.getAllClasses();
        classes.forEach(d -> {
            classs.addItem(new ClassItem(d));
        });

        JLabel lessonLabel = new JLabel("Group №: ", JLabel.LEFT);
        JComboBox lesson = new JComboBox();
        List<Lesson> lessons = ls.getList();
        lessons.forEach(d -> {
            lesson.addItem(new GroupItem(d));
        });


        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(dayLabel).addComponent(day))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(classLabel).addComponent(classs))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lessonLabel).addComponent(lesson))
        );

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(dayLabel).addComponent(classLabel).addComponent(lessonLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(day).addComponent(classs).addComponent(lesson)));


        okButton.addActionListener(e -> {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            GroupItem li = (GroupItem) lesson.getSelectedItem();
            Lesson l = li.lesson;
            ClassItem ci = (ClassItem) classs.getSelectedItem();
            com.model.Class c = ci.classs;
            GroupExam newSchedule = new GroupExam ();
            newSchedule.setLesson(l);
            newSchedule.setExamClass(c);
            try {
                Date parsed = format.parse(day.getText());
                java.sql.Date sql = new java.sql.Date(parsed.getTime());
                newSchedule.setDate(sql);
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            MainController.getMainController().renderTemplate("/saveSession", new HashMap<String, Object>() {{put("schedule", newSchedule);}});

            f.dispose();
        });

        cancelButton.addActionListener(e -> {
            f.dispose();
        });

        JLabel mainLabel = new JLabel("Create schedule");
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
