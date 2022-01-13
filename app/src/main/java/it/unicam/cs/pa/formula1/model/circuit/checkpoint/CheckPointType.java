package it.unicam.cs.pa.formula1.model.circuit.checkpoint;

/**
 * Responsibility : This enum describe all the CheckPoint Types with their colors
 *
 * @author Pierdominici
 */
public enum CheckPointType {
    START("#ffffff"),
    NORMAL("#000000"),
    FINISH("#ffffff"),
    START_FINISH("#ffffff"),
    OBLIGATORY("#ff0000");

    private final String hex;

    CheckPointType(String hex){
        this.hex=hex;
    }

    public String getHex() {
        return this.hex;
    }
}
