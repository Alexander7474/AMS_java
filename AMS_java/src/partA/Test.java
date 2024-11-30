package partA;

import partA.*;

import partB.*;
import partC.*;
import partE.*;

public class Test {
    public static void main(String[] args) throws MoteurRechercheExc {
    	Corpus c = null ;
    	
    	try {
	        c = new Corpus("src/booksummaries_sample.txt", DataSets.BOOK);
	    } catch (CorpusExc ce) { 
	        System.err.println("caught corpusExc: " + ce.getMessage());
	    }
    	

        
        Bm25 bm25 = new Bm25();
        
        String query = "The story is presented by Le Fanu";
        int maxDocsToShow = 3;

        System.out.println("Top " + maxDocsToShow + " Documents:");
        try {
        	bm25.processQuery(query, maxDocsToShow);
        }catch(Bm25Exc cd){
        	System.err.println("Bm25 Bm25Exc: " + cd.getMessage());
        }
        
    }
}



