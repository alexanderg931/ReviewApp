package grant.gawk.reviewapp;
public class Restaurant
{
    private long _id;
    private String Picture;
    private String Name;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCity() {
        return City;
    }

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

