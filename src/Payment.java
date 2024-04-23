public class Payment {
    private String webshopId;
    private String customerId;
    private String paymentMethod;
    private int amount;
    private String bankAccountNumber;
    private String cardNumber;
    private String paymentDate;

    public Payment(String webshopId, String customerId, String paymentMethod, int amount, 
            String bankAccountNumber, String cardNumber, String paymentDate) {
    		this.webshopId = webshopId;
    		this.customerId = customerId;
 			this.paymentMethod = paymentMethod;
 			this.amount = amount;
 			this.bankAccountNumber = bankAccountNumber;
 			this.cardNumber = cardNumber;
 			this.paymentDate = paymentDate;
}
    public String getWebshopId() {
        return webshopId;
    }

    public void setWebshopId(String webshopId) {
        this.webshopId = webshopId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }
}