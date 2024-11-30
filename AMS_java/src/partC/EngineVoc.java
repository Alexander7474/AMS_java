package partC;

import partA.Corpus;
import partE.MoteurRechercheExc;
import partE.TfidfExc;

public abstract class EngineVoc {
	private Vocabulary voc;
		
	public abstract EngineVoc processCorpus(Corpus c) throws MoteurRechercheExc;
	public abstract void processQuery(String request, int maxDocToShow) throws MoteurRechercheExc;
	
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
	
	public void vocabulaireWithStopList(Corpus c, StopList stopList) {
		this.voc.addStopList(stopList);
		this.voc.addCorpus(c);
	}
}