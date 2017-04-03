package grant.gawk.reviewapp;
import android.graphics.Bitmap;

/**
 * A data object used to store restaurant data, which includes a picture, the name, and the city.
 * Accompanying dish objects are not stored directly with the Restaurant.
 * <p>
 *     Contains three attributes:
 *     <ul>
 *         <li>Picture- A picture of the restaurant</li>
 *         <li>Name- The name of the restaurant</li>
 *         <li>City- The city the restaurant is in</li>
 *     </ul>
 * </p>
 * @version 1.0
 * @see Dish
 */
public class Restaurant
{
    private Bitmap Picture;
    private String Name;
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
     * Changes the initialized value of the restaurant's Picture attribute
     * @param picture   A picture to replace the current picture attribute.
     */
    public void setPicture(Bitmap picture) {
        Picture = picture;
    }

    /**
     * @return The restaurant's picture.
     */
    public Bitmap getPicture() {
        return Picture;
    }

    /**
     * @return The name of the restaurant.
     */
    public String getName() {
        return Name;
    }

    /**
     * Changes the initialized value of the restaurant's Name attribute
     * @param name  The name of the restaurant.
     */
    public void setName(String name) {
        Name = name;
    }

    /**
     *
     * @return The city the restaurant is in.
     */
    public String getCity() {
        return City;
    }

    /**
     * Changes the initialized value of the restaurant's City attribute
     * @param city  The city the restaurant is in
     */
    public void setCity(String city) {
        City = city;
    }
}

