/**
 * This is a draft program that I will use to keep track of
 * my floss (threads) that I use in my cross stitch projects.
 * 
 * This is the main class, all it does is call the UI.
 * 
 * When I buy floss, I can input the numbers here so I 
 * always know which colours I already own.
 * 
 * When I want to start a new project, I can create
 * a shopping list here that will tell me which floss I need
 * to buy. I can also mark things as a "refill" if I already
 * own the floss but am about to run out.
 * 
 * I can also choose to save my shopping list in a .txt file
 * for easy printing.
 * 
 * This is just a rough project, I would want to create a GUI and 
 * do some polishing, etc. There is some optimization that should 
 * be done as well. Right now several classes keep their own
 * "master" of the full list of floss colours. It could be kept
 * in one place and that info could be shared amongst the different 
 * classes.
 * 
 * Another thing to add is confirmation that an action has 
 * happened. Right now it's very bare bones.
 *
 * My dream is to make this into a Windows Phone 8 &
 * Windows 8 app :)
 * 
 */

package floss;

public class FlossProgram {

	public FlossProgram(){
		new UI();
	}
	
	public static void main( String args[] ) {
		
		new FlossProgram();
		
		
	
	}
	
	
}
