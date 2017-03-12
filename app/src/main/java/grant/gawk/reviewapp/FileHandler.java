package grant.gawk.reviewapp;
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Grant on 2/27/2017.
 */
import android.content.Context;
import android.os.Environment;

public class FileHandler {
    // Array List to hold extracted restaurant objects
    private ArrayList<Restaurant> restaurantList;

    // Array List to hold extracted dish objects
    private ArrayList<Dish> dishList;

    // Variable to hold the main restaurant file
    private File rTxt;

    // Variable to hold the dish file for a particular restaurant
    private File dTxt;

    // Required by Java to write characters to file efficiently
    private BufferedWriter output;

    // It can be fed a file in its constructor, so we'll use it to read the files
    private Scanner reader;

    // The current context. Only used to grab the external file directory.
    private Context appContext;

    public FileHandler(Context context) {
        // Constructor for the File Handler, takes the Application's Context as an argument
        // The constructor generates a restaurant file on first launch. If it's not the first launch (or there's already a restaurants.txt), it simply loads the data from that instead.
        appContext = context;

        // Makes a new File object at the location where we'll save the data. .getExternalFilesDir(null) gives us our app's personal external storage directory
        // So essentially, the first argument is the path, and the second argument is the file to find.
        rTxt = new File(appContext.getExternalFilesDir(null), "restaurants.txt");
        if (!rTxt.exists()) {
            // This checks if the text file doesn't exist and tries to create it. If it does, it just simply loads the list of restaurants because we already must have stuff saved in there
            try {
                rTxt.createNewFile();
            }
            catch (IOException e) {
                // If for some reason the file couldn't be created, it prints out the error. This happens if we don't have write permissions to the directory.
                System.err.println(e.toString());
            }
        }
        else {
            // If the file exists, go ahead and load the data on it (if any)
            loadRestaurants();
        }
    }

    public void writeDish(String res, Dish dish) {
        // Method to write a dish object to file.  Requires the restaurant name and dish object as inputs

        // Create a new File object that lies at /<our external data storage path>/<name of restaurant>.txt
        dTxt = new File(appContext.getExternalFilesDir(null), res+".txt");

        if(!dTxt.exists())
        // Check if the file doesn't exist and try to create it.
        // Throw an error if we can't write to this directory.
        {
            try {
                dTxt.createNewFile();
            }
            catch (IOException e) {
                System.err.println(e.toString());
            }
        }

        // Where it's actually written to file
        try {
            // Create a new BufferedWriter with an argument of a FileWriter.
            // FileWriter is a class that writes single characters to file.
            // Its arguments are the File object to write to, and a "true" meaning to append to file, rather than recreate anew
            // BufferedWriter is a class that writes whole strings to file, but requires a FileWriter to write
            // We could've just used FileWriter, but BufferedWriter makes it more efficient, in some way that I forgot by now
            output = new BufferedWriter(new FileWriter(dTxt, true));
            // I used underscores to be the delimiters we'll separate each attribute of the dish object with in the file
            // Each line is a separate dish object
            String toWrite = dish.getName() + "_" + dish.getDate() + "_" + dish.getComments() + "_" + Float.toString(dish.getRating());

            // Because the output object ITSELF is a BufferedWriter, we have to specify we're appending with the append method.
            // Now, I don't know if you have to send a FileWriter that's set to append if you're specifically calling this
            output.append(toWrite);

            // Writes a new line.
            output.newLine();

            // Close our file to be responsible programmers and not cause memory leaks
            output.close();
        }
        catch (IOException e) {
            // If for whatever reason we can't actually write to the directory
            System.err.println(e.toString());
        }
    }

    public void writeDish(Restaurant res, Dish dish) {
        // Overloaded method for writeDish. Lets you pass a restaurant object instead of just the name string. Still requires a dish object, though.
        writeDish(res.getName(), dish);
    }

    public void writeRestaurant(Restaurant res) {
        // Write a new restaurant to the restaurants file! See writeDish(String res, Dish dish) for detailed look into BufferedWriter/FileWriter
        try {
            // Open our restaurants file. Remember, our constructor made restaurants.txt loaded into our attribute rTxt, and we never need another restaurants file
            // So we don't have to go through the File object stuff and associated error handling
            output = new BufferedWriter(new FileWriter(rTxt, true));

            // Just like the dish file, we use underscores as the string delimiter
            String toWrite = res.getName() + "_" + res.getCity();

            // Append the restaurants file with our string
            output.append(toWrite);

            // Make a new line, each new line is a restaurant
            output.newLine();

            // Close our file
            output.close();
        }
        catch (IOException e)
        {
            // If we couldn't write to the directory, this error gets caught and printed to the console
            System.err.println(e.toString());
        }

        // Refresh our ArrayList of restaurants!
        loadRestaurants();
    }

    public void loadRestaurants() {
        // Our method to reload our ArrayList (or do the first additions) of Restaurant Objects
        try {
            // Create a new scanner loaded with our restaurant file as input
            // As noted in writeRestaurant, we just pass the attribute because it never changes. There's never a different restaurants.txt
            // And we assigned it in the constructor
            Scanner reader = new Scanner(rTxt);

            // Make a variable to hold a line of the file at a time
            String inputBuffer;

            // Initialize our restaurantList! Why? Because if we just did .add we would end up with duplicate entries
            restaurantList = new ArrayList<>();

            // While there is a next line to read in the file
            while(reader.hasNextLine()) {
                // Make an array of strings to hold each attribute of the restaurant
                String[] inputBroken;

                // Load a line into the buffer variable
                inputBuffer = reader.nextLine();

                // Break up the line by the underscores we added in writeRestaurant to separate our attributes
                inputBroken = inputBuffer.split("_");

                // Make a new restaurant with the information we extracted from file
                // Note: first argument is null because we can't save images yet
                Restaurant newRes = new Restaurant(null, inputBroken[0], inputBroken[1]);

                // Add the restaurant object to the array list
                restaurantList.add(newRes);
            }
        }
        catch (FileNotFoundException e) {
            // If for some reason the file can't be found, send this error to the console to be printed
            System.err.println(e.toString());
        }
    }

    public void loadDishes(String res) {
        // The method to load the dishes for a specific restaurant into our dish array list
        // Dishes loaded on demand for memory concerns, as opposed to restaurants which are front-loaded
        try {
            // Make a new file object at location /<External Files Directory>/<name of restaurant>.txt
            dTxt = new File(appContext.getExternalFilesDir(null), res+".txt");

            if (!dTxt.exists()) {
                // Check if the dish file doesn't exist. If it doesn't, try to create it.
                try {
                    dTxt.createNewFile();
                }
                catch (IOException e) {
                    // If we can't write to this directory, print the error to the console
                    System.err.println(e.toString());
                }
            }

            // Load the dish file into our Scanner object
            reader = new Scanner(dTxt);

            // Make a string to hold a line at a time from the file
            String inputBuffer;

            // Make an array of strings to hold each attribute of the dish object once we break up the inputBuffer
            String[] inputBroken;

            // Re-initialize the dishList so we don't end up with other restaurant's dishes in the list, and also so we don't get duplicates on update.
            dishList = new ArrayList<>();

            // While the file has a next line to read
            while (reader.hasNextLine()) {

                // Load the line into inputBuffer
                inputBuffer = reader.nextLine();

                // Split it up based on underscores separating each attribute
                inputBroken = inputBuffer.split("_");

                // Add our new dish object to the dish list
                /* public Dish(String n, String d, String c, float r) */
                dishList.add(new Dish(inputBroken[0], inputBroken[1], inputBroken[2], Float.parseFloat(inputBroken[3])));
            }
        }
        catch (FileNotFoundException e) {
            // Catch a FileNotFoundException if scanner can't find out dish file
            System.err.println(e.toString());
        }
    }

    public void loadDishes(Restaurant res) {
        // Overload of loadDishes where you can use the Restaurant object instead of the restaurant name string
        loadDishes(res.getName());
    }

    public ArrayList<Dish> getDishes() {
        return dishList;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurantList;
    }
}
