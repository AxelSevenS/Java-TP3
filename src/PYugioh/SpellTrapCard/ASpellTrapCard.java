package PYugioh;

import java.util.ArrayList;

public abstract class ASpellTrapCard implements IYugiohCard {
    protected Player player;
    protected GameBoard game;
    private CardPlacement placement = CardPlacement.Deck;
    public abstract SpellTrapType getType();

    public final CardPlacement getPlacement() {
        return placement;
    }
    public ArrayList<CardMoveEventListener> cardMoveEventListeners = new ArrayList<>();

    public final void initialize(Player player, GameBoard game) {
        this.player = player;
        this.game = game;
    }

    public abstract void invoke();

    public final void move(CardPlacement to) {
        if (to == getPlacement() || to == CardPlacement.Monster)
            return;

        switch (to) {
            case Field -> {
                if (this.player.fieldCard != null)
                    this.player.fieldCard.move(CardPlacement.Graveyard);
                this.player.fieldCard = this;
            }
            case SpellTrap -> {
                if (this.player.spellTrapCards.size() >= 5)
                    return;
                this.player.spellTrapCards.add((ASpellTrapCard) this);
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
            case SpellTrap -> this.player.spellTrapCards.remove(this);
            case ExtraDeck -> this.player.cardsInExtraDeck.remove(this);
            case Banished -> this.player.cardsInBanished.remove(this);
            case Graveyard -> this.player.cardsInGraveyard.remove(this);
            case Hand -> this.player.cardsInHand.remove(this);
            case Deck -> this.player.cardsInDeck.remove(this);
        }

        onMove(getPlacement(), placement);
        this.placement = placement;
    }
    public void addMoveEventListener(CardMoveEventListener listener) {
        cardMoveEventListeners.add(listener);
    }

}
