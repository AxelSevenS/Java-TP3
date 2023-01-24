package PYugioh;

import java.util.ArrayList;

public abstract class AMonsterCard implements IYugiohCard {

    protected Player player;
    protected GameBoard game;
    public CardPlacement placement = CardPlacement.Deck;

    public int attack;
    public int defense;

    public ArrayList<CardMoveEventListener> cardMoveEventListeners = new ArrayList<>();

    public abstract MonsterEffect getEffect();
    public abstract MonsterType getType();
    public abstract MonsterAttribute getAttribute();
    @Override
    public CardPlacement getPlacement() {
        return placement;
    }

    public abstract int getLevel();

    public AMonsterCard(int attack, int defense) {
        this.attack = attack;
        this.defense = defense;
    }
    @Override
    public void initialize(Player player, GameBoard game) {
        this.player = player;
        this.game = game;
    }
    @Override
    public final void move(CardPlacement to) {
        if (to == getPlacement() || to == CardPlacement.SpellTrap)
            return;

        switch (to) {
            case Field -> {
                if (this.player.fieldCard != null)
                    this.player.fieldCard.move(CardPlacement.Graveyard);
                this.player.fieldCard = this;
            }
            case Monster -> {
                if (this.player.monsterCards.size() >= 5)
                    return;
                this.player.monsterCards.add((AMonsterCard) this);
            }
            case ExtraDeck -> {
                if (this.player.cardsInExtraDeck.size() >= 15)
                    return;
                this.player.cardsInExtraDeck.add(this);
            }
            case Banished -> this.player.cardsInBanished.add(this);
            case Graveyard -> this.player.cardsInGraveyard.add(this);
            case Hand -> {
                if (this.player.cardsInHand.size() >= 6)
                    return;
                this.player.cardsInHand.add(this);
            }
            case Deck -> this.player.cardsInDeck.add(this);
        }

        switch (this.placement) {
            case Field -> this.player.fieldCard = null;
            case Monster -> this.player.monsterCards.remove(this);
            case ExtraDeck -> this.player.cardsInExtraDeck.remove(this);
            case Banished -> this.player.cardsInBanished.remove(this);
            case Graveyard -> this.player.cardsInGraveyard.remove(this);
            case Hand -> this.player.cardsInHand.remove(this);
            case Deck -> this.player.cardsInDeck.remove(this);
        }


        System.out.println("Moved " + getName() + " from " + getPlacement() + " to " + to);

        for (CardMoveEventListener listener : cardMoveEventListeners) {
            listener.invoke(getPlacement(), to);
            if (!listener.getIsPermanent())
                cardMoveEventListeners.remove(listener);
        }
        onMove(getPlacement(), to);

        this.placement = to;
    }
    public void addMoveEventListener(CardMoveEventListener listener) {
        cardMoveEventListeners.add(listener);
    }
}
