package PYugioh.Cards;

import javax.swing.*;

import PYugioh.*;

import java.awt.*;
import java.util.ArrayList;

public class ShadowSpellCard extends ASpellTrapCard {


    @Override
    public String getName() {
        return "Sortilège de l'Ombre";
    }

    @Override
    public String getDescription() {
        return "Activez cette carte en ciblant 1 monstre face recto contrôlé par votre adversaire ; il perd 700 ATK, et aussi, il ne peut ni attaquer ni changer sa position de combat.\n" +
                "Lorsqu’il quitte le Terrain, détruisez cette carte.";
    }

    @Override
    public String getID() {
        return "YS14-FR035";
    }

    @Override
    public Image getCardImage() {
        return new ImageIcon("yugioh cards/ShadowSpell.jpg").getImage();
    }
    
    @Override
    public SpellTrapType getType() {
        return SpellTrapType.Continuous;
    }

    @Override
    public void invoke() {
        int monsterZoneFlag = 1 << CardPlacement.Monster.ordinal();
        ArrayList<IYugiohCard> cards = this.player.getCards(monsterZoneFlag);
        this.player.pickFrom(cards, (pickedCard) -> {
                CardMoveEventListener listener = new CardMoveEventListener(
                    (from, to) -> {
                        this.move(CardPlacement.Graveyard);
                    },
                    false
                );
                pickedCard.addMoveEventListener(listener);
                AMonsterCard monsterCard = ((AMonsterCard) pickedCard);
                monsterCard.attack -= 700;
                // monsterCard.canAttack = false;
                // monsterCard.canChangePosition = false;

        },
        "Choisissez une de ces cartes à détruire."
    );
    }

    @Override
    public void onMove(CardPlacement from, CardPlacement to) {

    }
}
