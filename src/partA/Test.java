package partA;

import partA.*;
import partB.*;
import partC.*;

public class Test {
	public static void main(String[] args) {
		Corpus c = new Corpus("src/booksummaries_sample.txt", DataSets.BOOK);
		System.out.println(c.taille(new TailleMot()));
	}

}
