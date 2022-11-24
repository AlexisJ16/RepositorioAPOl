package model;

public class StandardUser extends ConsumerUser {

   // totalCountimum number of songs that can be purchased by a standard user.
   public static final int totalSongsToBePurchased = 100;

   // totalCountimum number of playlists that can be created by a standard user.
   public static final int totalPlaylistsToBePurchased = 20;

   private int counter;

   public StandardUser(String nickname, String id) {
      super(nickname, id);
      counter = 0;
   }

   public int getCounter() {
      return counter;
   }

   public void setCounter(int counter) {
      this.counter = counter;
   }

   /**
    * Is in charge of searching for the audio you wish to purchase.
    * 
    * @param name String name of the audio to be searched
    * @return Purchase search result
    */
   public Purchase searchAudioToPurchase(String name) {

      Purchase buy = null;
      boolean flag = false;

      for (int i = 0; i < super.getPurchaseList().size() && !flag; i++) {

         if (super.getPurchaseList().get(i).getChosenSong().getName().equalsIgnoreCase(name)) {
            buy = super.getPurchaseList().get(i);
            flag = true;
         }
      }

      return buy;
   }

   /**
    * This method allows to create a Playlist.
    * 
    * @param playlistName String the name of the playlist to be created.
    * @return msg String the message that indicates if the playlist was created
    *         successfully or not.
    */
   public String createPlaylist(String playlistName) {

      String msg = "";
      Playlist variable = searchPlaylist(playlistName);
      boolean available = availableCreation();

      if (variable == null) {

         if (available) {

            for (int i = 0; i < super.getPlaylists().size() && variable == null; i++) {

               if (super.getPlaylists().get(i) == null) {
                  super.getPlaylists().add(new Playlist(playlistName));
                  variable = super.getPlaylists().get(i);
               }
            }

         } else {
            msg = "No more playlists can be created within the platform.";
         }

      } else {
         msg = "The playlist you wish to create is already registered.";
      }

      return msg;
   }

   /**
    * Verify that the playlist to be created has not exceeded the
    * totalCountimum allowed for the user
    * 
    * @return boolean depends on whether or not there is space to create playlist
    */
   public boolean availableCreation() {

      boolean available = false;

      if (super.getPlaylists().size() < totalPlaylistsToBePurchased) {
         available = true;
      }

      return available;
   }

   /**
    * This method allows us to buy songs
    * 
    * @param audio Audio object to be purchased
    * @return msg String will indicate whether the purchase was successful or not.
    */
   public String purchaseAudio(Audio audio) {

      String msg = "";
      Purchase flag = searchAudioToPurchase(audio.getName());
      boolean available = availableToPurchase();

      if (flag == null) {

         if (available) {

            if (audio instanceof Song) {

               Song buySong = (Song) audio;
               msg += buySong.purchase();
               super.getPurchaseList().add(new Purchase(buySong));

            } else {
               msg = "This type of audio cannot be purchased with this type of user.";
            }

         } else {
            msg = "No more songs can be purchased";
         }
      }

      return msg;
   }

   /**
    * Verifies that the user has not exceeded the song purchase limit
    * 
    * @return boolean depends on whether or not the user has exceeded the purchase
    *         limit.
    */
   public boolean availableToPurchase() {

      boolean flag = false;

      if (super.getPurchaseList().size() < totalSongsToBePurchased) {
         flag = true;

      }

      return flag;
   }

   /**
    * Allows to share a playlist.
    * 
    * @param playlistName String is the name of the playlist.
    * @return msg String the message that indicates if the audio was shared
    *         successfully or not.
    */
   public String shareAPlaylist(String playlistName) {

      String msg = "";
      Playlist search = searchPlaylist(playlistName);

      if (search != null) {
         search.shareAPlaylist();

      } else {
         msg = "The playlist could not be found on the platform.";

      }

      return msg;
   }

   /**
    * Allows to search a playlist by its name.
    * 
    * @param playlistName String is the name of the playlist to be searched.
    * @return PlayList the playlist found.
    */
   public Playlist searchPlaylist(String playlistName) {

      Playlist search = null;
      boolean flag = false;

      for (int i = 0; i < super.getPlaylists().size() && !flag; i++) {

         if (super.getPlaylists().get(i) != null
               && super.getPlaylists().get(i).getPlaylistName().equalsIgnoreCase(playlistName)) {

            search = super.getPlaylists().get(i);
            flag = true;

         }
      }

      return search;
   }

   /**
    * Allows to change the name of a playlist
    * 
    * @param playlistName    String is the name of the playlist to be edited.
    * @param newPlaylistName String is the new name of the playlist.
    * @return msg String the message that indicates if the playlist was edited
    *         successfully or not.
    */
   public String editPlaylistName(String playlistName, String newPlaylistName) {

      String msg = "";
      Playlist check = searchPlaylist(playlistName);

      if (check != null) {
         check.setPlaylistName(newPlaylistName);

      } else {
         msg = "The playlist could not be found on the platform.";
      }

      return msg;
   }

   /**
    * Allows to add an audio to a playlist.<br>
    * 
    * @param playlistName String is the name of the playlist.
    * @param audio        Audio is the audio to be added.
    * @return msg String the message that indicates if the audio was added
    *         successfully or not.
    */
   public String addAudioToPlaylist(String playlistName, Audio audio) {

      String msg = "";
      Playlist search = searchPlaylist(playlistName);

      if (search != null) {
         msg = search.createAudio(audio);

      } else {
         msg = "The playlist could not be found on the platform.";
      }

      msg += "The audio was successfully added to the playlist.";
      return msg;
   }

   /**
    * Allows to remove an audio from a playlist.
    * 
    * @param playlistName String is the name of the playlist.
    * @param audio        Audio is the audio to be removed.
    * @return msg String the message that indicates if the audio was removed
    *         successfully or not.
    */
   public String deleteAudioToPlaylist(String playlistName, Audio audio) {

      String msg = "";
      Playlist delete = searchPlaylist(playlistName);

      if (delete != null) {
         msg = delete.deleteAudio(audio);

      } else {
         msg = "The playlist could not be found on the platform.";
      }

      msg += "The audio was successfully removed from the playlist.";
      return msg;
   }

   /**
    * Will show the advertisements
    * 
    * @return flag boolean if the condition is satisfied
    */
   public boolean displayAdvertisements() {

      boolean flag = false;

      if (counter == 2) {
         flag = true;
         counter = 0;

      } else {
         counter++;
      }

      return flag;
   }
}