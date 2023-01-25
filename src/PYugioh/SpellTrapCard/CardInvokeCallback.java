package PYugioh.SpellTrapCard;

import PYugioh.IYugiohCard;

import java.util.Objects;

@FunctionalInterface
public interface CardInvokeCallback {
    void invoke(IYugiohCard invoker);

    public default CardInvokeCallback andThen(CardInvokeCallback after) {
        Objects.requireNonNull(after);
        return (invoker) -> {
            invoke(invoker);
            after.invoke(invoker);
        };
    }
}