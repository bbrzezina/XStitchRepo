package floss;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.ArrayList;

public class Project {
	
	private SortedSet<Floss> p = null;
	private FullCollection fc = null;
	private MyCollection mc = null;
	private ArrayList<String> notInCollection = null;
	
	
	public Project() {
		this.p = new TreeSet<Floss>();
		this.fc = new FullCollection();
		this.mc = new MyCollection();
		this.notInCollection = new ArrayList<String>();
	}
	
	public void addFloss( String name ) {
			
		if ( ! (fc.isValidName(name)) )
			return;
			
		Floss f = mc.getFloss(name);
		
		// I don't own it, and I haven't marked it as to buy
		// otherwise just add that floss
		if ( f == null ) {
			this.p.add(new Floss(name, fc.getDescription(name), false, true));
			if (notInCollection.contains(name))
				return;
			else
				notInCollection.add(name);
		}
		else
			this.p.add(f);
		
	}
	
	public int getSize() {
		return p.size();
	}
	
	public String getToBuyString() {
		
		Iterator<Floss> i = p.iterator();
		
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
		
		return( "Floss Needed For Project: " + this.getSize() + "\n" +
				"New Floss: " + need + "\n" + 
				"Refill: " + refill + "\n" + 
				"Total To Buy: " + (need+refill) );
	}
	
	public boolean getNeedsNewFloss(){
		
		if ( this.notInCollection.size() == 0 )
			return false;
		
		return true;
	}
	
	public void addFlossToMyCollection() {
		Iterator<String> i = notInCollection.iterator();
		
		while (i.hasNext()) {
			String name = i.next();
			
			mc.addToBuy(name);

		}
	}

	
}
