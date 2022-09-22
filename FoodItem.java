import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class is the super class for Vegetable, Fruit, Preserve
 * This class contains methods that add food items information and update inventory and has instance varibles that hold data members of food items.
 * Student Name: Tanisa Tasneem
 * Student Number: 041005658
 * Section #: 302
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * 
 */
public class FoodItem implements Comparable<FoodItem>{
	
	/**Holds the item code for item*/
	protected int itemCode; //holds item code
	/**Holds the item name for item*/
	protected String itemName; //hold item name
	/**Holds the item price for item*/
	protected float itemPrice; //holds item price
	/**Holds the item quantity available in stockl*/
	protected int itemQuantityInStock; //holds quantity available int stock
	/**Holds the item cost for item*/
	protected float itemCost; //holds cost of item


	//default - constructor
	/**Default constructor*/
	public FoodItem(){

	}
	
	public int getItemCode() {
		return itemCode;
	}
	


	/**
	 * This method displayed the qualities of food items such as item code, name, quantity, price and cost in a formatted string.
	 * @return formatted string returned of food items in inventory
	 */
	@Override
	public String toString() { //displayed the information about food item in formatted string
		return "\nItem: " + itemCode + " "  + itemName + " " + itemQuantityInStock +
	               " price: $" + String.format("%.2f", itemPrice) +
	               " cost: $" + String.format("%.2f", itemCost);
	}
	
	
	/**
	 * This methods updates the quantity of food item by adding amount. It returns true when quantity is added and false if it fails
	 * @param amount parameter that updates a the quantity in stock
	 * @return true returned when quantity is added and false if it fails
	 */
	public boolean updateItem(int amount) { //updates quantity of food by adding amount
		int result = itemQuantityInStock + amount;
		if(result < 0) { //test if quanity is negative
			return false;
		} else {
			itemQuantityInStock = result; //quantity in stock equals result
			return true;
		}
	}
		
	

	/**
	 * This method adds food item information to inventory by taking item qualities such as name, quantity, cost, and price of items. Method returns true if program successfully reads in all fields
	 * @param scan this is a scanner object
	 * @param fromFile to add from user input or from file
	 * @return true returned when item is successfully added to inventory
	 */
	public boolean addItem(Scanner scan, boolean fromFile) { //adds food items information to inventory
		boolean test = true; //boolean variable
		do {
			try {
				if(fromFile) {
					test = inputCode(scan); //input item code
					System.out.print("Enter the name for the item: "); //taking input for item name
					itemName = scan.nextLine();
					System.out.print("Enter the quantity for the item: "); //taking input for quantity
					itemQuantityInStock = scan.nextInt();
					scan.nextLine();
					System.out.print("Enter the cost of the item: "); //taking input for item cost
					itemCost = scan.nextFloat();
					System.out.print("Enter the sales price of the item: "); //taking input for item price
					itemPrice = scan.nextFloat();	
					scan.nextLine();
					test = false; //change boolean to false
					return test;
				} else {
					readFromFile(scan);
					return test;
				}
			} catch (Exception e) {
				System.out.println("Invalid entry");
				scan.nextLine();
			}
		} while (test);
		
		return test;
	}


    private void readFromFile(Scanner scan) {
        itemCode=scan.nextInt(); //reads the item code
        scan.nextLine();
        itemName=scan.nextLine(); //reads the item name
        itemQuantityInStock=scan.nextInt(); //reads item quantity
        scan.nextLine();
        itemPrice=scan.nextFloat(); //reads the price of item
        scan.nextLine();
        itemCost=scan.nextFloat(); //reads the cost of item
        scan.nextLine();
    }
	

	/**
	 * This method checks if the item code of the object being acted on and the item object parameter are the same value, if yes then true is returned with an error message
	 * 
	 * @param item object parameter taken as an argument
	 * @return false returned if item codes are not equal 
	 */
	public boolean isEqual(FoodItem item) { 
		
		return itemCode == item.itemCode; //checks if itemcode is equal to parameter item code
	}
	
	/**
	 * This method takes a valid item code from the user using scanner object and returns true if valid code entered otherwise returns false
	 * @param scan it is a scanner object.
	 * @return true returned is code entered ios valid
	 */
	public boolean inputCode(Scanner scan) { //methods takes valid item code 
		boolean done = false;
		do { //do-while to take in code of item
			try {
				System.out.print("Enter the code for the item: ");
				itemCode = scan.nextInt();
				scan.nextLine();
				done = true;
			} catch (InputMismatchException e) { //catches input mismatch exception
				System.out.println("Invalid code");
				scan.nextLine();
			}
		} while (!done);
		return true;
	}
	
	/**
	 * This method adds items to an external file
	 * @param writer Formatter writer that prints out formatted output
	 */
	public void outputItem(Formatter writer) {
		writer.format("%d\n%s\n%d\n%.2f\n%.2f\n", itemCode, itemName, itemQuantityInStock, itemPrice, itemCost);	
	}

	/**
	 * Overriden method from Comparable that returns compared item code to parameter
	 */
	@Override
	public int compareTo(FoodItem o) {
		
		return this.itemCode-o.itemCode; //compares item code to parameter
	}



}