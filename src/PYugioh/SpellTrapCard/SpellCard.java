package PYugioh.SpellTrapCard;

import PYugioh.CardChangePlacementCallback;
import PYugioh.CardPlacement;
import PYugioh.DeckPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SpellCard extends ASpellTrapCard {

    public SpellCard(
            String name,
            String description,
            String id,
            String imagePath,
            SpellTrapType type,
            CardInvokeCallback onInvoke,
            CardChangePlacementCallback onMove
    ) {
        super(name, description, id, imagePath, type, onInvoke, onMove);
    }
}
