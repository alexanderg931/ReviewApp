package grant.gawk.reviewapp;
import android.graphics.Bitmap;

/**
 * <p>
 *      A data object used to store restaurant data, which includes a picture, the name, and the city.
 *      Accompanying dish objects are not stored directly with the Restaurant.
 * </p>
 * @author Khoa
 * @version 1.0
 * @since 1.0
 * @see Dish
 */
public class Restaurant
{
    /**
     * <p>
     *     A picture of the restaurant.
     * </p>
     */
    private Bitmap Picture;

    /**
     * <p>
     *     The name of the restaurant.
     * </p>
     */
    private String Name;

    /**
     * <p>
     *     The city the restaurant is in.
     * </p>
     */
    private String City;

    /**
     * A basic public constructor to initialize the Picture, Name, and City attributes of the class.
     * @param picture   A picture of the restaurant.
     * @param name      The name of the restaurant.
     * @param city      The city the restaurant is in.
     */
    public Restaurant(Bitmap picture, String name, String city){
        Picture = picture;
        Name = name;
        City = city;
    }

    /**
     * Changes the initialized value of the restaurant's Picture attribute.
     * @param picture   A picture to replace the current picture attribute.
     */
    public void setPicture(Bitmap picture) {
        Picture = picture;
    }

    /**
     * <p>
     *     Gets the restaurant's picture.
     * </p>
     * @return The restaurant's picture.
     */
    public Bitmap getPicture() {
        return Picture;
    }

    /**
     * <p>
     *     Gets the name of the restaurant.
     * </p>
     * @return The name of the restaurant.
     */
    public String getName() {
        return Name;
    }

    /**
     * <p>
     *     Changes the initialized value of the restaurant's Name attribute.
     * </p>
     * @param name  The name of the restaurant.
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     * <p>
     *     Gets the city the restaurant is in.
     * </p>
     * @return The city the restaurant is in.
     */
    public String getCity() {
        return City;
    }

    /**
     * <p>
     *     Changes the initialized value of the restaurant's City attribute
     * </p>
     * @param city  The city the restaurant is in
     */
    public void setCity(String city) {
        City = city;
    }
}

