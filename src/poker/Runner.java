// Maria Ines Aranguren
package poker;

public class Runner {

    public static void main(String[] args) {
        DeckOfCards myDeck = new DeckOfCards();
        myDeck.shuffle();

        // Testing getDiscardProbability() method of HandOfCards:
        System.out.println("*** TESTING getDiscardProbability() WITH DIFFERENT HANDS ***\n");
        System.out.println("To calculate the probability of getting a better hand when replacing exactly one particular card, this program \n"
                + "considers other hands that could be formed and compares their scores with the score of the original hand to quantify \n"
                + "the chances of improvement.");
        System.out.println("The higher the probabilistic score of improvement, the more likely it is that the hand would improve.");
        System.out.println("The highest possible score is: 1000");
        System.out.println("\n------------------------------------------------------------------------------------------- \n");

        // Test #1 - randomly generated hand
        HandOfCards hand1 = new HandOfCards(myDeck);
        System.out.println("Hand 1 (randomly dealt): ");
        hand1.print();
        System.out.println("\tType: " + hand1.identifyHand());
        int discardCard;
        System.out.printf("===> Probabilistic score representing the chances of improving Hand 1 discarding: \n");
        for (discardCard = 0; discardCard < hand1.getSIZE_OF_HAND(); discardCard++) {
            System.out.printf("\tCard %s: %d\n", hand1.getHand()[discardCard].toString(), hand1.getDiscardProbability(discardCard));
        }

        // Test #2 - high hand
        // Create high hand
        HandOfCards hand2 = new HandOfCards(myDeck);
        PlayingCard one = new PlayingCard("A", myDeck.SPADES, 1, 14);  // Create PlayingCard objects: defType, defSuit, defFaceVal, defGameVal
        PlayingCard two = new PlayingCard("K", myDeck.HEARTS, 13, 13);
        PlayingCard three = new PlayingCard("4", myDeck.CLUBS, 4, 4);
        PlayingCard four = new PlayingCard("10", myDeck.DIAMONDS, 10, 10);
        PlayingCard five = new PlayingCard("8", myDeck.DIAMONDS, 8, 8);
        hand2.replaceCard(one, 0);
        hand2.replaceCard(two, 1);
        hand2.replaceCard(three, 2);
        hand2.replaceCard(four, 3);
        hand2.replaceCard(five, 4);
        // Test for finding probability of improvement
        System.out.println("\n------------------------------------------------------------------------------------------- \n");
        System.out.println("Hand 2 (high hand): ");
        hand2.print();
        System.out.println("\tType: " + hand2.identifyHand());
        System.out.printf("===> Probabilistic score representing the chances of improving Hand 2 discarding: \n");
        for (discardCard = 0; discardCard < hand2.getSIZE_OF_HAND(); discardCard++) {
            System.out.printf("\tCard %s: %d\n", hand2.getHand()[discardCard].toString(), hand2.getDiscardProbability(discardCard));
        }

        // Test #3 - high hand with lower-ranked cards
        // Create high hand
        HandOfCards hand3 = new HandOfCards(myDeck);
        PlayingCard one3 = new PlayingCard("2", myDeck.SPADES, 2, 2);  // Create PlayingCard objects: defType, defSuit, defFaceVal, defGameVal
        PlayingCard two3 = new PlayingCard("4", myDeck.HEARTS, 4, 4);
        PlayingCard three3 = new PlayingCard("6", myDeck.CLUBS, 6, 6);
        PlayingCard four3 = new PlayingCard("10", myDeck.DIAMONDS, 10, 10);
        PlayingCard five3 = new PlayingCard("8", myDeck.DIAMONDS, 8, 8);
        hand3.replaceCard(one3, 0);
        hand3.replaceCard(two3, 1);
        hand3.replaceCard(three3, 2);
        hand3.replaceCard(four3, 3);
        hand3.replaceCard(five3, 4);
        // Test for finding probability of improvement
        System.out.println("\n------------------------------------------------------------------------------------------- \n");
        System.out.println("Hand 3 (high hand with lower-ranked cards): ");
        hand3.print();
        System.out.println("\tType: " + hand3.identifyHand());
        System.out.printf("===> Probabilistic score representing the chances of improving Hand 3 discarding: \n");
        for (discardCard = 0; discardCard < hand3.getSIZE_OF_HAND(); discardCard++) {
            System.out.printf("\tCard %s: %d\n", hand3.getHand()[discardCard].toString(), hand3.getDiscardProbability(discardCard));
        }

        // Test #4 - royal flush
        HandOfCards hand4 = new HandOfCards(myDeck);
        PlayingCard one4 = new PlayingCard("10", myDeck.HEARTS, 10, 10);  // Create PlayingCard objects: defType, defSuit, defFaceVal, defGameVal
        PlayingCard two4 = new PlayingCard("J", myDeck.HEARTS, 11, 11);
        PlayingCard three4 = new PlayingCard("Q", myDeck.HEARTS, 12, 12);
        PlayingCard four4 = new PlayingCard("K", myDeck.HEARTS, 13, 13);
        PlayingCard five4 = new PlayingCard("A", myDeck.HEARTS, 14, 14);
        hand4.replaceCard(one4, 0);
        hand4.replaceCard(two4, 1);
        hand4.replaceCard(three4, 2);
        hand4.replaceCard(four4, 3);
        hand4.replaceCard(five4, 4);
        // Test for finding probability of improvement
        System.out.println("\n------------------------------------------------------------------------------------------- \n");
        System.out.println("Hand 4 (royal flush): ");
        hand4.print();
        System.out.println("\tType: " + hand4.identifyHand());
        System.out.printf("===> Probabilistic score representing the chances of improving Hand 4 discarding: \n");
        for (discardCard = 0; discardCard < hand4.getSIZE_OF_HAND(); discardCard++) {
            System.out.printf("\tCard %s: %d\n", hand4.getHand()[discardCard].toString(), hand4.getDiscardProbability(discardCard));
        }

        // Test #5 - two pairs
        HandOfCards hand5 = new HandOfCards(myDeck);
        PlayingCard one5 = new PlayingCard("4", myDeck.HEARTS, 4, 4);  // Create PlayingCard objects: defType, defSuit, defFaceVal, defGameVal
        PlayingCard two5 = new PlayingCard("4", myDeck.DIAMONDS, 4, 4);
        PlayingCard three5 = new PlayingCard("9", myDeck.CLUBS, 9, 9);
        PlayingCard four5 = new PlayingCard("J", myDeck.SPADES, 11, 11);
        PlayingCard five5 = new PlayingCard("J", myDeck.DIAMONDS, 11, 11);
        hand5.replaceCard(one5, 0);
        hand5.replaceCard(two5, 1);
        hand5.replaceCard(three5, 2);
        hand5.replaceCard(four5, 3);
        hand5.replaceCard(five5, 4);
        // Test for finding probability of improvement
        System.out.println("\n------------------------------------------------------------------------------------------- \n");
        System.out.println("Hand 5 (two pairs): ");
        hand5.print();
        System.out.println("\tType: " + hand5.identifyHand());
        System.out.printf("===> Probabilistic score representing the chances of improving Hand 5 discarding: \n");
        for (discardCard = 0; discardCard < hand5.getSIZE_OF_HAND(); discardCard++) {
            System.out.printf("\tCard %s: %d\n", hand5.getHand()[discardCard].toString(), hand5.getDiscardProbability(discardCard));
        }

    }

}
