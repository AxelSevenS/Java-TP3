package PYugioh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Player {

    public Player opponent;

    private final String name;
    public int lifePoints;
    public int maxLifePoints;
    public ArrayList<IYugiohCard> cardsInHand;
    public ArrayList<IYugiohCard> cardsInDeck;
    public ArrayList<AMonsterCard> monsterCards;
    public ArrayList<ASpellTrapCard> spellTrapCards;
    public ArrayList<IYugiohCard> cardsInGraveyard;
    public ArrayList<IYugiohCard> cardsInBanished;
    public ArrayList<IYugiohCard> cardsInExtraDeck;
    public IYugiohCard fieldCard;

    public Player(String name, int lifePoints) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.maxLifePoints = lifePoints;
        this.cardsInHand = new ArrayList<>();
        this.cardsInDeck = new ArrayList<>();
        this.monsterCards = new ArrayList<>();
        this.spellTrapCards = new ArrayList<>();
        this.cardsInGraveyard = new ArrayList<>();
        this.cardsInBanished = new ArrayList<>();
        this.cardsInExtraDeck = new ArrayList<>();
        this.fieldCard = null;
    }

    public ArrayList<IYugiohCard> getCards(int deckFlags, Predicate<IYugiohCard> predicate) {
        ArrayList<IYugiohCard> selectedCards = new ArrayList<>();
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Hand)) {
            for (IYugiohCard card : cardsInHand) {
                if (predicate.test(card)) {
                    selectedCards.add(card);
                }
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Deck)) {
            for (IYugiohCard card : cardsInDeck) {
                if (predicate.test(card)) {
                    selectedCards.add(card);
                }
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Monster)) {
            for (IYugiohCard card : monsterCards) {
                if (predicate.test(card)) {
                    selectedCards.add(card);
                }
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.SpellTrap)) {
            for (IYugiohCard card : spellTrapCards) {
                if (predicate.test(card)) {
                    selectedCards.add(card);
                }
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Graveyard)) {
            for (IYugiohCard card : cardsInGraveyard) {
                if (predicate.test(card)) {
                    selectedCards.add(card);
                }
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Banished)) {
            for (IYugiohCard card : cardsInBanished) {
                if (predicate.test(card)) {
                    selectedCards.add(card);
                }
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.ExtraDeck)) {
            for (IYugiohCard card : cardsInExtraDeck) {
                if (predicate.test(card)) {
                    selectedCards.add(card);
                }
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Field)) {
            if (predicate.test(fieldCard)) {
                selectedCards.add(fieldCard);
            }
        }
        return selectedCards;
    }
    public ArrayList<IYugiohCard> getCards(int deckFlags) {
        ArrayList<IYugiohCard> selectedCards = new ArrayList<>();
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Hand)) {
            for (IYugiohCard card : cardsInHand) {
                selectedCards.add(card);
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Deck)) {
            for (IYugiohCard card : cardsInDeck) {
                selectedCards.add(card);
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Monster)) {
            for (IYugiohCard card : monsterCards) {
                selectedCards.add(card);
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.SpellTrap)) {
            for (IYugiohCard card : spellTrapCards) {
                selectedCards.add(card);
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Graveyard)) {
            for (IYugiohCard card : cardsInGraveyard) {
                selectedCards.add(card);
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Banished)) {
            for (IYugiohCard card : cardsInBanished) {
                selectedCards.add(card);
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.ExtraDeck)) {
            for (IYugiohCard card : cardsInExtraDeck) {
                selectedCards.add(card);
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Field)) {
            selectedCards.add(fieldCard);
        }
        return selectedCards;
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
        if (cardsInDeck.size() > 0) {
            cardsInDeck.get(0).move(CardPlacement.Deck);
        }
    }

    public void drawCard(int amount) {
        for (int i = 0; i < amount; i++) {
            drawCard();
        }
    }

    public ArrayList<AMonsterCard> findMonsterCardsInDeck(int effectFlags, int typeFlags, int attributeFlags) {
        ArrayList<AMonsterCard> selectedCards = new ArrayList<>();
        for (int i = 0; i < cardsInDeck.size(); i++) {

            if (!(cardsInDeck.get(i) instanceof AMonsterCard))
                continue;

            final AMonsterCard drawnCard = (AMonsterCard)cardsInDeck.get(i);

            boolean fitsEffect = effectFlags == -1 || drawnCard.getEffect().isFlaggedIn(effectFlags);
            boolean fitsType = typeFlags == -1 || EnumExtensions.isFlaggedIn(typeFlags, drawnCard.getType());
            boolean fitsAttribute = attributeFlags == -1 || EnumExtensions.isFlaggedIn(attributeFlags, drawnCard.getAttribute());
            if (fitsEffect && fitsType && fitsAttribute) {
                selectedCards.add(drawnCard);
                i--;
            }

        }
        return selectedCards;
    }

    public ArrayList<ASpellTrapCard> findSpellTrapCardsInDeck(int typeFlags) {
        ArrayList<ASpellTrapCard> selectedCards = new ArrayList<>();
        for (int i = 0; i < cardsInDeck.size(); i++) {

            if (!(cardsInDeck.get(i) instanceof ASpellTrapCard))
                continue;

            final ASpellTrapCard drawnCard = (ASpellTrapCard)cardsInDeck.get(i);

            boolean fitsType = typeFlags == -1 || EnumExtensions.isFlaggedIn(typeFlags, drawnCard.getType());
            if (fitsType) {
                selectedCards.add(drawnCard);
                i--;
            }

        }
        return selectedCards;
    }

}
