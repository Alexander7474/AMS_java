package partC;

import java.util.HashMap;

import partA.*;

public class TfIdf {
	private HashMap<Document, double[]> map;
	private double[] dimension;
	private Vocabulary voc;
	
	private TfIdf(Vocabulary v) {
		if(v == null) {
			this.voc = Vocabulary.createVocabulary();
		}else {
			this.voc = v;
		}
		
		map = new HashMap<Document, double[]>();
	}
	
	public TfIdf() {
		this.voc = Vocabulary.createVocabulary();
		
		map = new HashMap<Document, double[]>();
	}
	
	private void vocabulaire(Corpus c) {
		voc.addCorpus(c);
	}
	
	private void calcIdf() {
		
	}
	
	private void calcTf() {
		
	}
	
	public TfIdf processQuery(Corpus c) {
		TfIdf tf = new TfIdf(voc);
		
		tf.vocabulaire(c);
		tf.calcIdf();
		tf.calcTf();
		
		return tf;
	}
	
	public String toString() {
		String finalStr = voc.toString();
		
		return finalStr;
	}
	
}
