package ui;

import controllers.decorators.RequestPath;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

@RequestPath("/addCourse")
public class AddCourseView extends View{
    String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };

    @Override
    public void renderView() {
        JFrame f = new JFrame();
        f.setSize(400, 350);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        name.setColumns(20);
        JLabel nameLabel = new JLabel("Name: ", JLabel.LEFT);
        JComboBox deps = new JComboBox(petStrings);
        JLabel depsLabel = new JLabel("Department: ", JLabel.LEFT);
        JComboBox semestr = new JComboBox(petStrings);
        JLabel semestrLabel = new JLabel("Semester: ", JLabel.LEFT);
        JSpinner credits = new JSpinner(new SpinnerNumberModel(1, 1, 6, 1));
        JLabel creditsLabel = new JLabel("Credits: ", JLabel.LEFT);
        JSpinner lectures = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        JLabel lecturesLabel = new JLabel("Lectures: ", JLabel.LEFT);
        JSpinner practices = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        JLabel practicesLabel = new JLabel("Practices: ", JLabel.LEFT);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel).addComponent(name))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(depsLabel).addComponent(deps))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(semestrLabel).addComponent(semestr))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(creditsLabel).addComponent(credits))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lecturesLabel).addComponent(lectures))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(practicesLabel).addComponent(practices)));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(nameLabel).addComponent(depsLabel).addComponent(semestrLabel).addComponent(creditsLabel).addComponent(lecturesLabel).addComponent(practicesLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(name).addComponent(deps).addComponent(semestr).addComponent(credits).addComponent(lectures).addComponent(practices)));


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
