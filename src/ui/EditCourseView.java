package ui;

import controllers.configs.MainController;
import controllers.configs.ServicesDispatcher;
import controllers.decorators.RequestParam;
import controllers.decorators.RequestPath;
import controllers.exceptions.UnsatisfiedDependencyException;
import model.Course;
import model.Department;
import model.enums.Conclusion;
import services.DepartmentService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.List;


@RequestPath("/viewCourse")
public class EditCourseView extends View{

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
    public void renderView() throws UnsatisfiedDependencyException {
        DepartmentService ds = (DepartmentService) ServicesDispatcher.getServicesDispatcher().getService(DepartmentService.class.getName());

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
        System.out.println(ds);
        List<Department> departments = ds.getAll();
        departments.forEach(d -> {
            deps.addItem(new Item(d));
        });

        JComboBox semestr = new JComboBox();
        JLabel semestrLabel = new JLabel("Semester: ", JLabel.LEFT);

        JComboBox conclusion = new JComboBox(Conclusion.values());
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
                        .addComponent(semestrLabel).addComponent(semestr))
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
                        .addComponent(nameLabel).addComponent(depsLabel).addComponent(semestrLabel).addComponent(conclusionLabel).addComponent(creditsLabel).addComponent(lecturesLabel).addComponent(practicesLabel).addComponent(mandatory))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(name).addComponent(deps).addComponent(semestr).addComponent(conclusion).addComponent(credits).addComponent(lectures).addComponent(practices)));


        deps.addActionListener(e -> {
            JComboBox box = (JComboBox)e.getSource();
            Item item = (Item)box.getSelectedItem();
            System.out.println(item.department.getDepartmentId());
        });

        okButton.addActionListener(e -> {
            Item item = (Item)deps.getSelectedItem();
            Department d = new Department();
            d.setDepartmentId(item.department.getDepartmentId());
            Course newCourse = new Course();
            newCourse.setName(name.getText());
            newCourse.setDepartment(d);
            newCourse.setCredits((Integer) credits.getValue());
            newCourse.setLections((Integer) lectures.getValue());
            newCourse.setSeminars((Integer) practices.getValue());
            newCourse.setConclusion(conclusion.getSelectedItem().toString());
            System.out.println(newCourse.toString());
            MainController.getMainController().renderTemplate("/updateCourse", new HashMap<String, Object>() {{put("course", newCourse);}});

            f.dispose();
        });

        cancelButton.addActionListener(e -> {
            f.dispose();
        });

        JLabel mainLabel = new JLabel("Edit course");
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
