package PYugioh;

import PYugioh.MonsterCard.MonsterCard;
import PYugioh.SpellTrapCard.ASpellTrapCard;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Deck {
    public Deck(Player player) {
        this.player = player;
    }
    private Player player;
    public ArrayList<IYugiohCard> cardsInHand;
    public ArrayList<IYugiohCard> cardsInDeck;
    public ArrayList<MonsterCard> monsterCards;
    public ArrayList<ASpellTrapCard> spellTrapCards;
    public ArrayList<IYugiohCard> cardsInGraveyard;
    public ArrayList<IYugiohCard> cardsInBanished;
    public ArrayList<IYugiohCard> cardsInExtraDeck;
    public IYugiohCard fieldCard;

    public ArrayList<IYugiohCard> getCards(int deckFlags, Predicate<IYugiohCard> predicate) {
        ArrayList<IYugiohCard> selectedCards = new ArrayList<>();
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Hand)) {
            for (IYugiohCard card : cardsInHand) {
                if (predicate.test(card)) {
                    selectedCards.add(card);
                }
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Deck)) {
            for (IYugiohCard card : cardsInDeck) {
                if (predicate.test(card)) {
                    selectedCards.add(card);
                }
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Monster)) {
            for (IYugiohCard card : monsterCards) {
                if (predicate.test(card)) {
                    selectedCards.add(card);
                }
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.SpellTrap)) {
            for (IYugiohCard card : spellTrapCards) {
                if (predicate.test(card)) {
                    selectedCards.add(card);
                }
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Graveyard)) {
            for (IYugiohCard card : cardsInGraveyard) {
                if (predicate.test(card)) {
                    selectedCards.add(card);
                }
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Banished)) {
            for (IYugiohCard card : cardsInBanished) {
                if (predicate.test(card)) {
                    selectedCards.add(card);
                }
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.ExtraDeck)) {
            for (IYugiohCard card : cardsInExtraDeck) {
                if (predicate.test(card)) {
                    selectedCards.add(card);
                }
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Field)) {
            if (predicate.test(fieldCard)) {
                selectedCards.add(fieldCard);
            }
        }
        return selectedCards;
    }
    public ArrayList<IYugiohCard> getCards(int deckFlags) {
        ArrayList<IYugiohCard> selectedCards = new ArrayList<>();
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Hand)) {
            for (IYugiohCard card : cardsInHand) {
                selectedCards.add(card);
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Deck)) {
            for (IYugiohCard card : cardsInDeck) {
                selectedCards.add(card);
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Monster)) {
            for (IYugiohCard card : monsterCards) {
                selectedCards.add(card);
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.SpellTrap)) {
            for (IYugiohCard card : spellTrapCards) {
                selectedCards.add(card);
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Graveyard)) {
            for (IYugiohCard card : cardsInGraveyard) {
                selectedCards.add(card);
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Banished)) {
            for (IYugiohCard card : cardsInBanished) {
                selectedCards.add(card);
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.ExtraDeck)) {
            for (IYugiohCard card : cardsInExtraDeck) {
                selectedCards.add(card);
            }
        }
        if (EnumExtensions.isFlaggedIn(deckFlags, CardPlacement.Field)) {
            selectedCards.add(fieldCard);
        }
        return selectedCards;
    }

    public ArrayList<MonsterCard> findMonsterCardsInDeck(int effectFlags, int typeFlags, int attributeFlags) {
        ArrayList<MonsterCard> selectedCards = new ArrayList<>();
        for (int i = 0; i < cardsInDeck.size(); i++) {

            if (!(cardsInDeck.get(i) instanceof MonsterCard))
                continue;

            final MonsterCard drawnCard = (MonsterCard)cardsInDeck.get(i);

            boolean fitsEffect = effectFlags == -1 || drawnCard.getEffect().isFlaggedIn(effectFlags);
            boolean fitsType = typeFlags == -1 || EnumExtensions.isFlaggedIn(typeFlags, drawnCard.getType());
            boolean fitsAttribute = attributeFlags == -1 || EnumExtensions.isFlaggedIn(attributeFlags, drawnCard.getAttribute());
            if (fitsEffect && fitsType && fitsAttribute) {
                selectedCards.add(drawnCard);
                i--;
            }

        }
        return selectedCards;
    }

    public ArrayList<ASpellTrapCard> findSpellTrapCardsInDeck(int typeFlags) {
        ArrayList<ASpellTrapCard> selectedCards = new ArrayList<>();
        for (int i = 0; i < cardsInDeck.size(); i++) {

            if (!(cardsInDeck.get(i) instanceof ASpellTrapCard))
                continue;

            final ASpellTrapCard drawnCard = (ASpellTrapCard)cardsInDeck.get(i);

            boolean fitsType = typeFlags == -1 || EnumExtensions.isFlaggedIn(typeFlags, drawnCard.getType());
            if (fitsType) {
                selectedCards.add(drawnCard);
                i--;
            }

        }
        return selectedCards;
    }

}
