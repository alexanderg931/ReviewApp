package grant.gawk.reviewapp;

/**
 * The Dish object used to store data related to the individual dishes for a restaurant.
 * <p>
 *      Contains five attributes:
 *      <ul>
 *          <li>name- The String name of the dish</li>
 *          <li>comments- A String of the user's comments about the dish </li>
 *          <li>date- A String representation of a Java API generated date for which the dish was entered (ideally ordered)</li>
 *          <li>rating- A float holding a 0.0 to 5.0 rating of the dish</li>
 *          <li>favorite- An integer holding either a 0 or 1 to decide if the dish is a not a favorite or favorite, respectively</li>
 *      </ul>
 * </p>
 * @version 1.0
 * @see Restaurant
 */
public class Dish {
    private String name;
    private String comments;
    private String date;
    private float rating;
    private int favorite = 0;

    /**
     * Public constructor to initialize the attributes in the Dish object. Note that favorite is initialized to 0 on its own,
     * without use of the constructor.
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
     * @return The name of the dish
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return The date in a string format.
     */
    public String getDate()
    {
        return date;
    }

    /**
     * @return The numerical rating of the dish from 0.0-5.0, in .5 intervals
     */
    public float getRating()
    {
        return rating;
    }

    /**
     * @return The comments the user has made about the dish
     */
    public String getComments()
    {
        return comments;
    }

    /**
     * @return The 0 or 1 favorite attribute of the dish
     */
    public int isFavorite() {
        return favorite;
    }

    /**
     * Sets the dish as a favorite
     */
    public void setFavorite() {
        this.favorite = 1;
    }

    /**
     * Sets the dish as not a favorite
     */
    public void unFavorite() {
        this.favorite = 0;
    }


}
