package test;

import partA.Corpus;
import partA.DataSets;
import partC.*;
import partE.*;
/**
 * @author Yassine EL MSEBLI
 */

/**
 * @brief test l'exception CorpusExc verifiant l existence du ficher et qu il ne soit pas vide

 */
public class Bm25test {
    public static void Bm25test(String string) throws MoteurRechercheExc {

        Corpus c = new Corpus("src/booksummaries_sample.txt", DataSets.BOOK);
        

        Bm25 b = new Bm25();
        
        long start = System.nanoTime();
        b.processCorpus(c);
        long end = System.nanoTime();
        System.out.println("Temps de proces du corpus  :" + ((end - start) * Math.pow(10, -9)));
        

        start = System.nanoTime();
        b.processQuery("covering the period from the Decree of Cyrus to the dedication of the Second Temple", 1);
        b.processQuery("Beginning several months after the events in Blade Runner", 1);
        b.processQuery("Wells grows up in an orphanage where he spends his childhood", 1);
        end = System.nanoTime();
        
        System.out.println("Temps de proces query :" + ((end - start) * Math.pow(10, -9)));
        
        
        System.out.println(b.getVoc().getSize());
    }


}

