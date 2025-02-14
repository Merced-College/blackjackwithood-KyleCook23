package cardGame;

public class Card {
    // Card attributes
    private String suit;
    private String name;
    private int value;
    private String picture;

    //Constructor to initialize a card with its suit, name, value, and picture
    public Card(String suit, String name, int value, String picture) {
        this.suit = suit;
        this.name = name;
        this.value = value;
        this.picture = picture;
    }
    //grabs the suit of the card
    public String getSuit() {
        return suit;
    }
    //grabs the name of the card
    public String getName() {
        return name;
    }
    //grabs the value of the card
    public int getValue() {
        return value;
    }
    //grabs the picture of the card
    public String getPicture() {
        return picture;
    }
    //checks if the card is a face card
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Card card = (Card) obj;
        return value == card.value && name.trim().equalsIgnoreCase(card.name.trim());
    }
    //returns the card as a string
    @Override
    public String toString() {
        return suit + ", " + name + ", " + value + ", " + picture;
    }
}
