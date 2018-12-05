import java.util.List;

/**
 * Important actor in this game.
 */
public class Dealer extends Person {
    public Dealer(String name, int dealerMoney) {
        super(name, dealerMoney);
    }

    /**
     * After the game, the dealer will set this function to play the game.
     * Algorithm is very simple, getCard until points is above 17.
     * @param decks the decks that can be gotten by dealer.
     */
    public void play(List<Deck> decks) {
        while (calPoints() <= 17) {
            getCard(decks);
        }
    }
}
