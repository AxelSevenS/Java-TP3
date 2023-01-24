package PYugioh.SpellTrapCard;

import PYugioh.CardPlacement;
import PYugioh.Player;

import java.util.Objects;

@FunctionalInterface
public interface CardInvokeCallback {
    void invoke(Player player);

    public default CardInvokeCallback andThen(CardInvokeCallback after) {
        Objects.requireNonNull(after);
        return (player) -> {
            invoke(player);
            after.invoke(player);
        };
    }
}