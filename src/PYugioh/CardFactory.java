package PYugioh;

import PYugioh.CardPlacement;
import PYugioh.EnumExtensions;
import PYugioh.IYugiohCard;
import PYugioh.MonsterCard.*;
import PYugioh.SpellTrapCard.SpellCard;
import PYugioh.CardMoveCallback.*;
import PYugioh.SpellTrapCard.SpellTrapType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;

public class CardFactory {

    public static IYugiohCard BlueDragonSummoner() {
        
        return new MonsterCard(
                "Invocateur Dragon Bleu",
                "Si cette carte est envoyée depuis le Terrain au Cimetière : vous pouvez ajouter 1 Monstre Normal de Type Dragon/Guerrier/Magicien depuis votre Deck à votre main.",
                "YS14-FR017",
                new ImageIcon("yugioh cards/BlueDragonSummoner.jpg").getImage(),
                1500,
                600,
                (player, from, to) -> {

                    if ((from != CardPlacement.Monster && to != CardPlacement.Graveyard) || player.deck.cardsInHand.size() >= 6) return;

                    int deckFlags = 1 << CardPlacement.Deck.ordinal();

                    ArrayList<IYugiohCard> validCards = player.deck.getCards(deckFlags,
                            (card) -> {
                                if (card instanceof MonsterCard) {
                                    MonsterCard monsterCard = (MonsterCard) card;

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
                }
                
        );
    }

    public static SpellCard MysticalSpaceTyphoon() {
        return new SpellCard(
                "Typhon d'Espace Mystique",
                "Ciblez 1 Carte Magie/Piège sur le Terrain ; détruisez la cible.",
                "YS14-FR024",
                new ImageIcon("yugioh cards/MysticalSpaceTyphoon.jpg").getImage(),
                SpellTrapType.QuickPlay,
                (player) -> {
                    ArrayList<IYugiohCard> cards = new ArrayList<>();
                    cards.addAll(player.deck.spellTrapCards);
                    cards.addAll(player.opponent.deck.spellTrapCards);
                    player.pickFrom(cards,
                            (pickedCard) -> pickedCard.move(CardPlacement.Graveyard),
                            "Choisissez une de ces cartes à détruire.");
                },
                null
        );
    }
}
