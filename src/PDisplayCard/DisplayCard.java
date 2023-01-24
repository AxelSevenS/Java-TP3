package PDisplayCard;

import javax.swing.*;
import java.awt.*;

public class DisplayCard {

    public static void main(String[] args) {

        // display image
        JFrame frame = new JFrame("Display Card");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new FlowLayout());
        // add vertical scroll to the panel
        JScrollPane scrollPane = new JScrollPane(cardPanel);
        frame.add(scrollPane);


        JButton button = new JButton("Piocher une Carte");
        button.setMaximumSize(new Dimension(200, 50));
        button.addActionListener(e -> {
            cardPanel.add(drawCard());
            cardPanel.revalidate();
        });
        frame.add(button);


        frame.setSize(500, 500);
        frame.setVisible(true);

    }

    private static JLabel drawCard() {
        return new JLabel(new ImageIcon(CardInfo.createRandom().getImage()));
    }
}
