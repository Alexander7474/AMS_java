package test;

import partA.*;
import partB.*;
import partC.*;
import partE.Bm25Exc;
import partE.MoteurRechercheExc;

/**
 * @author Yassine EL MSEBLI
 */

/**
 * @brief test l'exception Bm25Exc verifiant l exstance de la requete

 */

public class Bm25ExcTest {
	public static void Bm25test(String args) throws MoteurRechercheExc {
		Corpus c = new Corpus("src/booksummaries_sample.txt", DataSets.BOOK);
		
		Bm25 B = new Bm25();
		StopList stpL = new StopList("src/stopWords.txt");
		
		long start = System.nanoTime();
		Bm25 Bmstp = B.processCorpus(c, stpL);
		long end = System.nanoTime();
		System.out.println("Temps de proces du corpus STOP LIST :" + ((end-start)*(Math.pow(10, -9))));
		
		
		try {
			Bmstp.processQuery("", 1);
		}catch(Bm25Exc cd){
			throw new Bm25Exc("the request is null or do not exist");
		}
		

	}
}