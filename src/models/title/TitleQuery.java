package models.title;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utilities.MyJDBCUtils;
import utilities.Global;
import abstracts.Genre;
import edits.title.CreateNewTitle;
import javax.swing.SwingUtilities;

/**
 * TitleQuery class encapsulate the notion of querying the title, create new titles.
 *  
 * @author Kok Heng
 *
 */

public class TitleQuery {

	/** Instantiate the new registration. */
	private CreateNewTitle createTitle = new CreateNewTitle();
	private Title title = new Title();

	/** Initial the JDBC connection driver URL. */
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

	/** Construct an empty constructor for catering instantiate the class object. */
	public TitleQuery() {
	}

	/*
	 * Querying a title from a database,
	 * display the result from querying.
	 */
	public void setSearchTitle() {
		// Get title (title name).
		String titleName = title.getTitleName();

		// check if title is in the database?
		if(queryTitle(titleName)) {
			System.out.printf("Title Found, Title Info Are Below:\n");
			title.setGenreTypeName();
		} else {
			System.out.printf("Title doesnt exists in system.\n");
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
		System.out.printf("Press c for create new title or n to return to the program\n");
		// Read user input by buffered reader.
		BufferedReader br = null;
		// read user input and store input value to this variable.
		String inputValue = "";

		try {
			// validate user input value as expected c or n.
			boolean getCorrectInput = false;
			br = new BufferedReader(new InputStreamReader(System.in));

			while(getCorrectInput == false) {
				// Reading in user input value.
				inputValue = br.readLine();

				/*
				 * Check what respond did user make.
				 * if 'c' for new registration.
				 * else if 'n' then return to the program.
				 */
				if(inputValue.equals("")) {
					// Prompt to enter again where field cannot be empty.
					System.out.printf("Input field must not be blanked, try again\n");
					getCorrectInput = false;
				} else if(inputValue.matches("[0-9]+")) {
					// Prompt to enter again where input value out of this range.
					System.out.printf("Input value must be letter only 'c' or 'n', try again\n");
					getCorrectInput = false;
				} else if(! inputValue.matches("[cCnN]+")) {
					// Prompt to enter again where input value out of this range.
					System.out.printf("Input value should within 'c' and 'n' only, try again\n");
					getCorrectInput = false;
				} else if(inputValue.equalsIgnoreCase("c")) {
					// Display a  creation form to the screen.
					SwingUtilities.invokeLater(new Runnable() {
					public void run() {
					createTitle.createRegistrationUI();
					}
					});
					getCorrectInput = true;
				} else if(inputValue.equalsIgnoreCase("n")) {
					title.getTitleInputKeyboard(); 
					setSearchTitle(); // reiterate the menu here for returning to home page.
										getCorrectInput = true;
				} else {
					// user didn't insert a correct value, prompt to try again.
					System.out.printf("YOU DIDN'T INSERT THE RIGHT OPTION, PLEASE TRY AGAIN.\n");
					getCorrectInput = false;
				}
			}
		} catch(Exception ex) {
			System.out.println("Errored! Invalid input, please try again");
			//ex.printStackTrace(); // for debugging purposes, print out suggested errors.
		}
	}

	/*
	 * Check if all entries are valid.
	 * All valid entries should return and upon titles status.
	 * @param titles the title name.
	 * @return true if title found else then false.
	 */
	public boolean queryTitle(String titles) {
		//Initial the database connection and its statement.

		Connection connection = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			// For testing purposes, this should be observation only.
			System.out.println("Searching title...");

			Class.forName(JDBC_DRIVER);
			connection = MyJDBCUtils.getConnection();

			// Create the prepare statement.
			String sql = "SELECT TitleName " +
					"FROM GenreType " +
					"WHERE TitleName LIKE ?";

			pstmt = connection.prepareStatement(sql);

			// Bind values into the parameter.
			pstmt.setString(1, "%"+titles+"%");

			// Let's update the row.

			/** For testing purposes, this should for observation only. */

			// Let us select all the records and display them.
			sql = "SELECT TitleID, Genre, TitleName, YearOfRelease, Format, NumbersInStock " +
					"FROM GenreType " +
					"WHERE TitleName LIKE '%" + titles + "%';";

			ResultSet rs = pstmt.executeQuery(sql);

			// Extract values from result set.
			while(rs.next()) {
				// Retrieve by column name.
				int title_id = rs.getInt("TitleID");
				String genre = rs.getString("Genre");
				String title_name = rs.getString("TitleName");
				int years = rs.getInt("YearOfRelease");
				String formats = rs.getString("Format");
				int numsInStock = rs.getInt("NumbersInStock");

				Global.TITLE_ID = title_id;
				Global.GENRE_NAME = genre;
				Global.TITLE_NAME = title_name;
				Global.TITLE_RELEASE_YEAR = years;
				Global.TITLE_FORMAT = formats;
				Global.TITLE_NUMBERSINSTOCK = numsInStock;

				// Let's update the row.
				if(title_name.matches("(.*)"+titles+"(.*)")) {
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
	 * Check if all entries are valid.
	 * All valid entries should return and upon title creation status.
	 * @param genre the genre type.
	 * @param titleName the title name.
	 * @param year the title years of release.
	 * @param format the title format e.g. CD, DVD.
	 * @param numsStock the numbers of stock of the title.
	 * @return true upon success or else then false.
	 */
	public boolean getTitleCreate(String genre, String titleName, int year, String format, int numsStock) {
		//Initial the database connection and its statement.
		Connection connection = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		try {
			// For testing purposes, this should be only observation.
			System.out.printf("Creating a new title...\n");

			Class.forName(JDBC_DRIVER);
			connection = MyJDBCUtils.getConnection();

			// Create the prepare statement.
			String sql = "INSERT INTO GenreType VALUES" +
					"(?, ?, ?, ?, ?, ?, ?)";

			pstmt = connection.prepareStatement(sql);

			// Bind values into the parameter.
			pstmt.setInt(1, 0);
			pstmt.setString(2, genre);
			pstmt.setString(3, titleName);
			pstmt.setInt(4, year);
			pstmt.setString(5, format);
			pstmt.setInt(6, numsStock);
			pstmt.setInt(7, 900003);

			/** Check if entries are valid. */
			int countRows = pstmt.executeUpdate();

			if(countRows > 0) {
				result = true;// return boolean values if column is greater than 1
			} else {
				result = false; // duplicated entries
			}

			// Let us select all the new records and display them.

			sql = "SELECT TitleID, Genre, TitleName, YearOfRelease, Format, NumbersInStock, CustomerID " +
					"FROM GenreType " +
					"WHERE TitleName LIKE '%" + titleName + "%';";

			ResultSet rs = pstmt.executeQuery(sql);

			// Extract values from result set.
			while(rs.next()) {
				// Retrieve by column name.
				int title_id = rs.getInt("TitleID");
				String genreType = rs.getString("Genre");
				String title_name = rs.getString("TitleName");
				int year_release = rs.getInt("YearOfRelease");
				String formats = rs.getString("Format");
				int nums_stock = rs.getInt("NumbersInStock");
				int customer_id = rs.getInt("CustomerID");

				Global.TITLE_ID = title_id;
				Global.GENRE_NAME = genreType;
				Global.TITLE_NAME = title_name;
				Global.TITLE_RELEASE_YEAR = year_release;
				Global.TITLE_FORMAT = formats;
				Global.TITLE_NUMBERSINSTOCK = nums_stock;
				Global.CUSTOMER_ID = customer_id;

				// For testing purposes, display result.
				title.setGenreTypeName();
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
