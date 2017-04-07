package grant.gawk.reviewapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;


class DataAccessObject {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    final static public String[] restaurantColumns = {
            DBHelper.RESTAURANT_ID,
            DBHelper.RESTAURANT_NAME,
            DBHelper.RESTAURANT_CITY,
            DBHelper.RESTAURANT_PICTURE
            };

    final static public String[] dishColumns = {
            DBHelper.DISH_DATE,
            DBHelper.DISH_PICTURE,
            DBHelper.DISH_RATING,
            DBHelper.DISH_COMMENT,
            DBHelper.DISH_ID,
            DBHelper.DISH_RESTAURANT_ID,
            DBHelper.DISH_NAME,
            DBHelper.DISH_FAVORITE
            };

     DataAccessObject(Context context){
         dbHelper = new DBHelper(context);
     }

     void open() throws SQLException{
         db = dbHelper.getWritableDatabase();
     }

     void close(){
         dbHelper.close();
     }

     Restaurant insertRestaurant(String picture, String name, String city){
         ContentValues values = new ContentValues(); //Creates a list of Key-Values pairs to add

         //Add values under appropriate columns
         values.put(DBHelper.RESTAURANT_PICTURE, picture);
         values.put(DBHelper.RESTAURANT_NAME, name);
         values.put(DBHelper.RESTAURANT_CITY, city);

         //add primary key
         long insertId = db.insert(DBHelper.TABLE_RESTAURANT, null, values);
         Log.d(TAG, "inserting at: " + insertId);

         Cursor cursor = db.query(DBHelper.TABLE_RESTAURANT, restaurantColumns,
                 DBHelper.RESTAURANT_ID + " = " + insertId, null, null, null, null);

         cursor.moveToFirst();
         Restaurant newRestaurant = cursorToRest(cursor);
         cursor.close();
         return newRestaurant;
     }

     //Delete a restaurant entry
     void deleteRest(Restaurant restaurant){
        long id = restaurant.getId();
        Log.d(TAG, "Deleting Restaurant with id: " + id);
        db.delete(DBHelper.TABLE_RESTAURANT, DBHelper.RESTAURANT_ID + " = " + id, null);

        Log.d(TAG, "Deleting All Dishes at Restaurant with id: " + id);
        db.delete(DBHelper.TABLE_DISH, DBHelper.DISH_RESTAURANT_ID + " = " + id, null);
    }

    //Return a list of all Restaurants
    List<Restaurant> getAllRestaurants(){
        List<Restaurant> restaurants = new ArrayList<>();

        //create a cursor pointing to the Restaurant Table
        Cursor cursor = db.query(DBHelper.TABLE_RESTAURANT, restaurantColumns, null, null, null, null, null);

        //Iterates through the table and adds each restaurant to a list
        Log.d(TAG, cursor.getPosition() + "");
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Restaurant restaurant = cursorToRest(cursor);
            restaurants.add(restaurant);
            cursor.moveToNext();
        }

        cursor.close();
        return restaurants;
    }

    private Restaurant cursorToRest(Cursor cursor){
        Log.d(TAG, cursor.getString(0));
        Log.d(TAG, cursor.getString(1));
        Log.d(TAG, cursor.getString(2));
        Log.d(TAG, cursor.getString(3));
        return new Restaurant(cursor.getLong(0), cursor.getString(3), cursor.getString(1), cursor.getString(2));
    }

    //insert a new Dish
    Dish insertDish(long restaurant_id, String name, String date, String comment, float rating, String picture){
        ContentValues values = new ContentValues();
        values.put(DBHelper.DISH_RESTAURANT_ID, restaurant_id);
        values.put(DBHelper.DISH_NAME, name);
        values.put(DBHelper.DISH_DATE, date);
        values.put(DBHelper.DISH_COMMENT, comment);
        values.put(DBHelper.DISH_RATING, rating);
        values.put(DBHelper.DISH_PICTURE, picture);

        long insertId = db.insert(DBHelper.TABLE_DISH, null, values);

        Cursor cursor = db.query(DBHelper.TABLE_DISH, dishColumns, DBHelper.DISH_ID + " = " + insertId,
                null, null, null, null);

        cursor.moveToFirst();
        Dish newDish = cursorToDish(cursor);
        cursor.close();
        return newDish;
    }

    //Delete an existing dish
    public void deleteDish(Dish dish){
        long id = dish.getId();
        Log.d(TAG, "Deleting Dish with id: " + id);
        db.delete(DBHelper.TABLE_DISH, DBHelper.DISH_ID + " = " + id, null);
    }

    //return a list of dishes from a specific restaurant
    List<Dish> getDishesFromRestaurant(long restaurant_id, Context context){
        List<Dish> dishes = new ArrayList<>();
        String whereClause = "restaurant_id = " + restaurant_id;
        String orderBy;

        orderBy = PreferenceManager.getDefaultSharedPreferences(context)
                .getString("pref_dish_name", "");
        orderBy = orderBy.concat(PreferenceManager.getDefaultSharedPreferences(context)
                .getString("pref_dish_date", ""));
        orderBy = orderBy.concat(PreferenceManager.getDefaultSharedPreferences(context)
                .getString("pref_dish_favorite", ""));
        orderBy = orderBy.concat(PreferenceManager.getDefaultSharedPreferences(context)
                .getString("pref_dish_rating", ""));

        if (orderBy.length() < 5){
            orderBy = "restaurant_id desc";
        }
        orderBy = orderBy.replaceAll(",$", "");
        Log.d(TAG, orderBy);


        //creates a Cursor pointing to the Dish Table
        Cursor cursor = db.query(DBHelper.TABLE_DISH, dishColumns, whereClause, null, null, null, orderBy);

        //Iterates through the table returning each dish matching the resturant ID
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Dish dish = cursorToDish(cursor);
            dishes.add(dish);
            cursor.moveToNext();
        }

        cursor.close();
        return dishes;

    }

    //return the row being pointed at by cursor
    private Dish cursorToDish(Cursor cursor){

        return new Dish(
            cursor.getLong(4),
            cursor.getLong(5),
            cursor.getString(6),
            cursor.getString(0),
            cursor.getString(3),
            cursor.getFloat(2)
        );
    }




}
