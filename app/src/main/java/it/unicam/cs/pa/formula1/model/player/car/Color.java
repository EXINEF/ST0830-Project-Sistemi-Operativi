package it.unicam.cs.pa.formula1.model.player.car;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Responsibility : This class provide the implementation of the Car's Color
 *
 * @author Pierdominici
 */
public class Color{

    private final String name;
    private final String hex;

    public Color(String name, String hex) {
        if(name==null)
            throw new NullPointerException("Color's name cannot be null...");
        if(hex==null)
            throw new NullPointerException("Color's hex cannot be null...");
        checkIfHexIsValid(hex);
        this.name = name;
        this.hex = hex;
    }

    public String getHex() {
        return hex;
    }

    @Override
    public String toString() {
        return name;
    }

    private void checkIfHexIsValid(String hex){
        if(hex.length()!=7)
            throw new IllegalArgumentException("HEX must be 7 characters...");
        if(hex.charAt(0)!='#')
            throw new IllegalArgumentException("HEX must start with #");
        String newHex = hex.substring(1);
        Pattern pattern = Pattern.compile("[^a-fA-F0-9]");
        Matcher matcher = pattern.matcher(newHex);
        if(matcher.find())
            throw new IllegalArgumentException("HEX must be from #000000 to #ffffff");
    }
}
