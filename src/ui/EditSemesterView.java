package ui;

import controllers.configs.MainController;
import controllers.decorators.RequestPath;
import controllers.exceptions.UnsatisfiedDependencyException;
import model.Semester;
import model.enums.SemesterEnum;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@RequestPath("/editSemester")
public class EditSemesterView extends View{
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
        JButton detailsButton = new JButton("See Semester's Weeks");
        panelDown.add(detailsButton);

        JComboBox sem = new JComboBox(SemesterEnum.values());
        JLabel semLabel = new JLabel("Season: ", JLabel.LEFT);

        Calendar c = Calendar.getInstance();
        JSpinner year = new JSpinner(new SpinnerNumberModel(c.get(Calendar.YEAR), c.get(Calendar.YEAR), c.get(Calendar.YEAR)+10, 1));
        JLabel yearLabel = new JLabel("Year: ", JLabel.LEFT);


        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(semLabel).addComponent(sem))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(yearLabel).addComponent(year))
        );

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(semLabel).addComponent(yearLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(sem).addComponent(year)));


        okButton.addActionListener(e -> {
            Semester newSem = new Semester();
            newSem.setSemesterId(Integer.parseInt(params.get("id").toString()));
            newSem.setSemester((SemesterEnum) sem.getSelectedItem());
            newSem.setYear((Integer) year.getValue());
            MainController.getMainController().renderTemplate("/updateSemester", new HashMap<String, Object>() {{put("semester", newSem);}});

            f.dispose();
        });

        detailsButton.addActionListener(e -> {
            MainController.getMainController().renderTemplate("/showWeeks", new HashMap<String, Object>() {{put("semesterId", Integer.parseInt(params.get("id").toString()));}});
            f.dispose();
        });

        cancelButton.addActionListener(e -> {
            f.dispose();
        });

        JLabel mainLabel = new JLabel("Edit Semester");
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
