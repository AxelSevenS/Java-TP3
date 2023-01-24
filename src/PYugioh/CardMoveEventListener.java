package PYugioh;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CardMoveEventListener {

    public CardMoveEventListener(BiConsumer<CardPlacement, CardPlacement> onCardMove, boolean isPermanent) {
        this.onCardMove = onCardMove;
        this.isPermanent = isPermanent;
    }
    private BiConsumer<CardPlacement, CardPlacement> onCardMove;
    private boolean isPermanent;

    public boolean getIsPermanent() {
        return isPermanent;
    }

    public void invoke(CardPlacement from, CardPlacement to) {
        onCardMove.accept(from, to);
    }
}
