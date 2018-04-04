package ui;

import controllers.configs.MainController;
import controllers.configs.ServicesDispatcher;
import controllers.decorators.RequestPath;
import controllers.exceptions.UnsatisfiedDependencyException;
import model.Department;
import services.DepartmentService;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@RequestPath("/editDepartment")
public class EditDepartmentView extends View{
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
        JButton detailsButton = new JButton("Detailed View");
        panelDown.add(detailsButton);
        JButton okButton = new JButton("OK");
        panelDown.add(okButton);
        JButton cancelButton = new JButton("Cancel");
        panelDown.add(cancelButton);

        JTextField name = new JTextField();
        JLabel nameLabel = new JLabel("Name: ", JLabel.LEFT);

        JSpinner building = new JSpinner(new SpinnerNumberModel(1, 1, 9, 1));
        JLabel buildingLabel = new JLabel("Building: ", JLabel.LEFT);


        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel).addComponent(name))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buildingLabel).addComponent(building))
        );

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel).addComponent(buildingLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(name).addComponent(building)));


        okButton.addActionListener(e -> {
            Department newDepartment = new Department();
            newDepartment.setDepartmentId(Integer.parseInt(params.get("id").toString()));
            newDepartment.setName(name.getText());
            newDepartment.setBuildingNumber((Integer) building.getValue());
            MainController.getMainController().renderTemplate("/updateDepartment", new HashMap<String, Object>() {{put("department", newDepartment);}});

            f.dispose();
        });

        detailsButton.addActionListener(e -> {
            Department d = ds.findById(new Department(Integer.parseInt(params.get("id").toString())));
            MainController.getMainController().renderTemplate("/detailsDepartment", new HashMap<String, Object>() {{put("department", d);}});
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
