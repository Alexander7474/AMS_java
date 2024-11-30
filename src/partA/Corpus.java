package partA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

import partB.Taille;
import partC.EngineVoc;
import partC.StopList;

/**
 * @author Alexandre LANTERNIER
 */
public class Corpus extends Vector<Document> {
	private static final long serialVersionUID = 1L;
	private String title;
	private EngineVoc engine;
	
	/**
	 * @brief renvoie un moteur de recherche de la class donné en param pour chercher dans le corpus
	 * @param engineToUse
	 * @return
	 */
	public EngineVoc getFeatures(EngineVoc engineToUse) {
		if(engine == null) {
			return engineToUse.processCorpus(this);
		}else {
			return engine;
		}
	}
	
	/**
	 * @brief renvoie un moteur de recherche de la class donné en param pour chercher dans le corpus avec une stop list
	 * @param engineToUse
	 * @param stopList
	 * @return
	 */
	public EngineVoc getFeatures(EngineVoc engineToUse, StopList stopList) {
		if(engine == null) {
			return engineToUse.processCorpus(this, stopList);
		}else {
			return engine;
		}
	}

	/**
	 * @brief Constructeur qui créer des document a partir d'un fichier txt pourt constituer le corpus
	 * @param title
	 * @param docType
	 */
	public Corpus(String title, DataSets docType) {
		super();
		this.title = title;
		
		String separator;
		int p1;
		int p2;
		int length;
		
		// en fonction du type de document les données ne sont pas agencé de la même manière
		if(docType == DataSets.WIKIPEDIA) {
			separator = "\\|\\|\\|";
			p1 = 0;
			p2 = 1;
			length = 2;
		}else {
			separator = "\t";
			p1 = 2;
			p2 = 6;
			length = 7;
		}
		
		// on lis le fichier 
		try (BufferedReader br = new BufferedReader(new FileReader(title))) {
            String l;
            while ((l = br.readLine()) != null) {
                
            	// on découpe chaque ligne pour récup le titre et le contenue du résumé
                String[] part = l.split(separator);
                if (part.length == length) {
                	
                    String titre = part[p1].trim();
                    String[] mots = part[p2].trim().split(" ");

                    Document doc = new Document(titre);
                    for (String m : mots) {
                    	if(!m.isEmpty()) {
                    		//on retire les caractère spéciaux a la fin de certain mot
                    		char lastChar = m.charAt(m.length()-1);
                    		if(!Character.isLetter(lastChar)) {
                    			m = m.substring(0, m.length() - 1);
                    		}
                        	doc.putMot(m);
                    	}
                    }

                    this.add(doc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
	}
	
	/**
	 * @brief rajout un document au corpus
	 * @param d
	 */
	public void addDocument(Document d) {
		this.add(d);
	}
	
	public String toString() {
		String finalStr = "Titre Corpus: " + title + "; Document(s) du corpus: ";
		
		for(int i = 0; i<this.size(); i++) {
			finalStr += this.get(i).toString() + " ";
		}
		
		return finalStr;
	}
	
	/**
	 * @brief renvoie la taille du corpus en mot ou en document en fonction de t
	 * @param t
	 * @return
	 */
	public int taille(Taille t) {
		return t.calculer(this);
	}
	
	/**
	 * @brief renvoie le document i
	 * @param i
	 * @return
	 */
	public Document getDoc(int i) {
		return this.get(i);
	}
	
}
