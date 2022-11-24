package model;

import java.util.ArrayList;
import java.util.Random;

public class NeoTunesController {

    public static Random advertisement = new Random();

    private ArrayList<User> allUsers;
    private ArrayList<Audio> allAudios;

    public NeoTunesController() {

        allUsers = new ArrayList<User>();
        allAudios = new ArrayList<Audio>();
    }

    /**
     * Search for a specific user's nickname
     * 
     * @param nickname String nickname of the searched user
     * @return User
     */
    public User searchUser(String nickname) {

        User verifyUser = null;
        boolean flag = false;

        for (int i = 0; i < allUsers.size() && !flag; i++) {

            if (allUsers.get(i).getNickname().equals(nickname)) {

                verifyUser = allUsers.get(i);
                flag = true;
            }
        }

        return verifyUser;
    }

    /**
     * Register a Artist
     * 
     * @param nickname String user nickname
     * @param id       String user id
     * @param name     String user name
     * @param url      String url of representative image for the artist
     * @return boolean will indicate whether or not the user was registered
     */
    public boolean registerArtist(String nickname, String id, String name, String url) {

        for (int i = 0; i < allUsers.size(); i++) {

            if (allUsers.get(i) != null) {

                if (allUsers.get(i).getId().equals(id)) {
                    return false;
                }

            }
        }

        Artist newArtist = new Artist(nickname, id, name, url);
        return allUsers.add(newArtist);
    }

    /**
     * Register a content creator
     * 
     * @param nickname String user nickname
     * @param id       String user id
     * @param name     String user name
     * @param url      String representative image url for the content
     *                 creator
     * @return boolean will indicate whether or not the user was registered
     */
    public boolean registerContentCreator(String nickname, String id, String name, String url) {

        User newContentCreator = searchUser(nickname);

        if (newContentCreator == null) {
            newContentCreator = new ContentCreator(nickname, id, name, url);
            return allUsers.add(newContentCreator);

        } else {
            return false;
        }

    }

    /**
     * Register a premium user
     * 
     * @param nickname String user nickname
     * @param id       String user id
     * @return boolean will indicate whether or not the user was registered
     */
    public boolean registerPremiumUser(String nickname, String id) {

        User newPremiumUser = searchUser(nickname);

        if (newPremiumUser == null) {
            newPremiumUser = new PremiumUser(nickname, id);
            return allUsers.add(newPremiumUser);

        } else {
            return false;
        }

    }

    /**
     * Register a standard user
     * 
     * @param nickname String user nickname
     * @param id       String user id
     * @return boolean will indicate whether or not the user was registered
     */
    public boolean registerStandardUser(String nickname, String id) {

        User newStandardUser = searchUser(nickname);

        if (newStandardUser == null) {

            newStandardUser = new StandardUser(nickname, id);
            return allUsers.add(newStandardUser);

        } else {
            return false;
        }

    }

    /**
     * Shows the audios
     * 
     * @return msg String existing audios
     */
    public String showAudios() {

        String msg = "";

        for (int i = 0; i < allAudios.size(); i++) {

            if (allAudios.get(i) != null) {
                msg += allAudios.get(i).getName();
            }

        }

        return msg;
    }

    /**
     * Request the parameters to register a podcast
     * 
     * @param name           String name of the podcast
     * @param distinctiveUrl String url of the representative image
     * @param duration       int duration of the podcast
     * @param nickname       String user nickname
     * @param description    String a short description of the podcast
     * @param category       int podcast category
     * @return msg String will indicate whether or not the podcast was created.
     */
    public String registerPodcast(String name, String distinctiveUrl, int duration, String nickname, String description,
            int category) {

        Audio podcast = searchAudio(name);
        User creator = searchUser(nickname);
        String msg = "The podcast was successfully created";

        if (creator != null) {

            if (podcast == null) {

                ContentCreator newContentCreator = (ContentCreator) creator;
                podcast = new Podcast(name, distinctiveUrl, duration, creator, description, category);
                allAudios.add(podcast);
                newContentCreator.addAudioToContentCreator((Song) podcast);

            } else {
                msg = "The operation could not be performed correctly.";
            }
        }

        return msg;
    }

    /**
     * Request data to register a song and add it to its respective artist
     * 
     * @param name           String name of the song
     * @param distinctiveUrl String
     * @param duration       int
     * @param nickname       String user nickname
     * @param album          String name of the album
     * @param genre          int genre of the song
     * @param sellingPrice   double price of the song
     * @return msg String will indicate whether or not the song could have been
     *         created.
     */
    public String registerSong(String name, String distinctiveUrl, int duration, String nickname, String album,
            int genre, double sellingPrice) {

        Audio song = searchAudio(name);
        User creator = searchUser(nickname);
        String msg = "The song was successfully registered";

        if (creator != null) {

            if (song == null) {
                Artist newArtist = (Artist) creator;
                song = new Song(name, distinctiveUrl, duration, creator, album, genre, sellingPrice);
                allAudios.add(song);
                newArtist.addAudioToArtist((Song) song);
            }

        } else {
            msg = "First you must register a user";
        }

        return msg;
    }

    /**
     * An empty playlist will be created and the requested user will be
     * added to it.
     * 
     * @param playlistName String name of the playlist to create
     * @param nickname     String user nickname
     * @return msg String will indicate whether or not the playlist could be
     *         created.
     */
    public String createPlaylist(String playlistName, String nickname) {

        String msg = "";
        User verifyUser = searchUser(nickname);

        if (verifyUser != null) {

            if (verifyUser instanceof PremiumUser) {
                msg = ((PremiumUser) verifyUser).createPlaylist(playlistName);

            } else if (verifyUser instanceof StandardUser) {
                msg = ((StandardUser) verifyUser).createPlaylist(playlistName);
            }

        } else {
            return msg += "The user entered is not valid within the platform.";
        }

        msg += "The playlist was successfully added to the user's playlist.";
        return msg;
    }

    /**
     * Search for the requested audio
     * 
     * @param audioName String name of the audio to be searched
     * @return object Audio
     */
    public Audio searchAudio(String audioName) {

        Audio variable = null;
        boolean found = false;

        for (int i = 0; i < allAudios.size() && !found; i++) {

            if (allAudios.get(i).getName().equals(audioName)) {

                variable = allAudios.get(i);
                found = true;

            }
        }

        return variable;
    }

    /**
     * Allows you to add an audio to a playlist
     * 
     * @param audioName    String
     * @param playlistName String name of the selected playlist
     * @param nickname     String user nickname
     * @return msg String will indicate whether or not the audio could have been
     *         added to the playlist.
     */
    public String addAudioToPlaylist(String audioName, String playlistName, String nickname) {

        String msg = "";
        User user = searchUser(nickname);
        Audio audio = searchAudio(audioName);

        if (user != null) {

            if (audio != null) {

                if (user instanceof ConsumerUser) {
                    msg = ((ConsumerUser) user).addAudioToPlaylist(playlistName, audio);

                }

            } else {
                msg = "The name of the audio was not found in the platform.";
            }

        } else {
            msg = "The user's name was not found in the platform.";

        }

        return msg;
    }

    /**
     * Allows us to remove audio from a playlist
     * 
     * @param playlistName String name of the required playlist
     * @param nickname     String user nickname
     * @param audioName    String name of the audio to be deleted
     * @return msg String will indicate whether or not the audio could be removed
     *         from the playlist.
     */
    public String deleteAudioToPlaylist(String playlistName, String nickname, String audioName) {

        String msg = "";
        User user = searchUser(nickname);
        Audio audio = searchAudio(audioName);

        if (user != null && audio != null) {

            if (user instanceof ConsumerUser) {

                msg = ((ConsumerUser) user).deleteAudioToPlaylist(playlistName, audio);

            } else {
                msg = "The user's name was not found in the platform.";
            }

        } else {
            msg = "The name of the audio was not found in the platform.";
        }

        return msg;
    }

    /**
     * Allows you to rename an existing playlist
     * 
     * @param nickname        String user nickname
     * @param playlistName    String playlist name to change
     * @param newPlaylistName String new playlist name
     * @return msg String will indicate whether or not the name could be changed.
     */
    public String editPlaylistName(String nickname, String playlistName, String newPlaylistName) {

        String msg = "";
        User user = searchUser(nickname);

        if (user != null) {

            if (user instanceof ConsumerUser) {
                msg = ((ConsumerUser) user).editPlaylistName(playlistName, newPlaylistName);
            }

        }

        msg += "The user's name was not found in the platform.";
        return msg;
    }

    /**
     * Allows you to share the playlist using the generated code
     * 
     * @param nickname     String user nickname
     * @param playlistName String name of the playlist to share
     * @return msg String indicates whether the playlist could be shared
     */
    public String shareAPlaylist(String nickname, String playlistName) {

        String msg = "";
        User verifyUser = searchUser(nickname);

        if (verifyUser != null) {

            if (verifyUser instanceof ConsumerUser) {
                msg = ((ConsumerUser) verifyUser).shareAPlaylist(playlistName);

            } else {
                msg = "The user is not allowed to share playlists.";
            }
        }

        msg += "Successfully shared playlist.";
        return msg;
    }

    /**
     * Shows the respective advertisements
     * 
     * @return msg String advertisement
     */
    public String showAdvertisement() {

        String msg = "";
        int advert = advertisement.nextInt(3);

        switch (advert) {
            case 0:
                msg = AdvertisingSpot.COCACOLA.play();
                break;
            case 1:
                msg = AdvertisingSpot.NIKE.play();
                break;
            case 2:
                msg = AdvertisingSpot.MyMs.play();
                break;
        }
        return msg;
    }

    /**
     * Description: allows you to play an audio
     * 
     * @param nickname  String user nickname
     * @param nameAudio String name of the audio to be played
     * @return msg String will indicate if the audio is playing
     */
    public String playAudio(String nickname, String nameAudio) {

        String msg = "";
        User user = searchUser(nickname);
        Audio audio = searchAudio(nameAudio);

        if (user != null) {

            if (user instanceof ConsumerUser) {

                if (audio != null) {

                    if (user instanceof PremiumUser) {
                        msg = ((PremiumUser) user).playAudio(audio);

                    } else if (user instanceof StandardUser) {
                        StandardUser finalUser = (StandardUser) user;

                        if (audio instanceof Song) {

                            if (finalUser.displayAdvertisements()) {
                                msg += showAdvertisement();
                                msg += "\n" + ((StandardUser) user).playAudio(audio);

                            } else {
                                msg = ((StandardUser) user).playAudio(audio);
                            }

                        } else {
                            msg += showAdvertisement();
                            msg += "\n" + ((StandardUser) user).playAudio(audio);
                        }

                    }
                } else {
                    msg = "Audio does not exist within the platform.";
                }

            }
        } else {
            msg = "The user does not exist within the platform.";
        }

        return msg;
    }

    /**
     * Allows the user to purchase a song
     * 
     * @param nickname  String user nickname
     * @param audioName String name of the audio you wish to purchase
     * @return msg String indicate whether or not it was possible to purchase the
     *         song
     */
    public String buySong(String nickname, String audioName) {

        String msg = "";
        User user = searchUser(nickname);
        Audio audio = searchAudio(audioName);

        if (user != null) {

            if (audio != null && audio instanceof Song) {

                if (user instanceof PremiumUser) {
                    msg = ((PremiumUser) user).purchaseAudio(audio);

                } else if (user instanceof StandardUser) {
                    msg = ((StandardUser) user).purchaseAudio(audio);
                }

            } else {
                msg = "Audio cannot be purchased by this user";
            }

        } else {
            msg = "The user does not exist within the platform.";
        }

        return msg;
    }

    /**
     * Calculates the number of plays of the podcasts on the platform
     * 
     * @return msg String the accumulated number of plays of the podcasts
     */
    public String playlistPlaybackReport() {

        String msg = "";
        int totalReproductions = 0;

        for (int i = 0; i < allAudios.size(); i++) {

            if (allAudios.get(i) instanceof Podcast) {
                totalReproductions += allAudios.get(i).getNumberOfPlays();
            }
        }

        if (totalReproductions != 0) {
            msg = "The total number of podcasts played is: " + totalReproductions;

        } else {
            msg = "No podcasts have been played so far.";
        }

        return msg;
    }

    /**
     * Calculates the number of plays of the songs on the platform
     * 
     * @return msg String the accumulated number of plays of the songs
     */
    public String songsPlaybackReport() {

        String msg = "";
        int totalReproductions = 0;

        for (int i = 0; i < allAudios.size(); i++) {

            if (allAudios.get(i) instanceof Song) {

                totalReproductions += (allAudios.get(i)).getNumberOfPlays();
            }
        }

        if (totalReproductions > 0) {
            msg = "The total number of songs played is:" + totalReproductions;

        } else {

            msg = "No songs have been played so far.";

        }

        return msg;
    }

    /**
     * Search for the song genre most listened to by a specific user
     * 
     * @param nickname nickname of the user you wish to consult
     * @return msg String genre most listened to by the user
     */
    public String genreMostPlayedByAUser(String nickname) {

        String msg = "";
        User user = searchUser(nickname);

        if (user != null) {

            if (user instanceof ConsumerUser) {
                ConsumerUser consuUser = (ConsumerUser) user;
                msg = consuUser.mostPlayedGenre();
            }

        } else {
            msg = "The user does not exist within the platform.";
        }

        return msg;

    }

    /**
     * Search for the most listened song genre across the platform
     * 
     * @return msg String most listened genre of the platform
     */
    public String mostPlayedGenreOnThePlatform() {

        String msg = "";

        int[] totalReproductions = new int[4];

        for (int i = 0; i < totalReproductions.length; i++) {

            totalReproductions[i] = totalplaybackGenres()[i];

        }

        int totalCount = 0;
        int counter = -1;

        for (int i = 0; i < totalReproductions.length; i++) {

            if (totalReproductions[i] > totalCount) {

                totalCount = totalReproductions[i];
                counter = i;
            }
        }
        switch (counter) {
            case 0:
                msg = "The most listened genre is: "
                        + "ROCK, with a total of " + totalReproductions + " reproductions.";
                break;
            case 1:
                msg = "The most listened genre is: "
                        + "POP, with a total of " + totalReproductions + " reproductions.";
                break;
            case 2:
                msg = "The most listened genre is: "
                        + "TRAP, with a total of " + totalReproductions + " reproductions.";
                break;
            case 3:
                msg = "The most listened genre is: "
                        + "HOUSE, with a total of " + totalReproductions + " reproductions.";
                break;
            default:
                msg = "No sound belonging to a genre has been reproduced.";
                break;
        }
        return msg;
    }

    /**
     * Sum of the reproductions in each specific genre
     * 
     * @return totalPlaybacks int[] reproductions by genre
     */
    public int[] totalplaybackGenres() {

        int[] totalPlaybacks = new int[4];

        for (int i = 0; i < allUsers.size(); i++) {

            if (allUsers.get(i) instanceof ConsumerUser) {
                ConsumerUser consuUser = (ConsumerUser) allUsers.get(i);

                for (int j = 0; j < totalPlaybacks.length; j++) {
                    totalPlaybacks[j] += consuUser.reproductionsPerGenre()[j];
                }

            }
        }

        return totalPlaybacks;
    }

    /**
     * Search for the podcast category most listened to by a specific
     * user
     * 
     * @param nickname nickname of the user you wish to consult
     * @return msg String category most listened to by the user
     */
    public String mostListenedCategorybyUser(String nickname) {

        String msg = "";

        User user = searchUser(nickname);

        if (user != null) {

            if (user instanceof ConsumerUser) {

                ConsumerUser check = (ConsumerUser) user;
                msg = check.mostPlayedCategory();

            }

        } else {

            msg = "The user does not exist within the platform.";
        }
        return msg;

    }

    /**
     * Sum of the reproductions in each specific category
     * 
     * @return total int[] reproductions by category
     */
    public int[] totalplaybackCategories() {

        int[] total = new int[4];

        for (int i = 0; i < allUsers.size(); i++) {

            if (allUsers.get(i) instanceof ConsumerUser) {

                ConsumerUser user = (ConsumerUser) allUsers.get(i);

                for (int j = 0; j < total.length; j++) {

                    total[j] += user.reproductionsPerCategory()[j];

                }

            }
        }

        return total;
    }

    /**
     * Search for the most listened podcast category across the
     * platform
     * 
     * @return msg String most listened category of the platform
     */
    public String MostListenedCategoryOnThePlatform() {

        String msg = "";

        int[] total = new int[4];

        for (int i = 0; i < total.length; i++) {

            total[i] = totalplaybackCategories()[i];

        }
        int totalCount = 0;
        int counter = -1;
        for (int i = 0; i < total.length; i++) {
            if (total[i] > totalCount) {
                totalCount = total[i];
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
     * Generates a top five with the artists and content creators with
     * the highest number of views
     * 
     * @return msg String
     */
    public String top5ArtistAndCreators() {

        String msg = "No audio has been played back";

        Artist[] top5Artist = new Artist[5];
        ContentCreator[] top5ContentCreators = new ContentCreator[5];

        ArrayList<Artist> artists = new ArrayList<Artist>();
        ArrayList<ContentCreator> contentCreators = new ArrayList<ContentCreator>();

        for (int i = 0; i < allUsers.size(); i++) {

            if (allUsers.get(i) instanceof Artist) {
                artists.add((Artist) allUsers.get(i));

            } else if (allUsers.get(i) instanceof ContentCreator) {
                contentCreators.add((ContentCreator) allUsers.get(i));

            }
        }

        ProducerUser totalCount = null;
        int count = 0;
        totalCount = artists.get(0);

        for (int i = 0; i < artists.size(); i++) {

            if (artists.get(i).getnumberOfReproductions() > totalCount.getnumberOfReproductions()) {
                totalCount = artists.get(i);

            }
            if (i == artists.size() - 1) {

                if ((top5Artist[4] == null)) {
                    top5Artist[count] = (Artist) totalCount;
                    count++;
                    artists.remove(totalCount);

                    if (artists.size() > 0) {
                        totalCount = artists.get(0);
                        i = 0;

                    }
                }
            }

        }

        count = 0;
        totalCount = contentCreators.get(0);

        for (int i = 0; i < contentCreators.size(); i++) {

            if (contentCreators.get(i).getnumberOfReproductions() > totalCount.getnumberOfReproductions()) {
                totalCount = contentCreators.get(i);
            }
            if (i == contentCreators.size() - 1) {

                if ((top5ContentCreators[4] == null)) {
                    top5ContentCreators[count] = (ContentCreator) totalCount;
                    count++;
                    contentCreators.remove(totalCount);

                    if (contentCreators.size() > 0) {
                        totalCount = contentCreators.get(0);
                        i = 0;
                    }
                }
            }

        }
        for (int i = 0; i < top5Artist.length; i++) {

            if (top5Artist[i] != null) {
                msg = "The top 5 artists are:\n ";
                msg += (i + 1) + "." + top5Artist[i].getName() + " with " + top5Artist[i].getnumberOfReproductions()
                        + " plays";

            }
        }

        for (int i = 0; i < top5ContentCreators.length; i++) {

            if (top5ContentCreators[i] != null) {
                msg += "\nThe top 5 content creators are:\n ";
                msg += (i + 1) + "." + top5ContentCreators[i].getName() + " with "
                        + top5ContentCreators[i].getnumberOfReproductions() + " plays";
            }
        }

        return msg;
    }

    /**
     * Collects podcast and song information and sorts them by number of
     * plays
     * 
     * @return msg String shows the top 10 songs and podcasts
     */
    public String top10SongsAndPodcast() {

        String msg = "No user has used the platform.";

        Song[] top10Songs = new Song[10];
        Podcast[] top10Podcast = new Podcast[10];

        ArrayList<Song> songs = new ArrayList<Song>();
        ArrayList<Podcast> podcasts = new ArrayList<Podcast>();

        for (int i = 0; i < allAudios.size(); i++) {

            if (allAudios.get(i) instanceof Song) {
                songs.add((Song) allAudios.get(i));

            } else if (allAudios.get(i) instanceof Podcast) {
                podcasts.add((Podcast) allAudios.get(i));
            }
        }

        int counter = 0;
        Audio totalCount = null;
        totalCount = songs.get(0);

        for (int i = 0; i < songs.size(); i++) {

            if (songs.get(i).getNumberOfPlays() > totalCount.getNumberOfPlays()) {
                totalCount = songs.get(i);

            }

            if (i == songs.size() - 1) {

                if ((top10Songs[9] == null)) {
                    top10Songs[counter] = (Song) totalCount;
                    counter++;
                    songs.remove(totalCount);

                    if (songs.size() > 0) {
                        totalCount = songs.get(0);
                        i = 0;
                    }
                }
            }

        }

        counter = 0;
        totalCount = podcasts.get(0);

        for (int i = 0; i < podcasts.size(); i++) {

            if (podcasts.get(i).getNumberOfPlays() > totalCount.getNumberOfPlays()) {
                totalCount = podcasts.get(i);
            }

            if (i == podcasts.size() - 1) {

                if ((top10Podcast[9] == null)) {
                    top10Podcast[counter] = (Podcast) totalCount;
                    counter++;
                    podcasts.remove(totalCount);

                    if (podcasts.size() > 0) {
                        totalCount = podcasts.get(0);
                        i = 0;
                    }
                }
            }

        }

        for (int i = 0; i < top10Songs.length; i++) {

            if (top10Songs[i] != null) {
                msg = "The top 10 songs are:\n ";
                msg += (i + 1) + "." + top10Songs[i].getName() + "with genre: " + top10Songs[i].getGenre().name()
                        + " with " + top10Songs[i].getNumberOfPlays() + " plays";
            }
        }

        for (int i = 0; i < top10Podcast.length; i++) {

            if (top10Podcast[i] != null) {
                msg += "\nThe top 10 podcasts are:\n ";
                msg += (i + 1) + "." + top10Podcast[i].getName() + "with Category: "
                        + top10Podcast[i].getCategory().name() + " with " + top10Podcast[i].getNumberOfPlays()
                        + " plays";
            }
        }

        return msg;
    }

    public String reportByGenre(String genre) {

        String msg = "The genre has not been listened to";

        int counter = 0;
        int finalcounter = 0;

        for (int i = 0; i < allAudios.size(); i++) {

            if (allAudios.get(i) instanceof Song) {
                Song report = (Song) allAudios.get(i);

                if (report.getGenre().name().equals(genre)) {
                    counter += report.getSellingPrice();
                }
            }
        }
        for (int i = 0; i < allUsers.size(); i++) {

            if (allUsers.get(i) instanceof ConsumerUser) {

                for (int index = 0; index < ((ConsumerUser) allUsers.get(i)).getPurchaseList().size(); index++) {

                    if (((ConsumerUser) allUsers.get(i)).getPurchaseList().get(index).getChosenSong().getGenre()
                            .name().equals(genre)) {
                        finalcounter += 1;

                    }

                }

            }

        }

        if (counter > 0 && finalcounter > 0) {
            msg = "The genre " + genre + " has sold " + finalcounter + "songs";

        }

        return msg;
    }

    /**
     * Compiles the information of the songs and selects the
     * best-selling one
     * 
     * @return msg String the best selling song
     */
    public String bestSellingSongReport() {

        String msg = "Report could not be generated successfully";
        Song totalCount = null;

        for (int i = 0; i < allAudios.size(); i++) {

            if (allAudios.get(i) instanceof Song) {
                Song songSold = (Song) allAudios.get(i);

                if (totalCount == null) {
                    totalCount = songSold;

                }
                if (songSold.getsalesQuantity() > totalCount.getsalesQuantity()) {
                    totalCount = songSold;

                }
            }
        }
        if (totalCount != null) {
            if (totalCount.getsalesQuantity() > 0) {
                msg = "The best-selling song on the platform is " + totalCount.getName() + " with "
                        + totalCount.getsalesQuantity() + " sales";
            }
        }

        return msg;
    }

}
