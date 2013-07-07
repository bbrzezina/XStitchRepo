package floss;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.io.*;

// this class keeps track of my collection of floss
public class MyCollection {
	
	private SortedSet<Floss> mc = null;
	private FullCollection fc = null;
	
	public MyCollection() {
		this.mc = new TreeSet<Floss>();
		this.extractCollection();
		// Full Collection is a list of all of the possible colours
		// and their official descriptions
		this.fc = new FullCollection();
	}
	
	// get the info from file
	private void extractCollection() {
		
		try {
			String curDir = System.getProperty("user.dir");
			FileInputStream fis = new FileInputStream(curDir + "//src//floss//My Collection");	

			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			
			String line = null;
			String[] data = null;
			while( ( line = br.readLine() ) != null ) {
				data = line.split(",");
				this.mc.add( new Floss(data[0], data[1], Boolean.valueOf(data[2]), Boolean.valueOf(data[3])) );
			}
			dis.close();
		} catch (Exception e) {
			System.out.println("err" + e.getMessage());
		}
		
	}
	
	// update the collection
	public void writeCollection() {
		try {
			String curDir = System.getProperty("user.dir");
			FileWriter fw = new FileWriter(curDir + "//src//floss//My Collection");	
			BufferedWriter bw = new BufferedWriter(fw);
			
			Iterator<Floss> i = mc.iterator();
			
			while (i.hasNext()) {
				bw.write(i.next().toString());
				bw.newLine();
			}
			bw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	// if it is a valid floss ID, add it to my collection
	public void add(String name) {
		
		if (fc.isValidName(name)) {
			this.mc.add( new Floss(name, fc.getDescription(name)));
		
		}
		writeCollection();
	}
	
	
	// add floss I own
	public void addOwned(String name) {
		
		// return if it's not a valid name
		if (! (fc.isValidName(name)))
			return;
		
		Floss f = getFloss(name);
		
		// don't already have the floss
		if (f == null) {
			this.mc.add( new Floss(name, fc.getDescription(name), true, false));
		}
		else {
			f.setOwn(true);
			f.setToBuy(false);
		}
			
		writeCollection();
	}
	
	// add floss I don't own
	public void addToBuy(String name) {
		// return if it's not a valid name
		if (! (fc.isValidName(name)))
			return;
		
		Floss f = getFloss(name);
		
		// don't already have the floss
		if (f == null) {
			this.mc.add( new Floss(name, fc.getDescription(name), false, true));
		}
		else {
			f.setToBuy(true);
		}
			
		writeCollection();
	}
	
	// see if collection already includes this
	public Floss getFloss(String name) {
		
		Iterator<Floss> i = mc.iterator();
		
		while (i.hasNext()) {
			Floss f = i.next();
			if (f.getName().equalsIgnoreCase(name))
				return f;
		}
		return null;
	}
	
	// misleading name.. just print the collection to the UI
	public void printCollection() {
		
		Iterator<Floss> i = mc.iterator();
	
		while (i.hasNext()) {
			Floss f = i.next();
			System.out.println(f.toString());
		}
	}
	
	// see various stats about my collection
	public void viewStats() {
		
		Iterator<Floss> i = mc.iterator();
		int need = 0;
		int refill = 0;
		
		while (i.hasNext()) {
			Floss f = i.next();
			
			if (f.isToBuy()) {
				if (f.isOwn())
					refill++;
				else
					need++;
			}
		}
		
		System.out.println("Number of floss you own: " + mc.size());
		System.out.println("Number of floss you don't own: " + (fc.size() - mc.size()) );
		System.out.println("Number of new floss to buy: " + need);
		System.out.println("Number of floss to refill: " + refill);
		System.out.println("Total number to buy: " + (need+refill));
	}
	
	
	// show the shopping list, 
	// shows which are refills and which are new
	public void viewShoppingList() {
		
		Iterator<Floss> i = mc.iterator();
		
		while (i.hasNext()) {
			Floss f = i.next();
			
			if (f.isToBuy()) {
				if (f.isOwn())
					System.out.println( f.getName() + " - " + f.getDescription() + " (refill)" );
				else
					System.out.println( f.getName() + " - " + f.getDescription() + " (new)" );
			}
		}
	}	
	
	// misleading name of this function
	// save the shopping list to a local file
	// (working folder, print.txt)
	public void writeToPrintCollection() {
		try {
			String curDir = System.getProperty("user.dir");
			FileWriter fw = new FileWriter(curDir + "//src//floss//Print.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			
			Iterator<Floss> i = mc.iterator();
			
			while (i.hasNext()) {
				
				Floss f = i.next();
				if (f.isToBuy()) {
					if (f.isOwn())
						bw.write( f.getName() + " - " + f.getDescription() + " (refill)" );
					else
						bw.write( f.getName() + " - " + f.getDescription() + " (new)" );
					
					bw.newLine();
				}
	
			}
			bw.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
}
