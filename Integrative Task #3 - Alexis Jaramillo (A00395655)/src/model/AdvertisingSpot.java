package model;

public enum AdvertisingSpot implements Playable {

    NIKE("Nike, Just do it"),
    COCACOLA("Coca-cola, open happiness"),
    MyMs("M&M, Melts in your mouth, not in your hands");

    private String msg;

    AdvertisingSpot(String msg) {
        this.msg = msg;
    }

    public String play() {
        String print;
        print = "Advertising will end soon: " + msg;
        return print;
    }

}
