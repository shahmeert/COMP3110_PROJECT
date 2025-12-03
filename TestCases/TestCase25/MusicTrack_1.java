public class MusicTrack_1 {

    private String title;
    private String artist;
    private int duration; // seconds
    private int playCount;

    public MusicTrack_1(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.playCount = 0;
    }

    public void play() {
        playCount++;
    }

    public int getPlayCount() {
        return playCount;
    }

    public String info() {
        return title + " by " + artist + " (" + duration + "s)";
    }
}
