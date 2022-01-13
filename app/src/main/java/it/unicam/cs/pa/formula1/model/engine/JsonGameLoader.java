package it.unicam.cs.pa.formula1.model.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.unicam.cs.pa.formula1.model.RacetrackGame;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Responsibility : This class load and save (for creating game easier) the game
 * from a Configuration File using GSON library
 *
 * @author Pierdominici
 */
public class JsonGameLoader {

    /**
     * ONLY FOR GAME CREATION PURPOSE:
     * It is used to write a game already loaded on a file so as to reopen it
     *
     * @param game game already load
     * @param filePath file path
     *
     * @throws Exception general Exception
     */
    public static void saveGameToFile(RacetrackGame game, String filePath) throws Exception {
        // setPrettyPrinting wrote Game nicely
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter file = new FileWriter(filePath);
            gson.toJson(game, file);
            file.close();
            System.out.println("LOG: Game save successfully in this file: "+filePath);
        } catch (IOException e) {
            System.out.println("LOG: Unable to save Game, there was an error...");
            e.printStackTrace();
            throw new Exception();
        }
    }

    /**
     * Load from JSON file an Object Game ready to play
     *
     * @param filePath filePath of the game
     *
     * @return game
     *
     * @throws Exception general Exception
     */
    public static RacetrackGame loadGameFromFile(String filePath) throws Exception {
        try {
            String jsonString = getStringFromFile(filePath);
            return new Gson().fromJson(jsonString, RacetrackGame.class);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new Exception("There was a problem while loading the file...");
        }
    }

    /**
     * Create a String from a File
     *
     * @param path Path File
     * @return String created
     *
     * @throws IOException I/O Exception
     */
    private static String getStringFromFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
