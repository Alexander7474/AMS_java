package partC;

import java.util.HashMap;
import partA.*;
import partB.*;
import partE.Bm25Exc;
import partE.MoteurRechercheExc;

/**
 * @author Yassine EL MSEBLI
 */

public class Bm25 extends EngineVoc {
    private HashMap<Document, double[]> tf;      	// Term Frequencie : permet  davoir la frequence de chaque terme du vocabulaire pour whque document
    private double[] idfBm25;                            //Inverse Document Frequency : pertinence d'un document pour un term (permet le calcul du score Bm25)
    private HashMap<Document, Double> bm25Scores;                    //score Bm25 pour chaque document du corpus en rapport avec le terme
    private double avgDL;                            //la taille moyenne des document
    private double k1 = 1.2;                         
    private double b = 0.75;                         

	/**
	 * @brief initialse le vocabulaire avec celui fournie et initialise les hashmapp
	 * @param v
	 * @return 
	 */
    public Bm25(Vocabulary v) {
        super(v);
        this.tf = new HashMap<>();
        this.bm25Scores = new HashMap<>();
    }

    //constructeur par defaut initialise chaque elemenet 
    public Bm25() {
		super();
		
		tf = new HashMap<Document, double[]>();
		bm25Scores = new HashMap<Document, Double>();
    }

	/**
	 * @brief calcul les idf (inverse document frequencie ) pour chaque document du corpus c et les ajoute dans idfbm25
	 * @param c
	 * @return 
	 */
    private void calcIdfBm25(Corpus c) {
        this.idfBm25 = new double[getVoc().getSize()];
        for (Mot m : getVoc().getHashMap().keySet()) {
            int docMotCnt = 0;
            for (int i = 0; i < c.size(); i++) {
                if (c.getDoc(i).isInDoc(m)) {
                    docMotCnt++;
                }
            }
            this.idfBm25[getVoc().getHashMap().get(m)] = Math.log((c.taille(new TailleDocument()) - docMotCnt + 0.5) / (docMotCnt + 0.5) + 1);
        }
    }
    
	/**
	 * @brief calcul de tf(term frequencie de chaque terme du vocabulaire pour chaque document du corpus c
	 * @param c
	 * @return 
	 */
    
    private void calcTfBm25(Corpus c) {
    	this.avgDL = (double) c.taille(new TailleMot()) / c.taille(new TailleDocument());
        for (int i = 0; i < c.taille(new TailleDocument()); i++) {
            double[] vec = new double[getVoc().getSize()];
            int docLength = c.getDoc(i).size();
            for (Mot m : getVoc().getHashMap().keySet()) {
                int cntM = 0;
                for (int j = 0; j < c.get(i).size(); j++) {
                    if (c.getDoc(i).getMot(j).equals(m)) {
                        cntM++;
                    }
                }
                vec[getVoc().getHashMap().get(m)] = (double) cntM / docLength; 
            }
            this.tf.put(c.getDoc(i), vec);
        }
    }
    
	/**
	 * @brief initialisation a partir d un corpu(ajout des terme dans le voc , calcul de tf et idf)
	 * @param c
	 * @return Bm25
	 */
    
    public Bm25 processCorpus(Corpus c) {
        Bm25 finalBm25 = new Bm25(getVoc());
        
        finalBm25.vocabulaire(c);
        finalBm25.calcTfBm25(c);      
        finalBm25.calcIdfBm25(c);     
        return finalBm25;
    }
  
	/**
	 * @brief calcul du score de chaque document pour la requete donne , organise donc les document en fonction de leur importance 
	 * @param request
	 * @return 
	 */
  
    private void calcBm25Scores(String request) {
        double[] requestVec = features(request); 
        this.bm25Scores.clear();

        for (Document d : tf.keySet()) {
            double score = 0.0;
            double[] tfVec = tf.get(d);

            for (int i = 0; i < getVoc().getSize(); i++) {
                if (requestVec[i] > 0) {
                    double f = tfVec[i];
                    score += idfBm25[i] * ((f * (k1 + 1)) / (f + k1 * (1 - b + b * (d.size() / avgDL))));
                }
            }

            bm25Scores.put(d, score);
        }
    }
    
	/**
	 * @brief recupere une requete et affiche les documents par ordre d importance 
	 * @param request , maxdoctoshow
	 * @return 
	 */

    public void processQuery(String request, int maxDocToShow) throws MoteurRechercheExc {
    	if(request==null || request=="") {
    		throw new Bm25Exc("the request do not exist");
    	}
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
    
    /**
	 * @brief // decoupe le string en plusieur objet mot pour pouvoir les rechercher
	 * @param request
	 * @return double[]
	 */
   
    private double[] features(String request) {
        String[] splittedRequest = request.split(" ");
        double[] vec = new double[getVoc().getSize()];

        for (String w : splittedRequest) {
            Mot m = new Mot(w);
            if (getVoc().getHashMap().containsKey(m)) {
                vec[getVoc().getHashMap().get(m)] += 1.0;
            }
        }

        for (int i = 0; i < vec.length; i++) {
            if (vec[i] > 0.0) {
                vec[i] /= splittedRequest.length;
            }
        }

        return vec;
    }
    
    /**
	 * @brief converti le document en un seul string avant de le renvoyer 
	 * @param 
	 * @return String 
	 */
   
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Document d : bm25Scores.keySet()) {
            result.append(d.toString()).append(" : ").append(bm25Scores.get(d)).append("\n");
        }
        return result.toString();
    }
    
    /**
	 * @brief meme chose que le processcorpus precedent mais celui ci utilise un vocabuaire plus court 
	 * @param c , stoplist
	 * @return Bm25
	 */
	@Override
	public Bm25 processCorpus(Corpus c, StopList stopList) {
        Bm25 finalBm25 = new Bm25(getVoc());
        
        finalBm25.vocabulaire(c);
        finalBm25.calcTfBm25(c);      
        finalBm25.calcIdfBm25(c);     
        return finalBm25;
	}
}
