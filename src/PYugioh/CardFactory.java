package PYugioh.Cards;

import PYugioh.CardPlacement;
import PYugioh.EnumExtensions;
import PYugioh.IYugiohCard;
import PYugioh.MonsterCard.AMonsterCard;
import PYugioh.MonsterCard.MonsterEffect;
import PYugioh.MonsterCard.MonsterType;

import java.util.ArrayList;

public class CardFactory {

    public static IYugiohCard BlueDragonSummoner() {
        
        return new MonsterCard(
                1500,
                600,
                (from, to) -> {

                    if ((from != CardPlacement.Monster && to != CardPlacement.Graveyard) || this.player.cardsInHand.size() >= 6) return;

                    int deckFlags = 1 << CardPlacement.Deck.ordinal();

                    ArrayList<IYugiohCard> validCards = this.player.getCards(deckFlags,
                            (card) -> {
                                if (card instanceof AMonsterCard) {
                                    AMonsterCard monsterCard = (AMonsterCard) card;

                                    int monsterEffectFlags = 1 << MonsterEffect.Normal.ordinal() | 1 << MonsterEffect.Effect.ordinal();
                                    if ( !EnumExtensions.isFlaggedIn(monsterEffectFlags, monsterCard.getEffect()) )
                                        return false;

                                    int monsterTypeFlags = 1 << MonsterType.Dragon.ordinal() | 1 << MonsterType.Warrior.ordinal() | 1 << MonsterType.Spellcaster.ordinal();
                                    if (!EnumExtensions.isFlaggedIn(monsterTypeFlags, monsterCard.getType()))
                                        return false;

                                    return true;
                                }
                                return false;
                            }
                    );

                    player.pickFrom(validCards,
                            (card) -> card.move(CardPlacement.Hand),
                            "Vous pouvez piocher une de ces cartes."
                    );
                },
                
        );
    }
}
