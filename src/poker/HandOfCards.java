// Maria Ines Aranguren
package poker;

import java.lang.Math;

public class HandOfCards {
    private PlayingCard[] hand;
    private final int SIZE_OF_HAND = 5;
    private final int PROBABILITY_FACTOR = 100;    // Used to expand probability scores by multiplying them by this factor (so that the scores are spread farther apart and they're not decimal values)
    private DeckOfCards deck;

    private final String ROYAL_FLUSH_STR = "royalFlush";
    private final String STRAIGHT_FLUSH_STR = "straightFlush";
    private final String FOUR_OF_A_KIND_STR = "fourOfAKind";
    private final String FULL_HOUSE_STR = "fullHouse";
    private final String FLUSH_STR = "flush";
    private final String STRAIGHT_STR = "straight";
    private final String THREE_OF_A_KIND_STR = "threeOfAKind";
    private final String TWO_PAIRS_STR = "twoPairs";
    private final String ONE_PAIR_STR = "onePair";
    private final String HIGH_STR = "high";

    // Default value of hands to allow for comparison of value between rankinds.
    // Increases by factor of 10 between each ranking to ensure that overall game value of card will correspondingly represent its value allowing hand type to take precedence over specific hands. 
    public static final int ROYAL_FLUSH_DFLT_VAL = (int) (1 * Math.pow(10, 10));
    public static final int STRAIGHT_FLUSH_DFLT_VAL = (int) (1 * Math.pow(10, 9));
    public static final int FOUR_OF_A_KIND_DFLT_VAL = (int) (1 * Math.pow(10, 8));
    public static final int FULL_HOUSE_DFLT_VAL = (int) (1 * Math.pow(10, 7));
    public static final int FLUSH_DFLT_VAL = (int) (1 * Math.pow(10, 6));
    public static final int STRAIGHT_DFLT_VAL = (int) (1 * Math.pow(10, 5));
    public static final int THREE_OF_A_KIND_DFLT_VAL = (int) (1 * Math.pow(10, 4));
    public static final int TWO_PAIR_DFLT_VAL = (int) (1 * Math.pow(10, 3));
    public static final int ONE_PAIR_DFLT_VAL = (int) (1 * Math.pow(10, 2));
    public static final int HIGH_HAND_DFLT_VAL = (int) (1 * Math.pow(10, 1));

    public HandOfCards(DeckOfCards sourceDeck) {
        this.deck = sourceDeck;
        this.hand = new PlayingCard[this.SIZE_OF_HAND];   // Instantiates hand by allocating an array of SIZE_OF_HAND PlayingCards
        for (int i = 0; i < this.SIZE_OF_HAND; i++) {     // Iterates through hand and deals SIZE_OF_HAND cards
            this.hand[i] = deck.dealNext();
        }
        this.sort();                                    // Sorts cards
    }

    public void print() {                               // Iterates through hand and prints string representation of cards
        this.sort();
        for (int i = 0; i < SIZE_OF_HAND; i++) {
            System.out.printf("%s ", hand[i].toString());
        }
        System.out.printf("\n");
    }
    
    public void printDiscardProb() {                               // Iterates through hand and prints string representation of cards
        System.out.printf("\t");
        for (int i = 0; i < SIZE_OF_HAND; i++) {
            System.out.printf("%s ", this.getDiscardProbability(i));
        }
        System.out.printf("\n");
    }

    public DeckOfCards returnSourceDeck() {
        return deck;
    }

    public PlayingCard[] getHand() {
        return this.hand;
    } 
    
    public void replaceCard(int position, PlayingCard newCard) {
        this.hand[position] = newCard;
    }
    
    public int getSIZE_OF_HAND() {
        return SIZE_OF_HAND;
    }

    public void sort() {                               // Sorts hand 2-A disregarding suits
        for (int i = 0; i < SIZE_OF_HAND - 1; i++) {      // Performs insertion sort on the hand of cards
            int j = i + 1;
            PlayingCard tmp = this.hand[j];
            while (j > 0 && tmp.getGameVal() < this.hand[j - 1].getGameVal()) {
                this.hand[j] = this.hand[j - 1];
                j--;
            }
            this.hand[j] = tmp;
        }
    }
    
    public void sortByDiscardProb() {                               // Sorts hand 2-A disregarding suits
        for (int i = 0; i < SIZE_OF_HAND - 1; i++) {      // Performs insertion sort on the hand of cards
            int j = i + 1;
            PlayingCard tmp = this.hand[j];
            while (j > 0 && this.getDiscardProbability(j) < this.getDiscardProbability(j - 1)) {
                this.hand[j] = this.hand[j - 1];
                j--;
            }
            this.hand[j] = tmp;
        }
    }

    public boolean isFourOfAKind() {                    // Contains four cards of the same rank and one card of another rank
        if (this.hand[0].getGameVal() != this.hand[1].getGameVal() && this.hand[1].getGameVal() == this.hand[4].getGameVal()) {          // Case A: Different card is first card
            return true;
        } else if (this.hand[4].getGameVal() != this.hand[3].getGameVal() && this.hand[3].getGameVal() == this.hand[0].getGameVal()) {   // Case B: Different card is last card
            return true;
        }
        return false;
    }

    public boolean isThreeOfAKind() {                   // Contains three cards of the same rank and two cards of two other ranks
        if (this.hand[0].getGameVal() == this.hand[2].getGameVal() && this.hand[2].getGameVal() != this.hand[3].getGameVal() && this.hand[3].getGameVal() != this.hand[4].getGameVal()) {          // Case A: Three same-rank cards first, then two unmatched cards
            return true;
        } else if (this.hand[0].getGameVal() != this.hand[1].getGameVal() && this.hand[1].getGameVal() == this.hand[3].getGameVal() && this.hand[3].getGameVal() != this.hand[4].getGameVal()) {   // Case B: Unmatched card first, then three same-rank cards, then another unmatched card
            return true;
        } else if (this.hand[0].getGameVal() != this.hand[1].getGameVal() && this.hand[1].getGameVal() != this.hand[2].getGameVal() && this.hand[2].getGameVal() == this.hand[4].getGameVal()) {   // Case C: Two unmatched cards first, then three same-rank cards
            return true;
        }
        return false;
    }

    public boolean isFullHouse() {                      // Contains three cards of one rank and two cards of another rank
        if (this.hand[0].getGameVal() == this.hand[1].getGameVal() && this.hand[1].getGameVal() != this.hand[2].getGameVal() && this.hand[2].getGameVal() == this.hand[4].getGameVal()) {          // Case A: Two same-rank cards first, then three same-rank cards
            return true;
        } else if (this.hand[0].getGameVal() == this.hand[2].getGameVal() && this.hand[2].getGameVal() != this.hand[3].getGameVal() && this.hand[3].getGameVal() == this.hand[4].getGameVal()) {   // Case B: Three same-rank cards first, then two same-rank cards
            return true;
        }
        return false;
    }

    public boolean isRoyalFlush() {           // An ace-high straight flush
        if (this.hand[0].getGameVal() == 10 && this.hand[1].getGameVal() == 11 && this.hand[2].getGameVal() == 12 && this.hand[3].getGameVal() == 13 && this.hand[4].getGameVal() == 14) {                                            // Checks if cards are 10, J, Q, K, A
            if (this.hand[0].getSuit() == this.hand[1].getSuit() && this.hand[0].getSuit() == this.hand[2].getSuit() && this.hand[0].getSuit() == this.hand[3].getSuit() && this.hand[0].getSuit() == this.hand[4].getSuit()) {   // Checks if cards are of the same suit
                return true;
            }
        }
        return false;
    }

    public boolean isStraightFlush() {       // Contains five cards of sequential rank, all of the same suit
        if (this.hand[0].getGameVal() == this.hand[1].getGameVal() - 1 && this.hand[1].getGameVal() == this.hand[2].getGameVal() - 1 && this.hand[2].getGameVal() == this.hand[3].getGameVal() - 1 && this.hand[3].getGameVal() == this.hand[4].getGameVal() - 1) { // Checks if cards are of sequential rank
            if (this.hand[0].getSuit() == this.hand[1].getSuit() && this.hand[0].getSuit() == this.hand[2].getSuit() && this.hand[0].getSuit() == this.hand[3].getSuit() && this.hand[0].getSuit() == this.hand[4].getSuit()) {                                     // Checks if cards are of the same suit
                if (!this.isRoyalFlush()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isStraight() {           // Contains five cards of sequential rank, not all of the same suit
        if (this.hand[0].getGameVal() == this.hand[1].getGameVal() - 1 && this.hand[1].getGameVal() == this.hand[2].getGameVal() - 1 && this.hand[2].getGameVal() == this.hand[3].getGameVal() - 1 && this.hand[3].getGameVal() == this.hand[4].getGameVal() - 1) { // Checks if all cards are sequential
            if (this.hand[0].getSuit() != this.hand[1].getSuit() || this.hand[0].getSuit() != this.hand[2].getSuit() || this.hand[0].getSuit() != this.hand[3].getSuit() || this.hand[0].getSuit() != this.hand[4].getSuit()) {                                  // Checks if not all cards are of the same suit
                return true;
            }
        }
        return false;
    }

    public boolean isFlush() {              // Contains five cards all of the same suit, not all of sequential rank
        if (this.hand[0].getSuit() == this.hand[1].getSuit() && this.hand[0].getSuit() == this.hand[2].getSuit() && this.hand[0].getSuit() == this.hand[3].getSuit() && this.hand[0].getSuit() == this.hand[4].getSuit()) {                                             // Checks if all cards are of the same suit
            if (this.hand[0].getGameVal() != this.hand[1].getGameVal() - 1 || this.hand[1].getGameVal() != this.hand[2].getGameVal() - 1 || this.hand[2].getGameVal() != this.hand[3].getGameVal() - 1 || this.hand[3].getGameVal() != this.hand[4].getGameVal() - 1) { // Checks if not all cards are sequential
                if (!this.isFullHouse()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isTwoPair() {            // Contains two cards of the same rank, two cards of another rank and one card of a third rank
        int pairsFound = 0;
        int rankPairA = 0;
        int rankPairB = 0;
        for (int i = 0; i < SIZE_OF_HAND - 1; i++) {
            if (this.hand[i].getGameVal() == this.hand[i + 1].getGameVal()) {   // Check neighboring cards to see if they're a pair
                pairsFound++;
                if (pairsFound == 1) {
                    rankPairA = this.hand[i].getGameVal();
                } else {
                    rankPairB = this.hand[i].getGameVal();
                }
            }
        }
        return (pairsFound == 2 && rankPairA != rankPairB);
    }

    public boolean isOnePair() {            // Contains two cards of the same rank and three cards of three other ranks
        int pairsFound = 0;                                                         // Leeps track of number of pairs found so that function returns true only when exactly one pair is found (false when there are no pairs, more than one pair, threeOfAKind, and fourOfAkind)
        for (int i = 0; i < SIZE_OF_HAND - 1; i++) {                                  // Loops through hand and checks adjacent cards to see if they're a pair
            if (this.hand[i].getGameVal() == this.hand[i + 1].getGameVal()) {
                pairsFound++;                                                       // Updates pairsFound to reflect new pairsFound
            }
        }
        return (pairsFound == 1);
    }

    public boolean isHighHand() {
        if (this.isOnePair() || this.isTwoPair() || this.isStraight() || this.isFlush() || this.isStraightFlush() || this.isRoyalFlush() || this.isThreeOfAKind() || this.isFourOfAKind() || this.isFullHouse()) {      // Checks if card falls under category of any other hand combination
            return false;
        }
        return true;
    }

    public int getGameValue() {
        int generalHandVal = 0;
        if (this.isHighHand()) {
            generalHandVal = HIGH_HAND_DFLT_VAL;
        } else if (this.isOnePair()) {
            generalHandVal = ONE_PAIR_DFLT_VAL;
        } else if (this.isTwoPair()) {
            generalHandVal = TWO_PAIR_DFLT_VAL;
        } else if (this.isThreeOfAKind()) {
            generalHandVal = THREE_OF_A_KIND_DFLT_VAL;
        } else if (this.isStraight()) {
            generalHandVal = STRAIGHT_DFLT_VAL;
        } else if (this.isFlush()) {
            generalHandVal = FLUSH_DFLT_VAL;
        } else if (this.isFullHouse()) {
            generalHandVal = FULL_HOUSE_DFLT_VAL;
        } else if (this.isFourOfAKind()) {
            generalHandVal = FOUR_OF_A_KIND_DFLT_VAL;
        } else if (this.isStraightFlush()) {
            generalHandVal = STRAIGHT_FLUSH_DFLT_VAL;
        } else if (this.isRoyalFlush()) {
            generalHandVal = ROYAL_FLUSH_DFLT_VAL;
        }

        int totalVal = generalHandVal;                        // totalVal = generalHandVal + value of each card
        int repeatedCardCount = 1;
        for (int i = 0; i < SIZE_OF_HAND; i++) {              // Loop through cards and specific value of each card
            while (i + 1 < SIZE_OF_HAND && this.hand[i].getGameVal() == this.hand[i + 1].getGameVal()) {
                i++;
                repeatedCardCount++;
            }
            totalVal += Math.pow(this.hand[i].getGameVal(), repeatedCardCount);
            repeatedCardCount = 1;
        }

        return totalVal;
    }

    public String identifyHand() {
        this.sort();
        if (this.isFlush()) {
            return FLUSH_STR;
        }
        if (this.isFourOfAKind()) {                                 // Intentionally not using else-ifs to test for correctness of functions
            return FOUR_OF_A_KIND_STR;
        }
        if (this.isFullHouse()) {
            return FULL_HOUSE_STR;
        }
        if (this.isHighHand()) {
            return HIGH_STR;
        }
        if (this.isOnePair()) {
            return ONE_PAIR_STR;
        }
        if (this.isRoyalFlush()) {
            return ROYAL_FLUSH_STR;
        }
        if (this.isStraight()) {
            return STRAIGHT_STR;
        }
        if (this.isStraightFlush()) {
            return STRAIGHT_FLUSH_STR;
        }
        if (this.isThreeOfAKind()) {
            return THREE_OF_A_KIND_STR;
        }
        if (this.isTwoPair()) {
            return TWO_PAIRS_STR;
        }
        return null;
    }

    public void replaceCard(PlayingCard card, int location) {    // Created solely for testing purposes to allow for creation of specific poker hand 
        // TODO: Remove before production. This is here for testing for now.
        this.hand[location] = card;
    }

    private HandOfCards[] copyNonDiscardingCards(int cardPosition, int totalPossibleHands) {
        int cardsInserted = 0;
        HandOfCards[] possibleHands = new HandOfCards[totalPossibleHands];      // Instantiate an array of hands to hold all other possible hands replacing specified card with all other possible cards (one row for each hand, SIZE_OF_HAND (5) columns/cards for each hand)
        HandOfCards newHand = new HandOfCards(new DeckOfCards());               // Instantiate hand to assign to hands in possibleHands to intialize properly (cards within it will be overwritten as all hands possible to make when replacing discarded card).

        // Copy over all cards in hand to all the hands in the array (possibleHands) except for specified card
        for (int handIndex = 0; handIndex < totalPossibleHands; handIndex++) {    // Loop through all hands
            possibleHands[handIndex] = new HandOfCards(new DeckOfCards());
            cardsInserted = 0;                      // Reset to 0 so that it will work properly each time around loop for all hands
            for (int cardIndex = 0; cardIndex < SIZE_OF_HAND; cardIndex++) {                                        // Copy over all hands except the one to be discarded
                if (cardIndex != cardPosition) {
                    possibleHands[handIndex].hand[cardsInserted] = null;
                    possibleHands[handIndex].hand[cardsInserted] = this.hand[cardIndex];
                    cardsInserted++;
                }
            }
        }
        return possibleHands;
    }

    private HandOfCards[] completeHandsWithDeck(HandOfCards[] possibleHands, int totalPossibleHands) {
        DeckOfCards checkDeck = new DeckOfCards();                              // Use this deck to see which cards are not in the hand and assume them to be in the deck
        boolean foundMissing = true;
        int handsCompleted = 0;
        int lastPosition = 4;           // First 4 cards should already be placed.
        for (int i = 0; i < checkDeck.getSIZE_OF_DECK(); i++) {
            for (int cardIndex = 0; cardIndex < SIZE_OF_HAND; cardIndex++) {
                if (checkDeck.peekCard(i).equals(this.hand[cardIndex])) {
                    foundMissing = false;
                    break;              // Stop looking for card
                }
            }
            if (foundMissing && handsCompleted < totalPossibleHands) {
                possibleHands[handsCompleted].hand[lastPosition] = checkDeck.peekCard(i);
                handsCompleted++;
            }
            foundMissing = true;
        }
        return possibleHands;
    }

    private int calculateProbImprove(int greater, int totalPossible) {
        float probImprovement = ((float) greater / totalPossible) * PROBABILITY_FACTOR;    // Probability calculated as fraction of possible hands that could improve card and then multiplied by PROBABILITY_FACTOR. This allows us to know what the maximum probability improvement score could be.
        int probImprovementInt = (int) Math.round(probImprovement);
        return probImprovementInt;
    }

    public int getDiscardProbability(int cardPosition) {
        // Assumes hand improvements come in changing rank of hand (disregards changing rank of cards)
        if (cardPosition < 0 || cardPosition > 4) {
            return 0;
        }
        int handScore = this.getGameValue();
        if (handScore > ROYAL_FLUSH_DFLT_VAL) {
            return 0;
        } else {
            // Copy over all cards in hand to all the hands in the array (possibleHands) except for specified card
            int totalPossibleHands = this.deck.getSIZE_OF_DECK() - SIZE_OF_HAND;
            HandOfCards[] possibleHands = copyNonDiscardingCards(cardPosition, totalPossibleHands);

            // Check deck to see which cards exist that aren't in hand. Put each of those in a different hand to complete all hands in possibleHands[][].
            possibleHands = completeHandsWithDeck(possibleHands, totalPossibleHands);

            // Loop through all hands in possibleHands and find scores for all.
            // Take note of how many are greater in value than current hand (without discarding card).
            int greaterScore = 0;
            int lesserScore = 0;
            int currentScore = this.getGameValue();
            for (int i = 0; i < totalPossibleHands; i++) {
                possibleHands[i].sort();    // Sort cards so their hand type can be correctly identified
                if (possibleHands[i].getGameValue() > currentScore) {
                    greaterScore++;
                } else if (possibleHands[i].getGameValue() < currentScore) {
                    lesserScore++;
                }
            }

            // Calculate probability of improvement as ratio between possible hands that have a better score over those that have a worse score (all cards not in hand are equally likely to be found in deck).
            int probImprovementInt = calculateProbImprove(greaterScore, totalPossibleHands);
            return probImprovementInt;

        }
    }

}



