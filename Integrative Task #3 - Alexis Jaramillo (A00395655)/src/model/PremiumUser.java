package model;

import java.util.ArrayList;

public class PremiumUser extends ConsumerUser {

	private ArrayList<Playlist> playlists;
	private ArrayList<Purchase> songList;

	public PremiumUser(String nickname, String id) {
		super(nickname, id);
		this.playlists = new ArrayList<Playlist>();
		this.songList = new ArrayList<Purchase>();
	}

	public ArrayList<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(ArrayList<Playlist> playlists) {
		this.playlists = playlists;
	}

	public ArrayList<Purchase> getPurchaseList() {
		return songList;
	}

	public void setpurchaseList(ArrayList<Purchase> songList) {
		this.songList = songList;
	}

	/**
	 * This method allows us to buy songs
	 * 
	 * @param audio Audio object to be purchased
	 * @return msg String will indicate whether the purchase was successful or not.
	 */
	public String purchaseAudio(Audio audio) {

		String msg = "";
		Purchase flag = searchAudio(audio.getName());

		if (flag == null) {

			if (audio instanceof Song) {
				Song newSong = (Song) audio;
				msg += newSong.purchase();
				super.getPurchaseList().add(new Purchase(newSong));

			} else {
				msg = "The audio you wish to purchase is not a song.";
			}
		}
		msg += "The song was successfully purchased";
		return msg;

	}

	/**
	 * This method allows to search a song by its name
	 * 
	 * @param name is the name of the song to be searched.
	 * @return check the shop that contains the song.
	 */
	public Purchase searchAudio(String name) {

		Purchase check = null;
		boolean search = false;

		for (int i = 0; i < super.getPurchaseList().size() && !search; i++) {

			if (super.getPurchaseList().get(i).getChosenSong().getName().equalsIgnoreCase(name)) {
				check = super.getPurchaseList().get(i);
				search = true;
			}
		}

		return check;
	}

	/**
	 * This method allows to add a playlist to the user's list of playlists.
	 * 
	 * @param playlistName String the name of the playlist to be added.
	 * @return msg String the message that indicates if the playlist was added
	 *         successfully or not.
	 */
	public String createPlaylist(String playlistName) {

		String msg = "Playlist successfully created";
		Playlist check = searchPlaylist(playlistName);

		if (check == null) {
			playlists.add(new Playlist(playlistName));

		} else {
			msg = "The name of the playlist is already registered in the platform.";
		}

		return msg;
	}

	/**
	 * Allows to add an audio to a playlist.
	 * 
	 * @param playlistName String is the name of the playlist.
	 * @param audio        Audio is the audio to be added.
	 * @return msg String the message that indicates if the audio was added
	 *         successfully or not.
	 */
	public String addAudioToPlaylist(String playlistName, Audio audio) {

		String msg = "";
		Playlist check = searchPlaylist(playlistName);

		if (check != null) {
			msg = check.createAudio(audio);

		} else {
			msg = "The name of the playlist could not be found.";
		}

		msg += "The audio was successfully added to the playlist.";
		return msg;
	}

	/**
	 * Allows to search a playlist by its name.
	 * 
	 * @param playlistName String is the name of the playlist to be searched.
	 * @return check PlayList the playlist found.
	 */
	public Playlist searchPlaylist(String playlistName) {

		Playlist check = null;
		boolean search = false;

		if (playlists != null) {

			for (int i = 0; i < playlists.size() && !search; i++) {

				if (playlists.get(i).getPlaylistName().equalsIgnoreCase(playlistName)) {
					check = playlists.get(i);
					search = true;
				}
			}
		}

		return check;
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
			msg = "The name of the playlist could not be found.";
		}

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
		Playlist check = searchPlaylist(playlistName);

		if (check != null) {
			msg = check.deleteAudio(audio);

		} else {
			msg = "The playlist is not registered on the platform.";

		}
		msg += "The audio was successfully deleted from the playlist.";
		return msg;

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
		Playlist check = searchPlaylist(playlistName);

		if (check != null) {
			msg = check.shareAPlaylist();
		}

		msg += "The playlist could not be shared";
		return msg;
	}

}