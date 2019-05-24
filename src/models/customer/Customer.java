package models.customer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import interfaces.customer.InterfaceCustomer;
import utilities.Global;

/*
 * Customer class represents the notion of customers.
 * It consists of customer id, name, customer phone.
 *
 * It implements the interface customer's methods.
 *
 * @author Kok Heng
 */

public class Customer implements InterfaceCustomer {

	/** Reference the global data field @file utilities/Global.java */ 

	/** Construct an empty Customer constructor. */
	public Customer() {
			}

	/**
	 * Construct a customer that representing the customers.
	 * 	 * @param customer_id the customer id number.
	 * @param firstname the customer first name.
	 * @param lastname the customer last name.
	 * @param phone the customer phone number.
	 */
	public Customer(int customer_id, String firstname, String lastname, String phone) {
		Global.CUSTOMER_ID = customer_id;
		Global.CUSTOMER_FIRSTNAME = firstname;
		Global.CUSTOMER_LASTNAME = lastname;
		Global.PHONE = phone;
	}

	/*
	 * Get customer id.
	 * @return customer id for the customer id number.
	 */
	public int getCustomerID() {
		return Global.CUSTOMER_ID;
	}

	/*
	 * Get customer first name.
	 * @return customer first name to represent the customer  first name.
	 */
	public String getCustomerFirstName() {
		return Global.CUSTOMER_FIRSTNAME;
	}

	/*
	 * Get customer last name.
	 * @return customer last name to represent the customer  last name.
	 */
	public String getCustomerLastName() {
		return Global.CUSTOMER_LASTNAME;
	}

	/*
	 * Get the phone the customer phone number.
	 * @return phone the string to represent customer phone number.
	 */
	public String getCustomerPhoneNumber() {
		return Global.PHONE;
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
			//ex.printStackTrace(); // debugging purposes, print out suggested errors
		}
		return inputValue;
	}

	/*
	 * Get customer id input from
	 * keyboard.
	 * (non-Java doc)
	 * 	 * @see interfaces.InterfaceCustomer#getCustomerInputFromKeyboard()
	 */
	@Override
	public void getCustomerInputFromKeyboard() {
		// Prompt enter customer id.
		System.out.printf("Enter customer id: \n");
		String value = getUserInput();
		// convert to integer.
		Global.CUSTOMER_ID = Integer.parseInt(value);
	}

	/*
	 * Set customer information to the standard screen.
	 * (non-Java doc)
	 * 	 * @see interfaces.InterfaceCustomer#setCustomerInfo()
	 */
	@Override
	public void setCustomerInfo() {
		System.out.printf("Customer ID: %-5d\nCustomer First Name: %-5s\nCustomer Last Name: %-5s\nPhone Number: %-5s\n", Global.CUSTOMER_ID, Global.CUSTOMER_FIRSTNAME, Global.CUSTOMER_LASTNAME, Global.PHONE);
	}

	public void setMembershipStatus() {
	System.out.printf("Membership Card ID: %s\n", Global.MEMBERSHIPCARD_ID);
}

}
