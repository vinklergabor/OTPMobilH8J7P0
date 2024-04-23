import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Reporting {
	private static final Logger logger = LoggerSetup.getLogger();
	
    public void generateCustomerReport(List<Customer> customers, List<Payment> payments) {
        Map<String, Customer> customerMap = new HashMap<>();
        for (Customer customer : customers) {
            customerMap.put(customer.getWebshopId() + "-" + customer.getCustomerId(), customer);
        }

        Map<String, Integer> spending = new HashMap<>();
        for (Payment payment : payments) {
            String key = payment.getWebshopId() + "-" + payment.getCustomerId();
            spending.put(key, spending.getOrDefault(key, 0) + payment.getAmount());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("report01.csv"))) {
            writer.println("NAME,ADDRESS,vásárlás összesen");
            for (Map.Entry<String, Integer> entry : spending.entrySet()) {
                Customer customer = customerMap.get(entry.getKey());
                writer.println(customer.getName() + "," + customer.getAddress() + "," + entry.getValue());
            }
        } catch (IOException e) {
            logger.severe("Can't write customer report: " + e.getMessage());
        }
        
        List<Map.Entry<String, Integer>> sortedSpenders = spending.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(2)
                .collect(Collectors.toList());

            try (PrintWriter writer = new PrintWriter(new FileWriter("top.csv"))) {
                writer.println("NAME, ADDRESS, vásárlás összesen");
                for (Map.Entry<String, Integer> entry : sortedSpenders) {
                    Customer customer = customerMap.get(entry.getKey());
                    if (customer != null) {
                        writer.println(customer.getName() + "," + customer.getAddress() + "," + entry.getValue());
                    }
                }
            } catch (IOException e) {
                logger.severe("Can't write top spenders report: " + e.getMessage());
            }
        }
    

    public void generateWebshopReport(List<Payment> payments) {
        Map<String, int[]> totals = new HashMap<>();
        for (Payment payment : payments) {
            int[] sums = totals.getOrDefault(payment.getWebshopId(), new int[2]);
            if (payment.getPaymentMethod().equals("card")) {
                sums[0] += payment.getAmount();
            } else if (payment.getPaymentMethod().equals("transfer")) {
                sums[1] += payment.getAmount();
            }
            totals.put(payment.getWebshopId(), sums);
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("report02.csv"))) {
            writer.println("WEBSHOP, kártyás vásárlások összege, átutalásos vásárlások összege");
            for (Map.Entry<String, int[]> entry : totals.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue()[0] + "," + entry.getValue()[1]);
            }
        } catch (IOException e) {
            logger.severe("Can't write webshop report: " + e.getMessage());
        }
    }
}
