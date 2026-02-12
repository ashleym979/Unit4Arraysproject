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


        int fiveKind=0;
        int fourKind=0;
        int fullHouse=0;
        int threeKind=0;
        int twoKind=0;
        int oneKind=0;
        int highCard=0;
        String[] rankTypeBid= new String[lines.length];
        String[] rankTypeString= new String[lines.length];
        int[] rankTypes= new int[lines.length];
        String[] hand= new String[lines.length];


        for (int i = 0; i < lines.length; i++) {


            int pairs=0;


            String temp = lines[i];
            String[] part = temp.split("\\|");
            int[] cards = new int[13];
            String raine = part[0];
            rankTypeBid[i]=part[1];
            hand[i]=part[0];
            String[] hailey= raine.split(",");


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
                    if ((t+1)==handNum[r]){
                        cards[t]++;
                    }
                }
            }
            boolean five= false;
            boolean four= false;
            boolean three=false;
            for (int s = 0; s < cards.length; s++) {

                if (cards[s] == 2) {
                    pairs++;
                }
                if (cards[s]==5){
                    five=true;
                }
                if (cards[s]==4){
                    four=true;
                }
                if (cards[s]==3){
                    three=true;
                }
            }
            if (five){
                fiveKind++;
                rankTypeString[i]="7";
            }else if (four){
                fourKind++;
                rankTypeString[i]="5";
            }else if (three&&pairs==1){
                fullHouse++;
                rankTypeString[i]="6";
            }else if (pairs==2){
                twoKind++;
                rankTypeString[i]="3";
            }else if (three){
                threeKind++;
                rankTypeString[i]="4";
            }else if (pairs==1){
                oneKind++;
                rankTypeString[i]="2";
            }else {
                highCard++;
                rankTypeString[i]="1";
            }
        }

        System.out.println(Arrays.toString(rankTypeString));

        for (int m = 0; m < rankTypeString.length; m++) {
            rankTypes[m]= parseInt(rankTypeString[m]);
        }

        for (int l = 0; l < rankTypeString.length; l++) {
            int temp=0;
            if (l<rankTypeString.length-1){
                if (rankTypes[l]>rankTypes[l+1]){
                    temp= rankTypes[l+1];
                    rankTypes[l+1]= rankTypes[l];
                    rankTypes[l]=temp;
                    System.out.println(temp);
                }
            }
        }
        System.out.println(Arrays.toString(rankTypes));

        System.out.println("Number of five of a kind hands: "+fiveKind);
        System.out.println("Number of full house hands: "+fullHouse);
        System.out.println("Number of four of a kind hands: "+fourKind);
        System.out.println("Number of three of a kind hands: "+threeKind);
        System.out.println("Number of two pair hands: "+twoKind);
        System.out.println("Number of one pair hands: "+oneKind);
        System.out.println("Number of high card hands: "+highCard);
    }
}
