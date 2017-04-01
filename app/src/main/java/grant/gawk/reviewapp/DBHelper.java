package grant.gawk.reviewapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This is a helper class with will Create and upgrade the Database
 */

public class DBHelper extends SQLiteOpenHelper {

    //Strings which declare names of the tables/Columns
    public static final String TABLE_RESTAURANT = "RESTAURANTS";
    public static final String RESTAURANT_ID = "_id";
    public static final String RESTAURANT_NAME = "name";
    public static final String RESTAURANT_PICTURE = "picture";
    public static final String RESTAURANT_CITY = "city";


    public static final String TABLE_DISH = "RESTAURANTS";
    public static final String DISH_ID = "_id";
    public static final String DISH_RESTAURANT_ID = "restaurant_id";
    public static final String DISH_NAME = "name";
    public static final String DISH_COMMENT = "comment";
    public static final String DISH_RATING = "rating";
    public static final String DISH_DATE = "date";
    public static final String DISH_PICTURE = "picture";



    //Strings of the Database Name/Version
    public static final String DB_NAME = "DejaFood.db";
    public static final int DB_VERSION = 2;

    //SQL String to create the Database
    public static final String RESTAURANT_CREATE = "create table "
            + TABLE_RESTAURANT + "( " + RESTAURANT_ID + " integer primary key autoincrement, "
            + RESTAURANT_NAME + " text not null, "
            + RESTAURANT_CITY + " text not null, "
            + RESTAURANT_PICTURE + " text"
            + ");";

    //SQL String to create the Database
    public static final String DISH_CREATE = "create table "
            + TABLE_DISH + "( " + DISH_ID + " integer primary key autoincrement, "
            + DISH_RESTAURANT_ID + " integer not null, "
            + DISH_NAME + " text not null, "
            + DISH_COMMENT + " text, "
            + DISH_DATE + " date not null, "
            + DISH_RATING + " text not null, "
            + DISH_PICTURE + " text, "
            + ");";

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(RESTAURANT_CREATE);
        db.execSQL(DISH_CREATE);
    }


    //This method is called on Startup if the DB Version number has changed
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISH);
        onCreate(db);
    }
}
