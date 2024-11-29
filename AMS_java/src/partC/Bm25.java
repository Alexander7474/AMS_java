package partC;

import java.util.HashMap;
import partA.*;
import partB.*;

public class Bm25 {
    private HashMap<Document, double[]> tf;          
    private double[] idf;                            
    private HashMap<Document, Double> bm25Scores;    
    private Vocabulary voc;                          
    private double avgDL;                            
    private double k1 = 1.2;                         
    private double b = 0.75;                         


    public Bm25(Vocabulary v) {
        this.voc = (v != null) ? v : Vocabulary.createVocabulary();
        this.tf = new HashMap<>();
        this.bm25Scores = new HashMap<>();
    }

 
    public Bm25() {
		this.voc = Vocabulary.createVocabulary();
		
		tf = new HashMap<Document, double[]>();
		bm25Scores = new HashMap<Document, Double>();
    }

 
    private void vocabulaire(Corpus c) {
        this.voc.addCorpus(c);
        this.avgDL = (double) c.taille(new TailleMot()) / c.taille(new TailleDocument());
    }

    /
    private void calcIdf(Corpus c) {
        this.idf = new double[voc.getSize()];
        for (Mot m : voc.getHashMap().keySet()) {
            int docMotCnt = 0;
            for (int i = 0; i < c.size(); i++) {
                if (c.getDoc(i).isInDoc(m)) {
                    docMotCnt++;
                }
            }
            this.idf[voc.getHashMap().get(m)] = Math.log((c.taille(new TailleDocument()) - docMotCnt + 0.5) / (docMotCnt + 0.5) + 1);
        }
    }

    
    private void calcTf(Corpus c) {
        for (int i = 0; i < c.taille(new TailleDocument()); i++) {
            double[] vec = new double[voc.getSize()];
            int docLength = c.getDoc(i).size();
            for (Mot m : voc.getHashMap().keySet()) {
                int cntM = 0;
                for (int j = 0; j < c.get(i).size(); j++) {
                    if (c.getDoc(i).getMot(j).equals(m)) {
                        cntM++;
                    }
                }
                vec[voc.getHashMap().get(m)] = (double) cntM / docLength; 
            }
            this.tf.put(c.getDoc(i), vec);
        }
    }

    
    public Bm25 processCorpus(Corpus c) {
        Bm25 finalBm25 = new Bm25(voc);
        finalBm25.vocabulaire(c); 
        finalBm25.calcTf(c);      
        finalBm25.calcIdf(c);     
        return finalBm25;
    }

   
    private void calcBm25Scores(String request) {
        double[] requestVec = features(request); 
        this.bm25Scores.clear();

        for (Document d : tf.keySet()) {
            double score = 0.0;
            double[] tfVec = tf.get(d);

            for (int i = 0; i < voc.getSize(); i++) {
                if (requestVec[i] > 0) {
                    double f = tfVec[i];
                    score += idf[i] * ((f * (k1 + 1)) / (f + k1 * (1 - b + b * (d.size() / avgDL))));
                }
            }

            bm25Scores.put(d, score);
        }
    }

    
    public void processQuery(String request, int maxDocToShow) {
        calcBm25Scores(request);

        System.out.println("Documents triés par pertinence (BM25) :\n");
        for (int i = 1; i <= maxDocToShow; i++) {
            double maxScore = -1;
            Document docToShow = null;

            for (Document d : bm25Scores.keySet()) {
                if (bm25Scores.get(d) > maxScore) {
                    maxScore = bm25Scores.get(d);
                    docToShow = d;
                }
            }

            if (docToShow != null) {
                System.out.println("Document " + i + ":\n");
                System.out.println(docToShow);
                System.out.println("\n");
                bm25Scores.remove(docToShow);
            }
        }
    }

    /
    private double[] features(String request) {
        String[] splittedRequest = request.split(" ");
        double[] vec = new double[voc.getSize()];

        for (String w : splittedRequest) {
            Mot m = new Mot(w);
            if (voc.getHashMap().containsKey(m)) {
                vec[voc.getHashMap().get(m)] += 1.0;
            }
        }

        for (int i = 0; i < vec.length; i++) {
            if (vec[i] > 0.0) {
                vec[i] /= splittedRequest.length;
            }
        }

        return vec;
    }

    
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Document d : bm25Scores.keySet()) {
            result.append(d.toString()).append(" : ").append(bm25Scores.get(d)).append("\n");
        }
        return result.toString();
    }
}


