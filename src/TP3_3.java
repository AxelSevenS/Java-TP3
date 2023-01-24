import java.util.ArrayList;
import java.util.function.Consumer;

import PYugioh.*;
import PYugioh.Cards.BlueDragonSummonerCard;
import PYugioh.Cards.MysticalSpaceTyphoonCard;


public class TP3_3 {

    private static Consumer<ArrayList<IYugiohCard>> test = (ArrayList<IYugiohCard> cards) -> {
        for (IYugiohCard card : cards) {
            System.out.println(card.getName());
        }
    };

    public static void main(String[] args) {
        IYugiohCard[] cards1 = new IYugiohCard[4];
        cards1[0] = new BlueDragonSummonerCard();
        cards1[1] = new BlueDragonSummonerCard();
        cards1[2] = new BlueDragonSummonerCard();
        cards1[3] = new BlueDragonSummonerCard();

        IYugiohCard[] cards2 = new IYugiohCard[1];
        cards2[0] = new MysticalSpaceTyphoonCard();

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        GameBoard game = new GameBoard(player1, player2, cards1, cards2);

        player1.cardsInDeck.get(0).move(CardPlacement.Hand);
        player1.cardsInHand.get(0).move(CardPlacement.Monster);

        player1.monsterCards.get(0).move(CardPlacement.Graveyard);

        // player1.cardsInDeck.get(0).move(CardPlacement.Hand);
        // player1.cardsInHand.get(0).move(CardPlacement.SpellTrap);

        // player2.cardsInDeck.get(0).move(CardPlacement.Hand);
        // player2.cardsInHand.get(0).move(CardPlacement.SpellTrap);
        // player2.spellTrapCards.get(0).invoke();
        // player1.pickFrom(player1.cardsInDeck, 2, "Test", test);
    }
}
