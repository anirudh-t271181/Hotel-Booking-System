import javax.swing.*;
import java.awt.*;

public class CheckOutFrame extends JFrame {
    private JTextField txtReservationId;

    public CheckOutFrame() {
        setTitle("Check Out");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        JPanel panel = new JPanel(new GridLayout(2, 2));

        JLabel lblReservationId = new JLabel("Reservation ID:");
        txtReservationId = new JTextField();

        JButton btnCheckOut = new JButton("Check Out");
        btnCheckOut.addActionListener(e -> checkOut());

        panel.add(lblReservationId);
        panel.add(txtReservationId);
        panel.add(btnCheckOut);

        add(panel);
    }

    private void checkOut() {
        int reservationId = Integer.parseInt(txtReservationId.getText());

        ReservationDAO reservationDAO = new ReservationDAO();
        Reservation reservation = reservationDAO.getReservationById(reservationId);

        if (reservation != null && "Checked In".equals(reservation.getStatus())) {
            reservation.setStatus("Checked Out");
            reservationDAO.updateReservation(reservation);

            RoomDAO roomDAO = new RoomDAO();
            Room room = roomDAO.getRoomById(reservation.getRoomId());
            room.setAvailable(true);
            roomDAO.updateRoom(room);

            JOptionPane.showMessageDialog(this, "Checked out successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid reservation ID or status.");
        }
        dispose();
    }
}
