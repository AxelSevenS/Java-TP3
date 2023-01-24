package PYugioh.SpellTrapCard;

import PYugioh.*;

import java.util.ArrayList;

public abstract class ASpellTrapCard implements IYugiohCard {
    protected Player player;
    protected GameBoard game;
    private CardPlacement placement = CardPlacement.Deck;
    public abstract SpellTrapType getType();

    public final CardPlacement getPlacement() {
        return placement;
    }

    public CardMoveCallback onMove;
    public ArrayList<CardMoveCallback> cardMoveCallbacks = new ArrayList<>();

    public CardInvokeCallback onInvoke;



    public void setPlayer(Player player) {
        this.player = player;
        switch (placement) {
            case Field -> player.deck.fieldCard = this;
            case Monster -> player.deck.spellTrapCards.add(this);
            case ExtraDeck -> player.deck.cardsInExtraDeck.add(this);
            case Banished -> player.deck.cardsInBanished.add(this);
            case Graveyard -> player.deck.cardsInGraveyard.add(this);
            case Hand -> player.deck.cardsInHand.add(this);
            case Deck -> player.deck.cardsInDeck.add(this);
        }
    }
    public final void invoke() {
        if (onInvoke != null)
            onInvoke.invoke(player);
    }

    public final void move(CardPlacement to) {
        if (to == getPlacement() || to == CardPlacement.Monster)
            return;

        switch (to) {
            case Field -> {
                if (this.player.deck.fieldCard != null)
                    this.player.deck.fieldCard.move(CardPlacement.Graveyard);
                this.player.deck.fieldCard = this;
            }
            case SpellTrap -> {
                if (this.player.deck.spellTrapCards.size() >= 5)
                    return;
                this.player.deck.spellTrapCards.add((ASpellTrapCard) this);
            }
            case ExtraDeck -> {
                if (this.player.deck.cardsInExtraDeck.size() >= 15)
                    return;
                this.player.deck.cardsInExtraDeck.add(this);
            }
            case Banished -> this.player.deck.cardsInBanished.add(this);
            case Graveyard -> this.player.deck.cardsInGraveyard.add(this);
            case Hand -> {
                if (this.player.deck.cardsInHand.size() >= 6)
                    return;
                this.player.deck.cardsInHand.add(this);
            }
            case Deck -> this.player.deck.cardsInDeck.add(this);
        }

        switch (this.placement) {
            case Field -> this.player.deck.fieldCard = null;
            case SpellTrap -> this.player.deck.spellTrapCards.remove(this);
            case ExtraDeck -> this.player.deck.cardsInExtraDeck.remove(this);
            case Banished -> this.player.deck.cardsInBanished.remove(this);
            case Graveyard -> this.player.deck.cardsInGraveyard.remove(this);
            case Hand -> this.player.deck.cardsInHand.remove(this);
            case Deck -> this.player.deck.cardsInDeck.remove(this);
        }

        if (onMove != null)
            onMove.invoke(player, getPlacement(), to);

        for (CardMoveCallback listener : cardMoveCallbacks) {
            listener.invoke(player, getPlacement(), to);
            cardMoveCallbacks.remove(listener);
        }

        this.placement = placement;
    }
    public void addMoveEventListener(CardMoveCallback listener) {
        cardMoveCallbacks.add(listener);
    }

}
