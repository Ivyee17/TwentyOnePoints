import sun.rmi.runtime.Log;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A deck without kings, which has only 52 cards.
 * <p>Jack is shown as 11, Queen as 12, King as 13. 1-10 as normal.</p>
 * <p>We don't support getCards, since anyone should get cards one by one by
 * {@code getCard} methods.</p>
 */
public class Deck {
    private Card[] cards;

    /**
     * Create a deck of cards as normal, which is from spade 1 to club 13.
     */
    public Deck() {
        cards = new Card[52];
        for (int i = 0; i < 52; i++) {
            cards[i] = new Card(Suit.values()[i / 13], i % 13+1);
        }
    }

    /**
     * Get a specific card.
     * <p>We judge whether the card follows the requirement. If not, we throws an internal error.</p>
     * @param suit the suit of the card.
     * @param faceValue the face value of the card.
     * @return the card as required.
     */
    public Card watchCard(Suit suit, int faceValue) {
        Card card = cards[suit.getIndex() * 13 + faceValue-1];
        if(card.getFaceValue() != faceValue) {
            throw new IllegalArgumentException("invalid face value "+faceValue);
        }
        if(card.getSuit() != suit) {
            throw new IllegalArgumentException("invalid suit"+suit);
        }
        return card;
    }

    /**
     * Watch a card by its number.
     * Unless urgent, never try to use this method. You can use {@link #watchCard(Suit, int)} instead.
     * @param number the sequent number of the card.
     * @return the card need to be watched
     */
    public Card watchCard(int number) {
        return watchCard(Suit.values()[number/13],number%13+1);
    }

    /**
     * Get the cards number which is not owned by any user.
     * @return the number which is not used by any users.
     */
    public int getCardRemaining() {
        int remaining = 0;
        for (int i = 0; i < 52; i++) {
            if (cards[i].getOwner() == null) remaining++;
        }
        return remaining;
    }

    /**
     * Get a card from this deck. Card should be chosen randomly.
     * @param person person who want to have this card (dealer or player)
     * @return the randomly chosen card.
     */
    public Card getCard(Person person){
        Random random=new Random();
        int randomNum=random.nextInt(52);
        int num=randomNum;

        Card card=watchCard(num);
        while(card.getOwner()!=null){
            num++;
            if(num==52){
                num=num%52;
            }
            card=watchCard(num);
            if(num==randomNum) {
                Exception e=new Exception();
                e.printStackTrace();
                break;
            }
        }
        card.setOwner(person);
        return card;
    }
}
