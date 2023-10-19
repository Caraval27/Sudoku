package lab4.alternativ2.sudoku.model;

import java.io.Serializable;

/**
 * Represents a square with a correct number, with a selected number and whether it's changeable or not.
 */
public class Square implements Serializable {
    private final int correctNumber;
    private int selectedNumber;
    private final boolean changeable;

    /**
     * Creates a new square with correct number and selected number and sets whether it's changeable or not.
     * @param correctNumber The given correct number of the new square.
     * @param selectedNumber The given selected number of the new square.
     * @param changeable Whether the new square is set to be changeable or not.
     */
    public Square(int correctNumber, int selectedNumber, boolean changeable) {
        this.correctNumber = correctNumber;
        this.selectedNumber = selectedNumber;
        this.changeable = changeable;
    }

    /**
     * Gets the correct number of this square.
     * @return The correct number of this square.
     */
    public int getCorrectNumber() {
        return correctNumber;
    }

    /**
     * Gets the selected number of this square.
     * @return The selected number of this square.
     */
    public int getSelectedNumber() {
        return selectedNumber;
    }

    /**
     * Gets whether this square is changeable or not.
     * @return Whether this square is changeable or not.
     */
    public boolean isChangeable() {
        return changeable;
    }

    /**
     * Sets a new selected number of this square.
     * @param selectedNumber The given new selected number of this square.
     */
    public void setSelectedNumber(int selectedNumber) {
        this.selectedNumber = selectedNumber;
    }

    /**
     * Converts the information of this square to a string.
     * @return The information of this square as a string.
     */
    @Override
    public String toString() {
        return "Square [correctNumber: " + correctNumber + ", selectedNumber: " + selectedNumber + ", changeable: " +
                changeable + ']';
    }
}