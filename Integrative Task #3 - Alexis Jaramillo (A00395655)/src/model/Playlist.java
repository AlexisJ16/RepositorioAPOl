package model;

import java.util.ArrayList;
import java.util.Random;

public class Playlist {

    public static Random random = new Random();

    private ArrayList<Audio> audios;
    private String playlistName;
    private int numberOfSongs;
    private int numberOfPodcasts;
    private String code;
    private int[][] matrix;

    public Playlist(String playlistName) {

        audios = new ArrayList<Audio>();

        this.playlistName = playlistName;
        this.numberOfSongs = 0;
        this.numberOfPodcasts = 0;

        generatematrixCode();
        matrix = new int[6][6];

    }

    public ArrayList<Audio> getAudios() {
        return audios;
    }

    public void addAudio(Audio audio) {
        audios.add(audio);
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public int getnumberOfSongs() {
        return numberOfSongs;
    }

    public void setnumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    public int getnumberOfPodcasts() {
        return numberOfPodcasts;
    }

    public void setnumberOfPodcasts(int numberOfPodcasts) {
        this.numberOfPodcasts = numberOfPodcasts;
    }

    public int[][] getmatrix() {
        return matrix;
    }

    public void setmatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Allows to display the audios saved in the list
     * 
     * @return msg String will display the list of audios
     */
    public String showAudios() {

        String msg = "";

        for (int i = 0; i < audios.size(); i++) {

            msg += audios.get(i).getName() + "\n";
        }

        return msg;
    }

    /**
     * This method provides a code for the playlist to be shared
     * 
     * @return msg String string with the code
     */
    public String shareAPlaylist() {

        String msg = "";

        if (audios.size() > 0) {
            generateCodeToMatrix();

            for (int i = 0; i < 6; i++) {

                for (int j = 0; j < 6; j++) {
                    msg += " " + matrix[i][j] + " ";
                }

                msg += "\n";
            }
            msg += "\nPlaylist code:" + code;

        } else {
            msg = "No data is found in the playlist.";
        }

        return msg;
    }

    /**
     * this method allows us to add an audio as appropriate
     * 
     * @param audio Audio object to add
     * @return msg String will indicate if the audio could be added to its
     *         corresponding list
     */
    public String createAudio(Audio audio) {

        String msg = "Audio has been successfully added";
        Audio check = searchAudio(audio.getName());

        if (check == null) {
            audios.add(audio);

            if (audio instanceof Song) {
                numberOfSongs++;

            } else {
                numberOfPodcasts++;
            }

        } else {
            msg = "The audio you want to record, is already created within the platform";
        }

        return msg;

    }

    /**
     * This method allows us to delete an audio
     * 
     * @param audio Audio object to be deleted
     * @return msg String will indicate whether or not the audio could be deleted.
     */
    public String deleteAudio(Audio audio) {

        String msg = "";
        Audio check = searchAudio(audio.getName());

        if (check != null) {
            audios.remove(audio);

        } else {
            msg = "The audio you wish to delete does not exist on the platform";
        }

        msg += "Audio has been successfully deleted";
        return msg;

    }

    /**
     * Allows us to search for an audio in the playlist
     * 
     * @param name String name of the audio to be searched
     * @return check Audio
     */
    public Audio searchAudio(String name) {

        Audio check = null;
        boolean flag = false;

        if (audios != null) {

            for (int i = 0; i < audios.size() && !flag; i++) {

                if (audios.get(i).getName().equalsIgnoreCase(name)) {
                    check = audios.get(i);
                    flag = true;
                }
            }
        }

        return check;
    }

    /**
     * Description:is in charge of generating the matrix with the code
     */
    public void generatematrixCode() {

        for (int i = 0; i < 6; i++) {

            for (int j = 0; j < 6; j++) {

                matrix[i][j] = random.nextInt(10);
            }
        }
    }

    /**
     * Handles the paths to auto-generate the code depending on the
     * content of the playlist
     */
    public void generateCodeToMatrix() {

        String msg1 = "";
        String msg2 = "";
        String msg3 = "";

        if (numberOfSongs > 0 && numberOfPodcasts > 0) {

            for (int i = 5; i >= 0; i--) {

                for (int j = 5; j >= 0; j--) {
                    int counter = i + j;

                    if (counter % 2 != 0) {

                        if (counter != 1) {
                            code += matrix[i][j] + " ";
                        }
                    }

                }
            }

        } else if (numberOfSongs > 0) {

            for (int j = 0; j < matrix.length; j++) {

                if (j > 0 && j < matrix.length - 1) {

                    msg2 += matrix[j][j] + " ";

                }
                msg1 += matrix[matrix.length - (j + 1)][0] + " ";
                msg3 += matrix[matrix.length - (j + 1)][matrix.length - 1] + " ";

            }

            code = msg1 + msg2 + msg3;

        } else if (numberOfPodcasts > 0) {

            for (int i = 0; i < matrix.length; i++) {
                msg1 += matrix[0][i] + " ";

                if (i != 0) {
                    msg2 += matrix[i][2] + " ";
                }

                if (i != matrix.length - 1) {
                    msg3 += matrix[matrix.length - (i + 1)][3] + " ";
                }
            }

            code = msg1.substring(0, 5) + " " + msg2 + msg3 + msg1.substring(6, 11);

        }

    }

}