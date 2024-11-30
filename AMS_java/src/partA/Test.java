package partA;

import partA.*;
import partB.*;
import partC.*;
import partE.CorpusExc;

public class Test {
    public static void main(String[] args) throws CorpusExc {

        Corpus c = new Corpus("src/booksummaries_sample.txt", DataSets.BOOK);

        Bm25 bm25 = new Bm25();
        
        StopList stpL = new StopList("src/stopWords.txt");

        Bm25 bmstp=bm25.processCorpusWithStopList(c, stpL);
        String query = "The story is presented by Le Fanu";
        int maxDocsToShow = 3;

        System.out.println("Top " + maxDocsToShow + " Documents:");
        bm25.processQuery(query, maxDocsToShow);
    }
}



