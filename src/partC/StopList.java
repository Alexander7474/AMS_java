package partC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Vector;

import partA.Document;
import partA.Mot;

public class StopList extends HashSet<Mot>{
	public StopList(String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String l;
            while ((l = br.readLine()) != null) {
                Mot m  = new Mot(l);
                this.add(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
