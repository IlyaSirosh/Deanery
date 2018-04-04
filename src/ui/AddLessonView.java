package ui;

import controllers.configs.MainController;
import controllers.configs.ServicesDispatcher;
import controllers.decorators.RequestPath;
import controllers.exceptions.UnsatisfiedDependencyException;
import model.*;
import model.enums.CourseConclusion;
import model.enums.LessonType;
import services.CourseService;
import services.DepartmentService;
import services.TeacherService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestPath("/addLesson")
public class AddLessonView extends View{
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

    private class TeacherItem
    {
        public Teacher teacher;

        public TeacherItem(Teacher teacher) {
        this.teacher = teacher;
    }
        public String toString() {
            return teacher.getName();
        }
    }

        private class CourseItem {
            public Course course;

            public CourseItem(Course course) {
                this.course = course;
            }

            public String toString() {
                return course.getName();
            }
        }


    @Override
    public void renderView(Map<String, Object> params) throws UnsatisfiedDependencyException {
        DepartmentService ds = (DepartmentService) ServicesDispatcher.getServicesDispatcher().getService(DepartmentService.class.getName());
        TeacherService ts = (TeacherService) ServicesDispatcher.getServicesDispatcher().getService(TeacherService.class.getName());
        CourseService cs = (CourseService) ServicesDispatcher.getServicesDispatcher().getService(CourseService.class.getName());


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

        JSpinner name = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        JLabel nameLabel = new JLabel("Group №: ", JLabel.LEFT);

        JComboBox type = new JComboBox(LessonType.values());
        JLabel typeLabel = new JLabel("Type: ", JLabel.LEFT);

        JComboBox teacher = new JComboBox();
        JLabel teacherLabel = new JLabel("Teacher: ", JLabel.LEFT);
        System.out.println(ds);
        List<Teacher> teachers = ts.getAll();
        teachers.forEach(d -> {
            teacher.addItem(new TeacherItem(d));
        });

        JComboBox course = new JComboBox();
        JLabel courseLabel = new JLabel("Course: ", JLabel.LEFT);
        List<Course> courses = cs.getAllCourses();
        courses.forEach(t -> {
            course.addItem(new CourseItem(t));
        });



        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel).addComponent(name))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(typeLabel).addComponent(type))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(teacherLabel).addComponent(teacher))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(courseLabel).addComponent(course))
                );

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel).addComponent(typeLabel).addComponent(teacherLabel).addComponent(courseLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(name).addComponent(type).addComponent(teacher).addComponent(course)));


        okButton.addActionListener(e -> {
            TeacherItem titem = (TeacherItem)teacher.getSelectedItem();
            Teacher t = new Teacher();
            t.setTeacherId(titem.teacher.getTeacherId());

            CourseItem citem = (CourseItem)course.getSelectedItem();
            Course c = new Course();
            c.setCourseId(citem.course.getCourseId());

            Lesson newLesson = new Lesson();
            newLesson.setType((LessonType) type.getSelectedItem());
            newLesson.setGroupNumber((Integer)name.getValue());
            newLesson.setTeacher(t);
            newLesson.setCourse(c);
            System.out.println(newLesson.toString());
            MainController.getMainController().renderTemplate("/saveLesson", new HashMap<String, Object>() {{put("lesson", newLesson);}});

            f.dispose();
        });

        cancelButton.addActionListener(e -> {
            f.dispose();
        });

        JLabel mainLabel = new JLabel("Create lesson");
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
