package test;

import partA.*;
import partB.*;
import partC.*;
import partE.CorpusExc;
import partE.MoteurRechercheExc;

/**
 * @author Yassine EL MSEBLI
 */

/**
 * @brief test l'exception CorpusExc verifiant l existence du ficher et qu il ne soit pas vide

 */
public class TestCorpus {
	public static void Corpustest(String args) throws MoteurRechercheExc {
		Corpus c = null ;
		try {
			c=new Corpus("src/empty.txt", DataSets.BOOK);
		}catch(CorpusExc cd) {
			throw new CorpusExc ("the file do not exist or is empty");
		}
				
	}
}
