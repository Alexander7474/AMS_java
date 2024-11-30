package partC;

import partA.Corpus;

/**
 * @brief superclass de bm25 et TfIdf
 * @author Alexandre LANTERNIER
 */
public abstract class EngineVoc {
	private Vocabulary voc;
		
	public abstract EngineVoc processCorpus(Corpus c);
	public abstract EngineVoc processCorpus(Corpus c,StopList stopList);
	public abstract void processQuery(String request, int maxDocToShow);
	
	/**
	 * @brief Créé le moteur avec un vocabulaire déjà existant
	 * @param v
	 */
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
	
	/**
	 * @brief Ajoute un corpus au vocabulaire
	 * @param c
	 */
	public void vocabulaire(Corpus c) {
		this.voc.addCorpus(c);
	}
	
	/**
	 * @brief Ajoute un corpus au vocabulaire avec une stopList
	 * @param c
	 * @param stopList
	 */
	public void vocabulaire(Corpus c, StopList stopList) {
		this.voc.addStopList(stopList);
		this.voc.addCorpus(c);
	}
}