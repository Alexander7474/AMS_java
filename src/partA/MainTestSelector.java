package partA;

import partE.*;
import java.util.Scanner;

/**
 * @author Yassine EL MSEBLI
 */

/**
 * @brief permet de tester les deux type de recherche , et les exctions possible

 */
public class MainTestSelector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Choose the test to run:");
        System.out.println("1. TfIdfExc Test");
        System.out.println("2. Bm25Exc Test");
        System.out.println("3. CorpusExc Test");
        System.out.println("4  Tfdif Test");
        System.out.println("5  Bm25 Test");
        System.out.println("6  Bm25stoplis test");
        System.out.println("7  TfidfStoplist");

        
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
                case 4:
                	TfidfTest.TfidfTest(" ");
                case 5:
                	Bm25test.Bm25test(" ");
                case 6:
                	Bm25stptest.Bm25stptest(" ");
                case 7:
                	Tfidfstptest.Tfidfstptest(" ");
            }
        } catch (MoteurRechercheExc e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
