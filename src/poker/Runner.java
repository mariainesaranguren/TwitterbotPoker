// Maria Ines Aranguren
package poker;

public class Runner {

    public static void main(String[] args) {
        DeckOfCards myDeck = new DeckOfCards();
        myDeck.shuffle();

        PokerPlayer player = new PokerPlayer(myDeck);
        System.out.printf("Original hand: ");
        player.playerHand.print();
        System.out.println("Game value of hand: " + player.playerHand.getGameValue());
        System.out.println("\nDiscarded " + player.discard() + " card(s).\n");
        System.out.printf("Final hand: ");
        player.playerHand.print();
        System.out.println("Game value of hand: " + player.playerHand.getGameValue());
    }

}
