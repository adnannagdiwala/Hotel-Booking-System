import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    public void addRoom(Room room) throws SQLException {
        String query = "INSERT INTO Rooms (room_number, room_type, room_price, is_available) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, room.getRoomNumber());
            ps.setString(2, room.getRoomType());
            ps.setDouble(3, room.getRoomPrice());
            ps.setBoolean(4, room.isAvailable());
            ps.executeUpdate();
        }
    }

    public List<Room> getAllRooms() throws SQLException {
        String query = "SELECT * FROM Rooms";
        List<Room> rooms = new ArrayList<>();
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Room room = new Room(rs.getInt("room_id"), rs.getString("room_number"),
                        rs.getString("room_type"), rs.getDouble("room_price"),
                        rs.getBoolean("is_available"));
                rooms.add(room);
            }
        }
        return rooms;
    }
}
