// Maria Ines Aranguren
package poker;

import java.lang.Math;

public class HandOfCards {

    private PlayingCard[] hand;
    private final int SIZE_OF_HAND = 5;
    private DeckOfCards deck;

    public static final int ROYAL_FLUSH_VAL = (int) (1 * Math.pow(10, 10));     // Default value of hand increases by factor of 10
                                                                                // between each ranking to ensure that overall game value
                                                                                // of card will correspondingly represent its value and be
                                                                                // appropriate for comparison with other hands. 
    public static final int STRAIGHT_FLUSH_VAL = (int) (1 * Math.pow(10, 9));
    public static final int FOUR_OF_A_KIND_VAL = (int) (1 * Math.pow(10, 8));
    public static final int FULL_HOUSE_VAL = (int) (1 * Math.pow(10, 7));
    public static final int FLUSH_VAL = (int) (1 * Math.pow(10, 6));
    public static final int STRAIGHT_VAL = (int) (1 * Math.pow(10, 5));
    public static final int THREE_OF_A_KIND_VAL = (int) (1 * Math.pow(10, 4));
    public static final int TWO_PAIR_VAL = (int) (1 * Math.pow(10, 3));
    public static final int ONE_PAIR_VAL = (int) (1 * Math.pow(10, 2));
    public static final int HIGH_HAND_VAL = (int) (1 * Math.pow(10, 1));

    public HandOfCards(DeckOfCards sourceDeck) {
        this.deck = sourceDeck;
        this.hand = new PlayingCard[this.SIZE_OF_HAND];   // Instantiates hand by allocating an array of SIZE_OF_HAND PlayingCards
        for (int i = 0; i < this.SIZE_OF_HAND; i++) {     // Iterates through hand and deals SIZE_OF_HAND cards
            this.hand[i] = deck.dealNext();
        }
        this.sort();                                    // Sorts cards
    }

    public void print() {                               // Iterates through hand and prints string representation of cards
        for (int i = 0; i < SIZE_OF_HAND; i++) {
            System.out.printf("%s ", hand[i].toString());
        }
        System.out.printf("\n");
    }

    public DeckOfCards returnSourceDeck() {
        return deck;
    }

    private void sort() {                               // Sorts hand 2-A disregarding suits
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
            generalHandVal = HIGH_HAND_VAL;
        } else if (this.isOnePair()) {
            generalHandVal = ONE_PAIR_VAL;
        } else if (this.isTwoPair()) {
            generalHandVal = TWO_PAIR_VAL;
        } else if (this.isThreeOfAKind()) {
            generalHandVal = THREE_OF_A_KIND_VAL;
        } else if (this.isStraight()) {
            generalHandVal = STRAIGHT_VAL;
        } else if (this.isFlush()) {
            generalHandVal = FLUSH_VAL;
        } else if (this.isFullHouse()) {
            generalHandVal = FULL_HOUSE_VAL;
        } else if (this.isFourOfAKind()) {
            generalHandVal = FOUR_OF_A_KIND_VAL;
        } else if (this.isStraightFlush()) {
            generalHandVal = STRAIGHT_FLUSH_VAL;
        } else if (this.isRoyalFlush()) {
            generalHandVal = ROYAL_FLUSH_VAL;
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
    
    public void identifyHand() {
        this.sort();
        if (this.isFlush()) {
            System.out.printf("The hand is a Flush!\n");
        }
        if (this.isFourOfAKind()) {                                 // Intentionally not using else-ifs to test for correctness of functions
            System.out.printf("The hand is a FourOfAKind!\n");
        }
        if (this.isFullHouse()) {
            System.out.printf("The hand is a FullHouse!\n");
        }
        if (this.isHighHand()) {
            System.out.printf("The hand is a HighHand!\n");
        }
        if (this.isOnePair()) {
            System.out.printf("The hand is a OnePair!\n");
        }
        if (this.isRoyalFlush()) {
            System.out.printf("The hand is a RoyalFlush!\n");
        }
        if (this.isStraight()) {
            System.out.printf("The hand is a Straight!\n");
        }
        if (this.isStraightFlush()) {
            System.out.printf("The hand is a StraightFlush!\n");
        }
        if (this.isThreeOfAKind()) {
            System.out.printf("The hand is a ThreeOfAKind!\n");
        }
        if (this.isTwoPair()) {
            System.out.printf("The hand is a TwoPair!\n");
        }
    }
    
    public void replaceCard(PlayingCard card, int location) {    // Created solely for testing purposes to allow for creation of specific poker hand 
                                                                 // TODO: Remove after assignment #4 
        this.hand[location] = card;
    }

}
