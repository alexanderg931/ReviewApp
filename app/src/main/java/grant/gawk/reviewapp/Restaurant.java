package grant.gawk.reviewapp;
import android.graphics.Bitmap;


public class Restaurant
{
    private Bitmap Picture;
    private String Name;
    private String City;

    public Restaurant(Bitmap picture, String name, String city){
        Picture = picture;
        Name = name;
        City = city;
    }

    public void setPicture(Bitmap picture) {
        Picture = picture;
    }
    public Bitmap getPicture() {
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
}

