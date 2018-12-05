import javax.swing.*;
import java.awt.*;

/**
 * Card which is need in Game, A {@link Deck} will be composed by 52 cards.
 */
public class Card extends JLabel{
    private Suit suit;
    private int faceValue;
    private Person owner;
    private int width = 70;
    private int height = 120;

    public Card(Suit suit,int faceValue){
        this.faceValue = faceValue;
        this.suit = suit;
        this.owner = null;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
