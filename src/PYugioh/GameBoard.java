package PYugioh;

public class GameBoard {

    Player player1;
    Player player2;

    public GameBoard(Player player1, Player player2, IYugiohCard[] cards1, IYugiohCard[] cards2) {
        this.player1 = player1;
        this.player1.opponent = player2;
        for (IYugiohCard card : cards1) {
            player1.cardsInDeck.add(card);
            card.initialize(player1, this);
        }

        this.player2 = player2;
        this.player2.opponent = player1;
        for (IYugiohCard card : cards2) {
            player2.cardsInDeck.add(card);
            card.initialize(player2, this);
        }
    }
}
