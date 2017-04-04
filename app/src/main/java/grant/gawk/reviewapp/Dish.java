package grant.gawk.reviewapp;


public class Dish {


    private long _id;
    private long restaurant_id;
    private String name;
    private String comments;
    private String date;
    private float rating;
    private int favorite = 0;
    private String picture;



    public Dish(long id, long rid, String name, String date, String comments, float rating) {
        this._id = id;
        this.restaurant_id = rid;
        this.name = name;
        this.date = date;
        this.comments = comments;
        this.rating = rating;

    }

    public String getName()
    {
        return name;
    }

    String getDate()
    {
        return date;
    }

    float getRating()
    {
        return rating;
    }

    String getComments()
    {
        return comments;
    }

    int isFavorite() {
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

    long getRestaurantId() {
        return restaurant_id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String toString(){

        return this.getName();
    }


}
