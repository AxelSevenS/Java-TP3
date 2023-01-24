package PYugioh.Cards;

import PYugioh.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class BlueDragonSummonerCard extends AMonsterCard {

    public BlueDragonSummonerCard() {
        super(1500, 600);
    }

    @Override
    public String getName() {
        return "Invocateur Dragon Bleu";
    }

    @Override
    public String getDescription() {
        return "Si cette carte est envoyée depuis le Terrain au Cimetière : vous pouvez ajouter 1 Monstre Normal de Type Dragon/Guerrier/Magicien depuis votre Deck à votre main.";
    }

    @Override
    public String getID() {
        return "YS14-FR017";
    }

    @Override
    public Image getCardImage() {
        return new ImageIcon("yugioh cards/BlueDragonSummoner.jpg").getImage();
    }

    @Override
    public MonsterType getType() {
        return MonsterType.Spellcaster;
    }

    @Override
    public MonsterEffect getEffect() {
        return MonsterEffect.Effect;
    }

    @Override
    public MonsterAttribute getAttribute() {
        return MonsterAttribute.WIND;
    }

    @Override
    public int getLevel() {
        return 4;
    }

    @Override
    public void onMove(CardPlacement from, CardPlacement to) {
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
    }
}
