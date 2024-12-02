package test;

import partA.*;
import partB.*;
import partC.*;
import partE.MoteurRechercheExc;
import partE.TfidfExc;

/**
 * @author Yassine EL MSEBLI
 */

/**
 * @brief verfie l existence du corpus c et qu il est au moin un document

 */
public class TfidfExcTest {
	public static void Tfidftest(String args) throws MoteurRechercheExc {
		Corpus c= null;
				
		TfIdf t = new TfIdf();
		StopList stpL = new StopList("src/stopWords.txt");
		
		long start = System.nanoTime();
		TfIdf tfStopList=null; 
		try {
			tfStopList= t.processCorpus(c, stpL);
		}catch(TfidfExc tf) {
			throw new TfidfExc("the corpus is empty or do not exist");
		}
		
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