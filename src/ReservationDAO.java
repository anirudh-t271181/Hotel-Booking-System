import java.sql.*;

public class ReservationDAO {
    private UserDAO userDAO = new UserDAO();

    public void makeReservation(Reservation reservation) {
        if (!userDAO.userExists(reservation.getUserId())) {
            throw new IllegalArgumentException("User ID does not exist");
        }

        String query = "INSERT INTO Reservations (user_id, room_id, check_in, check_out, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, reservation.getUserId());
            statement.setInt(2, reservation.getRoomId());
            statement.setDate(3, reservation.getCheckIn()); // Use java.sql.Date directly
            statement.setDate(4, reservation.getCheckOut()); // Use java.sql.Date directly
            statement.setString(5, reservation.getStatus());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Reservation getReservationById(int reservationId) {
        String query = "SELECT * FROM Reservations WHERE reservation_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, reservationId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationId(resultSet.getInt("reservation_id"));
                reservation.setUserId(resultSet.getInt("user_id"));
                reservation.setRoomId(resultSet.getInt("room_id"));
                reservation.setCheckIn(resultSet.getDate("check_in")); // Sets java.sql.Date
                reservation.setCheckOut(resultSet.getDate("check_out")); // Sets java.sql.Date
                reservation.setStatus(resultSet.getString("status"));
                return reservation;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateReservation(Reservation reservation) {
        String query = "UPDATE Reservations SET status = ? WHERE reservation_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, reservation.getStatus());
            statement.setInt(2, reservation.getReservationId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
