package partB;

import partA.Corpus;

/**
 * @author Yassine EL-MSEBLI
 */
public class TailleMot extends Taille{
	
	public int calculer(Corpus taille) {
		int size = 0;
		for(int i=0;i<taille.size();i++) {
			size=size+taille.get(i).size();
		}
		return size;
	}
}
