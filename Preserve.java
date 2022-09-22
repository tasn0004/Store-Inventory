import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class is a sub-class of FoodItem and overrides methods from foodItem to add information of food items.
 * Student Name: Tanisa Tasneem
 * Student Number: 041005658
 * Section #: 302
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * 
 */
public class Preserve extends FoodItem {
	
	private int jarSize;

	/**default constructor*/
	public Preserve() { //default construct
	} 
	
	/**
	 * This method displayed the qualities of food items such as item code, name, quantity, price and cost in addtion to jar size in a formatted string.
	 * @return formatted string returned of food items in inventory
	 */
	@Override
	public String toString() { //displayed the information about food item in formatted string
		return super.toString() + " size : " + jarSize + "mL";
	}

	/**
	 * This method adds food items to inventory by taking item qualities such as name, quantity, cost, price, and jar size of items. Method returns true if program successfully reads in all fields
	 * @param scan this is a scanner object
	 * @return true returned when item is successfully added to inventory
	 */
	@Override
	public boolean addItem(Scanner scan, boolean fromFile) { 
		//adds information of orchard supplier fruit
		super.addItem(scan, fromFile);
		if(fromFile) {
			System.out.print("Enter the size of the jar in millilitres: ");		
			jarSize = scan.nextInt();
			scan.nextLine();
			
		}
		else {
			jarSize = scan.nextInt();
			scan.nextLine();
		}
		return true;
	}
	
	/**
	 * This method overrides its superclass method to read from file
	 */
	@Override
	public void outputItem(Formatter writer) {
		super.outputItem(writer);
		writer.format("%d\n", jarSize);
	}
}
