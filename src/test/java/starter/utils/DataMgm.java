package starter.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public final class DataMgm {

    public static  JSONObject getJsonFromFile() throws ParseException {
        String data = "";
        try {
            File myObj = new File("src/test/resources/petData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = data.concat(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(data);
    }

}
