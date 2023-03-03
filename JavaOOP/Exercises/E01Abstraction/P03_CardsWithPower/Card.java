package E01Abstraction.P03_CardsWithPower;

public class Card {
    CardRanks cardRank;
    CardSuits cardSuit;

    public Card(CardRanks cardRank, CardSuits cardSuit) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
    }

    public int getPower() {
        return cardRank.getRankPower() + cardSuit.getSuitPower();
    }

    public String cardInfo() {
        return String.format("Card name: %s of %s; Card power: %d"
                , cardRank.name(), cardSuit.name(), getPower());
    }


}
