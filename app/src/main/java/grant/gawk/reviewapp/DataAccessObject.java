package grant.gawk.reviewapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Anthony on 4/1/2017.
 */

public class DataAccessObject {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    private String[] restaurantColumns = {
            DBHelper.RESTAURANT_ID,
            DBHelper.RESTAURANT_NAME,
            DBHelper.RESTAURANT_CITY,
            DBHelper.RESTAURANT_PICTURE
            };

    private String[] dishColumns = {
            DBHelper.DISH_DATE,
            DBHelper.DISH_PICTURE,
            DBHelper.DISH_RATING,
            DBHelper.DISH_COMMENT,
            DBHelper.DISH_ID,
            DBHelper.DISH_RESTAURANT_ID,
            DBHelper.DISH_NAME
            };

     public DataAccessObject(Context context){
         dbHelper = new DBHelper(context);
     }

     public void open() throws SQLException{
         db = dbHelper.getWritableDatabase();
     }

     public void close(){
         dbHelper.close();
     }

     public Restaurant insertRestaurant(String picture, String name, String city){
         ContentValues values = new ContentValues(); //Creates a list of Key-Values pairs to add

         //Add values under appropriate columns
         values.put(DBHelper.RESTAURANT_PICTURE, picture);
         values.put(DBHelper.RESTAURANT_NAME, name);
         values.put(DBHelper.RESTAURANT_CITY, city);

         //add primary key
         long insertId = db.insert(DBHelper.TABLE_RESTAURANT, null, values);

         Cursor cursor = db.query(DBHelper.TABLE_RESTAURANT, restaurantColumns,
                 DBHelper.RESTAURANT_ID + " = " + insertId, null, null, null, null);

         cursor.moveToFirst();
         Restaurant newRestaurant = cursorToRest(cursor);
         cursor.close();
         return newRestaurant;
     }

     //Delete a restaurant entry
    public void deleteRest(Restaurant restaurant){
        long id = restaurant.getId();
        Log.d(TAG, "Deleting Restaurant with id: " + id);
        db.delete(DBHelper.TABLE_RESTAURANT, DBHelper.RESTAURANT_ID + " = " + id, null);

        Log.d(TAG, "Deleting All Dishes at Restaurant with id: " + id);
        db.delete(DBHelper.TABLE_DISH, DBHelper.DISH_RESTAURANT_ID + " = " + id, null);
    }

    //Return a list of all Restaurants
    public List<Restaurant> getAllRestaurants(){
        List<Restaurant> restaurants = new ArrayList<>();

        //create a cursor pointing to the Restaurant Table
        Cursor cursor = db.query(DBHelper.TABLE_RESTAURANT, restaurantColumns, null, null, null, null, null);

        //Iterates through the table and adds each restaurant to a list
        while(!cursor.isAfterLast()){
            Restaurant restaurant = cursorToRest(cursor);
            restaurants.add(restaurant);
            cursor.moveToNext();
        }

        cursor.close();
        return restaurants;
    }

    private Restaurant cursorToRest(Cursor cursor){
        Restaurant restaurant = new Restaurant(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return restaurant;
    }



}
