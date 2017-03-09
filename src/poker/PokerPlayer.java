// Maria Ines Aranguren
package poker;

import java.util.Random;

public class PokerPlayer {

    HandOfCards playerHand;
    DeckOfCards sourceDeck;
    private final int MAX_DISCARD = 3;

    PokerPlayer(DeckOfCards src) {
        this.sourceDeck = src;
        this.playerHand = new HandOfCards(src);
    }

    public int discard() {
        Random randomGenerator = new Random();
        int discardCount = 0;
        PlayingCard[] toDiscard = new PlayingCard[MAX_DISCARD];
        this.playerHand.sortByDiscardProb();                                        // Sorts by descending discard probability score in order to give cards with a higher discardProb a greater chance to be replaced (since only up to three can be discarded)
        for (int i = 0; i < this.playerHand.getSIZE_OF_HAND(); i++) {
            if (this.playerHand.getDiscardProbability(i) > 0 && this.playerHand.getDiscardProbability(i) < 100 && discardCount < MAX_DISCARD) {
                int randomInt = randomGenerator.nextInt(100) + 1;
                if (randomInt < this.playerHand.getDiscardProbability(i)) {          // Discard card if randomInt is less than discard probability
                    toDiscard[discardCount] = this.playerHand.getHand()[i];
                    this.playerHand.replaceCard(i, this.sourceDeck.dealNext());      // Deal new card and replace discarded card with new card
                    discardCount++;
                }
            }
        }
        for (int i = discardCount - 1; i >= 0; i--) {
            this.sourceDeck.returnCard(toDiscard[i]);
        }
        this.playerHand.sort(); // TODO make private?
        return discardCount;
    }

}
