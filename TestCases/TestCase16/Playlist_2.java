import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Playlist_2 {

    private String title;
    private List<String> songs;

    public Playlist_2(String title) {
        this.title = title;
        this.songs = new ArrayList<>();
    }

    public void addSong(String song) {
        if (song != null && !song.isEmpty()) {
            songs.add(song);
        }
    }

    public boolean removeSong(String song) {
        return songs.remove(song);
    }

    public int size() {
        return songs.size();
    }

    public List<String> getSortedSongs() {
        List<String> copy = new ArrayList<>(songs);
        Collections.sort(copy);
        return copy;
    }
}
