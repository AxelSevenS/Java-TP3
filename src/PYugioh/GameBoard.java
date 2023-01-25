package PYugioh;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class GameBoard {

    Player player1;
    Player player2;

    private JFrame frame;
    private DeckPanel deckPanel1;

    public GameBoard(Player player1, Player player2) {
        this.player1 = player1;
        player1.opponent = player2;
        player1.board = this;
        this.player2 = player2;
        player2.opponent = player1;
        player2.board = this;
    }

    public void update() {
        if (deckPanel1 != null)
            deckPanel1.update();
    }

    public void start() {
        frame = new JFrame("Yugioh");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        deckPanel1 = new DeckPanel(player1.deck, player2.deck);
        frame.add(deckPanel1);

        frame.pack();
        frame.setVisible (true);

        
    }
}
