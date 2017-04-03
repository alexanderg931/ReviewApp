package grant.gawk.reviewapp;

/**
 * <p>
 *     The Dish object used to store data related to the individual dishes for a restaurant.
 * </p>
 * @version 1.0
 * @see Restaurant
 */
public class Dish {
    /**
     * <p>
     *     The String name of the dish
     * </p>
     */
    private String name;

    /**
     * <p>
     *     A String of the user's comments about the dish
     * </p>
     */
    private String comments;

    /**
     * <p>
     *     A String representation of a Java API generated date for which the dish was entered (ideally ordered)
     * </p>
     */
    private String date;

    /**
     * <p>
     *     A float holding a 0.0 to 5.0 (in increments of 0.5) rating of the dish
     * </p>
     */
    private float rating;

    /**
     * <p>
     *     An integer holding either a 0 or 1 to decide if the dish is a not a favorite or favorite, respectively
     * </p>
     */
    private int favorite = 0;

    /**
     * <p>
     *     Public constructor to initialize the attributes in the Dish object. Note that favorite is initialized to 0 on its own,
     *      without use of the constructor.
     * </p>
     * @param n The name of the dish
     * @param d The date the dish was ordered
     * @param c The user's comments about the dish
     * @param r The 0.0 to 5.0 rating of the dish
     */
    public Dish(String n, String d, String c, float r) {
        name = n;
        comments = c;
        rating = r;
        date = d;
    }

    /**
     * <p>
     *     Gets the name of the dish
     * </p>
     * @return The name of the dish
     */
    public String getName()
    {
        return name;
    }

    /**
     * <p>
     *     * Gets the date the dish was submitted
     * </p>
     * @return The date in a string format.
     */
    public String getDate()
    {
        return date;
    }

    /**
     * <p>
     *     Gets the rating of the dish
     * </p>
     * @return The numerical rating of the dish from 0.0-5.0, in .5 intervals
     */
    public float getRating()
    {
        return rating;
    }

    /**
     * <p>
     *     Gets the comments for the dish
     * </p>
     * @return The comments the user has made about the dish
     */
    public String getComments()
    {
        return comments;
    }

    /**
     * <p>
     *     Returns if the dish is a favorite
     * </p>
     * @return The 0 or 1 favorite attribute of the dish
     */
    public int isFavorite() {
        return favorite;
    }

    /**
     * <p>
     *     Sets the dish as a favorite
     * </p>
     */
    public void setFavorite() {
        this.favorite = 1;
    }

    /**
     * <p>
     *     Sets the dish as not a favorite
     * </p>
     */
    public void unFavorite() {
        this.favorite = 0;
    }


}
