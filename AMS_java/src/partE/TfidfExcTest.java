package partE;

import partA.*;
import partB.*;
import partC.*;
import partE.*;

public class TfidfExcTest {
    public static void main(String[] args) throws MoteurRechercheExc {
        Corpus c = new Corpus("src/booksummaries_sample.txt", DataSets.BOOK);

        TfIdf tfidf = new TfIdf();

        String query = "Children of Dune";
        int maxDocsToShow = 3;
        tfidf.processQuery(query, maxDocsToShow);
        
        System.out.println("Top " + maxDocsToShow + " Documents:");
        try {
        	tfidf.processCorpus(c);
        } catch (TfidfExc cd) {
            System.err.println("TfidfExc: " + cd.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

