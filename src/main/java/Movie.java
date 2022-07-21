public class Movie {
    private String title;
    private String image;
    private String imDbRating;

    public Movie() {}

    public Movie(String title, String image, String imDbRating) {
        this.title = title;
        this.image = image;
        this.imDbRating = imDbRating;
    }
    public String getTitle() {
        return title;
    }
    public String getImage() {
        return image;
    }
    public String getImDbRating() {
        return imDbRating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImDbRating(String imDbRating) {
        this.imDbRating = imDbRating;
    }

}
