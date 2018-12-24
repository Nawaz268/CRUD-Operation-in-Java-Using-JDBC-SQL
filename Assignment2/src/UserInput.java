
import java.util.Scanner;

public class UserInput {

	public Transaction createEntry(Transaction trxn) throws Exception {

		try {
			Scanner in = new Scanner(System.in);

			System.out.println("ENTER YOUR ID: \n");
			int id = in.nextInt();
			trxn.setiD(id);

			System.out.println("ENTER YOUR NAME: \n");
			String nameOnCard = in.next();
			while (nameOnCard.matches(".*[;:!@#$%^*+?<>].*")) {
				System.out.println("INVALID NAME, PLEASE RE-ENTER\n");
				nameOnCard = in.next();
			}
			trxn.setNameOnCard(nameOnCard);

			System.out.println("ENTER CARD NUMBER: \n");
			String cardNumber = in.next();
			while (cardNumber.matches(".*[;:!@#$%^*+?<>].*")) {
				System.out.println("INVALID NUMBER \n");
				cardNumber = in.next();
			}
			trxn.setCardNumber(cardNumber);

			String CardType = "";
			if (cardNumber.matches("^[5][1-5].*") && cardNumber.length() == 16) {
				CardType = "MasterCard";
				trxn.setCardType(CardType);

			} else if (cardNumber.matches("^[4].*") && cardNumber.length() == 16) {
				CardType = "Visa";
				trxn.setCardType(CardType);

			} else if (cardNumber.matches("^[3][4|7].*") && cardNumber.length() == 15) {
				CardType = "American Express";
				trxn.setCardType(CardType);

			} else {
				CardType = "Other";
				trxn.setCardType(CardType);
			}

			System.out.println("TYPE NEW UNIT PRICE \n");
			float price = in.nextFloat();
			trxn.setUnitPrice(price);

			System.out.println("\n TYPE NEW QUANTITY \n");
			int quantity = in.nextInt();
			trxn.setQuantity(quantity);
			
			

			System.out.println("TYPE EXPIRY DATE AS FOLLOWS" + ": \nMONTH(MM): \n");
			int month = in.nextInt();
			while (month > 12 || month < 0) {
				System.out.println("INCORRECT MONTH ENTERRED");
				month = in.nextInt();
			}

			System.out.println("YEAR(YYYY): \n");
			int year = in.nextInt();
			while (year < 2015 || year > 2032) {
				System.out.println("INCORRECT YEAR ENTERRED");
				year = in.nextInt();
			}

			String expiryDate = month + "/" + year;

			if (expiryDate.matches(".*[;:!@#$%^*+?<>].*")) {
				System.out.println("\nINVALID EXPIRY DATE ENTERED \n");

			}

			trxn.setExpDate(expiryDate);

			float totalPrice = price * quantity;
			trxn.setTotalPrice(totalPrice);

			String username = System.getProperty("user.name");
			trxn.setCreatedBy(username);

			return trxn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return trxn;

	}

	public Transaction updateEntry(Transaction trxn) {

		Boolean quantity = false;
		Boolean unitPrice = false;
		String NameOnCard = "";
		String CardNumber = "";

		try {
			Scanner user_input = new Scanner(System.in);
			System.out.println("\nPress y for the fields you want to update, otherwise N\n");

			System.out.println("Name on Card");
			char a = user_input.next().charAt(0);
			if (a == 'y') {
				NameOnCard = user_input.next();

				while (NameOnCard.matches(".*[;:!@#$%^*+?<>].*")) {
					System.out.println("SORRY, INVALID NAME INPUTTED, TYPE AGAIN");
					NameOnCard = user_input.next();
				}
				trxn.setNameOnCard(NameOnCard);
			}

			a = user_input.next().charAt(0);

			if (a == 'y') {
				CardNumber = user_input.next();
				System.out.println(trxn.getCardNumber());
				while (CardNumber.matches(".*[;:!@#$%^*+?<>].*")) {
					System.out.println("SORRY, INVALID CARD NUMBER INPUTTED, TYPE AGAIN");
					CardNumber = user_input.next();
				}

				trxn.setCardNumber(CardNumber);

			}

			if (CardNumber.matches("^[5][1-5].*") && CardNumber.length() == 16) {
				trxn.setCardType("MasterCard");
			} else if (CardNumber.matches("^[4].*") && CardNumber.length() == 16) {
				trxn.setCardType("Visa");
			} else if (CardNumber.matches("^[3][4|7].*") && CardNumber.length() == 15) {
				trxn.setCardType("American Express");
			} else {
				trxn.setCardType("Other");
			}

			System.out.println("Unit Price");
			a = user_input.next().charAt(0);
			if (a == 'y') {
				trxn.setUnitPrice(user_input.nextDouble());
				unitPrice = true;

			}
			System.out.println("Quantity");
			a = user_input.next().charAt(0);
			if (a == 'y') {
				trxn.setQuantity(user_input.nextInt());
				quantity = true;
			}

			System.out.println("Exp Date");
			a = user_input.next().charAt(0);
			if (a == 'y') {
				System.out.println("TYPE EXPIRY DATE AS FOLLOWS" + ": \nMONTH(MM): \n");

				int month = user_input.nextInt();
				while (month > 12 || month < 0) {
					System.out.println("Enter correct month");
					month = user_input.nextInt();
				}

				System.out.println("\nYEAR(YYYY): \n");
				int year = user_input.nextInt();
				while (year < 2015 || year > 2032) {
					System.out.println("INCORRECT YEAR ENTERRED");
					year = user_input.nextInt();
				}

				String expiryDate = month + "/" + year;
				if (expiryDate.matches(".*[;:!@#$%^*+?<>].*")) {
					System.out.println("SORRY INVALID EXPIRY DATE");

				}
				trxn.setExpDate(expiryDate);

			}

			if ((quantity == true) || (unitPrice == true)) {
				double total = trxn.getUnitPrice() * trxn.getQuantity();
				trxn.setTotalPrice(total);
			}

			return trxn;

		}

		catch (

		Exception e) {
			e.printStackTrace();
		}
		return trxn;

	}
}