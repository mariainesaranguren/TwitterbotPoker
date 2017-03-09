// Maria Ines Aranguren
package poker;  // Create poker package to organize classes and interfaces of application.

import java.util.Random;

class DeckOfCards {

    private PlayingCard[] deck;
    private int topCard;          // Pointer next card to be dealt 
    static private final int SIZE_OF_DECK = 52;
    static private final int SUIT_CARD_COUNT = 13;
    static public final char HEARTS = 'H';
    static public final char DIAMONDS = 'D';
    static public final char SPADES = 'S';
    static public final char CLUBS = 'C';

    public DeckOfCards() {        // DeckOfCards constructor initializes deck and defines PlayingCards within it
        this.topCard = 0;
        this.defineDeck();
    }

    public static int getSIZE_OF_DECK() {
        return SIZE_OF_DECK;
    }
    
    public PlayingCard peekCard(int position) {
        if (position < 0 || position > SIZE_OF_DECK) {
            return null;
        }
        return this.deck[position];
    }

    public void defineDeck() {    // Define attributes of each card within deck
        this.deck = new PlayingCard[SIZE_OF_DECK];
        String[] types = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        char[] suits = {HEARTS, DIAMONDS, SPADES, CLUBS};
        int[] faceValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        int[] gameValues = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        int cardCount = 0;
        for (int i = 0; i < SUIT_CARD_COUNT; i++) {
            for (char s : suits) {
                PlayingCard card = new PlayingCard(types[i], s, faceValues[i], gameValues[i]);   // Instantiate card with corresponding parameters for type, suit, face value, game value
                this.deck[cardCount] = card;                                                    // Place card in deck
                cardCount++;
            }
        }
        
    }
    
    public void reset() {           // Reinitializes the deck and shuffles it for a new game.
        this.defineDeck();
        this.shuffle();
    }

    public void shuffle() {         // Shuffles cards by swapping two randomly chosen cards SIZE_OF_DECK*SIZE_OF_DECK times
        Random rand = new Random();
        for (int i = 0; i < SIZE_OF_DECK * SIZE_OF_DECK; i++) {
            int a = rand.nextInt(SIZE_OF_DECK - topCard) + topCard;     // Generate a and b (two random numbers between topCard and 51 to index two cards)
            int b = rand.nextInt(SIZE_OF_DECK - topCard) + topCard;     // rand.nextInt(max - min + 1) + min
            swapCards(a, b);                                            // Calls helper function to swap cards a and b
        }
        return;
    }

    public synchronized PlayingCard dealNext() {  // Deal card and adjust pointer to next card
        if (topCard >= SIZE_OF_DECK) {
            System.out.println("Error: All cards have been dealt and the deck is empty.");
            return null;
        }
        PlayingCard cardDeal;
        cardDeal = deck[topCard];    // Select card indexed by topCard
        deck[topCard] = null;        // Get rid of card so that it won't be dealt again
        topCard++;                   // Adjust deck pointer to reflect decrease in available cards
        return cardDeal;
    }

    public synchronized void returnCard(PlayingCard discarded) {   // Returns discarded card to deck to make available for dealing
        if (discarded == null) {
            System.out.println("Error: Returned card was null.");
            return;
        }
        this.deck[topCard - 1] = discarded;           // Puts card on top of topCard
        topCard--;                                    // Updates topCard pointer
        this.shuffle();                               // Shuffles card so that discarded card isn't next card to be dealt
        return;
    }

    public void swapCards(int a, int b) {             // Swaps cards a and b
        PlayingCard tmp = this.deck[a];
        this.deck[a] = this.deck[b];
        this.deck[b] = tmp;
        return;
    }
}
