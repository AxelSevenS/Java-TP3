package PYugioh;

public class EnumExtensions {
    public static <T extends Enum<T>> boolean isFlaggedIn(int flags, T value) {
        return (flags & (1 << value.ordinal())) != 0;
    }
}
