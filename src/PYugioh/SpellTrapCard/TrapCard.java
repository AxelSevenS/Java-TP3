package PYugioh.SpellTrapCard;

import PYugioh.CardChangePlacementCallback;

public class TrapCard extends ASpellTrapCard {

    public TrapCard(
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
