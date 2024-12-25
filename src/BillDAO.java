import java.sql.*;

public class BillDAO {
    public void addBill(Bill bill) {
        String query = "INSERT INTO Bills (reservation_id, total_amount, payment_status) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, bill.getReservationId());
            statement.setDouble(2, bill.getTotalAmount());
            statement.setString(3, bill.getPaymentStatus());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Bill getBillByReservationId(int reservationId) {
        String query = "SELECT * FROM Bills WHERE reservation_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, reservationId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Bill bill = new Bill();
                bill.setBillId(resultSet.getInt("bill_id"));
                bill.setReservationId(resultSet.getInt("reservation_id"));
                bill.setTotalAmount(resultSet.getDouble("total_amount"));
                bill.setPaymentStatus(resultSet.getString("payment_status"));
                return bill;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateBill(Bill bill) {
        String query = "UPDATE Bills SET payment_status = ? WHERE bill_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, bill.getPaymentStatus());
            statement.setInt(2, bill.getBillId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
