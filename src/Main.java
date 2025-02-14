import javax.swing.*;
//import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Hotel Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        JButton manageRoomsButton = new JButton("Manage Rooms");
        JButton manageCustomersButton = new JButton("Manage Customers");
        JButton manageReservationsButton = new JButton("Manage Reservations");
        //JButton generateBillButton = new JButton("Generate Bill");

        manageRoomsButton.addActionListener(e -> manageRooms());
        manageCustomersButton.addActionListener(e -> manageCustomers());
        manageReservationsButton.addActionListener(e -> manageReservations());
        //generateBillButton.addActionListener(e -> generateBill());

        JPanel panel = new JPanel();
        panel.add(manageRoomsButton);
        panel.add(manageCustomersButton);
        panel.add(manageReservationsButton);
        //3panel.add(generateBillButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    // Room Management
    private static void manageRooms() {
        JFrame roomFrame = new JFrame("Room Management");
        roomFrame.setSize(400, 300);
        roomFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        roomFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JButton addRoomButton = new JButton("Add Room");
        JButton viewRoomsButton = new JButton("View Rooms");

        addRoomButton.addActionListener(e -> {
            String roomNumber = JOptionPane.showInputDialog("Enter Room Number:");
            String roomType = JOptionPane.showInputDialog("Enter Room Type:");
            String roomPriceStr = JOptionPane.showInputDialog("Enter Room Price:");

            double roomPrice = Double.parseDouble(roomPriceStr);
            Room room = new Room(0, roomNumber, roomType, roomPrice, true);

            try {
                RoomDAO roomDAO = new RoomDAO();
                roomDAO.addRoom(room);
                JOptionPane.showMessageDialog(roomFrame, "Room added successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(roomFrame, "Error adding room!");
            }
        });

        viewRoomsButton.addActionListener(e -> {
            try {
                RoomDAO roomDAO = new RoomDAO();
                List<Room> rooms = roomDAO.getAllRooms();

                StringBuilder sb = new StringBuilder();
                for (Room room : rooms) {
                    sb.append("Room Number: ").append(room.getRoomNumber())
                      .append(", Room Type: ").append(room.getRoomType())
                      .append(", Price: ").append(room.getRoomPrice())
                      .append(", Available: ").append(room.isAvailable())
                      .append("\n");
                }
                JOptionPane.showMessageDialog(roomFrame, sb.toString());
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(roomFrame, "Error retrieving rooms!");
            }
        });

        panel.add(addRoomButton);
        panel.add(viewRoomsButton);
        roomFrame.add(panel);
        roomFrame.setVisible(true);
    }

    // Customer Management
    private static void manageCustomers() {
        JFrame customerFrame = new JFrame("Customer Management");
        customerFrame.setSize(400, 300);
        customerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        customerFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JButton addCustomerButton = new JButton("Add Customer");
        JButton viewCustomersButton = new JButton("View Customers");

        addCustomerButton.addActionListener(e -> {
            String customerName = JOptionPane.showInputDialog("Enter Customer Name:");
            String customerPhone = JOptionPane.showInputDialog("Enter Customer Phone:");
            String customerEmail = JOptionPane.showInputDialog("Enter Customer Email:");

            Customer customer = new Customer(0, customerName, customerPhone, customerEmail);

            try {
                CustomerDAO customerDAO = new CustomerDAO();
                customerDAO.addCustomer(customer);
                JOptionPane.showMessageDialog(customerFrame, "Customer added successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(customerFrame, "Error adding customer!");
            }
        });

        viewCustomersButton.addActionListener(e -> {
            try {
                CustomerDAO customerDAO = new CustomerDAO();
                List<Customer> customers = customerDAO.getAllCustomers();

                StringBuilder sb = new StringBuilder();
                for (Customer customer : customers) {
                    sb.append("Customer Name: ").append(customer.getCustomerName())
                      .append(", Phone: ").append(customer.getCustomerPhone())
                      .append(", Email: ").append(customer.getCustomerEmail())
                      .append("\n");
                }
                JOptionPane.showMessageDialog(customerFrame, sb.toString());
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(customerFrame, "Error retrieving customers!");
            }
        });

        panel.add(addCustomerButton);
        panel.add(viewCustomersButton);
        customerFrame.add(panel);
        customerFrame.setVisible(true);
    }

    // Reservation Management
    private static void manageReservations() {
        JFrame reservationFrame = new JFrame("Reservation Management");
        reservationFrame.setSize(400, 300);
        reservationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reservationFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JButton addReservationButton = new JButton("Add Reservation");
        JButton viewReservationsButton = new JButton("View Reservations");

        addReservationButton.addActionListener(e -> {
            String roomIdStr = JOptionPane.showInputDialog("Enter Room ID:");
            String customerIdStr = JOptionPane.showInputDialog("Enter Customer ID:");
            String checkInDate = JOptionPane.showInputDialog("Enter Check-In Date (YYYY-MM-DD):");
            String checkOutDate = JOptionPane.showInputDialog("Enter Check-Out Date (YYYY-MM-DD):");

            int roomId = Integer.parseInt(roomIdStr);
            int customerId = Integer.parseInt(customerIdStr);

            Reservation reservation = new Reservation(0, roomId, customerId, checkInDate, checkOutDate, false);

            try {
                ReservationDAO reservationDAO = new ReservationDAO();
                reservationDAO.addReservation(reservation);
                JOptionPane.showMessageDialog(reservationFrame, "Reservation added successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(reservationFrame, "Error adding reservation!");
            }
        });

        viewReservationsButton.addActionListener(e -> {
            try {
                ReservationDAO reservationDAO = new ReservationDAO();
                List<Reservation> reservations = reservationDAO.getAllReservations();

                StringBuilder sb = new StringBuilder();
                for (Reservation reservation : reservations) {
                    sb.append("Reservation ID: ").append(reservation.getReservationId())
                      .append(", Room ID: ").append(reservation.getRoomId())
                      .append(", Customer ID: ").append(reservation.getCustomerId())
                      .append(", Check-In: ").append(reservation.getCheckInDate())
                      .append(", Check-Out: ").append(reservation.getCheckOutDate())
                      .append(", Checked Out: ").append(reservation.isCheckedOut())
                      .append("\n");
                }
                JOptionPane.showMessageDialog(reservationFrame, sb.toString());
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(reservationFrame, "Error retrieving reservations!");
            }
        });

        panel.add(addReservationButton);
        panel.add(viewReservationsButton);
        reservationFrame.add(panel);
        reservationFrame.setVisible(true);
    }

    // Billing Management
    private static void generateBill() {
        String reservationIdStr = JOptionPane.showInputDialog("Enter Reservation ID for billing:");
        int reservationId = Integer.parseInt(reservationIdStr);

        try {
            BillingDAO billingDAO = new BillingDAO();
            double totalBill = billingDAO.calculateBill(reservationId);
            JOptionPane.showMessageDialog(frame, "Total Bill for Reservation ID " + reservationId + ": ₹" + totalBill);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error generating bill!");
        }
    }
}
