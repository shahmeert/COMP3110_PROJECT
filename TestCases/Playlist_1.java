package TestCases;
import java.util.ArrayList;
import java.util.List;

public class Playlist_1 {

    private String name;
    private List<String> tracks;

    public Playlist_1(String name) {
        this.name = name;
        this.tracks = new ArrayList<>();
    }

    public void addTrack(String track) {
        if (track != null && !track.isEmpty()) {
            tracks.add(track);
        }
    }

    public boolean removeTrack(String track) {
        return tracks.remove(track);
    }

    public int getTrackCount() {
        return tracks.size();
    }

    public String getName() {
        return name;
    }

    public List<String> getTracksCopy() {
        return new ArrayList<>(tracks);
    }
}
