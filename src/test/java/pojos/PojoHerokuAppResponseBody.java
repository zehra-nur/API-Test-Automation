package pojos;

public class PojoHerokuAppResponseBody {

    // 1- tum variable'lari private olarak olustur

    private int bookingid;
    private PojoHerokuappRequestBody booking;

    // 2- tum variable'lar icin getter ve setter methodlari olustur

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public PojoHerokuappRequestBody getBooking() {
        return booking;
    }

    public void setBooking(PojoHerokuappRequestBody booking) {
        this.booking = booking;
    }

    // 3- tum parametreleri kullanarak bir constructor olustur

    public PojoHerokuAppResponseBody(int bookingid, PojoHerokuappRequestBody booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    // 4- default constructor yerine manuel olarak parametresiz bir constructor olustur

    public PojoHerokuAppResponseBody() {
    }

    // 5- toString methodu olusturalim

    @Override
    public String toString() {
        return "PojoHerokuAppResponseBody{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
