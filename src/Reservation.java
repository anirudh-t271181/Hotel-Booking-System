import java.sql.Date;

public class Reservation {
    private int reservationId;
    private int userId;
    private int roomId;
    private Date checkIn; // Use java.sql.Date
    private Date checkOut; // Use java.sql.Date
    private String status;

    // Getters and Setters
    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
    public Date getCheckIn() { return checkIn; } // Return java.sql.Date
    public void setCheckIn(Date checkIn) { this.checkIn = checkIn; } // Accept java.sql.Date
    public Date getCheckOut() { return checkOut; } // Return java.sql.Date
    public void setCheckOut(Date checkOut) { this.checkOut = checkOut; } // Accept java.sql.Date
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
