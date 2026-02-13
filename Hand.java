public class Hand {
    private int[] cards;
    private int[] totalOccurance;
    private int bid;
    private int hand;
    private int rank;

    public Hand(int[] cards, int bid, int hand, int rank, int[] totalOccurance) {
        this.hand = hand;
        this.rank = rank;
        this.cards = cards;
        this.bid = bid;

        this.totalOccurance = totalOccurance;
    }

    public int[] getTotalOccurance() {
        return totalOccurance;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int[] getCards() {
        return cards;
    }

    public int getBid() {
        return bid;
    }

    public int getHand() {
        return hand;
    }

    public void setHand(int hand) {
        this.hand = hand;
    }

    public boolean isStronger(int handNow, int handBefore, int[] cardsCurrent, int[] cardsBefore) {
        if (handNow > handBefore) {
            return true;
        } else if (handNow < handBefore) {
            return false;
        } else if (handNow == handBefore) {
            for (int i = 0; i < 5; i++) {
                if (cardsCurrent[i] > cardsBefore[i]) {
                    return true;
                } else if (cardsCurrent[i] < cardsBefore[i]) {
                    return false;
                } else if (cardsCurrent[i] == cardsBefore[i]){
                    i += 0;
                }
            }
        }
        return false;
    }

    public int[] jack(int[] currentHand) {
        for (int i = 0; i < 5; i++) {
            if(currentHand[i] == 11){
                currentHand[i] = 1;
            }
        }
        return currentHand;
    }


}