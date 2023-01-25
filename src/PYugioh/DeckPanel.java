package PYugioh;

import PYugioh.MonsterCard.*;
import PYugioh.SpellTrapCard.ASpellTrapCard;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

public class DeckPanel extends JPanel {

    public static final BufferedImage cardBackfaceImage;
    static {
        try {
            cardBackfaceImage = ImageIO.read( new File("yugioh cards/backface.jpg") );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final BufferedImage cardEmptyImage;
    static {
        try {
            cardEmptyImage = ImageIO.read( new File("yugioh cards/empty.jpg") );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static double defaultAngle = 0d;


    private Deck player1Deck;
    private final JButton player1Monster1;
    private final JButton player1Monster2;
    private final JButton player1Monster3;
    private final JButton player1Monster4;
    private final JButton player1Monster5;
    private final JButton player1Monster6;
    private final JButton player1Spell1;
    private final JButton player1Spell2;
    private final JButton player1Spell3;
    private final JButton player1Spell5;
    private final JButton player1Spell6;
    private final JButton player1Spell4;
    private final JButton player1DeckSlot;
    private final JButton player1Graveyard;

    private Deck player2Deck;
    private final JButton player2Monster1;
    private final JButton player2Monster2;
    private final JButton player2Monster3;
    private final JButton player2Monster4;
    private final JButton player2Monster5;
    private final JButton player2Monster6;
    private final JButton player2Spell1;
    private final JButton player2Spell2;
    private final JButton player2Spell3;
    private final JButton player2Spell5;
    private final JButton player2Spell6;
    private final JButton player2Spell4;
    private final JButton player2DeckSlot;
    private final JButton player2Graveyard;

    public DeckPanel(Deck deck1, Deck deck2) {
        //construct components

        this.player1Deck = deck1;
        this.player2Deck = deck2;


        player1Monster1 = new JButton ();
        player1Monster1.setBounds (195, 550, 95, 135);
        add (player1Monster1);

        player1Monster2 = new JButton ();
        player1Monster2.setBounds (325, 550, 95, 135);
        add (player1Monster2);

        player1Monster3 = new JButton ();
        player1Monster3.setBounds (455, 550, 95, 135);
        add (player1Monster3);

        player1Monster4 = new JButton ();
        player1Monster4.setBounds (580, 550, 95, 135);
        add (player1Monster4);

        player1Monster5 = new JButton ();
        player1Monster5.setBounds (705, 550, 95, 135);
        add (player1Monster5);

        player1Monster6 = new JButton ();
        player1Monster6.setBounds (835, 550, 95, 135);
        add (player1Monster6);

        player1Spell1 = new JButton ();
        player1Spell1.setBounds (195, 405, 95, 135);
        add (player1Spell1);

        player1Spell2 = new JButton ();
        player1Spell2.setBounds (325, 405, 95, 135);
        add (player1Spell2);

        player1Spell3 = new JButton ();
        player1Spell3.setBounds (455, 405, 95, 135);
        add (player1Spell3);

        player1Spell5 = new JButton ();
        player1Spell5.setBounds (580, 405, 95, 135);
        add (player1Spell5);

        player1Spell6 = new JButton ();
        player1Spell6.setBounds (705, 405, 95, 135);
        add (player1Spell6);

        player1Spell4 = new JButton ();
        player1Spell4.setBounds (835, 405, 95, 135);
        add (player1Spell4);


        player1DeckSlot = new JButton ();
        player1DeckSlot.setBounds (975, 550, 95, 135);
        add (player1DeckSlot);

        player1Graveyard = new JButton ();
        player1Graveyard.setBounds (975, 405, 95, 135);
        add (player1Graveyard);

        player2Monster1 = new JButton ();
        player2Monster1.setBounds (195, 200, 95, 135);
        add (player2Monster1);

        player2Monster2 = new JButton ();
        player2Monster2.setBounds (325, 200, 95, 135);
        add (player2Monster2);

        player2Monster3 = new JButton ();
        player2Monster3.setBounds (455, 200, 95, 135);
        add (player2Monster3);

        player2Monster4 = new JButton ();
        player2Monster4.setBounds (580, 200, 95, 135);
        add (player2Monster4);

        player2Monster5 = new JButton ();
        player2Monster5.setBounds (705, 200, 95, 135);
        add (player2Monster5);

        player2Monster6 = new JButton ();
        player2Monster6.setBounds (835, 200, 95, 135);
        add (player2Monster6);

        player2Spell1 = new JButton ();
        player2Spell1.setBounds (195, 55, 95, 135);
        add (player2Spell1);

        player2Spell2 = new JButton ();
        player2Spell2.setBounds (325, 55, 95, 135);
        add (player2Spell2);

        player2Spell3 = new JButton ();
        player2Spell3.setBounds (455, 55, 95, 135);
        add (player2Spell3);

        player2Spell5 = new JButton ();
        player2Spell5.setBounds (580, 55, 95, 135);
        add (player2Spell5);

        player2Spell6 = new JButton ();
        player2Spell6.setBounds (705, 55, 95, 135);
        add (player2Spell6);

        player2Spell4 = new JButton ();
        player2Spell4.setBounds (835, 55, 95, 135);
        add (player2Spell4);


        player2DeckSlot = new JButton ();
        player2DeckSlot.setBounds (55, 200, 95, 135);
        add (player2DeckSlot);

        player2Graveyard = new JButton ();
        player2Graveyard.setBounds (55, 55, 95, 135);
        add (player2Graveyard);

        update();



        //adjust size and set layout
        setPreferredSize (new Dimension (1110, 735));
        setLayout (null);

    }

    public void update() {
        defaultAngle = 0;
        UpdateMonsterCard(0, player1Monster1, player1Deck);
        UpdateMonsterCard(1, player1Monster2, player1Deck);
        UpdateMonsterCard(2, player1Monster3, player1Deck);
        UpdateMonsterCard(3, player1Monster4, player1Deck);
        UpdateMonsterCard(4, player1Monster5, player1Deck);
        UpdateMonsterCard(5, player1Monster6, player1Deck);

        UpdateSpellCard(0, player1Spell1, player1Deck);
        UpdateSpellCard(1, player1Spell2, player1Deck);
        UpdateSpellCard(2, player1Spell3, player1Deck);
        UpdateSpellCard(3, player1Spell5, player1Deck);
        UpdateSpellCard(4, player1Spell6, player1Deck);
        UpdateSpellCard(5, player1Spell4, player1Deck);

        BufferedImage deckImage = cardEmptyImage;
        if (player1Deck.cardsInDeck.size() > 0)
            deckImage = cardBackfaceImage;

        deckImage = rotate(deckImage, defaultAngle);
        player1DeckSlot.setIcon( new ImageIcon( deckImage.getScaledInstance(95, 135, Image.SCALE_SMOOTH) ) );

        
        BufferedImage graveyardImage = cardEmptyImage;
        if (player1Deck.cardsInGraveyard.size() > 0)
            graveyardImage = player1Deck.cardsInGraveyard.get(player1Deck.cardsInGraveyard.size()-1).getCardImage();

            graveyardImage = rotate(graveyardImage, defaultAngle);
            player1Graveyard.setIcon( new ImageIcon( graveyardImage.getScaledInstance(95, 135, Image.SCALE_SMOOTH) ) );

        defaultAngle = 180;
        UpdateMonsterCard(0, player2Monster1, player2Deck);
        UpdateMonsterCard(1, player2Monster2, player2Deck);
        UpdateMonsterCard(2, player2Monster3, player2Deck);
        UpdateMonsterCard(3, player2Monster4, player2Deck);
        UpdateMonsterCard(4, player2Monster5, player2Deck);
        UpdateMonsterCard(5, player2Monster6, player2Deck);

        UpdateSpellCard(0, player2Spell1, player2Deck);
        UpdateSpellCard(1, player2Spell2, player2Deck);
        UpdateSpellCard(2, player2Spell3, player2Deck);
        UpdateSpellCard(3, player2Spell5, player2Deck);
        UpdateSpellCard(4, player2Spell6, player2Deck);
        UpdateSpellCard(5, player2Spell4, player2Deck);

        deckImage = cardEmptyImage;
        if (player2Deck.cardsInDeck.size() > 0)
            deckImage = cardBackfaceImage;

        deckImage = rotate(deckImage, defaultAngle);
        player2DeckSlot.setIcon( new ImageIcon( deckImage.getScaledInstance(95, 135, Image.SCALE_SMOOTH) ) );

        
        graveyardImage = cardEmptyImage;
        if (player2Deck.cardsInGraveyard.size() > 0)
            graveyardImage = player2Deck.cardsInGraveyard.get(player2Deck.cardsInGraveyard.size()-1).getCardImage();

            graveyardImage = rotate(graveyardImage, defaultAngle);
            player2Graveyard.setIcon( new ImageIcon( graveyardImage.getScaledInstance(95, 135, Image.SCALE_SMOOTH) ) );
    }

    private void UpdateSpellCard(int index, JButton button, Deck deck) {

        BufferedImage image = cardEmptyImage;
        for( ActionListener eventListener : button.getActionListeners() ) {
            button.removeActionListener( eventListener );
        }

        if (deck.spellTrapCards.size() <= index) {
            image = rotate(image, defaultAngle);
        } else {
            ASpellTrapCard spellTrapCard = deck.spellTrapCards.get(index);
            button.addActionListener(e -> {
                spellTrapCard.invoke();
            });
            image = spellTrapCard.getCardImage();
            image = rotate(image, defaultAngle);
        }

        
            
        button.setIcon( new ImageIcon(image.getScaledInstance(95, 135, Image.SCALE_SMOOTH)) );
    }

    private void UpdateMonsterCard(int index, JButton button, Deck deck) {

        BufferedImage image = cardEmptyImage;
        if (deck.monsterCards.size() <= index) {
            image = rotate(image, defaultAngle);
        } else {
            MonsterCard monsterCard = deck.monsterCards.get(index);
            if (!monsterCard.isHidden())
                image = monsterCard.getCardImage();
    
            else if (monsterCard.isInAttackPosition()) {
                image = rotate(image, defaultAngle-90d);
            } else {
                image = rotate(image, defaultAngle);
            }
        }


        System.out.println(image);
            
        button.setIcon( new ImageIcon(image.getScaledInstance(95, 135, Image.SCALE_SMOOTH)) );
    }

    public static BufferedImage rotate(BufferedImage bimg, Double angle) {
        double sin = Math.abs(Math.sin(Math.toRadians(angle))),
               cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = bimg.getWidth();
        int h = bimg.getHeight();
        int neww = (int) Math.floor(w*cos + h*sin),
            newh = (int) Math.floor(h*cos + w*sin);
        BufferedImage rotated = new BufferedImage(neww, newh, bimg.getType());
        Graphics2D graphic = rotated.createGraphics();
        graphic.translate((neww-w)/2, (newh-h)/2);
        graphic.rotate(Math.toRadians(angle), w/2, h/2);
        graphic.drawRenderedImage(bimg, null);
        graphic.dispose();
        return rotated;
    }
}

