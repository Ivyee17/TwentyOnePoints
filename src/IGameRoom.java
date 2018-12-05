import java.util.List;

/**
 * This is the control center of the whole game.
 * This interface will coordinate the dealer, player and deck. Here, {@code Deck} and {@code Player} may have more than one,
 * which needs a list or array to store them. However, {@code Dealer} will just have one. After implementing all these
 * functions, you may pass the test to run all the game.
 * Also, this is the connection between UI design and object-oriented analysis and design.
 *
 * @see Dealer
 * @see Deck
 * @see Player
 */
public interface IGameRoom {

    List<Deck> getDecks();

    List<Player> getPlayers();

    Dealer getDealer();


    /**
     * Initial ONE player with name and money, add it to players list.
     * <p>Remember that here the money is not the stake. When we set the stake, {@link Player#setStake(int)}
     * will guarantee that stake should not more than money. {@link #isDeal()} will guarantee that
     * the total of player's stakes is less than dealer's money.</p>
     * <p>Once the money is set, it cannot be changed anymore.</p>
     *
     * @param name the player's name
     * @param money the money player's have
     * @return the initialized player
     *
     * @see Player#setStake(int)
     * @see #isDeal()
     */
    Player initPlayer(String name, int money);

    /**
     * Initial the dealer with decks and money.
     * <p>In the game, there may be more than one deck. As default, deck number will be 1. </p>
     * <p>Dealer's money will not be checked. Always try to init dealer before players so that problems won't happen.
     * Players' stake will be set in {@link #isDeal()}.</p>
     *
     * @param deckNumber the number of deck which joined the game
     * @param dealerMoney the money dealer have
     * @return the initialized dealer
     *
     * @see #isDeal()
     */
    Dealer initDealer(int deckNumber,int dealerMoney);

    /**
     * check the deal so that dealer can pay the money if he lost at all.
     * this is very important, please check each time you start the game, or dealer's money may become negative.
     *
     * @return {@code true} if can pay the money, {@code false} otherwise.
     */
    boolean isDeal();

    /**
     * Give each person 2 cards.
     *
     * @see #getCard(Player)
     */
    void initCard();

    /**
     * For the certain player, get one card from all decks.
     * We ensure that the card can't be in use by other players or dealer.
     * The details will be shown in {@link Player#getCard(List)}
     *
     * @param player the player that wants the card
     * @return a unused card
     *
     * @see Player#getCard(List)
     */
    Card getCard(Player player);

    /**
     * Make the players' status FINISHED.
     * Here we just changed the players' status. If there are still players whose status is not FINISHED,
     * {@link #calculateResult()} calculateResult} will not be executed normally.
     *
     * @param player the players which need to stop (or set as finished).
     *
     * @see #calculateResult()
     */
    void stopGettingCard(Player player);

    /**
     * Dealer's play the game and calculate the result (whether win or lose, win how much).
     * Players' result will be shown in {@link Status}. Different status mean different results, which will be used in
     * {@link #changeBalance()}. Note that we don't change the money they have, just judge whether the win or lose.
     *
     * @see #changeBalance()
     */
    void calculateResult() ;

    /**
     * Change the money players' and dealer's have.
     * Balance change will be done here. After this function is used, all money exchange will be finished, call {@link #roundFinal()}
     * if you want to start again.
     */
    void changeBalance() ;

    /**
     * To finish this game, use this function to empty all cardList and info.
     */
    void roundFinal();

    /**
     * This is for ending of the game, dealer, players and decks will be clear at all.
     */
    void terminate();

    /**
     * Judge whether the player and dealer can continue to play, if player or dealer's money is less than 0, then program finished.
     * @return {@code true} if can continue, {@code false} if not.
     */
    boolean canDeal();
}