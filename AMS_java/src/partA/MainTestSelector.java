package partA;

import partE.*;
import java.util.Scanner;

public class MainTestSelector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Choose the test to run:");
        System.out.println("1. TfIdf Test");
        System.out.println("2. Bm25 Test");
        System.out.println("3. Another Test");
        
        int choice = scanner.nextInt();
        
        try {
            switch (choice) {
                case 1:
                    TfidfExcTest.Tfidftest(" ");
                    break;
                case 2:
                    Bm25ExcTest.Bm25test(" ");
                    break;
                case 3:
                    TestCorpus.Corpustest(" ");
                	
            }
        } catch (MoteurRechercheExc e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
