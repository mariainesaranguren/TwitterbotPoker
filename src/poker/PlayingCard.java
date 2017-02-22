// Maria Ines Aranguren
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
              

    PlayingCard() {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose Tools | Templates.
    }

    public String toString() {
        return getType() + getSuit();             // Return description of card (type and suit)
    }

    public void setType(String t) {               // Set functions for type, suit, faceVal, and gameVal
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
}
