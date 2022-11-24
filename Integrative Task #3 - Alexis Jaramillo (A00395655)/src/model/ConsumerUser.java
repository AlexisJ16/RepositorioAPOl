package model;

import java.util.ArrayList;

public abstract class ConsumerUser extends User {

    private ArrayList<Playlist> playlists;
    private ArrayList<Purchase> purchaseList;
    private ArrayList<Play> reproductions;

    public ConsumerUser(String nickname, String id) {
        super(nickname, id);
        playlists = new ArrayList<Playlist>();
        purchaseList = new ArrayList<Purchase>();
        reproductions = new ArrayList<Play>();

    }

    public ArrayList<Play> getReproductions() {
        return reproductions;
    }

    public void setReproductions(ArrayList<Play> reproductions) {
        this.reproductions = reproductions;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ArrayList<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setpurchaseList(ArrayList<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    /**
     * This method allows to add a playlist.
     * 
     * @param playlistName String is the playlist's name.
     * @return String the message of the operation.
     */
    public abstract String createPlaylist(String playlistName);

    /**
     * This method allows to add an audio to a playlist.
     * 
     * @param playlistName String is the playlist's name.
     * @param audio        Audio is the audio object to be added.
     * @return String the message of the operation.
     */
    public abstract String addAudioToPlaylist(String playlistName, Audio audio);

    /**
     * This method allows to edit a playlist's name
     * 
     * @param playlistName    String is the playlist's name.
     * @param newPlaylistName String is the new playlist's name.
     * @return String the message of the operation.
     */
    public abstract String editPlaylistName(String playlistName, String newPlaylistName);

    /**
     * This metod allows to remove an audio from a playlist
     * 
     * @param playlistName String is the playlist's name.
     * @param audio        Audio is the audio to be deleted.
     * @return String the message of the operation.
     */
    public abstract String deleteAudioToPlaylist(String playlistName, Audio audio);

    /**
     * allows to share a playlist by means of an auto-generated code
     * 
     * @param playlistName String is the playlist's name.
     * @return String the message of the operation.
     */
    public abstract String shareAPlaylist(String playlistName);

    /**
     * This method play the audio
     * counter:none
     * 
     * @param audio Audio object to be reproduced
     * @return msg String message indicating playback
     */
    public String playAudio(Audio audio) {

        String msg = "";
        msg += audio.play();

        Play check = searchPlayback(audio.getName());

        if (check == null) {
            reproductions.add(new Play(audio));

        } else {
            check.updateInfo();
        }

        return msg;

    }

    /**
     * Description: This method allows to get the most listened genre.
     * pre:the playbacks must be already created.
     * counter : none
     * 
     * @return String the most listened genre.
     */
    public String mostPlayedGenre() {

        String msg = "";

        int[] typeOfGenre = reproductionsPerGenre();
        int totalCount = 0;
        int counter = -1;
        for (int i = 0; i < typeOfGenre.length; i++) {

            if (typeOfGenre[i] > totalCount) {
                totalCount = typeOfGenre[i];
                counter = i;
            }
        }

        switch (counter) {
            case 0:
                msg = "The most listened genre is: "
                        + "ROCK, with a total of " + totalCount + " reproductions.";
                break;
            case 1:
                msg = "The most listened genre is: "
                        + "POP, with a total of " + totalCount + " reproductions.";
                break;
            case 2:
                msg = "The most listened genre is: "
                        + "TRAP, with a total of " + totalCount + " reproductions.";
                break;
            case 3:
                msg = "The most listened genre is: "
                        + "HOUSE, with a total of " + totalCount + " reproductions.";
                break;
            default:
                msg = "No sound belonging to a genre has been reproduced.";
                break;
        }
        return msg;
    }

    /**
     * This method allows to get the number of playbacks per genre.
     * 
     * @return int[] the number of playbacks per genre.
     */
    public int[] reproductionsPerGenre() {

        int[] playbacks = new int[4];

        for (int i = 0; i < reproductions.size(); i++) {

            if (reproductions.get(i).getAudio() instanceof Song) {
                Song songs = (Song) reproductions.get(i).getAudio();

                if (songs.getGenre().equals(GenderType.ROCK)) {
                    playbacks[0] += reproductions.get(i).getReproduction();

                } else if (songs.getGenre().equals(GenderType.POP)) {
                    playbacks[1] += reproductions.get(i).getReproduction();

                } else if (songs.getGenre().equals(GenderType.TRAP)) {
                    playbacks[2] += reproductions.get(i).getReproduction();

                } else if (songs.getGenre().equals(GenderType.HOUSE)) {
                    playbacks[3] += reproductions.get(i).getReproduction();

                }
            }
        }

        return playbacks;
    }

    /**
     * This methos allows to get the most heared category.
     * 
     * @return String the most listened category of podcast.
     */
    public String mostPlayedCategory() {

        String msg = "";

        int[] categoryType = reproductionsPerCategory();

        int totalCount = 0;
        int counter = -1;
        for (int i = 0; i < categoryType.length; i++) {

            if (categoryType[i] > totalCount) {
                totalCount = categoryType[i];
                counter = i;
            }
        }

        switch (counter) {

            case 0:
                msg = "The most listened category is: "
                        + "POLITIC, with a total of " + totalCount + " reproductions.";
                break;
            case 1:
                msg = "The most listened category is: "
                        + "ENTERTAINMENT, with a total of " + totalCount + " reproductions.";
                break;
            case 2:
                msg = "The most listened category is: "
                        + "VIDEOGAME, with a total of " + totalCount + " reproductions.";
                break;
            case 3:
                msg = "The most listened category is: "
                        + "FASHION, with a total of " + totalCount + " reproductions.";
                break;
            default:
                msg = "No sound belonging to a category has been played.";
                break;
        }
        return msg;
    }

    /**
     * Description:In this method the reproductions are counted for each podcaste
     * category.
     * pre: none
     * post: none
     * 
     * @return playbacks int[] reproductions by category
     */
    public int[] reproductionsPerCategory() {

        int[] playbacks = new int[4];

        for (int i = 0; i < reproductions.size(); i++) {

            if (reproductions.get(i).getAudio() instanceof Podcast) {
                Podcast podcasts = (Podcast) reproductions.get(i).getAudio();

                if (podcasts.getCategory().equals(CategoryType.POLITIC)) {
                    playbacks[0] += reproductions.get(i).getReproduction();

                } else if (podcasts.getCategory().equals(CategoryType.ENTERTAINMENT)) {
                    playbacks[1] += reproductions.get(i).getReproduction();

                } else if (podcasts.getCategory().equals(CategoryType.VIDEOGAME)) {
                    playbacks[2] += reproductions.get(i).getReproduction();

                } else if (podcasts.getCategory().equals(CategoryType.FASHION)) {
                    playbacks[3] += reproductions.get(i).getReproduction();

                }
            }
        }

        return playbacks;
    }

    /**
     * this method allows to search a playback.
     * 
     * @param name String is the name of the audio that will be searched.
     * @return check Play object
     */
    public Play searchPlayback(String name) {

        Play check = null;
        boolean flag = false;

        for (int i = 0; i < reproductions.size() && !flag; i++) {

            if (reproductions.get(i).getAudio().getName().equalsIgnoreCase(name)) {
                check = reproductions.get(i);
                flag = true;
            }
        }

        return check;
    }

}
