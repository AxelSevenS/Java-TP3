package PYugioh;

import PYugioh.MonsterCard.MonsterCard;
import PYugioh.SpellTrapCard.ASpellTrapCard;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;

public class Player {

    public Player opponent;

    private final String name;
    public int lifePoints;
    public int maxLifePoints;
    public Deck deck;

    public Player(String name) {
        this.name = name;
        this.lifePoints = 2000;
        this.maxLifePoints = 2000;
        this.deck = new Deck(this);
    }


    public void pickFrom(ArrayList<IYugiohCard> cards, Consumer<IYugiohCard> callback, String message) {
        // display image
        JFrame frame = new JFrame("Choisissez une Carte");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        final IYugiohCard[] selected = {null};

        JLabel label = new JLabel(message);
        frame.add(label);


        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new FlowLayout());
        // add vertical scroll to the panel
        JScrollPane scrollPane = new JScrollPane(cardPanel);
        frame.add(scrollPane);
        for (IYugiohCard card : cards) {

            Image image = card.getCardImage().getScaledInstance(130, 190, Image.SCALE_SMOOTH);
            JButton button = new JButton(new ImageIcon(image));
            button.setBorder(null);
            button.addActionListener(e -> {
                if (selected[0] != null) {
                    // Supprimez la bordure de sélection quand on sélectionne un autre élément.
                    for (Component component : cardPanel.getComponents())
                        if (component instanceof JButton)
                            ((JButton) component).setBorder(null);
                    selected[0] = null;
                }
                selected[0] = card;
                // Ajouter une bordure pour montrer que la carte est sélectionnée
                button.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
            });
            cardPanel.add(button);
        }

        // Confirm Button
        JButton confirmButton = new JButton("Confirmer");
        confirmButton.addActionListener(e -> {
            callback.accept(selected[0]);
            frame.dispose();
        });
        frame.add(confirmButton);


        frame.setSize(500, 500);
        frame.setVisible(true);
    }
    public void pickFrom(ArrayList<IYugiohCard> cards, int count, Consumer<ArrayList<IYugiohCard>> callback, String message) {
        // display image
        JFrame frame = new JFrame("Choisissez une Carte");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        
        ArrayList<IYugiohCard> selected = new ArrayList<>();

        JLabel label = new JLabel(message);
        frame.add(label);


        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new FlowLayout());
        // add vertical scroll to the panel
        JScrollPane scrollPane = new JScrollPane(cardPanel);
        frame.add(scrollPane);
        for (IYugiohCard card : cards) {

            Image image = card.getCardImage().getScaledInstance(130, 190, Image.SCALE_SMOOTH);
            JButton button = new JButton(new ImageIcon(image));
            button.addActionListener(e -> {
                if (selected.contains(card)) {
                    selected.remove(card);
                    // Enlever la bordure pour montrer que la carte n'est plus sélectionnée
                    button.setBorder(null);
                } else if (selected.size() < count) {
                    selected.add(card);
                    // Ajouter une bordure pour montrer que la carte est sélectionnée
                    button.setBorder(BorderFactory.createLineBorder(Color.yellow, 5));
                }
            });
            cardPanel.add(button);
        }

        // Confirm Button
        JButton confirmButton = new JButton("Confirmer");
        confirmButton.addActionListener(e -> {
            callback.accept(selected);
            frame.dispose();
        });
        frame.add(confirmButton);


        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    public void drawCard() {
        if (deck.cardsInDeck.size() > 0) {
            deck.cardsInDeck.get(0).move(CardPlacement.Deck);
        }
    }

    public void drawCard(int amount) {
        for (int i = 0; i < amount; i++) {
            drawCard();
        }
    }

}
