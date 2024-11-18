import java.io.*;
import java.util.*;
public class Task4 {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the base currency (e.g., USD): ");
            String baseCurrency = scanner.nextLine().toUpperCase();
            System.out.println("Enter the target currency (e.g., INR): ");
            String targetCurrency = scanner.nextLine().toUpperCase();
            Map<String, Double> exchangeRates = loadExchangeRates("exchange_rates.csv");
            if (exchangeRates.isEmpty()) {
                System.out.println("Exchange rates file not found, using hardcoded values.");
                exchangeRates.putAll(getHardcodedRates());
            }
            String currencyPair = baseCurrency + "," + targetCurrency;
            if (!exchangeRates.containsKey(currencyPair)) {
                System.out.println("Exchange rate not found for this pair.");
                return;
            }
            double exchangeRate = exchangeRates.get(currencyPair);
            System.out.println("Enter the amount to convert: ");
            double amount = scanner.nextDouble();
            double convertedAmount = amount * exchangeRate;
            System.out.printf("Converted Amount: %.2f %s%n", convertedAmount, targetCurrency);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static Map<String, Double> loadExchangeRates(String filename) {
        Map<String, Double> rates = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String pair = data[0] + "," + data[1];
                double rate = Double.parseDouble(data[2]);
                rates.put(pair, rate);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File not found, falling back to manual or hardcoded rates.");
        }
        return rates;
    }
    private static Map<String, Double> getHardcodedRates() {
        Map<String, Double> rates = new HashMap<>();
        rates.put("USD,INR", 82.0);
        rates.put("USD,EUR", 0.92);
        rates.put("INR,USD", 0.012);
        rates.put("EUR,INR", 89.0);
        rates.put("USD,GBP", 0.76);
        rates.put("GBP,USD", 1.32);
        return rates;
    }
}