
public class Transaction {

	private int iD = 0;
	
	private String nameOnCard;
	
	private String cardNumber;
	
	private double unitPrice = 0.0;
	
	private int quantity;
	
	private double totalPrice;
	
	private String expDate;
	
	private String createdOn;
	
	private String createdBy;
	
	public String getCardType() {
		return CardType;
	}
	public void setCardType(String cardType) {
		CardType = cardType;
	}

	private String CardType;
	
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}	
	
	public String toString(){
		
		String results = new String();
		
		results = "[ID: " + iD + " , NameOnCard: " + nameOnCard
				+" , CardNumber: " + cardNumber+" , UnitPrice: " 
				+ unitPrice + " , Quantity: " + quantity +
				" , TotalPrice: " + totalPrice + " , ExpDate : " 
				+ expDate + " , Created On " + createdOn + " , Created By: " + createdBy + " , Card Type  " + CardType + "]";
		return results;

	}
	
	
}
