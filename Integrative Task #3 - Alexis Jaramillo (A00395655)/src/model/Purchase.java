package model;

import java.time.LocalDate;

public class Purchase {

    private LocalDate purchaseDate;
    private Song chosenSong;

    public Purchase(Song audioType) {
        this.purchaseDate = LocalDate.now();
        this.chosenSong = audioType;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Song getChosenSong() {
        return chosenSong;
    }

    public void setChosenSong(Song audioType) {
        this.chosenSong = audioType;
    }

}