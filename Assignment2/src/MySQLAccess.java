
/**
 * Original source code from 
 * http://www.vogella.com/tutorials/MySQLJava/article.html
 * 
**/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLAccess {
	Transaction trxn = new Transaction();

	public Connection setupConnection() throws Exception {

		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection("j" + "dbc:mysql://localhost:3306/JavaAssignment02?useUnicode=t"
					+ "rue&useJDBCCompliantTimezoneShift=" + "true&useLegacyDatet" + "imeCode=false&server"
					+ "Timezone=UTC", "root", "Arsenal20!");

			Logger.getAnonymousLogger().log(Level.INFO, "CONNECTED TO DATABASE");

		} catch (Exception e) {
			Logger.getAnonymousLogger().log(Level.SEVERE,
					"EXCEPTION AT CONNECTION IN MYSQLACCESS.JAVA, COULD NOT CONNECT TO DB");
			throw e;

		} finally {

		}
		return connection;
	}

	public Collection<Transaction> getAllTransactions(Connection connection) {
		Statement statement = null;
		ResultSet resultSet = null;
		Collection<Transaction> results = null;
		// Result set get the result of the SQL query
		try {
			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from transaction");
			Logger.getAnonymousLogger().log(Level.INFO, "SELECTED ALL FIELDS FROM DATABASE");

			results = createTrxns(resultSet);

			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getAnonymousLogger().log(Level.SEVERE, "EXCEPTION AT GETALLTRANSACTIONS() IN MYSQLACCESS");
			e.printStackTrace();
		} finally {
			statement = null;
			resultSet = null;
		}
		return results;

	}

	private Collection<Transaction> createTrxns(ResultSet resultSet) throws SQLException {
		Collection<Transaction> results = new ArrayList<Transaction>();

		// ResultSet is initially before the first data set
		while (resultSet.next()) {

			Transaction trxn = new Transaction();
			trxn.setiD(resultSet.getInt(1));
			trxn.setNameOnCard(resultSet.getString(2));
			trxn.setCardNumber(resultSet.getString(3));
			trxn.setUnitPrice(resultSet.getFloat(4));
			trxn.setQuantity(resultSet.getInt(5));
			trxn.setTotalPrice(resultSet.getFloat(6));
			trxn.setExpDate(resultSet.getString(7));
			trxn.setCreatedOn(resultSet.getString(8));
			trxn.setCreatedBy(resultSet.getString(9));
			trxn.setCardType(resultSet.getString(10));

			results.add(trxn);

		}

		return results;

	}

	public Transaction getTransaction(Transaction trxn, Connection connection) throws SQLException {

		Statement statement = null;
		ResultSet resultSet = null;

		try {

			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from transaction");
			Logger.getAnonymousLogger().log(Level.INFO, "SELECTED ALL COLUMNS FROM TRANSACTION");

			while (resultSet.next()) {
				int currentID = resultSet.getInt(1);
				if (trxn.getiD() == currentID) {
					statement = connection.createStatement();
					trxn.setiD(resultSet.getInt(1));
					trxn.setNameOnCard(resultSet.getString(2));
					trxn.setCardNumber(resultSet.getString(3));
					trxn.setUnitPrice(resultSet.getFloat(4));
					trxn.setQuantity(resultSet.getInt(5));
					trxn.setTotalPrice(resultSet.getFloat(6));
					trxn.setExpDate(resultSet.getString(7));
					trxn.setCreatedOn(resultSet.getString(8));
					trxn.setCreatedBy(resultSet.getString(9));
					trxn.setCardType(resultSet.getString(10));

				}

			}
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return trxn;

	}

	public boolean updateTransaction(Transaction trxn, Connection connection) throws SQLException {
		System.out.println(trxn.getiD());
		int count = 0;
		Statement statement = null;
		ResultSet resultSet = null;
		Scanner in = new Scanner(System.in);
		Boolean check = false;
		UserInput ui = new UserInput();
		trxn = ui.updateEntry(trxn);

		System.out.println(trxn.getiD());

		// Result set get the result of the SQL query
		try {
			// Statements allow to issue SQL queries to the database
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from transaction");

			while (resultSet.next()) {
				int currentID = resultSet.getInt(1);
				if (trxn.getiD() == currentID) {
					count++;

				}
			}

			if (count == 0) {
				System.out.println("\nID NOT FOUND. PLEASE USE CREATE INSTEAD \n");
				check = false;
				return check;
				
			} else {

				System.out.println(trxn.getNameOnCard());
				System.out.println(trxn.getCardNumber());
				System.out.println(trxn.getUnitPrice());
				System.out.println(trxn.getQuantity());
				System.out.println(trxn.getCardType());
				
				
				if (trxn.getNameOnCard() != null) {
					

					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE Transaction Set NameOnCard=? WHERE ID=?");
					preparestatement.setString(1, trxn.getNameOnCard());

					preparestatement.setInt(2, trxn.getiD());
					preparestatement.execute();
					System.out.println("\nUPDATE SUCCESS.\n");
				}

				if (trxn.getCardNumber() != null) {

					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE Transaction Set CardNumber=? WHERE ID=?");
					preparestatement.setString(1, trxn.getCardNumber());
					preparestatement.setInt(2, trxn.getiD());
					preparestatement.execute();
					System.out.println("\nUPDATE SUCCESS.\n");
				}

				if (trxn.getUnitPrice() != 0.0) {

					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE Transaction Set UnitPrice=? WHERE ID=?");
					preparestatement.setDouble(1, trxn.getUnitPrice());
					preparestatement.setInt(2, trxn.getiD());
					preparestatement.execute();
					System.out.println("\nUPDATE SUCCESS.\n");
				}

				if (trxn.getQuantity() != 0) {

					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE transaction Set Quantity=? WHERE ID=?");
					preparestatement.setInt(1, trxn.getQuantity());
					preparestatement.setInt(2, trxn.getiD());
					preparestatement.execute();
					System.out.println("\nUPDATE SUCCESS.\n");
				}

				if (trxn.getCardType() != null) {

					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE transaction Set CardType=? WHERE ID=?");
					preparestatement.setString(1, trxn.getCardType());
					preparestatement.setInt(2, trxn.getiD());
					preparestatement.execute();
					System.out.println("\nUPDATE SUCCESS.\n");
				}

				if (((trxn.getQuantity() != 0) || (trxn.getQuantity() != 0))) {
					double currentTotal = (trxn.getUnitPrice() * trxn.getQuantity());
					trxn.setTotalPrice(currentTotal);
					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE transaction Set TotalPrice=? WHERE ID=?");
					preparestatement.setDouble(1, trxn.getTotalPrice());
					preparestatement.setInt(2, trxn.getiD());
					preparestatement.execute();
				}

				if (trxn.getExpDate() != null) {

					PreparedStatement preparestatement = connection
							.prepareStatement("UPDATE transaction Set ExpDate=? WHERE ID=?");
					preparestatement.setString(1, trxn.getExpDate());
					preparestatement.setInt(2, trxn.getiD());
					preparestatement.execute();
					System.out.println("\nUPDATE SUCCESS.\n");
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getAnonymousLogger().log(Level.SEVERE, "EXCEPTION AT MYSQLACCESS.JAVA");
			e.printStackTrace();
		}
		return true;
	}

	public boolean removeTransaction(Transaction trxn, Connection connection) throws SQLException {
		try {

			Statement statement = null;
			ResultSet resultSet = null;
			int count = 0;
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from transaction");

			while (resultSet.next()) {
				int currentID = resultSet.getInt(1);
				if (trxn.getiD() == currentID) {
					count++;

				}
			}
			if (count == 0) {

				System.out.println("\nWRONG ID INPUT \n");

			} else {

				PreparedStatement preparestatement = connection.prepareStatement("Delete from transaction where ID=?");
				preparestatement.setInt(1, trxn.getiD());
				preparestatement.execute();
				System.out.println("\n DELETE SUCCESS \n");
			}

		} catch (SQLException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "EXCEPTION AT MYSQLACCESS.JAVA");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean createTransaction(Connection connection, Transaction trxn) throws Exception {

		int count = 0;
		boolean check = false;
		Scanner in = new Scanner(System.in);
		Statement statement = null;
		ResultSet resultSet = null;
		UserInput ui = new UserInput();
		trxn = ui.createEntry(trxn);
		// Statements allow to issue SQL queries to the database
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select * from transaction");

		while (resultSet.next()) {
			int currentID = resultSet.getInt(1);
			if (trxn.getiD() == currentID) {
				count++;

			}
		}

		try {

			if (count == 0) {

				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into  transaction values (?, ?, ?, ? , ?, ?,?,now(),?,?)");

				preparedStatement.setInt(1, trxn.getiD());
				preparedStatement.setString(2, trxn.getNameOnCard());
				preparedStatement.setString(3, trxn.getCardNumber());
				preparedStatement.setDouble(4, trxn.getUnitPrice());
				preparedStatement.setInt(5, trxn.getQuantity());
				preparedStatement.setDouble(6, trxn.getTotalPrice());
				preparedStatement.setString(7, trxn.getExpDate());
				preparedStatement.setString(8, trxn.getCreatedBy());
				preparedStatement.setString(9, trxn.getCardType());
				preparedStatement.executeUpdate();
				System.out.println("SUCCESS \n");
				check = true;
			}

			else {
				System.out.println("ID ALREADY EXISTS. PLEASE USE UPDATE INSTEAD  \n\n");

				check = false;
				return check;

			}

		} catch (SQLException e) {
			Logger.getAnonymousLogger().log(Level.SEVERE, "EXCEPTION AT MYSQLACCESS.JAVA");

			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			statement = null;
			resultSet = null;
		}

		return check;
	}
}
