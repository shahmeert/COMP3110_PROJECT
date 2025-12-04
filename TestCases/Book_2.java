
public class Book_2 {

    private String title;
    private String writer;
    private int pageCount;
    private double score;

    public Book_2(String title, String writer, int pageCount, double score) {
        this.title = title;
        this.writer = writer;
        this.pageCount = pageCount;
        this.score = score;
    }

    public String description() {
        return title + " by " + writer + ", " + pageCount + " pages, score: " + score;
    }

    public void adjustScore(double delta) {
        double updated = this.score + delta;
        if (updated >= 0 && updated <= 5) {
            this.score = updated;
        }
    }

    public boolean isLong() {
        return pageCount > 350;
    }

    public boolean excellent() {
        return score >= 4.5;
    }
}
