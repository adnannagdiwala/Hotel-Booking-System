import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {
    public void addReservation(Reservation reservation) throws SQLException {
        String query = "INSERT INTO reservations (room_id, customer_id, check_in_date, check_out_date, is_checked_out) VALUES (?, ?, ?, ?, ?)";
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, reservation.getRoomId());
            ps.setInt(2, reservation.getCustomerId());
            ps.setDate(3, Date.valueOf(reservation.getCheckInDate()));
            ps.setDate(4, Date.valueOf(reservation.getCheckOutDate()));
            ps.setBoolean(5, reservation.isCheckedOut());
            ps.executeUpdate();
        }
        catch(Exception E){
            E.printStackTrace();
        }
    }

    public List<Reservation> getAllReservations() throws SQLException {
        String query = "SELECT * FROM Reservations";
        List<Reservation> reservations = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reservation reservation = new Reservation(rs.getInt("reservation_id"),
                        rs.getInt("room_id"), rs.getInt("customer_id"),
                        rs.getDate("check_in_date").toString(), rs.getDate("check_out_date").toString(),
                        rs.getBoolean("is_checked_out"));
                reservations.add(reservation);
            }
        }
        return reservations;
    }
}
