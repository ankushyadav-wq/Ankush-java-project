import java.util.ArrayList;
import java.util.Scanner;

// Parent Class
class Courier {

    private int trackingId;
    private String senderName;
    private String receiverName;
    private String status;

    // Constructor
    public Courier(int trackingId, String senderName,
                   String receiverName, String status) {

        this.trackingId = trackingId;
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.status = status;
    }

    // Getters
    public int getTrackingId() {
        return trackingId;
    }

    public String getStatus() {
        return status;
    }

    // Setter
    public void setStatus(String status) {
        this.status = status;
    }

    // Display Method
    public void displayCourier() {

        System.out.println("\n----- Courier Details -----");
        System.out.println("Tracking ID : " + trackingId);
        System.out.println("Sender Name : " + senderName);
        System.out.println("Receiver Name : " + receiverName);
        System.out.println("Status : " + status);
    }
}

// Child Class (Inheritance)
class ExpressCourier extends Courier {

    private double extraCharge;

    public ExpressCourier(int trackingId,
                          String senderName,
                          String receiverName,
                          String status,
                          double extraCharge) {

        super(trackingId, senderName, receiverName, status);
        this.extraCharge = extraCharge;
    }

    // Method Overriding (Polymorphism)
    @Override
    public void displayCourier() {

        super.displayCourier();
        System.out.println("Extra Charge : " + extraCharge);
    }
}

// Service Class
class DeliveryService {

    ArrayList<Courier> couriers = new ArrayList<>();

    // Add Courier
    public void addCourier(Courier courier) {

        couriers.add(courier);
        System.out.println("\nCourier Added Successfully!");
    }

    // Track Courier
    public void trackCourier(int trackingId) {

        boolean found = false;

        for (Courier c : couriers) {

            if (c.getTrackingId() == trackingId) {

                c.displayCourier();
                found = true;
            }
        }

        if (!found) {
            System.out.println("\nCourier Not Found!");
        }
    }

    // Update Status
    public void updateStatus(int trackingId, String newStatus) {

        for (Courier c : couriers) {

            if (c.getTrackingId() == trackingId) {

                c.setStatus(newStatus);

                System.out.println("\nStatus Updated Successfully!");
                return;
            }
        }

        System.out.println("\nCourier Not Found!");
    }

    // View All Couriers
    public void viewAllCouriers() {

        if (couriers.isEmpty()) {

            System.out.println("\nNo Courier Available!");
            return;
        }

        for (Courier c : couriers) {

            c.displayCourier();
            System.out.println("---------------------------");
        }
    }
}

// Main Class
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DeliveryService service = new DeliveryService();

        while (true) {

            System.out.println("\n========== Courier Delivery Tracking System ==========");
            System.out.println("1. Add Normal Courier");
            System.out.println("2. Add Express Courier");
            System.out.println("3. Track Courier");
            System.out.println("4. Update Courier Status");
            System.out.println("5. View All Couriers");
            System.out.println("6. Exit");

            System.out.print("Enter Your Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    sc.nextLine();

                    System.out.print("Enter Tracking ID : ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Sender Name : ");
                    String sender = sc.nextLine();

                    System.out.print("Enter Receiver Name : ");
                    String receiver = sc.nextLine();

                    System.out.print("Enter Status : ");
                    String status = sc.nextLine();

                    Courier courier = new Courier(
                            id,
                            sender,
                            receiver,
                            status
                    );

                    service.addCourier(courier);

                    break;

                case 2:

                    sc.nextLine();

                    System.out.print("Enter Tracking ID : ");
                    int exId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Sender Name : ");
                    String exSender = sc.nextLine();

                    System.out.print("Enter Receiver Name : ");
                    String exReceiver = sc.nextLine();

                    System.out.print("Enter Status : ");
                    String exStatus = sc.nextLine();

                    System.out.print("Enter Extra Charge : ");
                    double charge = sc.nextDouble();

                    ExpressCourier expressCourier =
                            new ExpressCourier(
                                    exId,
                                    exSender,
                                    exReceiver,
                                    exStatus,
                                    charge
                            );

                    service.addCourier(expressCourier);

                    break;

                case 3:

                    System.out.print("Enter Tracking ID : ");
                    int trackId = sc.nextInt();

                    service.trackCourier(trackId);

                    break;

                case 4:

                    System.out.print("Enter Tracking ID : ");
                    int updateId = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter New Status : ");
                    String newStatus = sc.nextLine();

                    service.updateStatus(updateId, newStatus);

                    break;

                case 5:

                    service.viewAllCouriers();

                    break;

                case 6:

                    System.out.println("\nThank You!");
                    System.exit(0);

                default:

                    System.out.println("\nInvalid Choice!");
            }
        }
    }
}
