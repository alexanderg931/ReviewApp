package grant.gawk.reviewapp;

import org.junit.Test;

import static org.junit.Assert.*;


public class DataAccessObjectTest {


    //Checks to make sure DBHelper and DAO are on the same page
    @Test
    public void IsDishSQLStringSame() throws Exception{
        for (String column: DataAccessObject.dishColumns) {
            assertTrue(DBHelper.DISH_CREATE.contains(column));
        }

        String commaSplit[] = DBHelper.DISH_CREATE.split(",");
        assertTrue(commaSplit.length == DataAccessObject.dishColumns.length);
    }

    @Test
    public void IsRestaurantSQLStringSame() throws Exception{
        for (String column: DataAccessObject.restaurantColumns) {
            assertTrue(DBHelper.RESTAURANT_CREATE.contains(column));
        }

        String commaSplit[] = DBHelper.RESTAURANT_CREATE.split(",");
        assertTrue(commaSplit.length == DataAccessObject.restaurantColumns.length);
    }

    @Test
    public void checkSQLForTrailingComma() throws Exception{
        assertFalse(DBHelper.RESTAURANT_CREATE.contains(",);"));
        assertFalse(DBHelper.DISH_CREATE.contains(",);"));
    }
}