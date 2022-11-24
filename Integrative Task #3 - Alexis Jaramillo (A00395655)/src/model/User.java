package model;

import java.time.LocalDate;

public abstract class User {

    private String nickname;
    private String id;
    private LocalDate dateOfAffiliation;

    public User(String nickname, String id) {
        this.nickname = nickname;
        this.id = id;
        this.dateOfAffiliation = LocalDate.now();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getdateOfAffiliation() {
        return dateOfAffiliation;
    }

    public void setdateOfAffiliation(LocalDate dateOfAffiliation) {
        this.dateOfAffiliation = dateOfAffiliation;
    }

}