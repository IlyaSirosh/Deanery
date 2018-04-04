package ui;

import controllers.configs.MainController;
import controllers.decorators.RequestPath;
import controllers.exceptions.UnsatisfiedDependencyException;
import model.Class;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@RequestPath("/addClass")
public class AddClassView extends View{
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

        JTextField name = new JTextField();
        JLabel nameLabel = new JLabel("Number: ", JLabel.LEFT);

        JSpinner building = new JSpinner(new SpinnerNumberModel(1, 1, 9, 1));
        JLabel buildingLabel = new JLabel("Building: ", JLabel.LEFT);

        JTextField capacity = new JTextField();
        JLabel capacityLabel = new JLabel("Capacity: ", JLabel.LEFT);


        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(nameLabel).addComponent(name))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(buildingLabel).addComponent(building))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(capacityLabel).addComponent(capacity))
        );

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel).addComponent(buildingLabel).addComponent(capacityLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(name).addComponent(building).addComponent(capacity)));


        okButton.addActionListener(e -> {
            Class newClass = new Class();
            newClass.setBuilding((Integer) building.getValue());
            newClass.setNumber(Integer.parseInt(name.getText()));
            newClass.setCapacity(Integer.parseInt(capacity.getText()));
            MainController.getMainController().renderTemplate("/saveClass", new HashMap<String, Object>() {{put("class", newClass);}});

            f.dispose();
        });

        cancelButton.addActionListener(e -> {
            f.dispose();
        });

        JLabel mainLabel = new JLabel("Add class");
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
