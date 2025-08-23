import java.io.*;
import java.util.*;

class Stock {
    String symbol;
    double price;

    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    void updatePrice() {
        double change = (Math.random() - 0.5) * 10; // -5 to +5 range
        price = Math.max(1, price + change);
    }
}

class Portfolio {
    Map<String, Integer> holdings = new HashMap<>();
    double cash = 10000; // initial balance

    void buyStock(Stock stock, int quantity) {
        double cost = stock.price * quantity;
        if (cost > cash) {
            System.out.println("❌ Not enough balance!");
            return;
        }
        cash -= cost;
        holdings.put(stock.symbol, holdings.getOrDefault(stock.symbol, 0) + quantity);
        System.out.println("✅ Bought " + quantity + " of " + stock.symbol);
    }

    void sellStock(Stock stock, int quantity) {
        if (!holdings.containsKey(stock.symbol) || holdings.get(stock.symbol) < quantity) {
            System.out.println("❌ Not enough shares to sell!");
            return;
        }
        holdings.put(stock.symbol, holdings.get(stock.symbol) - quantity);
        cash += stock.price * quantity;
        System.out.println("✅ Sold " + quantity + " of " + stock.symbol);
    }

    void showPortfolio(Map<String, Stock> market) {
        System.out.println("\n--- Portfolio ---");
        System.out.println("Cash: $" + cash);
        double totalValue = cash;
        for (String sym : holdings.keySet()) {
            int qty = holdings.get(sym);
            double val = qty * market.get(sym).price;
            System.out.println(sym + " x " + qty + " = $" + val);
            totalValue += val;
        }
        System.out.println("Net Worth: $" + totalValue);
    }
}

public class StockTradingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Sample market
        Map<String, Stock> market = new HashMap<>();
        market.put("AAPL", new Stock("AAPL", 150));
        market.put("GOOG", new Stock("GOOG", 2800));
        market.put("TSLA", new Stock("TSLA", 700));

        Portfolio portfolio = new Portfolio();

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Show Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. Show Portfolio");
            System.out.println("5. Update Market Prices");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\n--- Market ---");
                    for (Stock s : market.values()) {
                        System.out.println(s.symbol + " : $" + s.price);
                    }
                    break;

                case 2:
                    System.out.print("Enter symbol: ");
                    String buySym = sc.next();
                    System.out.print("Enter quantity: ");
                    int buyQty = sc.nextInt();
                    if (market.containsKey(buySym)) {
                        portfolio.buyStock(market.get(buySym), buyQty);
                    } else {
                        System.out.println("❌ Invalid symbol!");
                    }
                    break;

                case 3:
                    System.out.print("Enter symbol: ");
                    String sellSym = sc.next();
                    System.out.print("Enter quantity: ");
                    int sellQty = sc.nextInt();
                    if (market.containsKey(sellSym)) {
                        portfolio.sellStock(market.get(sellSym), sellQty);
                    } else {
                        System.out.println("❌ Invalid symbol!");
                    }
                    break;

                case 4:
                    portfolio.showPortfolio(market);
                    break;

                case 5:
                    for (Stock s : market.values()) {
                        s.updatePrice();
                    }
                    System.out.println("📈 Market prices updated!");
                    break;

                case 6:
                    System.out.println("Goodbye!");
                    return;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }
    }
}
