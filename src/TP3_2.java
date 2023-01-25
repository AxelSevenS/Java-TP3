import javax.swing.*;

import PDisplayCard.DisplayCard;
import PJButton.ExoJButton;
import PBoxLayout.ExoBoxLayout;

public class TP3_2 {


    public static void main(String[] args) {
        // shell menu to select one of three exercises
        String[] choices = { "JButton", "BoxLayout", "DisplayCard" };
        String input = (String) JOptionPane.showInputDialog(
            null,
            "Choisissez un Exercice...",
            "ATTENTION",
            JOptionPane.QUESTION_MESSAGE, null,
            choices,
            choices[0]
        );

        if (input == null)
            System.exit(0);

        switch (input) {
            case "JButton":
                ExoJButton.main(null);
                break;
            case "BoxLayout":
                ExoBoxLayout.main(null);
                break;
            case "DisplayCard":
                DisplayCard.main(null);
                break;
            default:
                System.exit(0);
        }

    }

}
