package floss;

import java.io.*;

public class UI {
	
	// mc is a file used by the program
	// it keeps a persistent list of my floss
	private MyCollection mc = null;
	
	public UI() {
		
		this.mc = new MyCollection();
		
		// the main loop for the program
		while (true) {
			System.out.println("------------------------------------");
			System.out.println("What would you like to do?");
			System.out.println("1 - add floss you own");
			System.out.println("2 - add floss to buy");
			System.out.println("3 - new project");
			System.out.println("4 - show floss collection");
			System.out.println("5 - write shopping list to file");
			System.out.println("8 - full shopping list");
			System.out.println("9 - see stats");
			System.out.println("x - exit");
			
			// read the choice in
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = null;
			
			try {
				input = br.readLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			if (input.equals("1"))
				addOwned();
			else if (input.equals("2"))
				addToBuy();
			else if (input.equals("3"))
				newProject();
			else if (input.equals("4"))
				printCollection();
			else if (input.equals("5"))
				writeToPrintCollection();
			else if (input.equals("8"))
				seeShoppingList();
			else if (input.equals("9"))
				seeStats();
			else if (input.equalsIgnoreCase("x"))
				return;
		}
	}
	
	// mark floss as owned
	// add it if it's not there
	// set to buy as false
	public void addOwned() {
		
		while (true) {
			System.out.println("Add floss you own. Enter 'x' to return to main menu." );
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = null;
			
			try {
				input = br.readLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			if (input.equalsIgnoreCase("x"))
				return;
			else
				this.mc.addOwned(input);
		}
	}
	
	// mark floss as to buy
	// add if it's not there
	// don't change own
	public void addToBuy() {
				
		while (true) {
			System.out.println("Add floss to buy. Enter 'x' to return to main menu.");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = null;
			
			try {
				input = br.readLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			if (input.equalsIgnoreCase("x"))
				return;
			else
				this.mc.addToBuy(input);
		}
		
	}
	
	
	// This is very basic for now.
	// It is similar to the choice of adding floss,
	// but this lets you enter all the floss required,
	// then shows how much floss would need to be purchased
	// for the project. at the end you can choose
	// to add the floss to the shopping list
	public void newProject() {
		
		Project p = new Project();
		
		while (true) {
			
			System.out.println("Enter floss required for project");
			System.out.println("Enter x when complete");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = null;
			
			try {
				input = br.readLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			if (input.equalsIgnoreCase("x"))
				break;
			else 
				p.addFloss(input);
		}
		
		if (p.getSize() == 0 )
			System.out.println("No floss entered");
		else
			System.out.println(p.getToBuyString());
		
		if (p.getNeedsNewFloss()) {
			System.out.println("Add floss needed to my collection? (y/n)");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = null;
			
			try {
				input = br.readLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			// add the unbought stuff to my collection
			if (input.equalsIgnoreCase("y")) {
				p.addFlossToMyCollection();
			}
				
			
		}
		
	}
	
	
	// see all the floss that is marked as
	// to buy or refill
	public void seeShoppingList() {
		
		mc.viewShoppingList();
		
	}
	
	// see how much floss I have,
	// how many colours I don't own yet
	public void seeStats() {
		
		mc.viewStats();
		
	}
	
	// show the floss I own in the UI
	public void printCollection() {
		mc.printCollection();
	}
	
	// save the shopping list in a local file
	public void writeToPrintCollection() {
		mc.writeToPrintCollection();
	}

	
	
}
