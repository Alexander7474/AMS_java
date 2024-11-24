package partA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;

import partB.Taille;

public class Corpus extends Vector<Document> {
	private static final long serialVersionUID = 1L;
	private String title;

	public Corpus(String title, DataSets docType) {
		super();
		this.title = title;
		
		String separator;
		int p1;
		int p2;
		int length;
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
		
		try (BufferedReader br = new BufferedReader(new FileReader(title))) {
            String l;
            while ((l = br.readLine()) != null) {
                
                String[] part = l.split(separator);
                if (part.length == length) {
                	
                    String titre = part[p1].trim();
                    String[] mots = part[p2].trim().split(" ");

                    Document doc = new Document(titre);
                    for (String m : mots) {
                        doc.putMot(m);
                    }

                    this.add(doc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
	}
	
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
	
	public int taille(Taille t) {
		return t.calculer(this);
	}
	
	public Document getDoc(int i) {
		return this.get(i);
	}
	
}
