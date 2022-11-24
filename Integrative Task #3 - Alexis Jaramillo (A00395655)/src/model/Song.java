package model;

public class Song extends Audio implements Purchasable {

    private String album;
    private GenderType genre;
    private double sellingPrice;
    private int salesQuantity;

    public Song(String name, String distinctiveUrl, int duration, User creator, String album, int genre,
            double sellingPrice) {

        super(name, distinctiveUrl, duration, creator);
        this.album = album;
        this.genre = GenderType.values()[genre];
        this.sellingPrice = sellingPrice;
        this.salesQuantity = 0;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public GenderType getGenre() {
        return genre;
    }

    public void setGenre(GenderType genre) {
        this.genre = genre;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setsellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getsalesQuantity() {
        return salesQuantity;
    }

    public void setsalesQuantity(int salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    @Override
    public String play() {

        String msg = "";
        msg += "The song is playing:...";

        return msg;
    }

    /*
     * Gives us the information about the sale of the song
     * 
     * @return msg String the sold song
     */
    public String purchase() {

        salesQuantity++;
        super.getCreator().updateSoldInfo(super.getDuration());
        return "The song " + getName() + " has been sold";

    }

    /**
     * Description:calculates the total price of the sale
     * 
     * @return totalSales double
     */
    public double totalPrice() {

        double totalSales = salesQuantity * sellingPrice;

        return totalSales;

    }

    @Override
    public String toString() {
        return "Song [album=" + album + ", genre=" + genre + ", sellingPrice=" + sellingPrice + ", salesQuantity="
                + salesQuantity
                + "]";
    }

}
