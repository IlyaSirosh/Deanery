package com.ui;

import com.controllers.configs.MainController;
import com.controllers.decorators.RequestPath;
import com.controllers.decorators.View;
import com.controllers.exceptions.UnsatisfiedDependencyException;
import com.model.Department;
import com.model.Lesson;
import com.model.Teacher;
import com.resources.BeansDispatcher;
import com.services.DepartmentService;
import com.services.LessonService;
import com.services.TeacherService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@View
@RequestPath("/changeResultsFilter")
public class SessionResultView extends com.ui.View{
    String[] types = {"Department", "Teacher", "Group"};
    String dep = "dep";

    private class DepItem
    {
        public Department department;

        public DepItem(Department department) {
            this.department = department;
        }

        public String toString()
        {
            return department.getName();
        }
    }

    private class LesItem
    {
        public Lesson lesson;

        public LesItem(Lesson lesson) {
            this.lesson = lesson;
        }

        public String toString()
        {
            return lesson.getThreadName() + " " + lesson.getGroupNumber();
        }
    }

    private class TItem
    {
        public Teacher teacher;

        public TItem(Teacher teacher) {
            this.teacher = teacher;
        }

        public String toString()
        {
            return teacher.getName();
        }
    }

    @Override
    public void renderView(Map<String, Object> params) throws UnsatisfiedDependencyException {
        DepartmentService ds = (DepartmentService) BeansDispatcher.getBean(DepartmentService.class);
        LessonService ls = (LessonService) BeansDispatcher.getBean(LessonService.class);
        TeacherService ts = (TeacherService) BeansDispatcher.getBean(TeacherService.class);


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

        JComboBox type = new JComboBox(types);
        JLabel typeLabel = new JLabel("Type: ", JLabel.LEFT);

        JComboBox item = new JComboBox();
        JLabel itemLabel = new JLabel("Item: ", JLabel.LEFT);
        java.util.List<Department> deps = ds.getAll();
        deps.forEach(d -> {
            item.addItem(new DepItem(d));
        });

        type.addActionListener(e -> {
            JComboBox box = (JComboBox)e.getSource();
            String ite = box.getSelectedItem().toString();
            switch (ite){
                case "Department": item.removeAllItems();
                    java.util.List<Department> departments = ds.getAll();
                    departments.forEach(d -> {
                        item.addItem(new DepItem(d));
                    });
                    dep = "dep";
                    break;
                case "Teacher": item.removeAllItems();
                    java.util.List<Teacher> teachers = ts.getAll();
                    teachers.forEach(d -> {
                        item.addItem(new TItem(d));
                    });
                    dep = "tea";
                    break;
                case "Group": item.removeAllItems();
                    List<Lesson> lesson = ls.getList();
                    lesson.forEach(d -> {
                        item.addItem(new LesItem(d));
                    });
                    dep = "les";
                    break;
            }
        });


        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(typeLabel).addComponent(type))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(itemLabel).addComponent(item))
        );

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(typeLabel).addComponent(itemLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(type).addComponent(item)));


        okButton.addActionListener(e -> {
            if(dep == "dep"){
                DepItem ite = (DepItem)item.getSelectedItem();
                MainController.getMainController().renderTemplate("/showResults", new HashMap<String, Object>() {{put("orderObj", ite.department);}});
            } else if (dep == "tea")
            {
                TItem ite = (TItem)item.getSelectedItem();
                MainController.getMainController().renderTemplate("/showResults", new HashMap<String, Object>() {{put("orderObj", ite.teacher);}});

            } else{
                LesItem ite = (LesItem) item.getSelectedItem();
                MainController.getMainController().renderTemplate("/showResults", new HashMap<String, Object>() {{put("orderObj", ite.lesson);}});
            }


            f.dispose();
        });

        cancelButton.addActionListener(e -> {
            f.dispose();
        });

        JLabel mainLabel = new JLabel("Filter Results");
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
