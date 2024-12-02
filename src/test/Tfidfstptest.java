package test;

import partA.Corpus;
import partA.DataSets;
import partB.*;
import partC.*;
import partE.*;

/**
 * @author Yassine EL MSEBLI
 */

/**
 * @brief test l'exception CorpusExc verifiant l existence du ficher et qu il ne soit pas vide

 */
public class Tfidfstptest {
	public static void Tfidfstptest(String args) throws MoteurRechercheExc {
		Corpus c = new Corpus("src/booksummaries_sample.txt", DataSets.BOOK);
	
		TfIdf t = new TfIdf();
		StopList stpL = new StopList("src/stopWords.txt");
		
		long start = System.nanoTime();
		TfIdf tfStopList = t.processCorpus(c, stpL);
		long end = System.nanoTime();
		System.out.println("Temps de proces du corpus STOP LIST :" + ((end-start)*(Math.pow(10, -9))));
		
		start = System.nanoTime();
		tfStopList.processQuery("covering the period from the Decree of Cyrus to the dedication of the Second Temple", 1);
		tfStopList.processQuery("Beginning several months after the events in Blade Runner", 1);
		tfStopList.processQuery("Wells grows up in an orphanage where he spends his childhood", 1);
		end = System.nanoTime();
		
		System.out.println("Temps de proces query STOP LIST :" + ((end-start)*(Math.pow(10, -9))));
		System.out.println(tfStopList.getVoc().getSize());
	}
}
