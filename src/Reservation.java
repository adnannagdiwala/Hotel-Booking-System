public class Reservation {
    private int reservationId;
    private int roomId;
    private int customerId;
    private String checkInDate;
    private String checkOutDate;
    private boolean isCheckedOut;

    public Reservation(int reservationId, int roomId, int customerId, String checkInDate, String checkOutDate, boolean isCheckedOut) {
        this.reservationId = reservationId;
        this.roomId = roomId;
        this.customerId = customerId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.isCheckedOut = isCheckedOut;
    }

    public int getReservationId() { return reservationId; }
    public int getRoomId() { return roomId; }
    public int getCustomerId() { return customerId; }
    public String getCheckInDate() { return checkInDate; }
    public String getCheckOutDate() { return checkOutDate; }
    public boolean isCheckedOut() { return isCheckedOut; }
}
