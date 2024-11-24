package partC;

import java.util.HashMap;

import partA.*;
import partB.TailleDocument;

public class TfIdf {
	private HashMap<Document, double[]> tf;
	private double[] idf;
	private HashMap<Document, double[]> tfIdf;
	private Vocabulary voc;
	
	private TfIdf(Vocabulary v) {
		if(v == null) {
			this.voc = Vocabulary.createVocabulary();
		}else {
			this.voc = v;
		}
		
		tf = new HashMap<Document, double[]>();
		tfIdf = new HashMap<Document, double[]>();
	}
	
	public TfIdf() {
		this.voc = Vocabulary.createVocabulary();
		
		tf = new HashMap<Document, double[]>();
		tfIdf = new HashMap<Document, double[]>();
	}
	
	private void vocabulaire(Corpus c) {
		this.voc.addCorpus(c);
	}
	
	private void calcIdf(Corpus c) {
		this.idf = new double[voc.getSize()];	
		
		for(Mot m : voc.getHashMap().keySet()) {
			int docMotCnt = 0;
			for(int i = 0; i < c.size(); i++) {
				if(c.getDoc(i).isInDoc(m)) {
					docMotCnt++;
				}
			}
			//System.out.println(Math.log(c.taille(new TailleDocument())/ docMotCnt));
			this.idf[voc.getHashMap().get(m)] = Math.log(c.taille(new TailleDocument())/ docMotCnt);
		}
		
	}
	
	private void calcTf(Corpus c) {
		for(int i = 0; i < c.taille(new TailleDocument()); i++) {
			double[] vec = new double[voc.getHashMap().size()];
			for(Mot m : voc.getHashMap().keySet()) {
				int totalMotDoc = c.getDoc(i).size();
				int cntM = 0;
				for(int j = 0; j < c.get(i).size(); j++) {
					if(c.getDoc(i).getMot(j).equals(m)) {
						cntM++;
					}
				}
				
				vec[voc.getHashMap().get(m)] = (double) cntM/totalMotDoc;
			}
			
			this.tf.put(c.getDoc(i), vec);
		}
	}
	
	private void calcTfIdf() {
		for(Document d : tf.keySet()) {
			double[] tfIdfVec = new double[voc.getSize()];
			for(int i = 0; i < voc.getSize(); i++) {
				tfIdfVec[i] = tf.get(d)[i] * idf[i];
			}
			tfIdf.put(d, tfIdfVec);
		}
	}
	
	public TfIdf processCorpus(Corpus c) {
		TfIdf finalTfIdf = new TfIdf(voc);
		
		finalTfIdf.vocabulaire(c);
		finalTfIdf.calcTf(c);
		finalTfIdf.calcIdf(c);
		finalTfIdf.calcTfIdf();
		
		return finalTfIdf;
	}
	
	public void processQuery(String request, int maxDocToShow) {
		double[] requestVec = features(request);
		double[] requestTfIdfVec = new double[voc.getSize()];
		for(int i = 0; i < voc.getSize(); i++) {
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
	
	private double[] features(String request) {
		//on sépare la requete
		String[] splittedRequest = request.split(" ");
		double[] vec = new double[voc.getSize()];
		
		//on calcule la fréquence de chaque mot dans notre vocabulaire
		for(String w : splittedRequest) {
			Mot m = new Mot(w);
			if(voc.getHashMap().containsKey(m)) {
				vec[voc.getHashMap().get(m)] += 1.0;
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
	
}
