package partA;

/**
 * @author Alexandre LANTERNIER
 */
public enum DataSets {
	WIKIPEDIA("Wikipedia summary"),
	BOOK("Book summary");
	
	private final String nom;
	
	DataSets(String n){
		this.nom = n;
	}
	
	public String toString() {
		return nom;
	}
}
