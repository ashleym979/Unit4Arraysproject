import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String fileData = "";
        try {
            File f = new File("src/data");
            Scanner s = new Scanner(f);

            while (s.hasNextLine()) {
                String line = s.nextLine();
                fileData += line + "\n";
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        String[] lines = fileData.split("\n");

        Hand[] allHands = new Hand[lines.length];

        int fiveOfAKind = 0;
        int fourOfAKind = 0;
        int fullHouse = 0;
        int threeOfAKind = 0;
        int twoPair = 0;
        int onePair = 0;
        int highCard = 0;
        int handCombo = 0;

        //Part 1

        int index = 0;

        for (String line : lines) {
            int[] counter = new int[14];
            String[] bid = line.split("\\|");
            String bidValue = bid[0];
            int actualBidValue = Integer.parseInt(bid[1]);

            String[] numbers = bidValue.split(",");
            int[] values = new int[numbers.length];

            for (int j = 0; j < numbers.length; j++) {
                if (numbers[j].equals("Jack")) {
                    values[j] = 11;
                } else if (numbers[j].equals("Queen")) {
                    values[j] = 12;
                } else if (numbers[j].equals("King")) {
                    values[j] = 13;
                } else if (numbers[j].equals("Ace")) {
                    values[j] = 14;
                } else {
                    values[j] = Integer.parseInt(numbers[j]);
                }
            }

            for (int k = 2; k <= 14; k++) {
                for (int j = 0; j < 5; j++) {
                    if (values[j] == k) {
                        counter[k - 1] += 1;
                    }
                }
            }

            int triplet = 0;
            int two = 0;
            int singles = 0;

            for (int l = 0; l < 14; l++) {
                if (counter[l] == 5) {
                    fiveOfAKind += 1;
                    handCombo = 7;
                } else if (counter[l] == 4) {
                    fourOfAKind += 1;
                    handCombo = 6;
                } else if (counter[l] == 3) {
                    triplet += 1;
                } else if (counter[l] == 2) {
                    two += 1;
                } else if (counter[l] == 1) {
                    singles += 1;
                }
            }


            if (triplet == 1 && two == 1) {
                fullHouse += 1;
                handCombo = 5;
            } else if (triplet == 1) {
                threeOfAKind += 1;
                handCombo = 4;
            } else if (two == 2) {
                twoPair += 1;
                handCombo = 3;
            } else if (two == 1) {
                onePair += 1;
                handCombo = 2;
            } else if (singles == 5) {
                highCard += 1;
                handCombo = 1;
            }

            Hand h = new Hand(values, actualBidValue, handCombo, 0, counter);
            allHands[index] = h;

            index++;
        }

        System.out.println("Number of five of a kind: " + fiveOfAKind);
        System.out.println("Number of full house: " + fullHouse);
        System.out.println("Number of four of a kind: " + fourOfAKind);
        System.out.println("Number of three of a kind: " + threeOfAKind);
        System.out.println("Number of two pair: " + twoPair);
        System.out.println("Number of one pair: " + onePair);
        System.out.println("Number of high card: " + highCard);

        //part 2
        for (int i = 0; i < allHands.length; i++) {
            int numOfweakerHands = 0;
            for (int j = 0; j < allHands.length; j++) {
                int currentHand = allHands[i].getHand();
                int handBefore = allHands[j].getHand();
                int[] currentCards = allHands[i].getCards();
                int[] cardsBefore = allHands[j].getCards();
                boolean stronger = allHands[i].isStronger(currentHand, handBefore, currentCards, cardsBefore);

                if (stronger == true){
                    numOfweakerHands ++;
                }

                allHands[i].setRank(numOfweakerHands);
            }
        }

        int totalBid = 0;
        for (int i = 0; i < allHands.length; i++) {
            int bid = allHands[i].getBid();
            int rank = allHands[i].getRank() + 1;

            int eachBid = bid * rank;
            totalBid = totalBid + eachBid;
        }

        System.out.println("Total Bid Value: " + totalBid);

        //Part 3

        for (int i = 0; i < allHands.length; i++) {
            int[] counter2 = allHands[i].getTotalOccurance();
            int[] currentHand = allHands[i].getCards();
            if (allHands[i].getHand() == 6 || allHands[i].getHand() == 5){
                if (counter2[10] >= 1){
                    allHands[i].setHand(7);
                }
                allHands[i].jack(currentHand);
            } else if (allHands[i].getHand() == 4){
                if (counter2[10] >= 1){
                    allHands[i].setHand(6);
                }
                allHands[i].jack(currentHand);
            } else if (allHands[i].getHand() == 3) {
                if (counter2[10] == 2){
                    allHands[i].setHand(6);
                } else if (counter2[10] == 1) {
                    allHands[i].setHand(5);
                }
                allHands[i].jack(currentHand);
            } else if (allHands[i].getHand() == 2) {
                if (counter2[10] >= 1){
                    allHands[i].setHand(4);
                }
                allHands[i].jack(currentHand);
            } else if (allHands[i].getHand() == 1) {
                if (counter2[10] == 1){
                    allHands[i].setHand(2);
                }
                allHands[i].jack(currentHand);
            }
        }

        for (int i = 0; i < allHands.length; i++) {
            int numOfweakerHands = 0;
            for (int j = 0; j < allHands.length; j++) {
                int currentHand = allHands[i].getHand();
                int handBefore = allHands[j].getHand();
                int[] currentCards = allHands[i].getCards();
                int[] cardsBefore = allHands[j].getCards();
                boolean stronger = allHands[i].isStronger(currentHand, handBefore, currentCards, cardsBefore);

                if (stronger == true){
                    numOfweakerHands ++;
                }

                allHands[i].setRank(numOfweakerHands);
            }
        }

        int totalBidWithJacks = 0;
        for (int i = 0; i < allHands.length; i++) {
            int bid = allHands[i].getBid();
            int rank = allHands[i].getRank() + 1;

            int eachBid = bid * rank;
            totalBidWithJacks = totalBidWithJacks + eachBid;
        }

        System.out.println("Total Bid Value With Jacks Wild: " + totalBidWithJacks);

    }
}