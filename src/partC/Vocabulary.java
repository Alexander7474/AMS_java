package partC;

import java.util.HashMap;
import java.util.HashSet;

import partA.*;

public class Vocabulary {
	private static boolean alreadyExist = false;
	private HashMap<Mot, Integer> map;
	private HashSet<Mot> set;
	
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
		set = new HashSet<Mot>();
	}
	
	public void addCorpus(Corpus c) {
		Integer cnt = 0;
		for(int i = 0; i < c.size(); i++) {
			for(int y = 0; y < c.get(i).size(); y++) {
				map.put(c.get(i).get(y), cnt);
				cnt++;
			}
		}
	}
	
	public String toString() {
		String finalStr = "";
		
		for(Mot key : map.keySet()) {
			finalStr += key.getMot() + " : " + map.get(key).toString() + "\n";
		}
		
		return finalStr;
	}
	
}
