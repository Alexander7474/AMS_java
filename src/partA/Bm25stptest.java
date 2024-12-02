package partA;

import partB.*;
import partC.*;
import partE.*;

/**
 * @author Yassine EL MSEBLI
 */

/**
 * @brief test l'exception CorpusExc verifiant l existence du ficher et qu il ne soit pas vide

 */
public class Bm25stptest {
	public static void Bm25stptest(String args) throws MoteurRechercheExc {
		Corpus c = new Corpus("src/booksummaries_sample.txt", DataSets.BOOK);
	
		Bm25 b= new Bm25();
		StopList stpL = new StopList("src/stopWords.txt");
		
		long start = System.nanoTime();
		Bm25 Bm25StopList = b.processCorpus(c, stpL);
		long end = System.nanoTime();
		System.out.println("Temps de proces du corpus STOP LIST :" + ((end-start)*(Math.pow(10, -9))));
		
		start = System.nanoTime();
		Bm25StopList.processQuery("covering the period from the Decree of Cyrus to the dedication of the Second Temple", 1);
		Bm25StopList.processQuery("Beginning several months after the events in Blade Runner", 1);
		Bm25StopList.processQuery("Wells grows up in an orphanage where he spends his childhood", 1);
		end = System.nanoTime();
		
		System.out.println("Temps de proces query STOP LIST :" + ((end-start)*(Math.pow(10, -9))));
		System.out.println(Bm25StopList.getVoc().getSize());
	}
}
