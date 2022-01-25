package persistence;

// JsonWrite was modeled from given sample application JsonSerializationDemo. Link below:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

import model.MovieList;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// writes current list of watched movies to a save file in data
public class JsonWrite {

    private static final int indent = 5;

    private String destination;
    private PrintWriter pw;

    // EFFECTS: initializes the JsonWriter (constructor)
    public JsonWrite(String d) {
        destination = d;
    }

    // MODIFIES: this
    // EFFECTS: opens and starts the write. Throws FileNotFoundException if file is not found.
    public void open() throws FileNotFoundException {
        pw = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes MovieList to file as a Json
    public void write(MovieList ml) {
        JSONObject json = ml.toJson();
        saveStringToFile(json.toString(indent));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        pw.close();
    }

    // MODIFIES: this
    // EFFECTS: writes to file as a string
    public void saveStringToFile(String s) {
        pw.print(s);
    }



}
