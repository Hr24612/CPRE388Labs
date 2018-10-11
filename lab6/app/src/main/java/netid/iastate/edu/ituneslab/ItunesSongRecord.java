package netid.iastate.edu.ituneslab;

/**
 * This class holds information about an iTunes song record.
 */
public class ItunesSongRecord {
    /**
     * A string to represent the title of the album that contains this song
     */
    private String albumTitle;
    /**
     * A string to represent the song title
     */
    private String songTitle;

    /**
     * Constructor for an ItunesSongRecord
     *
     * @param songTitle  a String representation of the song title
     * @param albumTitle a String representation of the album title containing this song
     */
    public ItunesSongRecord(String albumTitle, String songTitle) {
        this.albumTitle = albumTitle;
        this.songTitle = songTitle;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }
}
