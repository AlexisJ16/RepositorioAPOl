package model;

import java.util.ArrayList;

public class ContentCreator extends ProducerUser {

    private ArrayList<Podcast> podcasts;

    public ContentCreator(String nickname, String id, String name, String url) {
        super(nickname, id, name, url);

        this.podcasts = new ArrayList<Podcast>();
    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    /**
     * In this method, a podcast is assigned to a content creator.
     * 
     * @param audio Audio the object to be added
     * @return msg String will indicate whether or not the audio could have been
     *         added.
     */
    public String addAudioToContentCreator(Audio audio) {

        String msg = "The podcast was successfully added";

        Audio check = verifyAudio(audio.getName());

        if (check == null) {
            podcasts.add((Podcast) audio);

        } else {
            msg = "The podcast you wish to register has already been created on the platform.";

        }

        return msg;
    }

    /**
     * Allows to search an audio in the user's list of audios
     * 
     * @param name is the name of the audio to be searched.
     * @return Audio the audio found.
     */
    public Audio verifyAudio(String name) {

        Audio audio = null;
        boolean flag = false;

        if (podcasts != null) {

            for (int i = 0; i < podcasts.size() && !flag; i++) {

                if (podcasts.get(i).getName().equalsIgnoreCase(name)) {
                    audio = podcasts.get(i);
                    flag = true;

                }
            }
        }

        return audio;
    }

}