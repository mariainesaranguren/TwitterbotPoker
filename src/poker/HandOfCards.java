// Maria Ines Aranguren
// Feb 15, 2017
// Programming Assignment Week	#3: OOP Representation of a HandOfCards
package poker;

public class HandOfCards {

    private PlayingCard[] hand;
    private final int sizeOfHand = 5;
    private DeckOfCards deck;

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
        if (sizeOfHand < 4) {                           // Checks if hand has enough cards to make FourOfAKind
            return false;
        }
        for (int i = 0; i < sizeOfHand - 3; i++) {
            if (this.hand[i].getGameVal() == this.hand[i + 1].getGameVal() && this.hand[i].getGameVal() == this.hand[i + 2].getGameVal() && this.hand[i].getGameVal() == this.hand[i + 3].getGameVal()) {
                int j = 0;                              // Variable to iterate through hand to check for common rank between non-quad cards
                if (i == 0) {                           // If quad starts with first card of hand, skip past four cards
                    j = 4;
                }
                int nextNonQuadCard = j + 1;            // Index for next card that isn't part of the quad
                while (j < sizeOfHand) {                // Loop through hand to verify that no cards share common rank
                    if (nextNonQuadCard == i) {         // If nextNonQuadCard points to start of quad, increment by 4a to skip quad cards
                        nextNonQuadCard += 4;
                    }
                    if (nextNonQuadCard < sizeOfHand) { // Check if nextNonQuadCard card points to card within the bounds of the hand
                        if (this.hand[j].getGameVal() == this.hand[i].getGameVal() || this.hand[j].getGameVal() == this.hand[nextNonQuadCard].getGameVal()) {  // Checks if rank is equal to either rank of triplet or rank of nextNonQuadCard
                            return false;
                        }
                        i = nextNonQuadCard;            // Update nextNonQuadCard to point to next card
                        nextNonQuadCard++;
                    } else {
                        break;                          // If nextNonQuadCard is out of bounds, checking is complete
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean isThreeOfAKind() {                   // Contains three cards of the same rank and two cards of two other ranks
        if (sizeOfHand < 3) {                           // Checks if hand has enough cards to make ThreeOfAKind
            return false;
        }
        for (int i = 0; i < sizeOfHand - 2; i++) {
            if (this.hand[i].getGameVal() == this.hand[i + 1].getGameVal() && this.hand[i].getGameVal() == this.hand[i + 2].getGameVal()) {  // Checks if there are at least three cards of the same rank
                int j = 0;                              // Variable to iterate through hand to check for common rank between non-triplet cards
                if (i == 0) {                           // If triplet starts with first card of hand, skip past three cards
                    j = 3;
                }
                int nextNonTripletCard = j + 1;          // Index for next card that isn't part of the triple
                while (j < sizeOfHand) {                 // Loop through hand to verify that no cards share common rank
                    if (nextNonTripletCard == i) {       // If nextNonTripletCard points to start of triplet, increment by 3 to skip triplet cards
                        nextNonTripletCard += 3;
                    }
                    if (nextNonTripletCard < sizeOfHand) {  // Check if nextNonTriplet card points to card within the bounds of the hand
                        if (this.hand[j].getGameVal() == this.hand[i].getGameVal() || this.hand[j].getGameVal() == this.hand[nextNonTripletCard].getGameVal()) {  // Checks if rank is equal to either rank of triplet or rank of nextNonTripletCard
                            return false;
                        }
                        i = nextNonTripletCard;         // Update nextNonTripletCard to point to next card
                        nextNonTripletCard++;
                    } else {
                        break;                          // If nextNonTripletCard is out of bounds, checking is complete
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean isFullHouse() {                      // Contains three cards of one rank and two cards of another rank
        if (sizeOfHand < 5) {                           // Checks if hand has enough cards to make FullHouse
            return false;
        }
        for (int i = 0; i < sizeOfHand - 2; i++) {
            if (this.hand[i].getGameVal() == this.hand[i + 1].getGameVal() && this.hand[i].getGameVal() == this.hand[i + 2].getGameVal()) {  // Checks if there are at least three cards of the same rank
                int j = 0;                              // Variable to iterate through hand to check for common rank between non-triplet cards
                if (i == 0) {                           // If triplet starts with first card of hand, skip past three cards
                    j = 3;
                }
                int nextNonTripletCard = j + 1;          // Index for next card that isn't part of the triple
                while (j < sizeOfHand) {                 // Loop through hand to verify that no cards share common rank
                    if (nextNonTripletCard == i) {       // If nextNonTripletCard points to start of triplet, increment by 3 to skip triplet cards
                        nextNonTripletCard += 3;
                    }
                    if (nextNonTripletCard < sizeOfHand) {  // Check if nextNonTriplet card points to card within the bounds of the hand
                        if (this.hand[j].getGameVal() != this.hand[i].getGameVal() && this.hand[j].getGameVal() == this.hand[nextNonTripletCard].getGameVal()) {  // Checks if rank is equal to either rank of triplet or rank of nextNonTripletCard
                            return true;
                        }
                        i = nextNonTripletCard;         // Update nextNonTripletCard to point to next card
                        nextNonTripletCard++;
                    } else {
                        break;                          // If nextNonTripletCard is out of bounds, checking is complete
                    }
                }
            }
        }
        return false;
    }

    public boolean isRoyalFlush() {           // An ace-high straight flush
        if (sizeOfHand < 5) {                 // Checks if hand has enough cards to make RoyalFlush
            return false;
        }
        for (int i = 0; i < sizeOfHand - 4; i++) {
            if (this.hand[i].getGameVal() == 14 && this.hand[i + 1].getGameVal() == 13 && this.hand[i + 2].getGameVal() == 12 && this.hand[i + 3].getGameVal() == 11 && this.hand[i + 4].getGameVal() == 10) {                                            // Checks if cards are A, K, Q, J, 10
                if (this.hand[i].getSuit() == this.hand[i + 1].getSuit() && this.hand[i].getSuit() == this.hand[i + 2].getSuit() && this.hand[i].getSuit() == this.hand[i + 3].getSuit() && this.hand[i].getSuit() == this.hand[i + 4].getSuit()) {     // Checks if cards are of the same suit
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isStraightFlush() {       // Contains five cards of sequential rank, all of the same suit
        if (sizeOfHand < 5) {                // Checks if hand has enough cards to make StraightFlush
            return false;
        }
        for (int i = 0; i < sizeOfHand - 4; i++) {
            if (this.hand[i].getGameVal() == this.hand[i + 1].getGameVal() - 1 && this.hand[i + 1].getGameVal() == this.hand[i + 2].getGameVal() - 1 && this.hand[i + 2].getGameVal() == this.hand[i + 3].getGameVal() - 1 && this.hand[i + 3].getGameVal() == this.hand[i + 4].getGameVal() - 1) {   // Checks if cards are of sequential rank
                if (this.hand[i].getSuit() == this.hand[i + 1].getSuit() && this.hand[i].getSuit() == this.hand[i + 2].getSuit() && this.hand[i].getSuit() == this.hand[i + 3].getSuit() && this.hand[i].getSuit() == this.hand[i + 4].getSuit()) {                                     // Checks if cards are of the same suit
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isStraight() {           // Contains five cards of sequential rank, not all of the same suit
        if (sizeOfHand < 5) {               // Checks if hand has enough cards to make Straight
            return false;
        }
        for (int i = 0; i < sizeOfHand - 4; i++) {
            if (this.hand[i].getGameVal() == this.hand[i + 1].getGameVal() - 1 && this.hand[i + 1].getGameVal() == this.hand[i + 2].getGameVal() - 1 && this.hand[i + 2].getGameVal() == this.hand[i + 3].getGameVal() - 1 && this.hand[i + 3].getGameVal() == this.hand[i + 4].getGameVal() - 1) {           // Checks if all cards are sequential
                if (!(this.hand[i].getSuit() == this.hand[i + 1].getSuit() && this.hand[i].getSuit() == this.hand[i + 2].getSuit() && this.hand[i].getSuit() == this.hand[i + 3].getSuit() && this.hand[i].getSuit() == this.hand[i + 4].getSuit())) {                                          // Checks if not all cards are of the same suit
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isFlush() {              // Contains five cards all of the same suit, not all of sequential rank
        if (sizeOfHand < 5) {               // Checks if hand has enough cards to make Flush
            return false;
        }
        for (int i = 0; i < sizeOfHand - 4; i++) {
            if (this.hand[i].getSuit() == this.hand[i + 1].getSuit() && this.hand[i].getSuit() == this.hand[i + 2].getSuit() && this.hand[i].getSuit() == this.hand[i + 3].getSuit() && this.hand[i].getSuit() == this.hand[i + 4].getSuit()) {                                                 // Checks if all cards are of the same suit
                if (!(this.hand[i].getGameVal() == this.hand[i + 1].getGameVal() - 1 && this.hand[i + 1].getGameVal() == this.hand[i + 2].getGameVal() - 1 && this.hand[i + 2].getGameVal() == this.hand[i + 3].getGameVal() - 1 && this.hand[i + 3].getGameVal() == this.hand[i + 4].getGameVal() - 1)) {    // Checks if not all cards are sequential
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isTwoPair() {            // Contains two cards of the same rank, two cards of another rank and one card of a third rank
        if (sizeOfHand < 4) {               // Checks if hand has enough cards to make TwoPair
            return false;
        }
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
        if (pairsFound == 2 && rankPairA != rankPairB) {  // Checks if found two pairs of different rank (also covers the case that they're three cards of a kind)
            return true;
        }
        return false;
    }

    public boolean isOnePair() {            // Contains two cards of the same rank and three cards of three other ranks
        if (sizeOfHand < 2) {               // Checks if hand has enough cards to make OnePair
            return false;
        }
        int pairsFound = 0;                                                         // Leeps track of number of pairs found so that function returns true only when exactly one pair is found (false when there are no pairs, more than one pair, threeOfAKind, and fourOfAkind)
        for (int i = 0; i < sizeOfHand - 1; i++) {                                  // Loops through hand and checks adjacent cards to see if they're a pair
            if (this.hand[i].getGameVal() == this.hand[i + 1].getGameVal()) {
                pairsFound++;                                                       // Updates pairsFound to reflect new pairsFound
            }
        }
        if (pairsFound == 1) {
            return true;
        }
        return false;
    }

    public boolean isHighHand() {
        if (this.isOnePair() || this.isTwoPair() || this.isStraight() || this.isFlush() || this.isStraightFlush() || this.isRoyalFlush() || this.isThreeOfAKind() || this.isFourOfAKind() || this.isFullHouse()) {      // Checks if card falls under category of any other hand combination
            return false;
        }
        return true;
    }

}
