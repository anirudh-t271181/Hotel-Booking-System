import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Hotel Booking System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeUI();
    }

    private void initializeUI() {
        JPanel panel = new JPanel();
        JButton btnReserve = new JButton("Make a Reservation");
        JButton btnCheckIn = new JButton("Check In");
        JButton btnCheckOut = new JButton("Check Out");
        JButton btnBilling = new JButton("Billing");
        JButton btnAddUser = new JButton("Add User");

        btnReserve.addActionListener(e -> new ReservationFrame().setVisible(true));
        btnCheckIn.addActionListener(e -> new CheckInFrame().setVisible(true));
        btnCheckOut.addActionListener(e -> new CheckOutFrame().setVisible(true));
        btnBilling.addActionListener(e -> new BillingFrame().setVisible(true));
        btnAddUser.addActionListener(e -> new AddUserFrame().setVisible(true));

        panel.add(btnReserve);
        panel.add(btnCheckIn);
        panel.add(btnCheckOut);
        panel.add(btnBilling);
        panel.add(btnAddUser);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
