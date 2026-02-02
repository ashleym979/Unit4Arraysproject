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
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        String[] lines = fileData.split("\n");

        for (String line : lines) {
            String[] numbers = line.split(" ");
            int[] values = new int[numbers.length];

            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i].equals("Ace")){
                    numbers[i]="14";
                } else if (numbers[i].equals("King")) {
                    numbers[i]="13";
                } else if (numbers[i].equals("Queen")) {
                    numbers[i]="12";
                } else if (numbers[i].equals("Jack")) {
                    
                }
            }
            }
        }

    }

}