// Maria Ines Aranguren
package poker;

public class Runner {

    public static void main(String[] args) {
        DeckOfCards myDeck = new DeckOfCards();
        myDeck.shuffle();
        
        // Testing getGameValue() method of HandOfCards:
        // Case A) One hand is better than other and hands are same (two flushes, one better than other)
        System.out.printf("-- Testing descrimination of different instances of same type of hand --\n");
        // Test 1 -- Flush of K, Q, 9, 8, 5 diamonds and flush of 3, 5, 6, 7, 8 spades
        // Constructing flush of diamonds hand by instantiating hand and overwriting cards
        HandOfCards handA = new HandOfCards(myDeck);
        PlayingCard oneA = new PlayingCard("K", myDeck.DIAMONDS, 13, 13);
        PlayingCard twoA = new PlayingCard("Q", myDeck.DIAMONDS, 12, 12);
        PlayingCard threeA = new PlayingCard("9", myDeck.DIAMONDS, 9, 9);
        PlayingCard fourA = new PlayingCard("8", myDeck.DIAMONDS, 8, 8); 
        PlayingCard fiveA = new PlayingCard("5", myDeck.DIAMONDS, 5, 5);
        handA.replaceCard(oneA, 0);
        handA.replaceCard(twoA, 1);
        handA.replaceCard(threeA, 2);
        handA.replaceCard(fourA, 3);
        handA.replaceCard(fiveA, 4);
        System.out.printf("Hand A is: ");
        handA.print();
        handA.identifyHand();
        // Constructing flush of spades hand by instantiating hand and overwriting cards
        HandOfCards handB = new HandOfCards(myDeck);
        PlayingCard oneB = new PlayingCard("3", myDeck.SPADES, 3, 3);
        PlayingCard twoB = new PlayingCard("5", myDeck.SPADES, 5, 5);
        PlayingCard threeB = new PlayingCard("6", myDeck.SPADES, 6, 6);
        PlayingCard fourB = new PlayingCard("7", myDeck.SPADES, 7, 7); 
        PlayingCard fiveB = new PlayingCard("8", myDeck.SPADES, 8, 8);
        handB.replaceCard(oneB, 0);
        handB.replaceCard(twoB, 1);
        handB.replaceCard(threeB, 2);
        handB.replaceCard(fourB, 3);
        handB.replaceCard(fiveB, 4);
        System.out.printf("Hand B is: ");
        handB.print();
        handB.identifyHand();
        // Compares two hands
        if (handA.getGameValue() > handB.getGameValue()) {
            System.out.printf("Hand A > Hand B\n");
        } else if (handA.getGameValue() < handB.getGameValue()) {
            System.out.printf("Hand A < Hand B\n");
        } else {
            System.out.printf("Hands tied!\n");
        }
        // Test 2 -- Full house of A, A, A, K, K and full house of 3, 3, 3, 8, 8
        // Constructing full house of A, A, A, K, K by instantiating hand and overwriting cards
//        HandOfCards handA2 = new HandOfCards(myDeck);
        PlayingCard oneA2 = new PlayingCard("A", myDeck.DIAMONDS, 1, 14);
        PlayingCard twoA2 = new PlayingCard("A", myDeck.SPADES, 1, 14);
        PlayingCard threeA2 = new PlayingCard("A", myDeck.CLUBS, 1, 14);
        PlayingCard fourA2 = new PlayingCard("K", myDeck.DIAMONDS, 13, 13); 
        PlayingCard fiveA2 = new PlayingCard("K", myDeck.SPADES, 13, 13);
        handA.replaceCard(oneA2, 0);
        handA.replaceCard(twoA2, 1);
        handA.replaceCard(threeA2, 2);
        handA.replaceCard(fourA2, 3);
        handA.replaceCard(fiveA2, 4);
        System.out.printf("\nHand A2 is: ");
        handA.print();
        handA.identifyHand();
        // Constructing full house of 3, 3, 3, 8, 8 by instantiating hand and overwriting cards
//        HandOfCards handB2 = new HandOfCards(myDeck);
        PlayingCard oneB2 = new PlayingCard("3", myDeck.SPADES, 3, 3);
        PlayingCard twoB2 = new PlayingCard("3", myDeck.SPADES, 3, 3);
        PlayingCard threeB2 = new PlayingCard("3", myDeck.SPADES, 3, 3);
        PlayingCard fourB2 = new PlayingCard("8", myDeck.HEARTS, 8, 8); 
        PlayingCard fiveB2 = new PlayingCard("8", myDeck.HEARTS, 8, 8);
        handB.replaceCard(oneB2, 0);
        handB.replaceCard(twoB2, 1);
        handB.replaceCard(threeB2, 2);
        handB.replaceCard(fourB2, 3);
        handB.replaceCard(fiveB2, 4);
        System.out.printf("Hand B2 is: ");
        handB.print();
        handB.identifyHand();
        // Compares two hands
        if (handA.getGameValue() > handB.getGameValue()) {
            System.out.printf("Hand A2 > Hand B2\n");
        } else if (handA.getGameValue() < handB.getGameValue()) {
            System.out.printf("Hand A2 < Hand B2\n");
        } else {
            System.out.printf("Hands tied!\n");
        }
        
        
        
        // Case B) Value of hands is tied (two royal flushes)
        System.out.printf("\n-- Testing hands of same type and tied score --\n");
        // Test 1 -- Two royal flushes
        // Constructing royal flush of hearts hand by instantiating hand and overwriting cards
        HandOfCards handC = new HandOfCards(myDeck);
        PlayingCard oneC = new PlayingCard("A", myDeck.HEARTS, 1, 14);
        PlayingCard twoC = new PlayingCard("K", myDeck.HEARTS, 13, 13);
        PlayingCard threeC = new PlayingCard("Q", myDeck.HEARTS, 12, 12);
        PlayingCard fourC = new PlayingCard("J", myDeck.HEARTS, 11, 11); 
        PlayingCard fiveC = new PlayingCard("10", myDeck.HEARTS, 10, 10);
        handC.replaceCard(oneC, 0);
        handC.replaceCard(twoC, 1);
        handC.replaceCard(threeC, 2);
        handC.replaceCard(fourC, 3);
        handC.replaceCard(fiveC, 4);
        System.out.printf("Hand C is: ");
        handC.print();
        handC.identifyHand();
        // Constructing royal flush of spades hand by instantiating hand and overwriting cards
        HandOfCards handD = new HandOfCards(myDeck);
        PlayingCard oneD = new PlayingCard("A", myDeck.SPADES, 1, 14);
        PlayingCard twoD = new PlayingCard("K", myDeck.SPADES, 13, 13);
        PlayingCard threeD = new PlayingCard("Q", myDeck.SPADES, 12, 12);
        PlayingCard fourD = new PlayingCard("J", myDeck.SPADES, 11, 11); 
        PlayingCard fiveD = new PlayingCard("10", myDeck.SPADES, 10, 10);
        handD.replaceCard(oneD, 0);
        handD.replaceCard(twoD, 1);
        handD.replaceCard(threeD, 2);
        handD.replaceCard(fourD, 3);
        handD.replaceCard(fiveD, 4);
        System.out.printf("Hand D is: ");
        handD.print();
        handD.identifyHand();
        // Compares two hands
        if (handC.getGameValue() > handD.getGameValue()) {
            System.out.printf("Hand C > Hand D\n");
        } else if (handC.getGameValue() < handD.getGameValue()) {
            System.out.printf("Hand C < Hand D\n");
        } else {
            System.out.printf("Hands tied!\n");
        }
        // Test 2 -- Two normal flushes
        // Constructing flush of K, Q, 9, 8, 7 hearts hand by instantiating hand and overwriting cards
        PlayingCard oneC2 = new PlayingCard("K", myDeck.HEARTS, 13, 13);
        PlayingCard twoC2 = new PlayingCard("Q", myDeck.HEARTS, 12, 12);
        PlayingCard threeC2 = new PlayingCard("9", myDeck.HEARTS, 9, 9);
        PlayingCard fourC2 = new PlayingCard("8", myDeck.HEARTS, 8, 8); 
        PlayingCard fiveC2 = new PlayingCard("7", myDeck.HEARTS, 7, 7);
        handC.replaceCard(oneC2, 0);
        handC.replaceCard(twoC2, 1);
        handC.replaceCard(threeC2, 2);
        handC.replaceCard(fourC2, 3);
        handC.replaceCard(fiveC2, 4);
        System.out.printf("\nHand C2 is: ");
        handC.print();
        handC.identifyHand();
        // Constructing flush of K, Q, 9, 8, 7 spades hand by instantiating hand and overwriting cards
        PlayingCard oneD2 = new PlayingCard("K", myDeck.SPADES, 13, 13);
        PlayingCard twoD2 = new PlayingCard("Q", myDeck.SPADES, 12, 12);
        PlayingCard threeD2 = new PlayingCard("9", myDeck.SPADES, 9, 9);
        PlayingCard fourD2 = new PlayingCard("8", myDeck.SPADES, 8, 8); 
        PlayingCard fiveD2 = new PlayingCard("7", myDeck.SPADES, 7, 7);
        handD.replaceCard(oneD2, 0);
        handD.replaceCard(twoD2, 1);
        handD.replaceCard(threeD2, 2);
        handD.replaceCard(fourD2, 3);
        handD.replaceCard(fiveD2, 4);
        System.out.printf("Hand D2 is: ");
        handD.print();
        handD.identifyHand();
        // Compares two hands
        if (handC.getGameValue() > handD.getGameValue()) {
            System.out.printf("Hand C2 > Hand D2\n");
        } else if (handC.getGameValue() < handD.getGameValue()) {
            System.out.printf("Hand C2 < Hand D2\n");
        } else {
            System.out.printf("Hands tied!\n");
        }
        
        // Case C) One hand is better than other and hands are different
        System.out.printf("\n-- Testing descrimination of different types of hands --\n");
        // Test 1 - Four of a kind and royal flush
        // Constructing hand of four of a kind (A, A, A, A, K) by instantiating hand and overwriting cards
        // Note: Intentionally chose high-scoring cards to show that it will still be inferior in overall hand value to royal flush
        HandOfCards handE = new HandOfCards(myDeck);
        PlayingCard oneE = new PlayingCard("A", myDeck.HEARTS, 1, 14);
        PlayingCard twoE = new PlayingCard("A", myDeck.DIAMONDS, 1, 14);
        PlayingCard threeE = new PlayingCard("A", myDeck.CLUBS, 1, 14);
        PlayingCard fourE = new PlayingCard("A", myDeck.SPADES, 1, 14); 
        PlayingCard fiveE = new PlayingCard("K", myDeck.HEARTS, 13, 13);
        handE.replaceCard(oneE, 0);
        handE.replaceCard(twoE, 1);
        handE.replaceCard(threeE, 2);
        handE.replaceCard(fourE, 3);
        handE.replaceCard(fiveE, 4);
        System.out.printf("Hand E is: ");
        handE.print();
        handE.identifyHand();
        // Constructing royal flush of spades hand by instantiating hand and overwriting cards
        HandOfCards handF = new HandOfCards(myDeck);
        PlayingCard oneF = new PlayingCard("A", myDeck.SPADES, 1, 14);
        PlayingCard twoF = new PlayingCard("K", myDeck.SPADES, 13, 13);
        PlayingCard threeF = new PlayingCard("Q", myDeck.SPADES, 12, 12);
        PlayingCard fourF = new PlayingCard("J", myDeck.SPADES, 11, 11); 
        PlayingCard fiveF = new PlayingCard("10", myDeck.SPADES, 10, 10);
        handF.replaceCard(oneF, 0);
        handF.replaceCard(twoF, 1);
        handF.replaceCard(threeF, 2);
        handF.replaceCard(fourF, 3);
        handF.replaceCard(fiveF, 4);
        System.out.printf("Hand F is: ");
        handF.print();
        handF.identifyHand();
        // Compares two hands
        if (handE.getGameValue() > handF.getGameValue()) {
            System.out.printf("Hand E > Hand F\n");
        } else if (handE.getGameValue() < handF.getGameValue()) {
            System.out.printf("Hand E < Hand F\n");
        } else {
            System.out.printf("Hands tied!\n");
        }
        // Test 2 -- High hand and one pair
        // Constructing high hand (K, 7, 5, 3, 2) by instantiating hand and overwriting cards
        // Note: Intentionally chose high-scoring cards to show that it will still be inferior in overall hand value to royal flush
        PlayingCard oneE2 = new PlayingCard("K", myDeck.HEARTS, 13, 13);
        PlayingCard twoE2 = new PlayingCard("7", myDeck.CLUBS, 7, 7);
        PlayingCard threeE2 = new PlayingCard("5", myDeck.DIAMONDS, 5, 5);
        PlayingCard fourE2 = new PlayingCard("3", myDeck.HEARTS, 3, 3); 
        PlayingCard fiveE2 = new PlayingCard("2", myDeck.CLUBS, 2, 2);
        handE.replaceCard(oneE2, 0);
        handE.replaceCard(twoE2, 1);
        handE.replaceCard(threeE2, 2);
        handE.replaceCard(fourE2, 3);
        handE.replaceCard(fiveE2, 4);
        System.out.printf("\nHand E2 is: ");
        handE.print();
        handE.identifyHand();
        // Constructing one pair (2, 2, 3, 5, 7) by instantiating hand and overwriting cards
        PlayingCard oneF2 = new PlayingCard("2", myDeck.SPADES, 2, 2);
        PlayingCard twoF2 = new PlayingCard("2", myDeck.DIAMONDS, 2, 2);
        PlayingCard threeF2 = new PlayingCard("3", myDeck.SPADES, 3, 3);
        PlayingCard fourF2 = new PlayingCard("5", myDeck.CLUBS, 5, 5); 
        PlayingCard fiveF2 = new PlayingCard("7", myDeck.HEARTS, 7, 7);
        handF.replaceCard(oneF2, 0);
        handF.replaceCard(twoF2, 1);
        handF.replaceCard(threeF2, 2);
        handF.replaceCard(fourF2, 3);
        handF.replaceCard(fiveF2, 4);
        System.out.printf("Hand F2 is: ");
        handF.print();
        handF.identifyHand();
        // Compares two hands
        if (handE.getGameValue() > handF.getGameValue()) {
            System.out.printf("Hand E2 > Hand F2\n");
        } else if (handE.getGameValue() < handF.getGameValue()) {
            System.out.printf("Hand E2 < Hand F2\n");
        } else {
            System.out.printf("Hands tied!\n");
        }
    }

}
