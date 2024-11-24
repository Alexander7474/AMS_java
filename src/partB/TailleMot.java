package partB;

import partA.Corpus;

public class TailleMot {
	private int size;
	public int calculer(Corpus taille) {
		for(int i=0;i<taille.size();i++) {
			size=size+taille.get(i).size();
		}
		return size;
	}
}
