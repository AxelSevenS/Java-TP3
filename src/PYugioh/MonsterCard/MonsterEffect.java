package PYugioh;

public enum MonsterEffect {
    Normal,
    Effect,
    Ritual,
    Fusion,
    Synchro,
    Xyz,
    Pendulum,
    Link;

    public boolean isFlaggedIn(int types) {
        return (types & (1 << this.ordinal())) != 0;
    }
}
