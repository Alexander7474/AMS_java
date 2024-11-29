package partA;

import partA.*;
import partB.*;
import partC.*;

public class Test {
    public static void main(String[] args) {

        Corpus c = new Corpus("src/booksummaries_sample.txt", DataSets.BOOK);

        Bm25 bm25 = new Bm25();

        bm25 = bm25.processCorpus(c);

        String query = "The story is presented by Le Fanu";
        int maxDocsToShow = 3;

        System.out.println("Top " + maxDocsToShow + " Documents:");
        bm25.processQuery(query, maxDocsToShow);
    }
}



