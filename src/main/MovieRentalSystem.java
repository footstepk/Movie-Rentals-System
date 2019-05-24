package main;

import models.title.Title;
import models.title.TitleQuery;
import models.customer.Customer;
import models.customer.CustomerRentedTitle;
import models.customer.CustomerQuery;
import edits.customer.RegisterNewCustomer;
import edits.customer.UpdateCustomer;
import edits.title.CreateNewTitle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.*;
import javax.swing.SwingUtilities;

/**
 * MovieRentalSystem is a class that represents the movie rental system that run the main method.
 * 
 * It contains the menu and menu selection to the screen and
 * 
 * invokes associated instance and its methods to perform the task.
 * 
 * @author Kok Heng
 *
 */

public class MovieRentalSystem {

	/** Declare the instance of associated class. */
	private Title title = new Title();
	private CreateNewTitle createTitle = new CreateNewTitle();
	private TitleQuery titleQuery = new TitleQuery();
	private Customer customer = new Customer();
	private RegisterNewCustomer registerCustomer = new RegisterNewCustomer();
	private UpdateCustomer updateCustomer = new UpdateCustomer();
	private CustomerRentedTitle customerRentedTitle = new CustomerRentedTitle();
	private CustomerQuery customerQuery = new CustomerQuery();
	
	/** Construct an empty constructor for class instance references. */
	public MovieRentalSystem() {
	}

	/*
	 * Set a message to represent the movie rental system to the standard screen.
	 */
	public void systemScreenMessage(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		date = new Date();
		System.out.printf("* Movie Rental Service Management System | %s *\n\n", dateFormat.format(date));
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
					System.out.println("Fields must not be empty, enter the correct value (1-7), try again!");
					getCorrectInput = false;
				} else if(! inputValue.matches("[1-7]+")) {
					// incorrect input range other than specified, prompt user to enter again!
					System.out.println("Just enter numbers 1 to 7 only, try again!");
					getCorrectInput = false;
				} else if(inputValue.matches("[a-zA-Z]+")) {
					// incorrect input range other than specified, prompt user to enter again!
					System.out.println("No letters are allowed, just enter numbers 1 to 7 only, try again!");
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
	 * Prompts user to select to continue
	 * to the program or quit the program.
	 */
	public void getPromptUserToContinue() {
		// Display some option message to user to continue or quit the program.
		System.out.printf("Press y to continue or n to quit the program\n");
		// Read user input by buffered reader.
		BufferedReader br = null;
		// read user input and store input value to this variable.
		String inputValue = "";

		try {
			// validate user input value as expected y or n.
			boolean getCorrectInput = false;
			br = new BufferedReader(new InputStreamReader(System.in));

			while(getCorrectInput == false) {
				// Reading in user input value.
				inputValue = br.readLine();

				/*
				 * Check what respond did user make.
				 * if 'y' continue to the program.
				 * else if 'n' then quit the program.
				 */

				if(inputValue.equals("")) {
					// Prompt user to enter some value, cannot leave it blank field.
					System.out.printf("Fields must not be blank, enter some value, try again.");
				} else if(inputValue.matches("[0-9]+")) {
					// Prompt user to enter just letter y or n only.
					System.out.printf("No numbers are allowed, enter leter y or n only, try again");
				} else if(inputValue.equalsIgnoreCase("y")) {
					menuSelected(); // Display a menu option again to the screen.
					getCorrectInput = true;
				} else if(inputValue.equalsIgnoreCase("n")) {
					quitProgram(); // Terminate the program, and display a message to the screen
					getCorrectInput = true;
				} else {
					// user didn't insert a correct value, prompt to try again.
					System.out.println("YOU DIDN'T INSERT THE RIGHT OPTION, PLEASE TRY AGAIN.");
					getCorrectInput = false;
				}
			}
		} catch(Exception ex) {
			System.out.println("Errored! Invalid input, please try again");
			ex.printStackTrace(); // for debugging purposes, print out suggested errors.
		} 
	}

	/*
	 * Display a menu option to the standard screen.
	 * It should have a list of option for user to select a task.
	 */
	private static void menuOption() {
		// Display a list menu option.
		System.out.println("Please choose from a list of movie rental option below");
		System.out.printf("Press 1 to search for titles\n");
		System.out.printf("Press 2 to search for customers\n");
		System.out.printf("Press 3 to add new titles\n");
		System.out.printf("Press 4 to add new customers\n");
		System.out.printf("Press 5 to update customer and their subscription plan\n");
		System.out.printf("Press 6 to register customer has rented a title\n");
		System.out.printf("Press 7 to quit the program\n");
	}

	/*
	 * Check user input to perform to
	 * the menu option operation
	 */
	public void menuSelected() {
		// load the menu option here.
		menuOption();

		// Read user selected menu option.
		//System.out.println("Please choose from 1 to 6 from the menu option");
		String option = getUserInput();

		// determine which menu option been selected by user
		// Perform the task activities upon the selected menu option.
		if(option.equals("1")) {
			title.getTitleInputKeyboard();
			titleQuery.setSearchTitle();
			// when operation completed, prompt user wish to continue.
			getPromptUserToContinue();
		} else if(option.equals("2")) {
			customer.getCustomerInputFromKeyboard();// Display an input field to the screen.
			customerQuery.setSearchCustomer();
			// when operation completed, prompt user wish to continue.
			getPromptUserToContinue();
		} else if(option.equals("3")) {
			// Display a  creation form to the screen.
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					createTitle.createRegistrationUI();
				}
			});
			// when operation completed, prompt user wish to continue.
			getPromptUserToContinue();
		} else if(option.equals("4")) {
			// Display a  registration form to the screen.
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					registerCustomer.createRegistrationUI();
				}
			});
			// when operation completed, prompt user wish to continue.
			getPromptUserToContinue();
		} else if(option.equals("5")) {
			// Display a  update form to the screen.
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					updateCustomer.createRegistrationUI();
				}
			});
			// when operation completed, prompt user wish to continue.
			getPromptUserToContinue();
		} else if(option.equals("6")) {
			customerRentedTitle.setRegisterCustomerRented();
			// when operation completed, prompt user wish to continue.
			getPromptUserToContinue();
		} else if(option.equals("7")) {
			// option 7 selected, then quit the program.
			quitProgram();
		} else {
			// User didn't select the menu option, prompt to try again.
			System.out.println("SORRY! YOU DIDN'T SELECT THE RIGHT OPTION! Please try again.");
			menuSelected();
		}
	}

	/*
	 * Get user to respond to
	 * quit the program, and
	 * display a goodbye message to the standard screen.
	 */
	public void quitProgram() {
		System.out.printf("You are quitted the program.\n");
		System.exit(0);
	}

	public static void main(String[] args) {

		/** Instantiate the instance of MovieRentalSystem and Date. */
		MovieRentalSystem mrs = new MovieRentalSystem();
		Date date = new Date();

		/** set a message to represent the movie rental system and current date to the screen. */
		mrs.systemScreenMessage(date);

		/** Display the menu section option the the screen. */
		mrs.menuSelected();
	}
}
