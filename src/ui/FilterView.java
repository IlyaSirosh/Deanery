package ui;

import controllers.configs.MainController;
import controllers.configs.ServicesDispatcher;
import controllers.decorators.RequestPath;
import controllers.exceptions.UnsatisfiedDependencyException;
import model.Class;
import model.Department;
import model.Lesson;
import model.Teacher;
import services.DepartmentService;
import services.LessonService;
import services.TeacherService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestPath("/changeScheduleFilter")
public class FilterView extends View{
    String[] types = {"Department", "Teacher", "Lesson"};
    boolean dep = true;

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
            return ""+lesson.getLessonId();
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
        DepartmentService ds = (DepartmentService) ServicesDispatcher.getServicesDispatcher().getService(DepartmentService.class.getName());
        LessonService ls = (LessonService) ServicesDispatcher.getServicesDispatcher().getService(LessonService.class.getName());
        TeacherService ts = (TeacherService) ServicesDispatcher.getServicesDispatcher().getService(TeacherService.class.getName());


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

        type.addActionListener(e -> {
            JComboBox box = (JComboBox)e.getSource();
            String ite = box.getSelectedItem().toString();
             switch (ite){
                 case "Department": List<Department> departments = ds.getAll();
                     departments.forEach(d -> {
                         item.addItem(new DepItem(d));
                     });
                     dep = true;
                     break;
                 case "Teacher": List<Teacher> teachers = ts.getAll();
                     teachers.forEach(d -> {
                         item.addItem(new TItem(d));
                     });
                     dep = false;
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
            if(dep){
                DepItem ite = (DepItem)item.getSelectedItem();
                Department d = new Department();
                d = ite.department;
                MainController.getMainController().renderTemplate("/showSchedule", new HashMap<String, Object>() {{put("orderObj", ite.department);}});
            } else
            {
                TItem ite = (TItem)item.getSelectedItem();
                Teacher d = new Teacher();
                d = ite.teacher;
                MainController.getMainController().renderTemplate("/showSchedule", new HashMap<String, Object>() {{put("orderObj", ite.teacher);}});

            }


            f.dispose();
        });

        cancelButton.addActionListener(e -> {
            f.dispose();
        });

        JLabel mainLabel = new JLabel("Add teacher");
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
