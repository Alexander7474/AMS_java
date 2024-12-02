package test;

import partA.Corpus;
import partA.DataSets;
import partC.*;
import partE.*;

public class TfidfTest {
    public static void TfidfTest(String string) throws MoteurRechercheExc {

        Corpus c = new Corpus("src/booksummaries_sample.txt", DataSets.BOOK);
        

        TfIdf t = new TfIdf();
        t.processCorpus(c);
        long start = System.nanoTime();

        long end = System.nanoTime();
        System.out.println("Temps de proces du corpus  :" + ((end - start) * Math.pow(10, -9)));
        

        start = System.nanoTime();
        t.processQuery("covering the period from the Decree of Cyrus to the dedication of the Second Temple", 1);
        t.processQuery("Beginning several months after the events in Blade Runner", 1);
        t.processQuery("Wells grows up in an orphanage where he spends his childhood", 1);
        end = System.nanoTime();
        
        System.out.println("Temps de proces query :" + ((end - start) * Math.pow(10, -9)));
        
        
        System.out.println(t.getVoc().getSize());
    }
}