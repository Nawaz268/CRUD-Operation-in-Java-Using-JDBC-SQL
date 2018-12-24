import java.sql.Connection;
import java.util.Collection;
//import java.util.Collection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Assignment2 {

	public static MySQLAccess m;

	public static void main(String[] args) throws Exception {
		final long startTime = System.currentTimeMillis(); // START TIMER
		System.setProperty("java.util.logging.config.file", "./logging.properties");
		MySQLAccess dao = new MySQLAccess();

		try {
			
			Transaction t = new Transaction();

			Connection connection = dao.setupConnection();

			while (true) {
				
				System.out.println("Press\n(1) CREATE\n(2) GET\n(3) DELETE\n(4) UPDATE\n(5) SHOW ALL RECORDS\n(6) EXIT\n ");
				Scanner in = new Scanner(System.in);
				int select = in.nextInt();
				

				if (select == 1) {
					
					
					
					dao.createTransaction(connection, t);
					
					

				}
				if (select == 2) {

					System.out.println("ID :\n");
					int id = in.nextInt();
					t.setiD(id);
					t= dao.getTransaction(t, connection);
					Logger.getAnonymousLogger().log(Level.INFO, "VIEW FUNCTION CALLED");
					System.out.println(t.toString() + "\n");

				}
				if (select == 3) {
					
						System.out.println("ID : \n");
						
						int id = in.nextInt();
						t.setiD(id);
						dao.removeTransaction(t , connection);
						Logger.getAnonymousLogger().log(Level.INFO, "DELETE FUNCTION CALLED");
						
				}
				if (select == 4) {
					System.out.println("ID :\n ");
					int id = in.nextInt();
					t.setiD(id);
					dao.updateTransaction( t,connection);
					Logger.getAnonymousLogger().log(Level.INFO, "UPDATE FUNCTION CALLED");
				}

				if (select == 5) {
					Logger.getAnonymousLogger().log(Level.INFO, "SHOW ALL RECORDS FUNCTION CALLED");
					Collection<Transaction> trxns = dao.getAllTransactions(connection);
					
					for (Transaction trxn : trxns) {
						Logger.getAnonymousLogger().log(Level.INFO, trxn.toString());
						
						System.out.println(trxn.toString());
					}
				}

				if (select == 6) {
					
					Logger.getAnonymousLogger().log(Level.INFO, "EXIT FUNCTION CALLED");
					in.close();
					break;
				}
				
			}

		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getAnonymousLogger().log(Level.SEVERE, "EXCEPTION IN MAIN OF ASSIGNMENT2.JAVA");
		}
		
		final long endTime = System.currentTimeMillis();
		Logger.getLogger("Main").log(Level.INFO, "Total execution time: " + (endTime - startTime) + " ms");
	}

}
