package utilities;

/**
 * Global is a class that contains all associated class instance and its variables.
 *
 * @author Kim Kok Heng
 *
 */

public class Global {

	/** Declare the global data field here. */

	/** Global data for customers. */
	public static int CUSTOMER_ID; // the customer id.
	public static String CUSTOMER_FIRSTNAME; // the customer first name.
	public static String CUSTOMER_LASTNAME; // the customer last name.
	public static String PHONE; // the customers phone number.

	/** Declare the global data field for Genre. */
	// The genre type name.
	public static String GENRE_NAME;

	/** Global data field for Title. */
	/** Declare the global data field .*/
	// The title id.
	public static int TITLE_ID;
	// The title name.
	public static String TITLE_NAME;
	// The title release year.
	public static int TITLE_RELEASE_YEAR;
	// The title format type.
	public static String TITLE_FORMAT;
	// The numbers of title in stock.
	public static int TITLE_NUMBERSINSTOCK;
	// The rented date for the title.
	public static String TITLE_RENTEDDATE;

	/** For transaction points, include accumulate, deduction and total points. */
	// Current point.
	public static int CURRENT_POINT;
	// Reward points from each rented titles.
	public static int REWARD_POINT;
	// total quantity of rented title.
	public static int QUANTITY;
	// point to be deducted.
	public static int DEDUCT_CURRENT_POINT;

	/** For membership card status, id. */
	// Membership card id.
	public static int MEMBERSHIPCARD_ID;
	// membership subscription.
	public static String SUBSCRIBED;
	// For status messages.
	public static String MESSAGE = "Success";
}
