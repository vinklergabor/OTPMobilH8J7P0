import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.*;

public class DataLoad {
	private static final Logger logger = LoggerSetup.getLogger();
    
    public List<Customer> loadCustomers(String filename) {
        List<Customer> customers = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    customers.add(new Customer(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim()));
                    
                } else {
                	
                    logger.warning("Invalid customer data: " + line);
                }
            }
        } catch (IOException e) {
        	
        	logger.severe("Can't read customers data: " + e.getMessage());
        }
        
        return customers;
    }

    public List<Payment> loadPayments(String filename) {
        List<Payment> payments = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    payments.add(new Payment(parts[0].trim(), parts[1].trim(), parts[2].trim(),
                                             Integer.parseInt(parts[3].trim()), parts[4].trim(), parts[5].trim(), parts[6].trim()));
                } else {
                    logger.warning("Invalid payment data: " + line);
                }
            }
        } catch (IOException | NumberFormatException e) {
            logger.severe("Can't read payments data: " + e.getMessage());
        }
        return payments;
    }
}
