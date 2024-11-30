package partA;

import java.util.Objects;

/**
 * @author Alexandre LANTERNIER
 */
public class Mot {
	private String m;

	public String getMot() {
		return m;
	}

	public Mot(String m) {
		super();
		this.m = m;
	}
	
   @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; //si même obj
        if (obj == null || getClass() != obj.getClass()) return false; // si null ou pas la même class
        Mot autreMot = (Mot) obj;
        return Objects.equals(m, autreMot.m); // Deux mots sont égaux si leurs String le sont
    }

    @Override
    public int hashCode() {
        return Objects.hash(m); // le hashCode de m est celui de sa String
    }
}
