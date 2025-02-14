public class Room {
    private int roomId;
    private String roomNumber;
    private String roomType;
    private double roomPrice;
    private boolean isAvailable;

    public Room(int roomId, String roomNumber, String roomType, double roomPrice, boolean isAvailable) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPrice = roomPrice;
        this.isAvailable = isAvailable;
    }

    public int getRoomId() { return roomId; }
    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getRoomPrice() { return roomPrice; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available) { isAvailable = available; }
}
