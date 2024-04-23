import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {
    	
        DataLoad dataset = new DataLoad();
        List<Customer> customers = dataset.loadCustomers(System.getProperty("user.dir")+ File.separator + "customers.csv");
        List<Payment> payments = dataset.loadPayments(System.getProperty("user.dir")+ File.separator + "payments.csv");

        Reporting report = new Reporting();
        report.generateCustomerReport(customers, payments);
        report.generateWebshopReport(payments);
    }
}