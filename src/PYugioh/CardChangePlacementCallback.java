package PYugioh;

import java.util.Objects;

@FunctionalInterface
public interface CardChangePlacementCallback {
    void invoke(IYugiohCard listener, CardPlacement from, CardPlacement to);

    public default CardChangePlacementCallback andThen(CardChangePlacementCallback after) {
        Objects.requireNonNull(after);
        return (listener, from, to) -> {
            invoke(listener, from, to);
            after.invoke(listener, from, to);
        };
    }
}
