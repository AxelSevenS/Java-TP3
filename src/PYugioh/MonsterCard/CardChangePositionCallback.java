package PYugioh.MonsterCard;

import java.util.Objects;

@FunctionalInterface
public interface CardChangePositionCallback {
    void invoke(MonsterPosition from, MonsterPosition to);

    public default CardChangePositionCallback andThen(CardChangePositionCallback after) {
        Objects.requireNonNull(after);
        return (from, to) -> {
            invoke(from, to);
            after.invoke(from, to);
        };
    }
}
