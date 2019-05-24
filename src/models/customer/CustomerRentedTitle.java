package models.customer;

import java.util.Scanner;
import java.util.Date;
import java.text.*;
import java.util.InputMismatchException;
import utilities.Global;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilities.MyJDBCUtils;

/**
 * CustomerRentedTitle is a class that represents a customer rented a title which capture,
 * the rented details.
 * 
 * @author Kok Heng
 *
 */

public class CustomerRentedTitle {

	/** Reference the global data field @file utilities/Global.java */ 

	/** Instantiate the membership card. */
	private MembershipCard membershipCard = new MembershipCard();

	/** Initial the JDBC connection driver URL. */
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM//dd");
	Date date = new Date();
	public CustomerRentedTitle() {
	}

	/*
	 * Get customer id input from keyboard.
	 * @param prompt the message to be prompted to user.
	 * @return the customer id.
	 */
	private static int getCustomerID(String prompt) {
		Scanner scan = null;
		int id = 0;
		boolean validID = false;
		while(! validID) {
			try {
				scan = new Scanner(System.in);
				System.out.print(prompt);
				id = scan.nextInt();
				validID = true;
			}
			catch(InputMismatchException ex) {
				scan.nextLine(); // clear the buffered line.
				System.out.println("Input Mismatched, enter integer only!");
			}
		}
		return id;
	}

	/*
	 * Get pay point input from keyboard.
	 * @param prompt the message to be prompted to user.
	 * @return the pay current point.
	 */
	private static int getPayByCurrentPoint(String prompt) {
		Scanner scan = null;
		int currentPoint = 0;
		boolean validCurrentPoint = false;
		while(! validCurrentPoint) {
			try {
				scan = new Scanner(System.in);
				System.out.print(prompt);
				currentPoint = scan.nextInt();
				validCurrentPoint = true;
			}
			catch(InputMismatchException ex) {
				scan.nextLine(); // clear the buffered line.
				System.out.println("Input Mismatched, enter integer only!");
			}
		}
		return currentPoint;
	}

	/*
	 * GET reward point input from keyboard.
	 * @param prompt the message to be prompted to user.
	 * @return the pay reward point.
	 */
	private static int getPayByRewardPoint(String prompt) {
		Scanner scan = null;
		int rewardPoint = 0;
		boolean validRewardPoint = false;
		while(! validRewardPoint) {
			try {
				scan = new Scanner(System.in);
				System.out.print(prompt);
				rewardPoint = scan.nextInt();
				validRewardPoint = true;
			}
			catch(InputMismatchException ex) {
				scan.nextLine(); // clear the buffered line.
				System.out.println("Input Mismatched, enter integer only!");
			}
		}
		return rewardPoint;
	}

	/*
	 * Get title name to be rented input from keyboard.
	 * @param prompt the message to be prompted to user.
	 * @return the title name to be rented.
	 */
	private static String getRentTitleName(String prompt) {
		Scanner scan = null;
		String input = "";
		boolean validInput = false;
		while(! validInput) {
			try {
				scan = new Scanner(System.in);
				System.out.print(prompt);
				input = scan.nextLine();
				if(input.equals("")) {
					System.out.println("Fields cannot be blanked, enter some title name!");
					validInput = false;
				}
				else {
					validInput = true;
				}
			}
			catch(Exception ex) {
				scan.nextLine(); // clear the buffered line.
			}				
		}
		return input;
	}

	/*
	 * Get quantity of rent input from keyboard.
	 * @param prompt the message to be prompted to user.
	 * @return the quantity of rent.
	 */
	private static int getRentQuantity(String prompt) {
		Scanner scan = null;
		int qty = 0;
		boolean validQty = false;
		while(! validQty) {
			try {
				scan = new Scanner(System.in);
				System.out.print(prompt);
				qty = scan.nextInt();
				validQty = true;
			}
			catch(InputMismatchException ex) {
				scan.nextLine(); // clear the buffered line.
				System.out.println("Input Mismatched, enter integer only!");
			}
		}
		return qty;
	}
/**
 * Get user input from keyboard for,
 * id, title name and quantity.
 */
	public void getCustomerRentInfo() {
		Global.CUSTOMER_ID = getCustomerID("Enter customer id: ");
		Global.TITLE_NAME = getRentTitleName("Enter title name: ");
		Global.QUANTITY = getRentQuantity("Enter quantity to be rented: ");
	}

	/**
 * Get user input point to be deducted from keyboard.
 */
	public void getCustomerPayByCurrentPoint() {
		Global.DEDUCT_CURRENT_POINT = getPayByCurrentPoint("Enter point to be deducted from current point: ");
	}

/**
 * Get user input point to be rewarded from keyboard.
 */
	public void getCustomerPayByRewardPoint() {
		Global.REWARD_POINT = getPayByRewardPoint("Enter point to be deducted from reward point: ");
	}

/*
 * Check if all entries are valid from database
 * @param title the title name to be searched.
 * @param qty the quantity in the stock.
 * @return true if entries are valid else then false.
 */
	public boolean isTitleExistAndHasStock(String titleName, int qty) {
		//Initial the database connection and its statement.

		Connection connection = null;
		PreparedStatement pstmt = null;
		PreparedStatement updateNumbersInStock = null;
		boolean result = false;
		try {
			Class.forName(JDBC_DRIVER);
			connection = MyJDBCUtils.getConnection();

			// Create the prepare statement.
			String sql = "SELECT TitleName, NumbersInStock " +
					"FROM GenreType " +
					"WHERE TitleName LIKE ? AND NumbersInStock > ?";

			String updateSql = "UPDATE GenreType SET NumbersInStock = NumbersInStock - ? " +
			"WHERE TitleName LIKE ?";

			pstmt = connection.prepareStatement(sql);
			updateNumbersInStock = connection.prepareStatement(updateSql);

			// Bind values into the parameter.
			pstmt.setString(1, "%"+titleName+"%");
			pstmt.setInt(2, qty);

			updateNumbersInStock.setInt(1, qty);
			updateNumbersInStock.setString(2, "%"+titleName+"%");

			// Let's update the row.
			// Let us select title name the records and display them.
			String query = "SELECT TitleName, NumbersInStock " +
					"FROM GenreType " +
					"WHERE TitleName LIKE '%" + titleName + "%' AND NumbersInStock > " + qty + ";";

			ResultSet rs = pstmt.executeQuery(query);

			// Extract values from result set.
			while(rs.next()) {
				// Retrieve by column name.
				String title = rs.getString("TitleName");
				int numbersInStock = rs.getInt("NumbersInStock");

				Global.TITLE_NAME = title;
				Global.TITLE_NUMBERSINSTOCK = numbersInStock;

				if(Global.TITLE_NAME.matches("(.*)"+titleName+"(.*)") && Global.TITLE_NUMBERSINSTOCK > qty) {
					result = true;
				} else {
					result = false;
				}
				// For testing purposes, display result.
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
 * Set the registration of rented title.
 */
	public void setRegisterCustomerRented() {
		getCustomerRentInfo();
		if(isTitleExistAndHasStock(Global.TITLE_NAME, Global.QUANTITY)) {
			getCustomerPayByCurrentPoint();
			if(getUsePoint(Global.CURRENT_POINT, Global.CUSTOMER_ID)) {
				System.out.printf("Rented Title Paid By: %-5d\n"
			+ "Transaction Completed: Success %n\n", Global.DEDUCT_CURRENT_POINT);
			}
			else {
				System.out.println("Current point is insufficient to pay this transaction this time.");
			}
			System.out.printf("Customer ID: %-5d\n"
					+ "Rented Title Name: %-5s\n"
					+ "Rented Quantity: %-5d\n"
					+"Rented Date: %-5s\n", Global.CUSTOMER_ID, Global.TITLE_NAME, Global.QUANTITY, dateFormat.format(date));
		}
		else {
			System.out.println("Errored! The title name doesn't or numbers is out  of stock.");
		}
	}

	public boolean getUsePoint(int point, int id) {
		//Initial the database connection and its statement.
		Connection connection = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			Class.forName(JDBC_DRIVER);
			connection = MyJDBCUtils.getConnection();

			// Create the prepare statement.
			String sql = "UPDATE MembershipCard SET " +
					"CurrentPoint = CurrentPoint - ? " +
					"WHERE CustomerID = ?";

			pstmt = connection.prepareStatement(sql);

			// Bind values into the parameter.
			pstmt.setInt(1, point);
			pstmt.setInt(2, id);

			// Let's update the row.
			int countRows = pstmt.executeUpdate();
			if(countRows > 0) {
				result = true;// return boolean values if column is greater than 1
			} else {
				result = false; // duplicated entries
			}

			// Let us select all the new records and display them.

			String query = "SELECT MembershipCardID, Subscribed, CurrentPoint, RewardPoint, CustomerID " +
					"FROM MembershipCard " +
					"WHERE CustomerID = " + id + ";";

			ResultSet rs = pstmt.executeQuery(query);

			// Extract values from result set.
			while(rs.next()) {
				// Retrieve by column name.
				int member_id = rs.getInt("MembershipCardID");
				String subscription = rs.getString("Subscribed");
				int current = rs.getInt("CurrentPoint");
				int reward = rs.getInt("RewardPoint");
				int customer_id = rs.getInt("CustomerID");

				Global.MEMBERSHIPCARD_ID = member_id;
				Global.SUBSCRIBED = subscription;
				Global.CURRENT_POINT = current;
				Global.REWARD_POINT = reward;
				Global.CUSTOMER_ID = customer_id;

				// For testing purposes, display result.
				membershipCard.setMembershipCardInfo();
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

}
