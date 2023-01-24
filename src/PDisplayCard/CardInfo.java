package PDisplayCard;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;

public class CardInfo {
    int rank;
    Suit suit;

    private final String filePath = "cards/";

    public BufferedImage getImage() {
        String path;
        switch (suit) {
            case CLUBS:
                path = "clubs-";
                break;
            case DIAMONDS:
                path = "diamonds-";
                break;
            case HEARTS:
                path = "hearts-";
                break;
            default:
                path = "spades-";
                break;
        }
        switch (rank) {
            case 11:
                path += "j";
                break;
            case 12:
                path += "q";
                break;
            case 13:
                path += "k";
                break;
            default:
                path += rank;
                break;
        }

        File file = new File(filePath + path + ".png");
        try {
            return ImageIO.read(file);
        } catch (Exception e) {
            System.out.println("Error reading file " + file.getAbsolutePath());
            return null;
        }
    }

    public static CardInfo createRandom() {
        CardInfo card = new CardInfo();
        card.rank = (int) (Math.random() * 13) + 1;
        card.suit = Suit.values()[(int) (Math.random() * 4)];
        return card;
    }
}
