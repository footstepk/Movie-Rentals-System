package models.customer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.SwingUtilities;
import edits.customer.RegisterNewCustomer;
import utilities.MyJDBCUtils;
import utilities.Global;

/**
 * CustomerQuery class encapsulate the notion of querying the customer, register new customers,
 * update customer details. 
 * @author Kok Heng
 *
 */

public class CustomerQuery {

	/** Instantiate the new instance of customer, registration  and new membership card. */
	private Customer customer = new Customer();
	private RegisterNewCustomer registerCustomer = new RegisterNewCustomer();
	private MembershipCard membershipCard = new MembershipCard();

	/** Initial the JDBC connection driver URL. */
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	/** Construct an empty constructor for catering instantiate the class object. */
	public CustomerQuery() {
	}

	/*
	 * Querying a customer from a database,
	 * display the result from querying.
	 */
	public void setSearchCustomer() {
		// Get customer (customer id).
		int id = customer.getCustomerID();

		// check if customer is in the database?
		if(getCustomerInfo(id)) {
			System.out.printf("Customer Found, Customer Info Are Below:\n");
			customer.setCustomerInfo();
		} else {
			System.out.printf("Customer doesnt exists in system.\n");
			// Prompt user to select a new creation or return to the program.
			getUserRespond();
		}
	}

	/*
	 * Prompts user to select to continue
	 * to the registration program or return to the program.
	 */
	public void getUserRespond() {
		// Display some option message to user to continue or quit the program.
		System.out.printf("Press r for register new customer or n to return to the program\n");
		// Read user input by buffered reader.
		BufferedReader br = null;
		// read user input and store input value to this variable.
		String inputValue = "";

		try {
			// validate user input value as expected r or n.
			boolean getCorrectInput = false;
			br = new BufferedReader(new InputStreamReader(System.in));

			while(getCorrectInput == false) {
				// Reading in user input value.
				inputValue = br.readLine();

				/*
				 * Check what respond did user make.
				 * if 'r' for new registration.
				 * else if 'n' then return to the program.
				 */
				if(inputValue.equals("")) {
					// Prompt to enter again where field cannot be empty.
					System.out.printf("Input field must not be blanked, try again\n");
					getCorrectInput = false;
				} else if(inputValue.matches("[0-9]+")) {
					// Prompt to enter again where input value out of this range.
					System.out.printf("Input value must be letter only 'r' or 'n', try again\n");
					getCorrectInput = false;
				} else if(! inputValue.matches("[nNrR]+")) {
					// Prompt to enter again where input value out of this range.
					System.out.printf("Input value should within 'r' and 'n' only, try again\n");
					getCorrectInput = false;
				} else if(inputValue.equalsIgnoreCase("r")) {
					// Display a  creation form to the screen.
					SwingUtilities.invokeLater(new Runnable() {
					public void run() {
					registerCustomer.createRegistrationUI();
					}
					});
					getCorrectInput = true;
				} else if(inputValue.equalsIgnoreCase("n")) {
					// get input from keyboard.
					customer.getCustomerInputFromKeyboard();
					setSearchCustomer(); // reiterate the menu here for returning to home page.
					getCorrectInput = true;
				} else {
					// user didn't insert a correct value, prompt to try again.
					System.out.printf("YOU DIDN'T INSERT THE RIGHT OPTION, PLEASE TRY AGAIN.\n");
					getCorrectInput = false;
				}
			}
		} catch(Exception ex) {
			System.out.println("Errored! Invalid input, please try again");
			ex.printStackTrace(); // for debugging purposes, print out suggested errors.
		}
	}

	/*
	 * Check if all entries are valid.
	 * All valid entries should return and upon their membership status.
	 */
	public boolean getCustomerInfo(int id) {
		//Initial the database connection and its statement.

		Connection connection = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			// For testing purposes, this should be observation only.
			System.out.println("Searching customer...");

			Class.forName(JDBC_DRIVER);
			connection = MyJDBCUtils.getConnection();

			// Create the prepare statement.
			String sql = "SELECT * " +
					"FROM Customers " +
					"WHERE CustomerID = ?";

			pstmt = connection.prepareStatement(sql);

			// Bind values into the parameter.
			pstmt.setInt(1, id);

			// Let's update the row.

			/** For testing purposes, this should for observation only. */

			// Let us select all the records and display them.
			String query = "SELECT CustomerID, FirstName, LastName, PhoneNumber " +
					"FROM Customers " +
					"WHERE CustomerID = " + id + ";";

			ResultSet rs = pstmt.executeQuery(query);

			// Extract values from result set.
			while(rs.next()) {
				// Retrieve by column name.
				int customer_id = rs.getInt("CustomerID");
				String first = rs.getString("FirstName");
				String last = rs.getString("LastName");
				String phone = rs.getString("PhoneNumber");

				Global.CUSTOMER_ID = customer_id;
				Global.CUSTOMER_FIRSTNAME = first;
				Global.CUSTOMER_LASTNAME = last;
				Global.PHONE = phone;

				if(id == Global.CUSTOMER_ID) {
					result = true;
				} else {
					result = false;
				}
				// For testing purposes, display result.
				//				customer.setCustomerInfo();
			}

			// Clean the environment.
			rs.close(); pstmt.close(); //connection.close();

		} catch(SQLException ex) {
			System.out.println("Errored! The connection to the database cannot be established, try again");
		} catch(Exception ex) {
			//ex.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
			}		catch(SQLException ex) {
			}
			//try {
			//if(connection != null) {
			//connection.close();
			//}
			//}catch(SQLException e) {
			//}
		}
		return result;
	}

	/*
	 * Check if all entries are valid.
	 * All valid entries should return and upon their registration status.
	 * @param first the customer first name.
	 * @param last the customer last name.
	 * @param phone the customer phone number.
	 * @param subscribe the subscription plan.
	 * @param currentPoint the current point.
	 * @param rewardPoint the reward point.
	 * @return true if rows have impacted else then false.
	 */
	public boolean getCustomerRegister(String first, String last, String phone, String subscribe, int currentPoint, int rewardPoint) {
		//Initial the database connection and its statement.
		Connection connection = null;
		PreparedStatement registerCustomer = null;
		PreparedStatement registerMembershipCard = null;
		boolean result = false;
		try {
			// For testing purposes, this should be only observation.
			System.out.printf("Registering a new customer...\n");

			Class.forName(JDBC_DRIVER);
			connection = MyJDBCUtils.getConnection();

			// Create the prepare statement.
			String sql = "INSERT INTO customers VALUES" +
					"(?, ?, ?, ?)";

			registerCustomer = connection.prepareStatement(sql);

			// Bind values into the parameter.
			registerCustomer.setInt(1, 0);
			registerCustomer.setString(2, first);
			registerCustomer.setString(3, last);
			registerCustomer.setString(4, phone);

			// Let's update the row.
			int countRow1 = registerCustomer.executeUpdate();

			if(countRow1 > 0) {
				result = true;// return boolean values if column is greater than 1
			} else {
				result = false; // duplicated entries
			}

			// Let us select all the new records and display them.
			sql = "SELECT CustomerID, FirstName, LastName, PhoneNumber " +
					"FROM customers " +
					"WHERE FirstName = '" + first + "' AND LastName = '" + last + "';";

			ResultSet rs = registerCustomer.executeQuery(sql);

			// Extract values from result set.
			while(rs.next()) {
				// Retrieve by column name.
				int customer_id = rs.getInt("CustomerID");
				String first_name = rs.getString("FirstName");
				String last_name = rs.getString("LastName");
				String phones = rs.getString("PhoneNumber");

				Global.CUSTOMER_ID = customer_id;
				Global.CUSTOMER_FIRSTNAME = first_name;
				Global.CUSTOMER_LASTNAME = last_name;
				Global.PHONE = phones;

				// For testing purposes, display result.
				//customer.setCustomerInfo();
			}

			// Create the prepare statement.
			String sql1 = "INSERT INTO MembershipCard VALUES" +
		"(?, ?, ?, ?, ?)";

			registerMembershipCard = connection.prepareStatement(sql1);

			// Bind values into the parameter.

registerMembershipCard.setInt(1, 0);
registerMembershipCard.setString(2, subscribe);
registerMembershipCard.setInt(3, currentPoint);
registerMembershipCard.setInt(4, rewardPoint);
registerMembershipCard.setInt(5, Global.CUSTOMER_ID);

// Let's update the row.

int countRow2 = registerMembershipCard.executeUpdate();

if(countRow2 > 0) {
	result = true;// return boolean values if column is greater than 1
} else {
	result = false; // duplicated entries
}

sql1 = "SELECT MembershipCardID, Subscribed, CurrentPoint, RewardPoint, CustomerID " +
		"FROM MembershipCard " +
		"WHERE CustomerID = " + Global.CUSTOMER_ID + ";";

			ResultSet rs1 = registerMembershipCard.executeQuery(sql1);

			// Extract values from result set.
			while(rs1.next()) {
				// Retrieve by column name.
				int member_id = rs1.getInt("MembershipCardID");
				String subscription = rs1.getString("Subscribed");
				int current_point = rs1.getInt("CurrentPoint");
				int reward_point = rs1.getInt("RewardPoint");
				int customer_id = rs1.getInt("CustomerID");

				Global.MEMBERSHIPCARD_ID = member_id;
				Global.SUBSCRIBED = subscription;
				Global.CURRENT_POINT = current_point;
				Global.REWARD_POINT = reward_point;
				Global.CUSTOMER_ID = customer_id;

				// For testing purposes, display result.
				membershipCard.setMembershipCardInfo();
			}
			// Clean the environment.
			rs.close(); registerCustomer.close(); rs1.close(); registerMembershipCard.close(); //connection.close();

		} catch(SQLException ex) {
			System.out.println("Errored! The connection to the database cannot be established, try again");
		} catch(Exception ex) {
			//ex.printStackTrace();
		} finally {
			try {
				if(registerCustomer != null && registerMembershipCard != null) {
					registerCustomer.close();
					registerMembershipCard.close();
				}
			}		catch(SQLException ex) {
			}
			//try {
			//if(connection != null) {
			//connection.close();
			//}
			//}catch(SQLException e) {
			//}
		}
		return result;
	}

	/*
	 * Check if all entries are valid.
	 * All valid entries should return and upon update registration status.
	 * @param id the customer id.
	 * @param phone the customer phone number.
	 * @param subscribe the subscription of the plan.
	 * @param currentPoint the current point.
	 * @return true if rows have impacted else then false.
	 */
	public boolean getUpdateCustomer(int id, String phone, String subscribe, int currentPoint) {
		//Initial the database connection and its statement.
		Connection connection = null;
		PreparedStatement updateCustomer = null;
		PreparedStatement updateMembershipCard = null;
		boolean result = false;
		try {
			// For testing purposes, this should be only observation.
			System.out.printf("Updating a customer details and subscription...\n");

			Class.forName(JDBC_DRIVER);
			connection = MyJDBCUtils.getConnection();

			// Create the prepare statement.
			String sql1 = "UPDATE Customers SET " +
			"PhoneNumber = ? " +
					"WHERE CustomerID = ?";

			updateCustomer = connection.prepareStatement(sql1);

			// Bind values into the parameter.
			updateCustomer.setString(1, phone);
			updateCustomer.setInt(2, id);
			
			// Let's update the row.
			int countRow1 = updateCustomer.executeUpdate();
			if(countRow1 > 0) {
				result = true;// return boolean values if column is greater than 1
			} else {
				result = false; // duplicated entries
			}

			// Let us select all the new records and display them.

			sql1 = "SELECT CustomerID, FirstName, LastName, PhoneNumber " +
					"FROM Customers " +
					"WHERE CustomerID = " + id + ";";

			ResultSet rs1 = updateCustomer.executeQuery(sql1);

			// Extract values from result set.
			while(rs1.next()) {
				// Retrieve by column name.
				int customer_id = rs1.getInt("CustomerID");
				String first = rs1.getString("FirstName");
				String last = rs1.getString("LastName");
				String phone_number = rs1.getString("PhoneNumber");

				Global.CUSTOMER_ID = customer_id;
				Global.CUSTOMER_FIRSTNAME = first;
				Global.CUSTOMER_LASTNAME = last;
				Global.PHONE = phone_number;

				// For testing purposes, display result.
				//membershipCard.setMembershipCardInfo();
			}

			// Create the prepare statement.
			String sql2 = "UPDATE MembershipCard SET " +
					"Subscribed = ?, CurrentPoint = ? " +
					"WHERE CustomerID = ?";

updateMembershipCard = connection.prepareStatement(sql2);

			// Bind values into the parameter.

updateMembershipCard.setString(1, subscribe);
			updateMembershipCard.setInt(2, currentPoint);
			updateMembershipCard.setInt(3, id);

			// Let's update the row.
			int countRow2 = updateMembershipCard.executeUpdate();
			if(countRow2 > 0) {
				result = true;// return boolean values if column is greater than 1
			} else {
				result = false; // duplicated entries
			}

			// Let us select all the new records and display them.
			sql2 = "SELECT MembershipCardID, Subscribed, CurrentPoint, RewardPoint, CustomerID " +
					"FROM MembershipCard " +
					"WHERE CustomerID = " + id + ";";

			ResultSet rs2 = updateMembershipCard.executeQuery(sql2);

			// Extract values from result set.
			while(rs2.next()) {
				// Retrieve by column name.
				int member_id = rs2.getInt("MembershipCardID");
				String subscription = rs2.getString("Subscribed");
				int current = rs2.getInt("CurrentPoint");
				int reward = rs2.getInt("RewardPoint");
				int customer_id = rs2.getInt("CustomerID");

				Global.MEMBERSHIPCARD_ID = member_id;
				Global.SUBSCRIBED = subscription;
				Global.CURRENT_POINT = current;
				Global.REWARD_POINT = reward;
				Global.CUSTOMER_ID = customer_id;

				// For testing purposes, display result.
				membershipCard.setMembershipCardInfo();
			}
			// Clean the environment.
			rs1.close(); updateCustomer.close(); rs2.close(); updateMembershipCard.close(); //connection.close();

		} catch(SQLException ex) {
			System.out.println("Errored! The connection to the database cannot be established, try again");
		} catch(Exception ex) {
			//ex.printStackTrace();
		} finally {
			try {
				if(updateCustomer!= null && updateMembershipCard != null) {
					updateCustomer.close();
					updateMembershipCard.close();
				}
			}		catch(SQLException ex) {
			}
			//try {
			//if(connection != null) {
			//connection.close();
			//}
			//}catch(SQLException e) {
			//}
		}
		return result;
	}

}
