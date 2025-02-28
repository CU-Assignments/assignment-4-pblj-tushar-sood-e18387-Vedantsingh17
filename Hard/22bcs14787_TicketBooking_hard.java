import java.util.*;

class TicketBookingSystem {
    private int availableSeats;

    public TicketBookingSystem(int seats) {
        this.availableSeats = seats;
    }

    public synchronized boolean bookTicket(String user, int requestedSeats) {
        if (requestedSeats <= availableSeats) {
            System.out.println(user + " booked " + requestedSeats + " seat(s).");
            availableSeats -= requestedSeats;
            return true;
        } else {
            System.out.println(user + " failed to book. Not enough seats.");
            return false;
        }
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
}

class User extends Thread {
    private TicketBookingSystem system;
    private int requestedSeats;
    private String userType;

    public User(String name, TicketBookingSystem system, int requestedSeats, String userType, int priority) {
        super(name);
        this.system = system;
        this.requestedSeats = requestedSeats;
        this.userType = userType;
        this.setPriority(priority);  // Set thread priority (higher for VIPs)
    }

    @Override
    public void run() {
        system.bookTicket(getName() + " (" + userType + ")", requestedSeats);
    }
}

public class TicketBooking {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(5); // 5 seats available

        User vip1 = new User("Deep", system, 2, "VIP", Thread.MAX_PRIORITY);
        User normal1 = new User("Raj", system, 1, "Normal", Thread.NORM_PRIORITY);
        User vip2 = new User("Shivam", system, 1, "VIP", Thread.MAX_PRIORITY);
        User normal2 = new User("Lokesh", system, 2, "Normal", Thread.NORM_PRIORITY);

        vip1.start();
        normal1.start();
        vip2.start();
        normal2.start();
    }
}
