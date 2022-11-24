package model;

public abstract class Audio implements Playable {

    private String name;
    private String distinctiveUrl;
    private int duration;
    private int numberOfReproductions;
    private ProducerUser creator;

    public Audio(String name, String distinctiveUrl, int duration, User creator) {
        this.name = name;
        this.distinctiveUrl = distinctiveUrl;
        this.duration = duration;
        this.numberOfReproductions = 0;
        this.creator = (ProducerUser) creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistinctiveUrl() {
        return distinctiveUrl;
    }

    public void setDistinctiveUrl(String distinctiveUrl) {
        this.distinctiveUrl = distinctiveUrl;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNumberOfPlays() {
        return numberOfReproductions;
    }

    public void setNumberOfPlays(int numberOfReproductions) {
        this.numberOfReproductions = numberOfReproductions;
    }

    public ProducerUser getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = (ProducerUser) creator;
    }

    @Override
    public String toString() {
        return "Audio [name=" + name + ", distinctiveUrl=" + distinctiveUrl + ", duration=" + duration
                + ", numberOfReproductions=" + numberOfReproductions + "creator=" + creator + "]";
    }

}