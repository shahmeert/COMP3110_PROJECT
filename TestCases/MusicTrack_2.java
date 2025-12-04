package TestCases;
public class MusicTrack_2 {

    private String name;
    private String performer;
    private int lengthSeconds;
    private int plays;

    public MusicTrack_2(String name, String performer, int lengthSeconds) {
        this.name = name;
        this.performer = performer;
        this.lengthSeconds = lengthSeconds;
        this.plays = 0;
    }

    public void playOnce() {
        plays++;
    }

    public int timesPlayed() {
        return plays;
    }

    public String describe() {
        return name + " by " + performer + " [" + lengthSeconds + "s]";
    }
}
