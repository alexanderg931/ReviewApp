package grant.gawk.reviewapp;

/**
 * Created by Grant on 2/18/2017.
 */

public class Dish {
    private String name;
    private String comments;
    private String date;
    private float rating;
    private int favorite;

    public Dish(String n, String d, String c, float r) {
        name = n;
        comments = c;
        rating = r;
        date = d;
    }

    public String getName()
    {
        return name;
    }

    public String getDate()
    {
        return date;
    }

    public float getRating()
    {
        return rating;
    }

    public String getComments()
    {
        return comments;
    }

    public int isFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }


}
