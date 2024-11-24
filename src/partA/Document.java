package partA;

import java.util.ArrayList;

public class Document extends ArrayList<Mot>{
	private static final long serialVersionUID = 1L;
	private String title;
	
	public Document(String title) {
		super();
		this.title = title;
	}
	
	public void putMot(Mot m) {
		this.add(m);
	}
	
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
	
	public Mot getMot(int i) {
		return this.get(i);
	}
	
	public boolean isInDoc(Mot m) {
		for(int i = 0; i < this.size(); i++) {
			if(m.equals(this.getMot(i))) {
				return true;
			}
		}
		return false;
	}
}
