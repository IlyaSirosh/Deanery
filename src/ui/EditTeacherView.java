package ui;

import controllers.configs.MainController;
import controllers.configs.ServicesDispatcher;
import controllers.decorators.RequestPath;
import controllers.exceptions.UnsatisfiedDependencyException;
import model.Department;
import model.Teacher;
import services.DepartmentService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestPath("/editTeacher")
public class EditTeacherView extends View{
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
        DepartmentService ds = (DepartmentService) ServicesDispatcher.getServicesDispatcher().getService(DepartmentService.class.getName());

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

        JTextField name = new JTextField();
        JLabel nameLabel = new JLabel("Name: ", JLabel.LEFT);

        JComboBox deps = new JComboBox();
        JLabel depsLabel = new JLabel("Department: ", JLabel.LEFT);
        System.out.println(ds);
        List<Department> departments = ds.getAll();
        departments.forEach(d -> {
            deps.addItem(new Item(d));
        });

        JTextField role = new JTextField();
        JLabel roleLabel = new JLabel("Role: ", JLabel.LEFT);


        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel).addComponent(name))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(depsLabel).addComponent(deps))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(roleLabel).addComponent(role))
        );

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel).addComponent(depsLabel).addComponent(roleLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(name).addComponent(deps).addComponent(role)));


        deps.addActionListener(e -> {
            JComboBox box = (JComboBox)e.getSource();
            Item item = (Item)box.getSelectedItem();
            System.out.println(item.department.getDepartmentId());
        });

        okButton.addActionListener(e -> {
            Item item = (Item)deps.getSelectedItem();
            Department d = new Department();
            d.setDepartmentId(item.department.getDepartmentId());
            Teacher newTeacher = new Teacher();
            newTeacher.setTeacherId(Integer.parseInt(params.get("id").toString()));
            newTeacher.setName(name.getText());
            newTeacher.setDepartment(d);
            newTeacher.setRole(role.getText());
            MainController.getMainController().renderTemplate("/updateTeacher", new HashMap<String, Object>() {{put("teacher", newTeacher);}});

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
