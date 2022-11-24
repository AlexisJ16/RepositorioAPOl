package ui;

import java.util.Scanner;

import model.NeoTunesController;

//Description: welcome to neotunes, where you can really own your music catalog,
//by registering as a user, registering your songs and even buying them. 

public class NeoTunesManager {

    public static Scanner sc = new Scanner(System.in);
    public static NeoTunesController controller = new NeoTunesController();

    public static void main(String[] args) {

        NeoTunesManager menu = new NeoTunesManager();
        int option;

        do {
            do {
                menu.menu();
                option = sc.nextInt();

            } while (option < 0 || option > 16);

        } while (option != 0);

    }

    private void menu() {

        boolean exit = false;

        while (!exit) {

            System.out.println("\n¡Welcome to NeoTunes!"
                    + "\nPlease select an option"
                    + "\n1.Register producer users"
                    + "\n2.Register consumer users"
                    + "\n3.Record audio"
                    + "\n4.Create playlist"
                    + "\n5.Edit playlist"
                    + "\n6.Share a playlist"
                    + "\n7.Play audio"
                    + "\n8.Buy a song"
                    + "\n9.Generate report of accumulated reproductions"
                    + "\n10.Know the most listened song genre"
                    + "\n11.Know the most listened podcast category"
                    + "\n12.Check out the top five artists and content creators"
                    + "\n13.Check the top ten songs and podcasts"
                    + "\n14.Generate report by genre"
                    + "\n15.Generate report of the best-selling song on the platform"
                    + "\n0.Exit");
            int election = sc.nextInt();

            switch (election) {

                case 1:
                    System.out.println("\n1.Register an artist");
                    System.out.println("\n2.Register a content creator ");
                    int option = sc.nextInt();

                    if (option == 1) {
                        registerArtist();

                    } else if (option == 2) {
                        registerContentCreator();

                    } else {
                        System.out.println("Type a valid option");
                    }
                    break;

                case 2:
                    System.out.println("1.Register a standard user");
                    System.out.println("2.Register a premium user");

                    option = sc.nextInt();

                    if (option == 1) {
                        registerStandarUser();

                    } else if (option == 2) {
                        registerPremiumUser();

                    } else {
                        System.out.println("Type a valid option");

                    }
                    break;

                case 3:
                    System.out.println("1.Record a song");
                    System.out.println("2.Register a podcast ");

                    option = sc.nextInt();

                    if (option == 1) {
                        recordSong();

                    } else if (option == 2) {
                        registerPodcast();

                    } else {
                        System.out.println("Type a valid option");
                    }
                    break;

                case 4:
                    createPlaylist();
                    break;

                case 5:
                    System.out.println("1.Rename the playlist");
                    System.out.println("2.Add audio to the playlist");
                    System.out.println("3.Remove audio from playlist");

                    option = sc.nextInt();

                    if (option == 1) {
                        renamePlaylist();

                    } else if (option == 2) {
                        addAudioToPlaylist();

                    } else if (option == 3) {
                        deleteAudio();

                    } else {
                        System.out.println("Type a valid option");
                    }
                    break;

                case 6:
                    shareAPlaylist();
                    break;

                case 7:
                    playAudio();
                    break;

                case 8:
                    buySong();
                    break;

                case 9:
                    System.out.print("1.To know the total number of reproductions of the songs ");
                    System.out.println("2.Know the total number of plays of the podcasts ");
                    option = sc.nextInt();

                    if (option == 1) {
                        System.out.println(controller.songsPlaybackReport());

                    } else if (option == 2) {
                        System.out.println(controller.playlistPlaybackReport());

                    } else {
                        System.out.println("Type a valid option");
                    }

                case 10:
                    System.out.print("1.Know the most listened song genre for the entire platform.");
                    System.out.println("2.Knowing the most listened song genre for a specific user ");
                    option = sc.nextInt();

                    if (option == 1) {
                        System.out.println(controller.mostPlayedGenreOnThePlatform());

                    } else if (option == 2) {
                        genreMostPlayedByAUser();

                    } else {
                        System.out.println("Type a valid option");
                    }
                    break;

                case 11:
                    System.out.print("1.Know the most listened song genre for the entire platform.");
                    System.out.println("2.Knowing the most listened song genre for a specific user ");
                    option = sc.nextInt();

                    if (option == 1) {
                        System.out.println(controller.MostListenedCategoryOnThePlatform());

                    } else if (option == 2) {
                        mostListenedCategoryToByUser();

                    } else {
                        System.out.println("Type a valid option");
                    }
                    break;

                case 12:
                    System.out.println(controller.top5ArtistAndCreators());
                    break;

                case 13:
                    System.out.println(controller.top10SongsAndPodcast());
                    break;

                case 14:
                    reportByGenre();
                    break;

                case 15:
                    System.out.println(controller.bestSellingSongReport());
                    break;

                case 0:
                    sc.nextLine();
                    exit = true;
                    System.out.println("Thanks for using our system");
                    break;

                default:
                    System.out.println("You must type a valid option");
                    break;
            }
        }

    }

    /**
     * Asks for the necessary data to register a artist
     * 
     */
    public void registerArtist() {

        System.out.println("Enter the artist's nickname ");
        sc.nextLine();
        String nickname = sc.nextLine();

        System.out.println("Enter the artist id");
        String id = sc.nextLine();

        System.out.println("Type the artist's name ");
        String name = sc.nextLine();

        System.out.println("Type the url corresponding to the artist ");
        String url = sc.nextLine();

        if (controller.registerArtist(nickname, id, name, url)) {

            System.out.println("Artist successfully registered");

        } else {
            System.out.println("Error,Artist could not be registered");
        }

    }

    /**
     * Asks for the necessary data to register a content creator
     * 
     */
    public void registerContentCreator() {

        System.out.println("Enter the content creator's nickname");
        sc.nextLine();
        String nickname = sc.nextLine();

        System.out.println("Enter the content creator id");
        String id = sc.nextLine();

        System.out.println("Type the content creator's name ");
        String name = sc.nextLine();

        System.out.println("Type the url corresponding to the content creator ");
        String url = sc.nextLine();

        if (controller.registerContentCreator(nickname, id, name, url)) {

            System.out.println("Content creator successfully registered");

        } else {

            System.out.println("Error, contente creator could not be registered");
        }

    }

    /**
     * Asks for the necessary data to register a premium user
     */
    public void registerPremiumUser() {

        System.out.println("Enter the premium user's nickname");
        sc.nextLine();
        String nickname = sc.nextLine();

        System.out.println("Enter the premium user id");
        String id = sc.nextLine();

        if (controller.registerPremiumUser(nickname, id)) {

            System.out.println("Premium user successfully registered");

        } else {
            System.out.println("Error, premium user could not be registered");
        }

    }

    /**
     * Asks for the necessary data to register a standard user
     */
    public void registerStandarUser() {

        System.out.println("Enter the premium user's nickname");
        sc.nextLine();
        String nickname = sc.nextLine();

        System.out.println("Enter the premium user id");
        String id = sc.nextLine();

        if (controller.registerStandardUser(nickname, id)) {

            System.out.println("Standard user successfully registered");

        } else {
            System.out.println("Error, standard user could not be registered");
        }

    }

    /**
     * Request the necessary data to register a song
     */
    public void recordSong() {

        System.out.println("Enter the podcast name");
        String name = sc.nextLine();

        System.out.println("Type the url pertaining to the album artwork ");
        String distinctiveUrl = sc.nextLine();

        System.out.println("How many minutes is the song?");
        int duration = sc.nextInt();

        System.out.println("Please, type the nickname of the artist the song belongs to");
        String nickname = sc.nextLine();

        System.out.println("Type the name of the album the song belongs to ");
        String album = sc.nextLine();

        System.out.println("What is your song genre\n0.Rock\n1.Pop\n2.Trap\n3.House");
        int genre = sc.nextInt();

        System.out.println("Type the selling value of the song (in dollars)");
        double sellingPrice = sc.nextDouble();

        controller.registerSong(name, distinctiveUrl, duration, nickname, album, genre, sellingPrice);

    }

    /**
     * Request data to register a podcast
     * 
     */
    public void registerPodcast() {

        System.out.println("Enter the podcast name");
        String name = sc.nextLine();

        System.out.println("Enter the url of your podcast image");
        String distinctiveUrl = sc.nextLine();

        System.out.println("How many minutes is the podcast?");
        int duration = sc.nextInt();

        System.out.println("Please, enter the nickname of the creator to which the content belongs ");
        String nickname = sc.nextLine();

        System.out.println("Please, write a brief description of the content of your podcast");
        String description = sc.nextLine();

        System.out.println("Select your podcast category: \n0.Politics\n1.Entertainment\n2.VideoGame\n3.Fashion");
        int category = sc.nextInt();

        controller.registerPodcast(name, distinctiveUrl, duration, nickname, description, category);
    }

    /**
     * Requests data to create an empty playlist to which songs can be added later
     * on
     * 
     */
    public void createPlaylist() {

        System.out.println("Enter the playlist name");
        sc.nextLine();
        String playlistName = sc.nextLine();

        System.out.println("Please, enter your user nickname again");
        sc.nextLine();
        String nickname = sc.nextLine();

        controller.createPlaylist(playlistName, nickname);

    }

    /**
     * Requests data to create an empty playlist to which songs can be
     * added later on
     */
    public void renamePlaylist() {

        System.out.println("Please, enter your user nickname again");
        String nickname = sc.nextLine();

        System.out.println("Enter the current name of the playlist");
        String playlistName = sc.nextLine();

        System.out.println("");
        String newPlaylistName = sc.nextLine();

        controller.editPlaylistName(nickname, playlistName, newPlaylistName);

    }

    /**
     * Request data to add an audio to a playlist
     */
    private void addAudioToPlaylist() {

        System.out.println("Please, enter the name of the audio you want to add");
        String audioName = sc.nextLine();

        System.out.println("Enter the name of the playlist it will be added to");
        String playlistName = sc.nextLine();

        System.out.println("Please, enter your user nickname again");
        String nickname = sc.nextLine();

        controller.addAudioToPlaylist(audioName, playlistName, nickname);

    }

    /**
     * Request data to delete an audio
     */
    private void deleteAudio() {

        System.out.println("Enter the name of the playlist the audio belongs to ");
        String playlistName = sc.nextLine();

        System.out.println("Please, enter your user nickname again");
        String nickname = sc.nextLine();

        System.out.println("Please, enter the name of the audio you want to remove");
        String audioName = sc.nextLine();

        controller.deleteAudioToPlaylist(playlistName, nickname, audioName);

    }

    /**
     * Request data to share a playlist
     */
    private void shareAPlaylist() {

        System.out.println("Please, enter your user nickname ");
        String nickname = sc.nextLine();

        System.out.println("Type the name of the playlist you want to share  ");
        String playlistName = sc.nextLine();

        controller.shareAPlaylist(nickname, playlistName);

    }

    /**
     * Requests the user's data and the name of the audio you want to
     * listen to
     */
    private void playAudio() {

        System.out.println("Please, enter your user nickname ");
        String nickname = sc.nextLine();

        System.out.println("Type the name of the audio you want to listen to ");
        String audioName = sc.nextLine();

        controller.playAudio(nickname, audioName);

    }

    /**
     * Request buyer's data and the song you want to buy
     */
    private void buySong() {

        System.out.println("Please, enter your user nickname ");
        String nickname = sc.nextLine();

        System.out.println("Type the name of the audio you want to listen to ");
        String audioName = sc.nextLine();

        controller.buySong(nickname, audioName);

    }

    /**
     * In this method, we will be able to consult the most listened
     * song genre for a specific user.
     */
    private void genreMostPlayedByAUser() {

        System.out.println("Please, enter your user nickname ");
        String nickname = sc.nextLine();

        controller.genreMostPlayedByAUser(nickname);
    }

    /**
     * Requests the gender in order to make your report
     */
    private void reportByGenre() {

        System.out.println("Select the genre to show : \n1.Rock\n2. Pop\n3. Trap\4. House");
        int option = sc.nextInt();

        switch (option) {
            case 1:
                System.out.println(controller.reportByGenre("ROCK"));
                break;
            case 2:
                System.out.println(controller.reportByGenre("POP"));
                break;
            case 3:
                System.out.println(controller.reportByGenre("TRAP"));
                break;
            case 4:
                System.out.println(controller.reportByGenre("HOUSE"));
                break;
        }
    }

    /**
     * In this method, we will be able to consult the most listened
     * podcast category for a specific user.
     */
    private void mostListenedCategoryToByUser() {

        System.out.println("Please, enter your user nickname ");
        String nickname = sc.nextLine();
        controller.mostListenedCategorybyUser(nickname);
    }
}