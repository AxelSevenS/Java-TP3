package PYugioh.Cards;

import javax.swing.*;

import PYugioh.*;

import java.awt.*;
import java.util.ArrayList;

public class MysticalSpaceTyphoonCard extends ASpellTrapCard {


    @Override
    public String getName() {
        return "Typhon d'Espace Mystique";
    }

    @Override
    public String getDescription() {
        return "Ciblez 1 Carte Magie/Piège sur le Terrain ; détruisez la cible.";
    }

    @Override
    public String getID() {
        return "YS14-FR024";
    }

    @Override
    public Image getCardImage() {
        return new ImageIcon("yugioh cards/MysticalSpaceTyphoon.jpg").getImage();
    }

    @Override
    public SpellTrapType getType() {
        return SpellTrapType.QuickPlay;
    }

    @Override
    public void invoke() {
        ArrayList<IYugiohCard> cards = new ArrayList<>();
        cards.addAll(this.player.spellTrapCards);
        cards.addAll(this.player.opponent.spellTrapCards);
        this.player.pickFrom(cards,
            (pickedCard) -> pickedCard.move(CardPlacement.Graveyard),
            "Choisissez une de ces cartes à détruire."
        );
    }


    @Override
    public void onMove(CardPlacement from, CardPlacement to) {

    }
}
