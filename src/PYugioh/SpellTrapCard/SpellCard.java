package PYugioh.SpellTrapCard;

import PYugioh.CardMoveCallback;
import PYugioh.CardPlacement;

import java.awt.*;

public class SpellCard extends ASpellTrapCard {

    public SpellCard(
            String name,
            String description,
            String id,
            Image cardImage,
            SpellTrapType type,
            CardInvokeCallback onInvoke,
            CardMoveCallback onMove
    ) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.cardImage = cardImage;
        this.type = type;
        this.onInvoke = onInvoke;
        this.onMove = onMove;
    }

    private String name;
    private String description;
    private String id;
    private Image cardImage;
    private SpellTrapType type;

    private CardInvokeCallback onInvoke;
    private CardMoveCallback onMove;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public Image getCardImage() {
        return cardImage;
    }

    @Override
    public SpellTrapType getType() {
        return type;
    }
}
