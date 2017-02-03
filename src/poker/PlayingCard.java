// Maria Ines Aranguren
// Feb 2, 2017
// Assignment #1: OOP Representation of Playing Cards
package poker;  // Create poker package to organize classes and interfaces of application.

class PlayingCard {                        // Class definition to represent playing cards of a poker playing system
    private String type;
    private char suit;
    private int faceVal;
    private int gameVal;

    public PlayingCard(String defType, char defSuit, int defFaceVal, int defGameVal) {  // Constructor to instantiate card with given parameters
        this.type = defType;
        this.suit = defSuit;
        this.faceVal = defFaceVal;
        this.gameVal = defGameVal;
    }
    public String toString() {
        return getType() + getSuit();             // Return description of card (type and suit)
    }
    public void setType(String t) {           // Set functions for type, suit, faceVal, and gameVal
        this.type = t;
    }
    public void setSuit(char s) {
        this.suit = s;
    }
    public void setFaceVal(int f) {
        this.faceVal = f;
    }
    public void setGameVal(int g) {
        this.gameVal = g;
    }
    public String getType() {                 // Get functions for type, suit, faceVal, and gameVal
        return this.type;
    }
    public char getSuit() {
        return this.suit;
    }
    public int getFaceVal() {
        return this.faceVal;
    }
    public int getGameVal() {
        return this.gameVal;
    }

   
    
    public static void main(String[] args) {  // Test functionality of class members and methods
        // Create array of 52 PlayingCards
        PlayingCard[] deck = new PlayingCard[52];
        
        // Define attributes of each card
        String[] types = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        char[] suits = {'H', 'D', 'S', 'C'};
        int faceVal;
        int gameVal;
        int cardCount = 0;
        for (String t : types) {
            for (char s : suits) {
                if (t == "A") {                 // Assign corresponding faceVal and gameVal for cards A, J, Q, K
                    faceVal = 1;
                    gameVal = 14;
                } else if (t == "J") {
                    faceVal = 11;
                    gameVal = 11;
                } else if (t == "Q") {
                    faceVal = 12;
                    gameVal = 12;
                } else if (t == "K") {
                    faceVal = 13;
                    gameVal = 13;
                } else {                        // If not A, J, Q, or K, assign faceVal and gameVal to be same as value of type
                    faceVal = Integer.parseInt(t);
                    gameVal = Integer.parseInt(t);
                }
                PlayingCard card = new PlayingCard(t, s, faceVal, gameVal);   // Instantiate card with corresponding parameters for type, suit, face value, game value

                //Place card into deck
                deck[cardCount] = card;
                cardCount++;
            }
        }
        
        // Look through all cards and print their string representations
        cardCount = 1;
        String format = "%-10s%-10s%n";
        System.out.printf(format, "Card # ", "String Representation");
        for (PlayingCard card : deck) {                                   // Loop through all cards in deck
            System.out.printf(format, cardCount, card.toString());          // Print card count and string representation
            if (cardCount == 52) {                                          // For last card, demonstrate all public access methods by printing card info
                System.out.printf("Demonstration of public access methods for obtaining card info:\n");
                System.out.printf("Card # %d, Type: %s, Suit: %s, Face Value: %d, Game Value: %d\n", cardCount, card.getType(), card.getSuit(), card.getFaceVal(), card.getGameVal());
            }
            cardCount++;
        }

    }

}
