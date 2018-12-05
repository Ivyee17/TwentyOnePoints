/**
 * Here's the suit of cards.
 * No setter supplied since all things are not allowed to be changed.
 */
public enum Suit {
    Spade("Spade",0),Heart("Heart",1),Diamond("Diamond",2),Club("Club",3);
    private String name;
    private int index;

    Suit(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

}