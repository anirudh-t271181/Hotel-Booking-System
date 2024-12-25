import javax.swing.*;
import java.awt.*;

public class CheckInFrame extends JFrame {
    private JTextField txtReservationId;

    public CheckInFrame() {
        setTitle("Check In");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        JPanel panel = new JPanel(new GridLayout(2, 2));

        JLabel lblReservationId = new JLabel("Reservation ID:");
        txtReservationId = new JTextField();

        JButton btnCheckIn = new JButton("Check In");
        btnCheckIn.addActionListener(e -> checkIn());

        panel.add(lblReservationId);
        panel.add(txtReservationId);
        panel.add(btnCheckIn);

        add(panel);
    }

    private void checkIn() {
        int reservationId = Integer.parseInt(txtReservationId.getText());

        ReservationDAO reservationDAO = new ReservationDAO();
        Reservation reservation = reservationDAO.getReservationById(reservationId);

        if (reservation != null && "Reserved".equals(reservation.getStatus())) {
            reservation.setStatus("Checked In");
            reservationDAO.updateReservation(reservation);

            RoomDAO roomDAO = new RoomDAO();
            Room room = roomDAO.getRoomById(reservation.getRoomId());
            room.setAvailable(false);
            roomDAO.updateRoom(room);

            JOptionPane.showMessageDialog(this, "Checked in successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid reservation ID or status.");
        }
        dispose();
    }
}
