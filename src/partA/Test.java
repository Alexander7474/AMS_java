package partA;

import partA.*;
import partB.*;
import partC.*;

public class Test {
	public static void main(String[] args) {
		Corpus c = new Corpus("src/booksummaries_sample.txt", DataSets.BOOK);
		TfIdf t = new TfIdf();
		
		long start = System.nanoTime();
		TfIdf tfNoStopList = t.processCorpus(c);
		long end = System.nanoTime();
		System.out.println("Temps de proces du corpus SANS STOP LIST :" + ((end-start)*(Math.pow(10, -9))));
		
		start = System.nanoTime();
		StopList stpL = new StopList("src/stopWords.txt");
		TfIdf tfStopList = t.processCorpusWithStopList(c,stpL);
		end = System.nanoTime();
		
		System.out.println("Temps de proces du corpus AVEC STOP LIST :" + ((end-start)*(Math.pow(10, -9))));
		
		start = System.nanoTime();
		tfStopList.processQuery("covering the period from the Decree of Cyrus to the dedication of the Second Temple", 1);
		end = System.nanoTime();
		
		System.out.println("Temps de proces query SANS STOP LIST :" + ((end-start)*(Math.pow(10, -9))));
		
		start = System.nanoTime();
		tfStopList.processQuery("covering the period from the Decree of Cyrus to the dedication of the Second Temple", 1);
		end = System.nanoTime();
		
		System.out.println("Temps de proces query AVEC STOP LIST :" + ((end-start)*(Math.pow(10, -9))));
	}
}
