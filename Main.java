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


        for (int i = 0; i < lines.length; i++) {


            int pairs=0;


            String temp = lines[i];
            String[] part = temp.split("\\|");
            int[] cards = new int[13];
            String raine = part[0];
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
            }else if (four){
                fourKind++;
            }else if (three&&pairs==1){
                fullHouse++;
            }else if (pairs==2){
                twoKind++;
            }else if (three){
                threeKind++;
            }else if (pairs==1){
                oneKind++;
            }else {
                highCard++;
            }



        }


        System.out.println("Number of five of a kind hands: "+fiveKind);
        System.out.println("Number of full house hands: "+fullHouse);
        System.out.println("Number of four of a kind hands: "+fourKind);
        System.out.println("Number of three of a kind hands: "+threeKind);
        System.out.println("Number of two pair hands: "+twoKind);
        System.out.println("Number of one pair hands: "+oneKind);
        System.out.println("Number of high card hands: "+highCard);
    }
}

