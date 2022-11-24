package model;

import java.util.ArrayList;

public class Artist extends ProducerUser {

    private ArrayList<Song> songs;

    public Artist(String nickname, String id, String name, String url) {
        super(nickname, id, name, url);

        this.songs = new ArrayList<Song>();

    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Artist [songs=" + songs + "]";
    }

    /**
     * Description:to register a song you need to assign it to its respective
     * creator
     * 
     * @param audio Audio is the object of type audio to be added
     * @return msg String will indicate whether the audio could be added or not
     */
    public String addAudioToArtist(Audio audio) {

        String msg = "The audio was successfully added";
        Audio check = verifyAudio(audio.getName());

        if (check == null) {
            songs.add((Song) audio);
            return "The audio added correctly";

        } else {
            msg = "The audio could not be added correctly";
        }

        return msg;
    }

    /**
     * Description:this method searches for the user name that you received to see
     * if it can be added or not
     * 
     * @param name String parameter to be searched for or verified
     * @return audio Audio
     */
    public Audio verifyAudio(String name) {
        Audio audio = null;
        boolean flag = false;

        if (songs != null) {
            for (int i = 0; i < songs.size() && !flag; i++) {
                if (songs.get(i).getName().equalsIgnoreCase(name)) {
                    audio = songs.get(i);
                    flag = true;
                }
            }
        }
        return audio;
    }

}
