import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;




import static java.lang.Integer.parseInt;








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


        int fiveKind = 0;
        int fourKind = 0;
        int fullHouse = 0;
        int threeKind = 0;
        int twoKind = 0;
        int oneKind = 0;
        int highCard = 0;
        String[] rankTypeBidString = new String[lines.length];
        int[] rankTypeBid = new int[lines.length];
        String[] rankTypeString = new String[lines.length];
        int[] rankTypes = new int[lines.length];
        String[] hand = new String[lines.length];


        for (int i = 0; i < lines.length; i++) {


            int pairs = 0;


            String temp = lines[i];
            String[] part = temp.split("\\|");
            int[] cards = new int[13];
            String raine = part[0];
            rankTypeBidString[i] = part[1];
            hand[i] = part[0];
            String[] hailey = raine.split(",");


            int[] handNum = new int[hailey.length];
            for (int j = 0; j < hailey.length; j++) {
                if (hailey[j].equals("Ace")) {
                    hailey[j] = "1";
                } else if (hailey[j].equals("King")) {
                    hailey[j] = "13";
                } else if (hailey[j].equals("Queen")) {
                    hailey[j] = "12";
                } else if (hailey[j].equals("Jack")) {
                    hailey[j] = "11";
                }
                handNum[j] = parseInt(hailey[j]);
            }


            for (int t = 0; t < cards.length; t++) {
                for (int r = 0; r < handNum.length; r++) {
                    if ((t + 1) == handNum[r]) {
                        cards[t]++;
                    }
                }
            }
            boolean five = false;
            boolean four = false;
            boolean three = false;
            for (int s = 0; s < cards.length; s++) {


                if (cards[s] == 2) {
                    pairs++;
                }
                if (cards[s] == 5) {
                    five = true;
                }
                if (cards[s] == 4) {
                    four = true;
                }
                if (cards[s] == 3) {
                    three = true;
                }
            }
            if (five) {
                fiveKind++;
                rankTypeString[i] = "7";
            } else if (four) {
                fourKind++;
                rankTypeString[i] = "6";
            } else if (three && pairs == 1) {
                fullHouse++;
                rankTypeString[i] = "5";
            } else if (pairs == 2) {
                twoKind++;
                rankTypeString[i] = "3";
            } else if (three) {
                threeKind++;
                rankTypeString[i] = "4";
            } else if (pairs == 1) {
                oneKind++;
                rankTypeString[i] = "2";
            } else {
                highCard++;
                rankTypeString[i] = "1";
            }
        }

        for (int m = 0; m < rankTypeString.length; m++) {
            rankTypes[m] = parseInt(rankTypeString[m]);
            rankTypeBid[m] = parseInt(rankTypeBidString[m]);
        }

        int n = lines.length;
        int[] handRanks = new int[n];
        int[][] handValues = new int[n][5];

        for (int i = 0; i < n; i++) {
            String[] cards1 = hand[i].split(",");
            int[] values1 = new int[5];
            for (int k = 0; k < 5; k++) {
                if (cards1[k].equals("Ace")) values1[k] = 14;
                else if (cards1[k].equals("King")) values1[k] = 13;
                else if (cards1[k].equals("Queen")) values1[k] = 12;
                else if (cards1[k].equals("Jack")) values1[k] = 11;
                else values1[k] = parseInt(cards1[k]);
            }

            // Build frequency array
            int[] freq1 = new int[15];
            for (int v : values1) freq1[v]++;

            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                boolean hand2Stronger = false;

                if (rankTypes[j] > rankTypes[i]) hand2Stronger = true;
                else if (rankTypes[j] == rankTypes[i]) {
                    String[] cards2 = hand[j].split(",");
                    int[] values2 = new int[5];
                    for (int k = 0; k < 5; k++) {
                        if (cards2[k].equals("Ace")) values2[k] = 14;
                        else if (cards2[k].equals("King")) values2[k] = 13;
                        else if (cards2[k].equals("Queen")) values2[k] = 12;
                        else if (cards2[k].equals("Jack")) values2[k] = 11;
                        else values2[k] = parseInt(cards2[k]);
                    }

                    int[] freq2 = new int[15];
                    for (int v : values2) freq2[v]++;

                    // Compare hands manually by frequency and then by value
                    outer:
                    for (int count = 5; count >= 1; count--) {
                        for (int val = 14; val >= 2; val--) {
                            if (freq2[val] == count && freq1[val] < count) {
                                hand2Stronger = true;
                                break outer;
                            } else if (freq1[val] == count && freq2[val] < count) {
                                hand2Stronger = false;
                                break outer;
                            }
                        }
                    }
                }
                if (hand2Stronger) handRanks[i]++;
            }
        }




        int totalValue = 0;
        for (int i = 0; i < n; i++) totalValue += handRanks[i] * rankTypeBid[i];


        System.out.println("Number of five of a kind hands: " + fiveKind);
        System.out.println("Number of full house hands: " + fullHouse);
        System.out.println("Number of four of a kind hands: " + fourKind);
        System.out.println("Number of three of a kind hands: " + threeKind);
        System.out.println("Number of two pair hands: " + twoKind);
        System.out.println("Number of one pair hands: " + oneKind);
        System.out.println("Number of high card hands: " + highCard);
        System.out.println("Total Bid Value: " + totalValue);
    }
}
