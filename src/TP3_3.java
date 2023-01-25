import java.util.ArrayList;
import java.util.function.Consumer;

import PYugioh.*;


public class TP3_3 {


    public static void main(String[] args) {
        IYugiohCard[] cards1 = new IYugiohCard[3];
        cards1[0] = CardFactory.MysticalSpaceTyphoon();
        cards1[1] = CardFactory.ShadowSpell();
        cards1[2] = CardFactory.BlueDragonSummoner();

        IYugiohCard[] cards2 = new IYugiohCard[2];
        cards2[0] = CardFactory.MysticalSpaceTyphoon();
        cards2[1] = CardFactory.BlueDragonSummoner();

        Player player1 = new Player("Player 1", cards1);
        Player player2 = new Player("Player 2", cards2);
        GameBoard game = new GameBoard(player1, player2);

        IYugiohCard card1 = player1.deck.cardsInDeck.get(0);
        IYugiohCard card2 = player1.deck.cardsInDeck.get(1);
        IYugiohCard card3 = player1.deck.cardsInDeck.get(2);
        player1.placeCard(card1);
        player1.placeCard(card2);
        player1.placeCard(card3);

        IYugiohCard card4 = player2.deck.cardsInDeck.get(0);
        IYugiohCard card5 = player2.deck.cardsInDeck.get(1);
        player2.placeCard(card4);
        player2.placeCard(card5);

        // Cliquez sur les cartes pour les Utiliser


        game.start();
    }
}
