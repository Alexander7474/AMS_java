package partC;

import java.util.HashMap;

import partA.*;
import partB.TailleDocument;
import partE.MoteurRechercheExc;
import partE.TfidfExc;

/**
 * @author Alexandre LANTERNIER
 */
public class TfIdf extends EngineVoc{
	private HashMap<Document, double[]> tf;
	private double[] idf;
	private HashMap<Document, double[]> tfIdf;
	
	/**
	 * @brief Créer Tfidf avec un vocabulaire
	 * @param v
	 */
	private TfIdf(Vocabulary v) {
		super(v);
		
		tf = new HashMap<Document, double[]>();
		tfIdf = new HashMap<Document, double[]>();
	}
	
	/**
	 * @brief Créer Tfidf sans un vocabulaire
	 */
	public TfIdf() {
		super();
		
		tf = new HashMap<Document, double[]>();
		tfIdf = new HashMap<Document, double[]>();
	}
	
	/**
	 * @brief calcule le poid idf des mots dans le vocabulaire 
	 * @param c
	 */
	private void calcIdf(Corpus c) {
		this.idf = new double[getVoc().getSize()];	
		
		for(Mot m : getVoc().getHashMap().keySet()) {
			int docMotCnt = 0;
			for(int i = 0; i < c.size(); i++) {
				if(c.getDoc(i).isInDoc(m)) {
					docMotCnt++;
				}
			}
			//System.out.println(Math.log(c.taille(new TailleDocument())/ docMotCnt));
			this.idf[getVoc().getHashMap().get(m)] = Math.log(c.taille(new TailleDocument())/ docMotCnt);
		}
		
	}
	
	/**
	 * @brief calcule le poid tf des mots de chaque document
	 * @param c
	 */
	private void calcTf(Corpus c) {
		for(int i = 0; i < c.taille(new TailleDocument()); i++) {
			double[] vec = new double[getVoc().getHashMap().size()];
			for(Mot m : getVoc().getHashMap().keySet()) {
				int totalMotDoc = c.getDoc(i).size();
				int cntM = 0;
				for(int j = 0; j < c.get(i).size(); j++) {
					if(c.getDoc(i).getMot(j).equals(m)) {
						cntM++;
					}
				}
				
				vec[getVoc().getHashMap().get(m)] = (double) cntM/totalMotDoc;
			}
			
			this.tf.put(c.getDoc(i), vec);
		}
	}
	
	/**
	 * @brief calcule le poid tfidf de chaque mots
	 */
	private void calcTfIdf() {
		for(Document d : tf.keySet()) {
			double[] tfIdfVec = new double[getVoc().getSize()];
			for(int i = 0; i < getVoc().getSize(); i++) {
				tfIdfVec[i] = tf.get(d)[i] * idf[i];
			}
			tfIdf.put(d, tfIdfVec);
		}
	}
	
	/**
	 * @throws MoteurRechercheExc 
	 * @brief Créé une instance de TfIdf avec un corpus
	 */
	public TfIdf processCorpus(Corpus c) throws MoteurRechercheExc {
		TfIdf finalTfIdf = new TfIdf(getVoc());
		if(c==null || c.taille(new TailleDocument())==0) {
			throw new TfidfExc("the corpus do not exist or is empty");
		}
		finalTfIdf.vocabulaire(c);
		finalTfIdf.calcTf(c);
		finalTfIdf.calcIdf(c);
		finalTfIdf.calcTfIdf();
		
		return finalTfIdf;
	}
	
	/**
	 * @brief Détermine les document les plus pertinent pour contenir les mots de la requête
	 * @param request
	 * @param maxDocToShow
	 * @return
	 */
	public void processQuery(String request, int maxDocToShow) {
		double[] requestVec = features(request);
		double[] requestTfIdfVec = new double[getVoc().getSize()];
		for(int i = 0; i < getVoc().getSize(); i++) {
			requestTfIdfVec[i] = requestVec[i] * idf[i];
			//if(requestTfIdfVec[i] != 0.0) System.out.println(requestTfIdfVec[i]);
		}
		
		HashMap<Document, Double> scoresDocs = new HashMap<Document, Double>();
		
		for(Document d : tf.keySet()) {
			double produitScalaire = 0.0;
			double normeR = 0.0;
			double normeD = 0.0;
			for(int i = 0; i<requestTfIdfVec.length; i++) {
				produitScalaire += requestTfIdfVec[i] * tfIdf.get(d)[i];
				normeR += requestTfIdfVec[i];
				normeD += tfIdf.get(d)[i];
			}
			
			normeR = Math.sqrt(normeR);
			normeD = Math.sqrt(normeD);
			
			double finalScore = produitScalaire / (normeD * normeR);
			
			scoresDocs.put(d, finalScore);
			
		}
		
		System.out.println("Document trié par pertinence :\n");
		for(int i = 1; i <= maxDocToShow; i++) {
			double maxScore = 0.0;
			Document docToShow = null;
			
			for(Document d : scoresDocs.keySet()) {
				if(scoresDocs.get(d) > maxScore) {
					maxScore = scoresDocs.get(d);
					docToShow = d;
				}
				
			}
			
			System.out.println("Document " + i + ":\n");
			System.out.println(docToShow);
			System.out.println("\n");
			scoresDocs.remove(docToShow);
		}
		
	}
	
	/**
	 * @brief détermien le poid tf de chaque mot de la requête
	 * @param request
	 * @return
	 */
	private double[] features(String request) {
		//on sépare la requete
		String[] splittedRequest = request.split(" ");
		double[] vec = new double[getVoc().getSize()];
		
		//on calcule la fréquence de chaque mot dans notre getVoc()abulaire
		for(String w : splittedRequest) {
			Mot m = new Mot(w);
			if(getVoc().getHashMap().containsKey(m)) {
				vec[getVoc().getHashMap().get(m)] += 1.0;
			}
		}
		
		for(int i = 0; i < vec.length; i++) {
			if(vec[i] > 0.0) {
				vec[i] /= splittedRequest.length;
			}
			
		}

		return vec;
	}
	
	public String toString() {
		String finalStr = "x : " + idf[1];
		
		return finalStr;
	}
	
	/**
	 * @brief Créé une instance de TfIdf avec un corpus et une stoplist
	 * @param c
	 * @param stopList
	 * @return
	 * @throws MoteurRechercheExc 
	 */
	public TfIdf processCorpus(Corpus c,StopList stopList) throws MoteurRechercheExc {
		TfIdf finalTfIdf = new TfIdf(getVoc());
		if(c==null || c.taille(new TailleDocument())==0) {
			throw new TfidfExc("the corpus do not exist or is empty");
		}
		finalTfIdf.vocabulaire(c, stopList);
		finalTfIdf.calcTf(c);
		finalTfIdf.calcIdf(c);
		finalTfIdf.calcTfIdf();
		
		return finalTfIdf;
	}
	
}

