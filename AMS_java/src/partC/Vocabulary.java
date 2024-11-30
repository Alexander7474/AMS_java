package partC;

import java.util.HashMap;
import java.util.HashSet;

import partA.*;

/**
 * @author Alexandre LANTERNIER
 */
public class Vocabulary {
	private static boolean alreadyExist = false;
	private HashMap<Mot, Integer> map;
	private HashSet<Mot> stopList;

	/**
	 * @brief Créer l'unique instance de Vocabulaire
	 * @return
	 */
	public static Vocabulary createVocabulary() {
		if(! alreadyExist) {
			alreadyExist = true;
			return new Vocabulary();
		}else {
			System.err.println("Vocabulary already exist");
			return null;
		}
	}
	
	private Vocabulary() {
		map = new HashMap<Mot, Integer>();
		stopList = new HashSet<Mot>();
	}
	
	/**
	 * @brief rajoute un corpus au vocabulaire
	 * @param c
	 */
	public void addCorpus(Corpus c) {
		Integer cnt = 0;
		for(int i = 0; i < c.size(); i++) {
			for(int y = 0; y < c.get(i).size(); y++) {
				Mot m = c.get(i).get(y);
				if(!map.containsKey(m) && !stopList.contains(m)) { // on verifie que le mot n'est pas déja dans le voc et n'est pas dans la stopList
					map.put(c.get(i).get(y), cnt);
					cnt++;
				}
				
			}
		}
	}
	
	public HashMap<Mot, Integer> getHashMap(){
		return map;
	}
	
	public int getSize() {
		return map.size();
	}
	
	public String toString() {
		String finalStr = "";
		
		for(Mot key : map.keySet()) {
			finalStr += key.getMot() + " : " + map.get(key).toString() + "\n";
		}
		
		return finalStr;
	}
	
	
	public HashSet<Mot> getStopList() {
		return stopList;
	}

	public void addStopList(StopList stopList) {
		this.stopList.addAll((HashSet<Mot>) stopList);
	}
	
}