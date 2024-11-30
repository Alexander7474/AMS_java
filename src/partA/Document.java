package partA;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * @brief Class Document 
 * @author Alexandre LANTERNIER
 */
public class Document extends ArrayList<Mot>{
	private static final long serialVersionUID = 1L;
	private String title;
	
	public Document(String title) {
		super();
		this.title = title;
	}
	
	/**
	 * @brief Rajoute un mot dans le Doc
	 * @param m
	 */
	public void putMot(Mot m) {
		this.add(m);
	}
	
	
	/**
	 * @brief rajout un mot dans le doc à partir d'une String
	 * @param m
	 */
	public void putMot(String m) {
		Mot newMot = new Mot(m);
		this.add(newMot);
	}
	
	public String toString() {
		String finalStr = title + " |||";
		
		for(int i = 0; i < this.size(); i++) {
			finalStr += " " + this.get(i).getMot();
		}
		
		finalStr += "\n";
		
		return finalStr;
	}
	
	/**
	 * @brief renvoie le mot i
	 * @param i
	 * @return
	 */
	public Mot getMot(int i) {
		return this.get(i);
	}
	
	/**
	 * @brief vérifie si un mot est dans le document
	 * @param m
	 * @return
	 */
	public boolean isInDoc(Mot m) {
		for(int i = 0; i < this.size(); i++) {
			if(m.equals(this.getMot(i))) {
				return true;
			}
		}
		return false;
	}
}
