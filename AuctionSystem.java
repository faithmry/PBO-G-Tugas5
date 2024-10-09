import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Item {
    int id;
    String name;
    String description;
    double startingPrice;
    double currentBid;
    Bidder highestBidder;

    public Item(int id, String name, String description, double startingPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startingPrice = startingPrice;
        this.currentBid = startingPrice;
        this.highestBidder = null;
    }
}

class Bidder {
    int id;
    String name;

    public Bidder(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

public class AuctionSystem {
    private List<Item> items;
    private Map<Integer, Bidder> bidders;

    public AuctionSystem() {
        items = new ArrayList<>();
        bidders = new HashMap<>();
    }

    public void addItem(int id, String name, String description, double startingPrice) {
        Item item = new Item(id, name, description, startingPrice);
        items.add(item);
    }

    public void registerBidder(int id, String name) {
        Bidder bidder = new Bidder(id, name);
        bidders.put(id, bidder);
    }

    public void placeBid(int itemId, int bidderId, double bidAmount) {
        Item item = findItemById(itemId);
        Bidder bidder = bidders.get(bidderId);

        if (item != null && bidder != null) {
            if (bidAmount > item.currentBid) {
                item.currentBid = bidAmount;
                item.highestBidder = bidder;
                System.out.println("Bid placed successfully.");
            } else {
                System.out.println("Bid amount must be higher than the current bid.");
            }
        } else {
            System.out.println("Invalid item or bidder ID.");
        }
    }

    public void showItems() {
        for (Item item : items) {
            System.out.println("Item ID: " + item.id + ", Name: " + item.name + ", Current Bid: " + item.currentBid
                    + ", Highest Bidder: " + (item.highestBidder != null ? item.highestBidder.name : "None"));
        }
    }

    public void showBidders() {
        for (Bidder bidder : bidders.values()) {
            System.out.println("Bidder ID: " + bidder.id + ", Name: " + bidder.name);
        }
    }

    private Item findItemById(int id) {
        for (Item item : items) {
            if (item.id == id) {
                return item;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        AuctionSystem auctionSystem = new AuctionSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1. Register Bidder");
            System.out.println("2. Add Item");
            System.out.println("3. Place Bid");
            System.out.println("4. Show Items");
            System.out.println("5. Show Bidders");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.print("Enter Bidder ID: ");
                    int bidderId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Bidder Name: ");
                    String bidderName = scanner.nextLine();
                    auctionSystem.registerBidder(bidderId, bidderName);
                    break;
                case 2:
                    System.out.print("Enter Item ID: ");
                    int itemId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Item Name: ");
                    String itemName = scanner.nextLine();
                    System.out.print("Enter Item Description: ");
                    String itemDescription = scanner.nextLine();
                    System.out.print("Enter Starting Price: ");
                    double startingPrice = scanner.nextDouble();
                    auctionSystem.addItem(itemId, itemName, itemDescription, startingPrice);
                    break;
                case 3:
                    System.out.print("Enter Item ID: ");
                    int bidItemId = scanner.nextInt();
                    System.out.print("Enter Bidder ID: ");
                    int bidBidderId = scanner.nextInt();
                    System.out.print("Enter Bid Amount: ");
                    double bidAmount = scanner.nextDouble();
                    auctionSystem.placeBid(bidItemId, bidBidderId, bidAmount);
                    break;
                case 4:
                    auctionSystem.showItems();
                    break;
                case 5:
                    auctionSystem.showBidders();
                    break;
                case 6:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}