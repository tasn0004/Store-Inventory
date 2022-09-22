import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains the random generated array and its iterative and recursive searching processes.
 * Student Name: Tanisa Tasneem
 * Student Number: 041005658
 * Section #: 302
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * 
 */
public class Assign2 {
	
	/**
	 * Main to test code from Inventory class 
	 * @param args Variable to store command line arguments
	 * @throws IllegalAccessException Illegeal Access exception
	 */
		public static void main(String[] args) throws IllegalAccessException {
			Scanner scan = new Scanner(System.in); //scanner object
			int select = 0; //initializing choice
			Inventory inventory = new Inventory(); //object of inventory
			boolean test = false; //boolean variable true to add, buy or sell
	
		do { // do-while loop
			try { // try block
				
				
				// switch statement
				switch (select = displayMenu(scan)) { 

				case 1: 
					test = inventory.addItem(scan, true); //adds items to inventory
					if (!test) { //error in adding item to inventory
						System.out.println("Item could not be added");
					}
					break;
				case 2:
					System.out.println(inventory); //displays items in inventory
					System.out.println();
					break;
				case 3:
					test = inventory.updateQuantity(scan, true); //sell items if item code exists in inventory
					if (!test) { //error buying item
						System.out.println("Error...could not buy item");
					}
					break;
				case 4:
					if(!inventory.updateQuantity(scan, false)) { //buy valid quantity of item using item code
						System.out.println("Error...could not sell item");
					}
					break;
				case 5: 
					test = inventory.searchForItem(scan); //case for sorting the Array list by code
					if(!test) {
						System.out.println("Code not found in inventory... ");
					}
					break;
				case 6: 
					inventory.saveToFile(scan); //case to save into file
					break;
				case 7: 
					inventory.addItem(scan, false); //case to read inventory from file name
					scan.nextLine();
					break;
				case 8: 
					System.out.println("Exiting..."); //exiting switch case
					break;
				default:
					System.out.println("Invalid input..please try again");
					break;
				}
		} catch (InputMismatchException e) { // catch block
			System.out.println("Invalid selection...please try again");
		} catch (NumberFormatException e) {
			System.out.println("Invalid selection...please try again");
		} catch (IllegalAccessException e) {
			System.out.println(e.getMessage());
			scan.nextLine();
		}
	} while (select != 8); //do-whole loop condition
}
	/**
	 * This method displays the main menu and input from user to perform appropriate options from menu.
	 * @param scan this is a scanner object
	 * @throws IllegalAccessException Illegal Access Exception thrown
	 * @return option the option chosen by the user
	 */
	public static int displayMenu(Scanner scan) throws IllegalAccessException, NumberFormatException {
		int option;
		System.out.print("Please select one of the following:\n"
				+ "1: Add Item to Inventory\n"
				+ "2: Display Current Inventory\n"
				+ "3: Buy Item(s)\n"
				+ "4: Sell Item(s)\n"
				+ "5: Search for Item\n"
				+ "6: Save Inventory to File\n"
				+ "7: Read Inventory from File\n"
				+ "8: To Exit\n"
				+ "> ");
		String var = scan.nextLine();
		//used to check error for whitespace
		if(var.isBlank()) { 
			throw new NumberFormatException(); //throws number format exception
		} else if(var.isEmpty()) {
			throw new IllegalArgumentException(); //throws illegal argument exception
		} else {
			option = Integer.parseInt(var); //string is parsed to integer and returned 
		}
		return option;
		
	}
}

