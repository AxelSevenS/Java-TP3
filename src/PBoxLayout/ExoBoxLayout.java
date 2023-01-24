package PBoxLayout;

import javax.swing.*;
import java.awt.*;

public class ExoBoxLayout {

    public static void main(String[] args) {


        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));



        // Name Field
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JLabel label = new JLabel("Nom : ");
        panel.add(label);

        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(125, 20));

        label.setLabelFor(nameField);
        panel.add(nameField);


        fieldsPanel.add(panel);



        // Password Field
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        label = new JLabel("Mot de Passe : ");
        panel.add(label);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(125, 20));

        label.setLabelFor(passwordField);
        panel.add(passwordField);

        fieldsPanel.add(panel);


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.5;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.ipady = 50;
        frame.add(fieldsPanel, gbc);


        // Buttons
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // OK Button
        JButton button = new JButton("OK");
        button.setMaximumSize(new Dimension(100, 20));
        button.addActionListener(e -> {
            displayOutput(frame, "OK", nameField.getText(), String.valueOf(passwordField.getPassword()));
        });
        panel.add(button, BorderLayout.WEST);

        // Cancel Button
        button = new JButton("Cancel");
        button.setMaximumSize(new Dimension(100, 20));
        button.addActionListener(e -> {
            displayOutput(frame, "Cancel", nameField.getText(), String.valueOf(passwordField.getPassword()));
        });
        panel.add(button, BorderLayout.EAST);

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 200;
        gbc.ipady = 0;
        frame.add(panel, gbc);


        frame.setSize(550, 200);
        frame.setVisible(true);
    }

    private static void displayOutput(JFrame frame, String status, String name, String password) {
        String message = String.format("Vous avez appuyé sur : %s\nNom : %s\nPassword : %s", status, name, password);
        JOptionPane.showMessageDialog(frame, message);
    }
}
