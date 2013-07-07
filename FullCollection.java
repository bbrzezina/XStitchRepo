package floss;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.io.*;

// this class keeps track of the full collection
// of floss
public class FullCollection {
	
	private SortedSet<Floss> fc = null;
	
	public FullCollection() {
		this.fc = new TreeSet<Floss>();
		this.generateCollection();
	}
	
	// get a full list to use within the program from a file
	private void generateCollection() {
		
		try {
			
			String curDir = System.getProperty("user.dir");
			FileInputStream fis = new FileInputStream(curDir + "//src//floss//DMC list");			
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			
			String line = null;
			String[] data = null;
			while( ( line = br.readLine() ) != null ) {
				data = line.split(",");
				addFloss(data[0], data[1]);
			}
			dis.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	private void addFloss( String name, String description) {
		this.fc.add(new Floss(name, description));
	}
	

	public void printCollection() {
		
		Iterator<Floss> i = fc.iterator();
	
		while (i.hasNext()) {
			Floss f = i.next();
			System.out.println(f.toString());
		}
		
	}
	
	
	public String getDescription( String name ) {
		
		Iterator<Floss> i = fc.iterator();
		
		while (i.hasNext()) {
			Floss f = i.next();
			if ( f.getName().equalsIgnoreCase(name) ) 
				return f.getDescription();
		}		

		return null;
	
	}
	
	
	// determine if a floss ID is valid
	public boolean isValidName(String name) {
		Iterator<Floss> i = fc.iterator();
		
		while (i.hasNext()) {
			Floss f = i.next();
			if ( f.getName().equalsIgnoreCase(name) ) 
				return true;
		}		

		return false;
	}
	
	public int size() {
		return this.fc.size();
	}
		
}
