import java.sql.*;

public class RoomDAO {
    public void addRoom(Room room) {
        String query = "INSERT INTO Rooms (type, price_per_night, is_available) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, room.getType());
            statement.setDouble(2, room.getPricePerNight());
            statement.setBoolean(3, room.isAvailable());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Room getRoomById(int roomId) {
        String query = "SELECT * FROM Rooms WHERE room_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, roomId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Room room = new Room();
                room.setRoomId(resultSet.getInt("room_id"));
                room.setType(resultSet.getString("type"));
                room.setPricePerNight(resultSet.getDouble("price_per_night"));
                room.setAvailable(resultSet.getBoolean("is_available"));
                return room;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateRoom(Room room) {
        String query = "UPDATE Rooms SET type = ?, price_per_night = ?, is_available = ? WHERE room_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, room.getType());
            statement.setDouble(2, room.getPricePerNight());
            statement.setBoolean(3, room.isAvailable());
            statement.setInt(4, room.getRoomId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
