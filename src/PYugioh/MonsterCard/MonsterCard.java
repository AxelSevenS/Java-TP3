package PYugioh.MonsterCard;

import PYugioh.*;
import PYugioh.CardMoveCallback;

import java.awt.*;
import java.util.ArrayList;
import java.util.function.BiConsumer;

public class MonsterCard implements IYugiohCard{
    public MonsterCard(
            String name,
            String description,
            String id,
            Image cardImage,
            int attack,
            int defense,
            CardMoveCallback onMove
    ) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.cardImage = cardImage;
        this.attack = attack;
        this.defense = defense;
        this.onMove = onMove;
    }

    private String name;
    private String description;
    private String id;
    private Image cardImage;
    private MonsterType monsterType;
    private MonsterEffect monsterEffect;
    private MonsterAttribute monsterAttribute;
    public int attack;
    public int defense;
    private CardMoveCallback onMove;

    private CardPlacement placement;

    private Player player;
    private ArrayList<CardMoveCallback> cardMoveCallbacks = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public Image getCardImage() {
        return cardImage;
    }

    @Override
    public CardPlacement getPlacement() {
        return placement;
    }

    public MonsterType getType() { return monsterType; }
    public MonsterEffect getEffect() { return monsterEffect; }
    public MonsterAttribute getAttribute() { return monsterAttribute; }


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        switch (placement) {
            case Field -> player.deck.fieldCard = this;
            case Monster -> player.deck.monsterCards.add(this);
            case ExtraDeck -> player.deck.cardsInExtraDeck.add(this);
            case Banished -> player.deck.cardsInBanished.add(this);
            case Graveyard -> player.deck.cardsInGraveyard.add(this);
            case Hand -> player.deck.cardsInHand.add(this);
            case Deck -> player.deck.cardsInDeck.add(this);
        }
    }

    @Override
    public void move(CardPlacement to) {
        if (to == getPlacement() || to == CardPlacement.SpellTrap)
            return;

        switch (to) {
            case Field -> {
                if (this.player.deck.fieldCard != null)
                    this.player.deck.fieldCard.move(CardPlacement.Graveyard);
                this.player.deck.fieldCard = this;
            }
            case Monster -> {
                if (this.player.deck.monsterCards.size() >= 5)
                    return;
                this.player.deck.monsterCards.add(this);
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
            case Monster -> this.player.deck.monsterCards.remove(this);
            case ExtraDeck -> this.player.deck.cardsInExtraDeck.remove(this);
            case Banished -> this.player.deck.cardsInBanished.remove(this);
            case Graveyard -> this.player.deck.cardsInGraveyard.remove(this);
            case Hand -> this.player.deck.cardsInHand.remove(this);
            case Deck -> this.player.deck.cardsInDeck.remove(this);
        }


        System.out.println("Moved " + getName() + " from " + getPlacement() + " to " + to);

        for (CardMoveCallback listener : cardMoveCallbacks) {
            listener.invoke(player, getPlacement(), to);
            cardMoveCallbacks.remove(listener);
        }
        onMove.invoke(player, placement, to);
        placement = to;
    }

    @Override
    public void addMoveEventListener(CardMoveCallback listener) {

    }
}
