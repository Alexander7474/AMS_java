package partC;

import java.util.HashMap;

import partA.*;
import partB.TailleDocument;

public class TfIdf {
	private HashMap<Document, double[]> tf;
	private double[] idf;
	private Vocabulary voc;
	
	private TfIdf(Vocabulary v) {
		if(v == null) {
			this.voc = Vocabulary.createVocabulary();
		}else {
			this.voc = v;
		}
		
		tf = new HashMap<Document, double[]>();
	}
	
	public TfIdf() {
		this.voc = Vocabulary.createVocabulary();
		
		tf = new HashMap<Document, double[]>();
	}
	
	private void vocabulaire(Corpus c) {
		this.voc.addCorpus(c);
	}
	
	private void calcIdf(Corpus c) {
		this.idf = new double[voc.getHashMap().size()];	
		
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
	
	public TfIdf processCorpus(Corpus c) {
		TfIdf tf = new TfIdf(voc);
		
		tf.vocabulaire(c);
		tf.calcTf(c);
		tf.calcIdf(c);
		
		return tf;
	}
	
	public void processQuery(String request, int maxDoxToShow) {
		
	}
	
	private double[] features(String request) {
		String[] splittedRequest = request.split("");
		double[] vec = new double[splittedRequest.length];
		
		for(String m : splittedRequest) {
			
		}
		
		return vec;
	}
	
	public String toString() {
		String finalStr = "x : " + idf[1];
		
		return finalStr;
	}
	
}
