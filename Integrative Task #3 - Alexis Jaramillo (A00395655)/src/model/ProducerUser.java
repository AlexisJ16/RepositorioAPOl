package model;

public class ProducerUser extends User {

    private String name;

    private double totalTimeHeard;

    private String url;

    private int numberOfReproductions;

    public ProducerUser(String nickname, String id, String name, String url) {
        super(nickname, id);
        this.name = name;
        this.totalTimeHeard = 0;
        this.url = url;
        this.numberOfReproductions = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalTimeHeard() {
        return totalTimeHeard;
    }

    public void setTotalTimeHeard(double totalTimeHeard) {
        this.totalTimeHeard = totalTimeHeard;
    }

    public String getRepresentativeUrl() {
        return url;
    }

    public void setRepresentativeUrl(String url) {
        this.url = url;
    }

    public int getnumberOfReproductions() {
        return numberOfReproductions;
    }

    public void setnumberOfReproductions(int numberOfReproductions) {
        this.numberOfReproductions = numberOfReproductions;
    }

    public void updateSoldInfo(int duration) {
        numberOfReproductions++;
        totalTimeHeard += duration;
    }

}