// Maria Ines Aranguren
package poker;

public class Runner {

    public static void main(String[] args) {
        DeckOfCards myDeck = new DeckOfCards();
        myDeck.shuffle();
        HandOfCards myHand = new HandOfCards(myDeck);
        System.out.printf("Your hand is: ");
        myHand.print();
        if (myHand.isFlush()) {
            System.out.printf("The hand is a Flush!\n");
        }
        if (myHand.isFourOfAKind()) {                                 // Intentionally not using else-ifs to test for correctness of functions
            System.out.printf("The hand is a FourOfAKind!\n");
        }
        if (myHand.isFullHouse()) {
            System.out.printf("The hand is a FullHouse!\n");
        }
        if (myHand.isHighHand()) {
            System.out.printf("The hand is a HighHand!\n");
        }
        if (myHand.isOnePair()) {
            System.out.printf("The hand is a OnePair!\n");
        }
        if (myHand.isRoyalFlush()) {
            System.out.printf("The hand is a RoyalFlush!\n");
        }
        if (myHand.isStraight()) {
            System.out.printf("The hand is a Straight!\n");
        }
        if (myHand.isStraightFlush()) {
            System.out.printf("The hand is a StraightFlush!\n");
        }
        if (myHand.isThreeOfAKind()) {
            System.out.printf("The hand is a ThreeOfAKind!\n");
        }
        if (myHand.isTwoPair()) {
            System.out.printf("The hand is a TwoPair!\n");
        }
        
        System.out.printf("Please run again to see another hand and its classification.\n");
        
    }

}
