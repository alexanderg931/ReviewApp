package grant.gawk.reviewapp;
public class Restaurant
{
    private long _id;
    private String Picture;
    private String Name;

    /**
     * <p>
     *     The city the restaurant is in.
     * </p>
     */
    private String City;



    Restaurant(long id, String picture, String name, String city){
        _id = id;
        Picture = picture;
        Name = name;
        City = city;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }
    public String getPicture() {
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

    public long getId() {
        return _id;
    }

    @Override
    public String toString(){

        return this.getName();
    }
}

