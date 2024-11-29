package partC;

import partA.Corpus;

public abstract class EngineVoc {
	private Vocabulary voc;
	
	public abstract EngineVoc processCorpus(Corpus c);
	public abstract void processQuery(String request, int maxDocToShow);
	
	public EngineVoc(Vocabulary v) {
		super();
		if(v == null) {
			this.voc = Vocabulary.createVocabulary();
		}else {
			this.voc = v;
		}
	}
	
	public EngineVoc() {
		super();
		this.voc = Vocabulary.createVocabulary();
	}
	
	public Vocabulary getVoc() {
		return voc;
	}
	
	public void vocabulaire(Corpus c) {
		this.voc.addCorpus(c);
	}
}