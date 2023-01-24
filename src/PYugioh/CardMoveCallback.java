package PYugioh;

import java.util.Objects;

@FunctionalInterface
public interface CardMoveCallback {
    void invoke(Player player, CardPlacement from, CardPlacement to);

    public default CardMoveCallback andThen(CardMoveCallback after) {
        Objects.requireNonNull(after);
        return (player, from, to) -> {
            invoke(player, from, to);
            after.invoke(player, from, to);
        };
    }
}
