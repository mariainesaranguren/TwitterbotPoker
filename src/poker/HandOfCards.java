// Maria Ines Aranguren
package poker;
import java.lang.Math;

public class HandOfCards {

    private PlayingCard[] hand;
    private final int sizeOfHand = 5;
    private DeckOfCards deck;

    public int ROYAL_FLUSH_VAL = (int) (1*Math.pow(10,10));
    public int STRAIGHT_FLUSH_VAL = (int) (1*Math.pow(10,9));
    public int FOUR_OF_A_KIND_VAL = (int) (1*Math.pow(10,8));
    public int FULL_HOUSE_VAL = (int) (1*Math.pow(10,7));
    public int FLUSH_VAL = (int) (1*Math.pow(10,6));
    public int STRAIGHT_VAL = (int) (1*Math.pow(10,5));
    public int THREE_OF_A_KIND_VAL = (int) (1*Math.pow(10,4));
    public int TWO_PAIR_VAL = (int) (1*Math.pow(10,3));
    public int ONE_PAIR_VAL = (int) (1*Math.pow(10,2));
    public int HIGH_HAND_VAL = (int) (1*Math.pow(10,1));

    public HandOfCards(DeckOfCards sourceDeck) {
        this.deck = sourceDeck;
        this.hand = new PlayingCard[this.sizeOfHand];   // Instantiates hand by allocating an array of sizeOfHand PlayingCards
        for (int i = 0; i < this.sizeOfHand; i++) {     // Iterates through hand and deals sizeOfHand cards
            this.hand[i] = deck.dealNext();
        }
        this.sort();                                    // Sorts cards
    }

    public void print() {                               // Iterates through hand and prints string representation of cards
        for (int i = 0; i < sizeOfHand; i++) {
            System.out.printf("%s ", hand[i].toString());
        }
        System.out.printf("\n");
    }

    public DeckOfCards returnSourceDeck() {
        return deck;
    }

    private void sort() {                               // Sorts hand 2-A disregarding suits
        for (int i = 0; i < sizeOfHand - 1; i++) {      // Performs insertion sort on the hand of cards
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
                return true;
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
                return true;
            }
        }
        return false;
    }

    public boolean isTwoPair() {            // Contains two cards of the same rank, two cards of another rank and one card of a third rank
        int pairsFound = 0;
        int rankPairA = 0;
        int rankPairB = 0;
        for (int i = 0; i < sizeOfHand - 1; i++) {
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
        for (int i = 0; i < sizeOfHand - 1; i++) {                                  // Loops through hand and checks adjacent cards to see if they're a pair
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

}
