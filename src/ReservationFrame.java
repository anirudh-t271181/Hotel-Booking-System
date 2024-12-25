import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;


public class ReservationFrame extends JFrame {
    private JTextField txtUserId, txtRoomId, txtCheckIn, txtCheckOut;

    public ReservationFrame() {
        setTitle("Make a Reservation");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeUI();
    }

    private void initializeUI() {
        JPanel panel = new JPanel(new GridLayout(5, 2));

        JLabel lblUserId = new JLabel("User ID:");
        txtUserId = new JTextField();

        JLabel lblRoomId = new JLabel("Room ID:");
        txtRoomId = new JTextField();

        JLabel lblCheckIn = new JLabel("Check-In Date (yyyy-mm-dd):");
        txtCheckIn = new JTextField();

        JLabel lblCheckOut = new JLabel("Check-Out Date (yyyy-mm-dd):");
        txtCheckOut = new JTextField();

        JButton btnReserve = new JButton("Reserve");
        btnReserve.addActionListener(e -> makeReservation());

        panel.add(lblUserId);
        panel.add(txtUserId);
        panel.add(lblRoomId);
        panel.add(txtRoomId);
        panel.add(lblCheckIn);
        panel.add(txtCheckIn);
        panel.add(lblCheckOut);
        panel.add(txtCheckOut);
        panel.add(btnReserve);

        add(panel);
    }

    private void makeReservation() {
        int userId = Integer.parseInt(txtUserId.getText());
        int roomId = Integer.parseInt(txtRoomId.getText());
        LocalDate checkIn = LocalDate.parse(txtCheckIn.getText());
        LocalDate checkOut = LocalDate.parse(txtCheckOut.getText());

        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setRoomId(roomId);
        reservation.setCheckIn(Date.valueOf(checkIn)); // Convert LocalDate to java.sql.Date
        reservation.setCheckOut(Date.valueOf(checkOut)); // Convert LocalDate to java.sql.Date
        reservation.setStatus("Reserved");

        ReservationDAO reservationDAO = new ReservationDAO();
        try {
            reservationDAO.makeReservation(reservation);
            JOptionPane.showMessageDialog(this, "Reservation made successfully!");
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to make reservation.");
        }

        dispose();
    }



}
