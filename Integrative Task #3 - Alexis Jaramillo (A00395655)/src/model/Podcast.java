package model;

public class Podcast extends Audio {

    private String description;
    private CategoryType category;

    public Podcast(String name, String distinctiveUrl, int duration, User creator, String description, int category) {

        super(name, distinctiveUrl, duration, creator);
        this.description = description;
        this.category = CategoryType.values()[category];

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryType getCategory() {
        return category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    @Override
    public String play() {
        String msg = "";

        msg += "The podcast is now playing";

        return msg;
    }

    @Override
    public String toString() {
        return "Podcast [description=" + description + ", category=" + category + "]";
    }

}
