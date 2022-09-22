import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * CET - CS Academic Level 3
 * Declaration: I declare that this is my own original work and is free from Plagiarism
 * This class contains the inventory array that holds food items, it holds the sub menu to call correct derived FoodItem class and updates inventory after item is sold or bought.
 * Student Name: Tanisa Tasneem
 * Student Number: 041005658
 * Section #: 302
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD. 
 * 
 * 
 */
public class Inventory {
	/**An inventory array that holds food items*/
	private static ArrayList<FoodItem>  inventory = new ArrayList<FoodItem>(); //declaring food item object arraylist
	/**Holds the index in inventory Array List*/
	private int numItems; //index in inventory Array List
	/**Holds item for Array List input*/
	private FoodItem item; //declaring item for array list input
	/**Stores the charater for fruit*/
	private final String fruit = "f";     //declare string for fruit
	/**Stores the charater for vegetable*/
    private final String vegetable = "v";     //declare string for vegetable
    /**Stores the charater for preserve*/
    private final String preserve = "p"; //declare string for preserve
	
	/**no-argument constructor*/
	public Inventory(){
		numItems = 0;
	/*	inventory.ensureCapacity(numItems); */
		inventory = new ArrayList<>(); //instantiating ArrayList
	}
	
	
	/**
	 * Adds an item to the inventory array using polymorphism to call addItem method in the correct derived FoodItem class for input of the fields of the FoodItem.
	 * @param scan this is an object of scanner
	 * @param choice choice for switch statement 
	 * @param fromFile boolean value to add from user input or file
	 * @return test returned when item is successfully added to inventory array
	 * @throws IllegalAccessException throws illegal access exception
	 */
	public boolean readFromFile(String choice, Scanner scan, boolean fromFile) throws IllegalAccessException {
		boolean test = false;

			//switch case for sub menu
			switch (choice) {
				case "f":
					item = new Fruit(); //takes properties from Fruit class
					break;
				case "v":
					item = new Vegetable(); //takes properties from Vegetable class
					break;
				case "p":
					item = new Preserve(); //takes properties from Preserve class
					break;
				default:
					System.out.println("Invalid entry");
			}
			try {
				item.addItem(scan, fromFile);
				int exist = alreadyExists(item);
				if (exist == -1) {
					test = inventory.add(item);
					Collections.sort(inventory);
					numItems++;
			} else {
				throw new IllegalAccessException("Item code already exists\nError Encountered while reading the file, aborting...");
				} 
				
			} catch (NullPointerException e) {
				System.out.println("Item code already exists");
			}
		return test;
	}

	/**
	 * Adds item to the array list from user input else it asks for file name path from where it is to be read from
	 * @param scan Scanner object
	 * @param fromFile Boolean to tell whether to input from keyboard or from file
	 * @return test returned when item is successfully added to inventory array
	 * @throws IllegalAccessException Error message
	 */
	public boolean addItem(Scanner scan, boolean fromFile) throws IllegalAccessException {
		boolean test = false;
		String choice;
		
		//do {
			if(fromFile) { //if item is to be added from user input
				System.out.print("Do you wish to add a fruit(f), vegetable(v) or a preserve(p)? ");
				choice = scan.next();
				test = readFromFile(choice, scan, fromFile);
			} else { //if file is going to be read
				try {
					System.out.print("Enter the filename to read from: ");
					Path fileName = Paths.get(scan.next());
					scan = new Scanner(fileName);
					while (scan.hasNext()) {
						choice = scan.nextLine();
						test = readFromFile(choice, scan, fromFile);
					}
				} catch (FileNotFoundException e ) {
					System.out.println("File Not Found, ignoring...");
				} catch (NoSuchFileException e) {
					System.out.println("File Not Found, ignoring...");
					scan.nextLine();
				}catch (NoSuchElementException e) {
                    System.out.println("No such element");
                    scan.nextLine();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		//} while (!test);
		return test; //returns boolean
	}
	/**
	 * Displays the all data members in the class in a formatted string according to each type of item
	 * @return display the formatted string of all data memebers
	 */
	@Override
	public String toString() { //displayed the information about food item in formatted string
		String display= "Inventory:";

			for(int i = 0; i < numItems; i++) {
				display += inventory.get(i).toString();
			}
			return display;	
	}
	
	/**
	 * Returns the index of a FoodItem in the inventory array with the same itemCode as the FoodItem object in the parameter list, else returns -1
	 * @param fooditem object parameter taken as an argument
	 * @return -1 returned if item code was not found in inventory array
	 */
	public int alreadyExists(FoodItem fooditem) {
        int index = -1;
        if (fooditem.isEqual(fooditem)) {       //testing similarities
             index = recursiveBinarySearch(fooditem.getItemCode(),inventory,0,numItems-1);
            return index;
        }
        return index;
	}
	
	/**
	 * This method sorts the ArrayList inventory using recursive binary search
	 * @param itemCode holds itemcode of item
	 * @param fooditem ArrayList of foot items
	 * @param first first index for searching
	 * @param last last index for searching
	 * @return -1 
	 */
	public int recursiveBinarySearch(int itemCode, ArrayList<FoodItem> fooditem, int first, int last) {
	
		if (first <= last) {
			int mid = first + (last - first) / 2;
			if (fooditem.get(mid).getItemCode() < itemCode) {
				first = mid + 1;
				return recursiveBinarySearch(itemCode, fooditem, first, last);
			} else if (fooditem.get(mid).getItemCode() == itemCode) {
				return mid;
			} else {
				last = mid -1;
				return recursiveBinarySearch(itemCode, fooditem, first, last);
			}
		}
			return -1;
	}
	
	
	/**
	 * Reads in an itemCode and quantity to buy or sell items in inventory and updates that item by the input quantity in the inventory array.
	 * @param scan this is an object of scanner
	 * @param buyOrSell boolean parameter is used to denote whether buying operation (true) or selling operation (false) is occurring
	 * @return false if update was successfull otherwise it returns true
	 */
	public boolean updateQuantity(Scanner scan, boolean buyOrSell) {
	    try {       //try statement
	            if (numItems > 0) {       //make sure that there is items in the array
	                int quantity;       //declare quantity
	                System.out.print("Enter the code for the item: ");      //print statement
	                int code = scan.nextInt();      //input the item code needed
	                scan.nextLine();
	                int index = recursiveBinarySearch(code, inventory, 0, numItems - 1);        //search for the code in the array
	                boolean quantityGoods = false;      //declare boolean
	                if (index != -1) {      //if the item exist
	                    if (buyOrSell) {    
	                    	System.out.print("Enter valid quantity to buy: ");
	                    	quantity = scan.nextInt();
	                    	scan.nextLine();
	                        quantityGoods = inventory.get(index).updateItem(quantity);      //method to update quantity
	                    } else {
	                    	System.out.print("Enter valid quantity to sell: ");
	                    	quantity = scan.nextInt();
	                    	scan.nextLine();
	                    	quantityGoods =inventory.get(index).updateItem(quantity * -1);     //method to update quantity
	                    }
	                }
	                return quantityGoods;       //return true or false
	            }
	        } catch (ArrayIndexOutOfBoundsException e) {        //exception catch
	            System.out.println();
	        } catch (NumberFormatException e) {     //exception catch
	            System.err.println("Please enter an integer");     //exception catch
	        }
	        return false;       //return false
	    }
	
	/**
	 * This method creates a file and adds food information to it
	 * @param scan Takes user input
	 */
	public void saveToFile(Scanner scan){
        Formatter writer = null;
        try{
            System.out.print("Enter the filename to save to: ");
            String filename = scan.nextLine();
          
            
            writer=new Formatter(filename);
            
            
            for (FoodItem item : inventory){
                if(item instanceof Fruit) {
                    writer.format("%s\n", fruit);
                } else if(item instanceof Vegetable) {
                    writer.format("%s\n", vegetable);
                } else if(item instanceof Preserve){
                    writer.format("%s\n", preserve);
                }
                item.outputItem(writer);
            }
        }catch (SecurityException e){
            System.out.println("");
        } catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
        if (writer != null){
            writer.close();
        }
    }
	/**
	 * This method searches for an item in the inventory by itemcode
	 * @param scan Takes user input
	 * @return boolean true
	 */
	public boolean searchForItem(Scanner scan) {
		System.out.print("Enter the code for the item: ");
		int code = scan.nextInt();
		scan.nextLine();
		int index = recursiveBinarySearch(code, inventory, 0, numItems - 1);

		if (index == -1) {
    	 return false;
		} else {
			System.out.println(inventory.get(index));
			return true;
		}
    }

}
