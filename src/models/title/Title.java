package models.title;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import abstracts.Genre;
import utilities.Global;

/**
 * Title class is a class that represents a genre type of title properties.
 * It extends the Genre class method.
 * 
 * @author Kok Heng
 *
 */

public class Title extends Genre {

	/** Declare global data field. (Refer utilities/global variables) */

	/** Construct an empty constructor for catering empty Title constructor. */
	public Title() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Construct the title with its properties.
	 * @param genre the super class genre type name.
	 * @param titleID the title id.
	 * @param titleName the title type of name.
	 * @param year the title release year.
	 * @param format the title format.
	 * @param qty the numbers of music in stock.
//	 *@param rented date the date music been rented
	 */
	public Title(String genre, int titleId, String titleName, int year, String format, int qty) {
		super(genre); // inherited from parent's class constructor.
		Global.TITLE_ID = titleId;
		Global.TITLE_NAME = titleName;
		Global.TITLE_RELEASE_YEAR = year;
		Global.TITLE_FORMAT = format;
		Global.TITLE_NUMBERSINSTOCK = qty;
		//Global.TITLE_RENTEDDATE = rented;
	}

	/*
	 * Get the title id.
	 * @return the title id.  
	 */
	public int getTitleId() {
		return Global.TITLE_ID;
	}

	/*
	 * Get the title name.
	 * @return the title name.  
	 */
	public String getTitleName() {
		return Global.TITLE_NAME;
	}

	/*
	 * Get the title year of release.
	 * @return the title year of release.  
	 */
	public int getTitleYearRelease() {
		return Global.TITLE_RELEASE_YEAR;
	}

	/*
	 * Get the title format.
	 * @return the title format.  
	 */
	public String getTitleFormat() {
		return Global.TITLE_FORMAT;
	}

	/*
	 * Get the numbers of title in stock.
	 * @return the numbers of title in stock  
	 */
	public int getTitleNumbersInStock() {
		return Global.TITLE_NUMBERSINSTOCK;
	}

	/*
	 * Get the title rented date.
	 * @return the title date been rented.  
	 */
	public String getTitleRentedDate() {
		return Global.TITLE_RENTEDDATE;
	}

	/*
	 * Generic method that uses the buffered reader to read input from user using the keyboard
	 *  @return a String which is a generic answer
	 */
	private static String getUserInput() {
		// read user input by buffered reader.
		BufferedReader br = null;
		// Store input value to this variable.
		String inputValue = "";
		try {
			boolean getCorrectInput = false; // check input validation
			br = new BufferedReader(new InputStreamReader(System.in));

			// Check if user has entered some value.
			while(getCorrectInput == false) {
				inputValue = br.readLine();

				// Repeat until user had entered some value, and quit the loop.
				if(inputValue.equals("")) {
					// Prompt user enter some value again!
					System.out.println("Fields must not be empty, enter the correct value, try again!");
					getCorrectInput = false;
				} else {
					// else if correct value entered, quit the loop here!
					getCorrectInput = true;
				}
			}
		} catch(Exception ex) {
			System.out.println("Errored! You had not entered a valid number, please try again");
			ex.printStackTrace(); // debugging purposes, print out suggested errors
		}
		return inputValue;
	}

	/*
	 * Implementing the body of method header from it super class.
	 * Get the genre and its title name from
	 * input keyboard.
*/
	@Override
	public void getTitleInputKeyboard() {
		// Prompt enter title name.
		System.out.printf("Enter title name: \n");
		Global.TITLE_NAME = getUserInput();
			}

	/*
	 * Implementing the body of method header from it super class.
	 * Set the genre type and its properties info to the standard screen.
	 */
	@Override
	public void setGenreTypeName() {
		// invoke the super class toString for the genre type name.
		super.showGenre();
		System.out.printf("Title ID: %-5d\nTitle Name: %-5s\n" +
				"Year of Release: %-5d\nTitle Format: %-5s\n" +
				"Numbers in Stock: %-5d\nCreation Status: %-5s\n", Global.TITLE_ID, Global.TITLE_NAME, Global.TITLE_RELEASE_YEAR, Global.TITLE_FORMAT, Global.TITLE_NUMBERSINSTOCK, Global.MESSAGE);
	}
}