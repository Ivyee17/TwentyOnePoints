import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Base class of dealer and player.
 *
 * @see Dealer
 * @see Player
 */
public class Person {
    private int money;
    private String name;
    private List<Card> cardList;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Person(String name, int money) {
        this.name = name;
        this.cardList = new ArrayList<>();
        this.money=money;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get one more card by person.
     * @param decks decks can be gotten from
     * @return the one more card, which is chosen randomly.
     */
    public Card getCard(List<Deck> decks) {
        Random random = new Random();
        int randomInt = random.nextInt(decks.size());
        int pos = randomInt;
        while (decks.get(randomInt).getCardRemaining() == 0) {
            pos++;
            if (randomInt == pos) {
                Exception e = new Exception();
                e.printStackTrace();
                break;
            }
        }
        Card card = decks.get(randomInt).getCard(this);
        this.getCardList().add(card);
        return card;
    }

    /**
     * Calculate the points of this person.
     * Remember that Ace can be seen as one point or eleven points. In order to let all persons' points closer to 21 points,
     * this method will make the Aces' value as large as possible (but not exceed 21, if can).
     * @return the total points of this player
     */
    public int calPoints() {
        int basicPoints = 0;
        int aceNumber = 0;
        for (int i = 0; i < cardList.size(); i++) {
            int faceValue = cardList.get(i).getFaceValue();
            if (faceValue > 10) basicPoints += 10;
            else if (faceValue == 1) {
                aceNumber++;
                basicPoints += 1;
            } else {
                basicPoints += faceValue;
            }
        }
        int acesAddPoints = aceNumber * 9;
        while (basicPoints + acesAddPoints > 21) {
            acesAddPoints -= 9;
            if (acesAddPoints < 0) {
                acesAddPoints = 0;
                break;
            }
        }
        return basicPoints + acesAddPoints;

    }

    /**
     * Judge whether this person has BlackJack, which is important in {@link Game#calculateResult()}.
     * If has BlackJack, player should not get card again. If he again get the card, then BlackJack will have no use.
     * In fact, only two circumstances will have BlackJack: two cards with A+J, two cards with J+A.
     * @return {@code true} if have, {@code false} if not.
     */
    public boolean isBlackJack() {
        if (cardList.size() != 2) return false;
        else {
            return ((cardList.get(0).getFaceValue() == 1 && cardList.get(1).getFaceValue() == 11)
                    || (cardList.get(0).getFaceValue() == 11 && cardList.get(1).getFaceValue() == 1));
        }
    }

    /**
     * After the game is finished (not terminated, new round may have), this function should be called to empty the
     * list and release the ownership of the card.
     */
    public void roundFinal(){
        for(int i=0;i<cardList.size();i++){
            cardList.get(i).setOwner(null);
        }
        cardList.clear();
    }


}
