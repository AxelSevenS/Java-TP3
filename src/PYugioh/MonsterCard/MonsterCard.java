package PYugioh.MonsterCard;

import PSchoolClass.SchoolClass;
import PYugioh.*;
import PYugioh.CardChangePlacementCallback;
import com.google.gson.Gson;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.BiConsumer;

import javax.imageio.ImageIO;

public class MonsterCard implements IYugiohCard{
    public MonsterCard(
            String name,
            String description,
            String id,
            String imagePath,
            int attack,
            int defense,
            CardChangePlacementCallback onMove
    ) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.imagePath = imagePath;
        this.attack = attack;
        this.defense = defense;
        this.onMove = onMove;
    }

    private String name;
    private String description;
    private String id;
    private String imagePath;
    private MonsterType monsterType;
    private MonsterEffect monsterEffect;
    private MonsterAttribute monsterAttribute;
    public int attack;
    public int defense;

    private CardPlacement placement = CardPlacement.Deck;
    private CardChangePlacementCallback onMove;
    private ArrayList<CardChangePlacementCallback> cardChangePlacementCallbacks = new ArrayList<>();

    private boolean attackPosition = true;
    public boolean canChangePosition;
    private CardChangePositionCallback onChangePosition;
    private ArrayList<CardChangePositionCallback> cardChangePositionCallbacks = new ArrayList<>();

    private boolean hidden = false;


    private Player player;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getID() {
        return id;
    }

    public BufferedImage getCardImage() {
        try {
            return ImageIO.read(new File(imagePath));
        } catch (Exception e) {
            e.printStackTrace();
            return DeckPanel.cardBackfaceImage;
        }
    }

    public CardPlacement getPlacement() {
        return placement;
    }

    public boolean isInAttackPosition() {
        return attackPosition;
    }
    public boolean isHidden() {
        return hidden;
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
            case Monster -> player.deck.monsterCards.add(this);
            case Graveyard -> player.deck.cardsInGraveyard.add(this);
            case Hand -> player.deck.cardsInHand.add(this);
            case Deck -> player.deck.cardsInDeck.add(this);
            default -> {}
        }
    }

    public void move(CardPlacement to) {
        if (to == getPlacement() || to == CardPlacement.SpellTrap)
            return;

        switch (to) {
            case Monster -> {
                if (this.player.deck.monsterCards.size() >= 5)
                    return;
                this.player.deck.monsterCards.add(this);
            }
            case Graveyard -> this.player.deck.cardsInGraveyard.add(this);
            case Hand -> {
                if (this.player.deck.cardsInHand.size() >= 6)
                    return;
                this.player.deck.cardsInHand.add(this);
            }
            case Deck -> this.player.deck.cardsInDeck.add(this);
        }

        switch (placement) {
            case Monster -> this.player.deck.monsterCards.remove(this);
            case Graveyard -> this.player.deck.cardsInGraveyard.remove(this);
            case Hand -> this.player.deck.cardsInHand.remove(this);
            case Deck -> this.player.deck.cardsInDeck.remove(this);
            default -> {}
        }


        System.out.println("Moved " + getName() + " from " + getPlacement() + " to " + to);

        for (CardChangePlacementCallback listener : cardChangePlacementCallbacks) {
            listener.invoke(this, getPlacement(), to);
            cardChangePlacementCallbacks.remove(listener);
        }
        onMove.invoke(this, placement, to);
        placement = to;
    }
    /**
     * @param path
     */
    public void saveToFile(Path path) {

        try {
            Gson gson = new Gson();
            FileWriter file = new FileWriter( path.toString() );
            String json = gson.toJson(this);
            file.write(json);
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * @param path
     */
    public static MonsterCard loadFromFile(Path path) {
        try {
            Gson gson = new Gson();
            FileReader file = new FileReader( path.toString() );
            MonsterCard card = gson.fromJson(file, MonsterCard.class);
            card.setPlayer(null);
            return card;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addChangePlacementActionListener(CardChangePlacementCallback listener) {
        cardChangePlacementCallbacks.add(listener);
    }

    public void addChangePositionActionListener(CardChangePositionCallback listener) {
        cardChangePositionCallbacks.add(listener);
    }
}
