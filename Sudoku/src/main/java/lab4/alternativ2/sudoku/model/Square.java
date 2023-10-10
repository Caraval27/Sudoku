package lab4.alternativ2.sudoku.model;

public class Square {
    private final int correctNumber;
    private int selectedNumber;
    private final boolean changeable;

    public Square(int correctNumber, boolean changeable) {
        this.correctNumber = correctNumber;
        this.changeable = changeable;
    }

    public int getCorrectNumber() {
        return correctNumber;
    }

    public int getSelectedNumber() {
        return selectedNumber;
    }

    public boolean isChangeable() {
        return changeable;
    }

    public void setSelectedNumber(int selectedNumber) {
        this.selectedNumber = selectedNumber;
    }

    public void clearSelectedNumber() {
        selectedNumber = 0;
    }

    @Override
    public String toString() {
        return "Square{ correctNumber=" + correctNumber + ", selectedNumber=" + selectedNumber + ", changeable=" +
                changeable + '}';
    }
}
