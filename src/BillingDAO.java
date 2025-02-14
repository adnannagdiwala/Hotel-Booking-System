import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BillingDAO {
    public double calculateBill(int reservationId) throws SQLException {
        String query = "SELECT r.check_in_date, r.check_out_date, ro.room_price " +
                       "FROM Reservations r " +
                       "JOIN Rooms ro ON r.room_id = ro.room_id " +
                       "WHERE r.reservation_id = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, reservationId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                LocalDate checkInDate = rs.getDate("check_in_date").toLocalDate();
                LocalDate checkOutDate = rs.getDate("check_out_date").toLocalDate();
                double roomPrice = rs.getDouble("room_price");

                long daysStayed = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
                if (daysStayed == 0) {
                    daysStayed = 1;
                }

                return daysStayed * roomPrice;
            } else {
                throw new SQLException("Reservation not found!");
            }
        }
    }
}
