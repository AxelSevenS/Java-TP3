package PYugioh;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface IYugiohCard {

    String getName();
    String getDescription();
    String getID();

    BufferedImage getCardImage();

    CardPlacement getPlacement();

    Player getPlayer();
    void setPlayer(Player player);

    void move(CardPlacement to);
    void addChangePlacementActionListener(CardChangePlacementCallback listener);
}
