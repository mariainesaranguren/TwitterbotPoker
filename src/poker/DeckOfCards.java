// Maria Ines Aranguren
// Feb 8, 2017
// Programming Assignment Week	#2: OOP Representation of a DeckOfCards

package poker;  // Create poker package to organize classes and interfaces of application.
import java.util.Random;

class DeckOfCards {
  public DeckOfCards() {        // DeckOfCards constructor initializes deck and defines PlayingCards within it
        this.topCard = 0;
        this.defineDeck();
  }
  private PlayingCard[] deck;
  private int topCard;          // Pointer next card to be dealt 
  
  public void defineDeck() {    // Define attributes of each card within deck
    this.deck = null;
    this.deck = new PlayingCard[52];
    String[] types = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    char[] suits = {'H', 'D', 'S', 'C'};
    int[] faceValues = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    int[] gameValues = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    int cardCount = 0;
    for(int i=0; i<13; i++){
        for (char s : suits) {
            PlayingCard card = new PlayingCard(types[i], s, faceValues[i], gameValues[i]);   // Instantiate card with corresponding parameters for type, suit, face value, game value
            this.deck[cardCount] = card;                                                     //Place card in deck
            cardCount++;
        }
    }
  }
  public void reset() {         // Reinitializes the deck and shuffles it for a new game.
      this.defineDeck();
      this.shuffle();
  }
  public void shuffle() {         // Shuffles cards by swapping two randomly chosen cards 52*52 times
    Random rand = new Random();
    for (int i=0; i<52*52; i++) {
      int a = rand.nextInt(52-topCard)+topCard;     // Generate a and b (two random numbers between topCard and 51 to index two cards)
      int b = rand.nextInt(52-topCard)+topCard;     // rand.nextInt(max - min + 1) + min
      swapCards(a, b);                              // Calls helper function to swap cards a and b
    }
  }
   public PlayingCard dealNext() {  // Deal card and adjust pointer to next card
       PlayingCard cardDeal;
       cardDeal = deck[topCard];    // Select card indexed by topCard
       deck[topCard] = null;        // Get rid of card so that it won't be dealt again
       topCard++;                   // Adjust deck pointer to reflect decrease in available cards
       if (topCard >= 52) {
           return null;
       }
       return cardDeal;
   }
  public void returnCard(PlayingCard discarded) {   // Returns discarded card to deck to make available for dealing
      this.deck[topCard-1] = discarded;             // Puts card on top of topCard
      topCard--;                                    // Updates topCard pointer
      this.shuffle();                               // Shuffles card so that discarded card isn't next card to be dealt
  }
  public void swapCards(int a, int b) {             // Swaps cards a and b
      PlayingCard tmp = this.deck[a];
      this.deck[a] = this.deck[b];
      this.deck[b] = tmp;
  }
  public static void main(String[] args) {
    DeckOfCards myDeck = new DeckOfCards();
    PlayingCard card = myDeck.deck[0];
    System.out.printf("Initialize deck and print card in position 0:\n");
    System.out.printf("\tCard # %d -- Type: %s, Suit: %s, Face Value: %d, Game Value: %d\n", 1, card.getType(), card.getSuit(), card.getFaceVal(), card.getGameVal());
    System.out.printf("Shuffle deck and print card in position 0:\n");
    myDeck.shuffle();
    card = myDeck.deck[0];
    System.out.printf("\tCard # %d -- Type: %s, Suit: %s, Face Value: %d, Game Value: %d\n", 1, card.getType(), card.getSuit(), card.getFaceVal(), card.getGameVal());
    System.out.printf("Reset deck and print card in position 0:\n");
    myDeck.reset();
    card = myDeck.deck[0];
    System.out.printf("\tCard # %d -- Type: %s, Suit: %s, Face Value: %d, Game Value: %d\n", 1, card.getType(), card.getSuit(), card.getFaceVal(), card.getGameVal());
    System.out.printf("Deal two cards:\n");
    System.out.printf("\tPointer to next card before dealing: %d\n", myDeck.topCard);
    PlayingCard deal = myDeck.dealNext();
    System.out.printf("\tDealt Card -- Type: %s, Suit: %s, Face Value: %d, Game Value: %d\n", deal.getType(), deal.getSuit(), deal.getFaceVal(), deal.getGameVal());
    deal = myDeck.dealNext();
    System.out.printf("\tDealt Card -- Type: %s, Suit: %s, Face Value: %d, Game Value: %d\n", deal.getType(), deal.getSuit(), deal.getFaceVal(), deal.getGameVal());
    System.out.printf("\tPointer to next card after dealing cards: %d\n", myDeck.topCard);
    System.out.printf("Discard last dealt card (Type: %s, Suit: %s, Face Value: %d, Game Value: %d):\n", deal.getType(), deal.getSuit(), deal.getFaceVal(), deal.getGameVal());
    myDeck.returnCard(deal);
    System.out.printf("\tPointer to next card: %d\n", myDeck.topCard);
    System.out.printf("Dealing card (* Notice cards are shuffled when cards are discarded and returned to deck):\n");
    deal = myDeck.dealNext();
    System.out.printf("\tDealt Card -- Type: %s, Suit: %s, Face Value: %d, Game Value: %d\n", deal.getType(), deal.getSuit(), deal.getFaceVal(), deal.getGameVal());
    System.out.printf("Deal 50 more cards to show what happens when a card requested to be dealt but deck is empty:\n");
    for (int i=0; i<50; i++) {
        deal = myDeck.dealNext();
        System.out.printf("\tDealt Card #%d -- ", i+1);
        try {
            System.out.printf("Type: %s, Suit: %s, Face Value: %d, Game Value: %d\n", deal.getType(), deal.getSuit(), deal.getFaceVal(), deal.getGameVal());
        } catch(NullPointerException e) {
            System.out.printf("Error: Cannot deal card. Deck is empty.\n");
        }
    }
  }
}

