package grant.gawk.reviewapp;

/**
 * Created by Grant on 2/18/2017.
 */

public class Dish {


    private long _id;
    private long restaurant_id;
    private String name;
    private String comments;
    private String date;
    private float rating;
    private int favorite = 0;

    public Dish(long id, long rid, String n, String d, String c, float r) {
        _id = id;
        restaurant_id = rid;
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

    public void setFavorite() {
        this.favorite = 1;
    }

    public void unFavorite() {
        this.favorite = 0;
    }

    public long getId() {
        return _id;
    }

    public long getRestaurantId() {
        return restaurant_id;
    }


}
