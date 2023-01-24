package PYugioh;

import java.awt.*;

public interface IYugiohCard {

    void initialize(Player player, GameBoard game);

    String getName();
    String getDescription();
    String getID();

    Image getCardImage();

    CardPlacement getPlacement();

    void move(CardPlacement to);
    void addMoveEventListener(CardMoveEventListener listener);
    void onMove(CardPlacement from, CardPlacement to);
}
