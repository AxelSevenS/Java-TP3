package PYugioh;

import java.awt.*;

public interface IYugiohCard {

    String getName();
    String getDescription();
    String getID();

    Image getCardImage();

    CardPlacement getPlacement();

    void move(CardPlacement to);
    void addMoveEventListener(CardMoveCallback listener);
}
