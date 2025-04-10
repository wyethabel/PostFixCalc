/*
    The main runner class for my PostFix Calculator.
    Contains a selection fo test notations and a process
    for reading from a local file.
 */

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyPostFixCalc MyPostFixCalc = new MyPostFixCalc();

        String[] testNotations = {
                "2 4 +",
                "3 7 * 5 + 3 %",
                "3 75 * 652 +",
                "5 0 /",  // Zero division handled with infinity return.
                "",  // Bad formatting example.
                "5 8 9 +"  // Bad formatting example.
        };

        // Local test process.
        System.out.println("Main test");
        for (String i : testNotations) {
            try {
                double output = MyPostFixCalc.postFixProcessing(i);
                System.out.printf("Notation: %s Result: %.2f%n", i, output);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // File test process.
        // [CRITICAL]: If files are provided separately, please repackage in the src folder.
        System.out.println("---------------------------");
        System.out.println("File test");
        try (Scanner input = new Scanner(new File("src/NotationExamples.txt"))) {
            while (input.hasNextLine()) {
                String currentNotation = input.nextLine();
                try {
                    double output = MyPostFixCalc.postFixProcessing(currentNotation);
                    System.out.printf("Notation: %s Result: %.2f%n", currentNotation, output);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("This file is not available.");
        }
    }
}