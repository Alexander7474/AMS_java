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
		

	}
}