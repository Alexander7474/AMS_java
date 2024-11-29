package partA;

import partA.*;
import partB.*;
import partC.*;

public class Test {
	public static void main(String[] args) {
		Corpus c = new Corpus("src/booksummaries_sample.txt", DataSets.BOOK);
		TfIdf t = new TfIdf();
		TfIdf t2 = t.processCorpus(c);
		t2.processQuery("The story is presented by Le Fanu", 3);
	}
}
