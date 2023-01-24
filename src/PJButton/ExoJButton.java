package PJButton;

import javax.swing.*;
import java.awt.*;

public class ExoJButton {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Test des Boutons");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());


        JButton button = new JButton("Bouton Simple");
        button.setSize(300, 50);
        button.setLocation(100, 100);
        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Bouton Simple"));

        JButton button2 = new JButton("Bouton Fantaisie");
        button2.setPreferredSize(new Dimension(200, 50));
        button2.setLocation(500, 75);
        button2.setIcon(new ImageIcon("src/TP3_2.png"));
        button2.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Bouton Fantaisie"));


        frame.add(button);
        // frame.add(Box.createHorizontalStrut(30)); // Fixed width invisible separator.
        frame.add(button2);


        frame.setSize(900, 300);
        frame.setVisible(true);
    }
}
