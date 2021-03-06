package com.ui;

import com.controllers.configs.MainController;
import com.controllers.decorators.RequestPath;
import com.controllers.exceptions.UnsatisfiedDependencyException;
import com.model.Course;
import com.model.Department;
import com.model.Semester;
import com.model.enums.CourseConclusion;
import com.resources.BeansDispatcher;
import com.services.DeaneryService;
import com.services.DepartmentService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@com.controllers.decorators.View
@RequestPath("/addCourse")
public class AddCourseView extends View{
    private class SemItem
    {
        public Semester semester;

        public SemItem(Semester semester) {
            this.semester = semester;
        }

        public String toString()
        {
            return semester.getSemester() + " " + semester.getYear();
        }
    }

    private class Item
    {
        public Department department;

        public Item(Department department) {
            this.department = department;
        }

        public String toString()
        {
            return department.getName();
        }
    }

    @Override
    public void renderView(Map<String, Object> params) throws UnsatisfiedDependencyException {
        DepartmentService ds = (DepartmentService) BeansDispatcher.getBean(DepartmentService.class);
        DeaneryService ss = (DeaneryService) BeansDispatcher.getBean(DeaneryService.class);

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

        JTextField name = new JTextField();
        JLabel nameLabel = new JLabel("Name: ", JLabel.LEFT);

        JComboBox deps = new JComboBox();
        JLabel depsLabel = new JLabel("Department: ", JLabel.LEFT);
        List<Department> departments = ds.getAll();
        departments.forEach(d -> {
            deps.addItem(new Item(d));
        });

        JComboBox semester = new JComboBox();
        JLabel semesterLabel = new JLabel("Semester: ", JLabel.LEFT);
        java.util.List<Semester> semesters = ss.getAllSemesters();
        semesters.forEach(d -> {
            semester.addItem(new SemItem(d));
        });

        JComboBox conclusion = new JComboBox(CourseConclusion.values());
        JLabel conclusionLabel = new JLabel("Conclusion: ", JLabel.LEFT);

        JSpinner credits = new JSpinner(new SpinnerNumberModel(1, 1, 6, 1));
        JLabel creditsLabel = new JLabel("Credits: ", JLabel.LEFT);

        JSpinner lectures = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        JLabel lecturesLabel = new JLabel("Lectures: ", JLabel.LEFT);

        JSpinner practices = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        JLabel practicesLabel = new JLabel("Practices: ", JLabel.LEFT);

        JCheckBox mandatory = new JCheckBox("Is Mandatory");
        mandatory.setSelected(true);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel).addComponent(name))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(depsLabel).addComponent(deps))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(semesterLabel).addComponent(semester))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(conclusionLabel).addComponent(conclusion))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(creditsLabel).addComponent(credits))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lecturesLabel).addComponent(lectures))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(practicesLabel).addComponent(practices))
                .addComponent(mandatory));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel).addComponent(depsLabel).addComponent(semesterLabel).addComponent(conclusionLabel).addComponent(creditsLabel).addComponent(lecturesLabel).addComponent(practicesLabel).addComponent(mandatory))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(name).addComponent(deps).addComponent(semester).addComponent(conclusion).addComponent(credits).addComponent(lectures).addComponent(practices)));


        deps.addActionListener(e -> {
            JComboBox box = (JComboBox)e.getSource();
            Item item = (Item)box.getSelectedItem();
        });

        okButton.addActionListener(e -> {
            SemItem sitem = (SemItem)semester.getSelectedItem();
            Semester s = new Semester();
            s.setSemesterId(sitem.semester.getSemesterId());
            Item item = (Item)deps.getSelectedItem();
            Department d = new Department();
            d.setDepartmentId(item.department.getDepartmentId());
            Course newCourse = new Course();
            newCourse.setName(name.getText());
            newCourse.setDepartment(d);
            newCourse.setCredits((Integer) credits.getValue());
            newCourse.setLections((Integer) lectures.getValue());
            newCourse.setSeminars((Integer) practices.getValue());
            newCourse.setConclusion((CourseConclusion) conclusion.getSelectedItem());
            newCourse.setObligatory(mandatory.isSelected());
            MainController.getMainController().renderTemplate("/saveCourse", new HashMap<String, Object>() {{put("course", newCourse);}});

            f.dispose();
        });

        cancelButton.addActionListener(e -> {
            f.dispose();
        });

        JLabel mainLabel = new JLabel("Create course");
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
