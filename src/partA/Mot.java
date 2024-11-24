package partA;

import java.util.Objects;

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
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false; 
        Mot autreMot = (Mot) obj;
        return Objects.equals(m, autreMot.m); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(m); 
    }
}
