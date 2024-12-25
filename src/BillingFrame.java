import javax.swing.*;
import java.awt.*;

public class BillingFrame extends JFrame {
    private JTextField txtReservationId;

    public BillingFrame() {
        setTitle("Billing");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        JPanel panel = new JPanel(new GridLayout(2, 2));

        JLabel lblReservationId = new JLabel("Reservation ID:");
        txtReservationId = new JTextField();

        JButton btnGenerateBill = new JButton("Generate Bill");
        btnGenerateBill.addActionListener(e -> generateBill());

        panel.add(lblReservationId);
        panel.add(txtReservationId);
        panel.add(btnGenerateBill);

        add(panel);
    }

    private void generateBill() {
        int reservationId = Integer.parseInt(txtReservationId.getText());

        ReservationDAO reservationDAO = new ReservationDAO();
        Reservation reservation = reservationDAO.getReservationById(reservationId);

        if (reservation != null) {
            RoomDAO roomDAO = new RoomDAO();
            Room room = roomDAO.getRoomById(reservation.getRoomId());

            long days = (reservation.getCheckOut().getTime() - reservation.getCheckIn().getTime()) / (1000 * 60 * 60 * 24);
            double totalAmount = days * room.getPricePerNight();

            Bill bill = new Bill();
            bill.setReservationId(reservationId);
            bill.setTotalAmount(totalAmount);
            bill.setPaymentStatus("Unpaid");

            BillDAO billDAO = new BillDAO();
            billDAO.addBill(bill);

            JOptionPane.showMessageDialog(this, "Bill generated successfully!");

            // Mark reservation as completed
            reservation.setStatus("Completed");
            reservationDAO.updateReservation(reservation);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid reservation ID.");
        }
        dispose();
    }
}
