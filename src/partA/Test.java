package partA;

public class Test {
	public static void main(String[] args) {
		Corpus testc = new Corpus("src/booksummaries_sample.txt", DataSets.BOOK);
		System.out.println(testc);
		System.out.println("end");
	}

}
