package TestCases;
public class Book_1 {

    private String title;
    private String author;
    private int pages;
    private double rating;

    public Book_1(String title, String author, int pages, double rating) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.rating = rating;
    }

    public String getSummary() {
        return title + " by " + author + ", pages: " + pages + ", rating: " + rating;
    }

    public void updateRating(double newRating) {
        if (newRating >= 0 && newRating <= 5) {
            this.rating = newRating;
        }
    }

    public boolean isLong() {
        return pages > 350;
    }

    public boolean isHighlyRated() {
        return rating >= 4.0;
    }
}
