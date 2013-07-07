package floss;


// This class represents a floss
// Floss has a (string) ID
// floss can be owned or not owned
// floss can need to be bought or not
// floss has a colour description
public class Floss  implements Comparable<Floss> {
	
	private String name = null;
	private String description = null;
	private boolean own = false;
	private boolean toBuy = false;
	
	// Constructors
	public Floss( String name, String description ) {
		this.setName(name);
		this.setDescription(description);
	}
	
	public Floss( String name, String description, boolean own, boolean toBuy ) {
		this(name, description);
		this.setOwn(own);
		this.setToBuy(toBuy);
	}
	
	// getters and setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isOwn() {
		return own;
	}

	public void setOwn(boolean own) {
		this.own = own;
	}

	public boolean isToBuy() {
		return toBuy;
	}

	public void setToBuy(boolean toBuy) {
		this.toBuy = toBuy;
	}

	
	// needed to override this function
	// so it would sort properly
	@Override
	public int compareTo(Floss f) {
		
		if ( isInt(this.getName()) && isInt(f.getName()) ) {
			int t = getInt(this.getName());
			int x = getInt(f.getName());
			
			if ( t == x )
				return 0;
			else if ( t < x )
				return -1;
			else
				return 1;
		}
		
		return this.getName().compareToIgnoreCase(f.getName());
		
	}
	
	// used by sorting
	private boolean isInt(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	// used by sorting
	private int getInt(String s) {
		return Integer.parseInt(s);
	}
	
	// a different way to view the info relating to the floss
	public void print() {
		System.out.println(this.getName());
		System.out.println(this.getDescription());
		System.out.println(this.isOwn());
		System.out.println(this.isToBuy());
	}
	
	// text info about the floss object
	public String toString() {
		return this.getName() + "," + this.getDescription() + "," + this.isOwn() + "," + this.isToBuy();
	}

		
	
}
