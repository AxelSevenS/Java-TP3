package PYugioh;

import PYugioh.MonsterCard.*;
import PYugioh.SpellTrapCard.SpellCard;
import PYugioh.SpellTrapCard.SpellTrapType;
import PYugioh.SpellTrapCard.TrapCard;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CardFactory {

    public static IYugiohCard BlueDragonSummoner() {

        BufferedImage image = null;
        
        return new MonsterCard(
                "Invocateur Dragon Bleu",
                "Si cette carte est envoyée depuis le Terrain au Cimetière : vous pouvez ajouter 1 Monstre Normal de Type Dragon/Guerrier/Magicien depuis votre Deck à votre main.",
                "YS14-FR017",
                "yugioh cards/BlueDragonSummoner.jpg",
                1500,
                600,
                (listener, from, to) -> {

                    if ((from != CardPlacement.Monster && to != CardPlacement.Graveyard) || listener.getPlayer().deck.cardsInHand.size() >= 6) return;

                    int deckFlags = 1 << CardPlacement.Deck.ordinal();

                    ArrayList<IYugiohCard> validCards = listener.getPlayer().deck.getCards(deckFlags,
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

                    listener.getPlayer().pickFrom(
                        validCards,
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
                "yugioh cards/MysticalSpaceTyphoon.jpg",
                SpellTrapType.QuickPlay,
                (invoker) -> {
                    ArrayList<IYugiohCard> cards = new ArrayList<>();
                    cards.addAll(invoker.getPlayer().deck.spellTrapCards);
                    cards.addAll(invoker.getPlayer().opponent.deck.spellTrapCards);
                    invoker.getPlayer().pickFrom(
                        cards,
                        (pickedCard) -> {
                            pickedCard.move(CardPlacement.Graveyard);
                            invoker.move(CardPlacement.Graveyard);
                        },
                        "Choisissez une de ces cartes à détruire."
                    );

                },
                null
        );
    }

    public static TrapCard ShadowSpell() {
        return new TrapCard(
                "Sortilège de l'Ombre",
                "Activez cette carte en ciblant 1 monstre face recto contrôlé par votre adversaire ; il perd 700 ATK, et aussi, il ne peut ni attaquer ni changer sa position de combat.",
                "YS14-FR035",
                "yugioh cards/ShadowSpell.jpg",
                SpellTrapType.Continuous,
                (invoker) -> {
                    int monsterZoneFlag = 1 << CardPlacement.Monster.ordinal();
                    ArrayList<IYugiohCard> cards = invoker.getPlayer().opponent.deck.getCards(monsterZoneFlag);
                    invoker.getPlayer().pickFrom(cards, 
                        (pickedCard) -> {
                            
                            pickedCard.addChangePlacementActionListener( (listener, from, to) -> {
                                if (to == CardPlacement.Graveyard) {
                                    listener.move(CardPlacement.Graveyard);
                                }
                            });
                            MonsterCard monsterCard = ((MonsterCard) pickedCard);
                            monsterCard.attack -= 700;
                            // monsterCard.canAttack = false;
                            monsterCard.canChangePosition = false;
            
                        },
                        "Choisissez une carte à laquelle infliger le sortilège (-700 ATK, Pos d'Atq restreinte)."
                    );
                },
                null
        );
    }
}
