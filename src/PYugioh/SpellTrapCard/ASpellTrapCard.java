package PYugioh.SpellTrapCard;

import PYugioh.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public abstract class ASpellTrapCard implements IYugiohCard {

    
    public ASpellTrapCard(
            String name,
            String description,
            String id,
            String imagePath,
            SpellTrapType type,
            CardInvokeCallback onInvoke,
            CardChangePlacementCallback onMove
    ) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.imagePath = imagePath;
        this.type = type;
        this.onInvoke = onInvoke;
        this.onMove = onMove;
    }


    protected Player player;
    protected GameBoard game;

    private String name;
    private String description;
    private String id;
    private String imagePath;
    private SpellTrapType type;
    private CardPlacement placement = CardPlacement.Deck;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getID() {
        return id;
    }

    public SpellTrapType getType() {
        return type;
    }

    public final CardPlacement getPlacement() {
        return placement;
    }

    public BufferedImage getCardImage() {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
            return DeckPanel.cardBackfaceImage;
        }
    }

    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
        switch (placement) {
            case Monster -> player.deck.spellTrapCards.add(this);
            case Graveyard -> player.deck.cardsInGraveyard.add(this);
            case Hand -> player.deck.cardsInHand.add(this);
            case Deck -> player.deck.cardsInDeck.add(this);
            default -> {}
        }
    }
    public CardChangePlacementCallback onMove;
    public ArrayList<CardChangePlacementCallback> cardChangePlacementCallbacks = new ArrayList<>();

    public CardInvokeCallback onInvoke;
    public ArrayList<CardInvokeCallback> cardInvokeCallbacks = new ArrayList<>();



    public final void invoke() {
        if (onInvoke != null)
            onInvoke.invoke(this);
    }

    public final void move(CardPlacement to) {
        if (to == getPlacement() || to == CardPlacement.Monster)
            return;

        switch (to) {
            case SpellTrap -> {
                if (player.deck.spellTrapCards.size() >= 5)
                    return;
                player.deck.spellTrapCards.add((ASpellTrapCard) this);
            }
            case Graveyard -> player.deck.cardsInGraveyard.add(this);
            case Hand -> {
                if (player.deck.cardsInHand.size() >= 6)
                    return;
                player.deck.cardsInHand.add(this);
            }
            case Deck -> player.deck.cardsInDeck.add(this);
            default -> {}
        }

        switch (this.placement) {
            case SpellTrap -> player.deck.spellTrapCards.remove(this);
            case Graveyard -> player.deck.cardsInGraveyard.remove(this);
            case Hand -> player.deck.cardsInHand.remove(this);
            case Deck -> player.deck.cardsInDeck.remove(this);
            default -> {}
        }

        if (onMove != null)
            onMove.invoke(this, getPlacement(), to);

        for (CardChangePlacementCallback listener : cardChangePlacementCallbacks) {
            listener.invoke(this, getPlacement(), to);
            cardChangePlacementCallbacks.remove(listener);
        }

        player.board.update();
        this.placement = to;
    }
    public void addChangePlacementActionListener(CardChangePlacementCallback listener) {
        cardChangePlacementCallbacks.add(listener);
    }
    public void addInvokeActionListener(CardInvokeCallback listener) {
        cardInvokeCallbacks.add(listener);
    }

}
