package lab4.alternativ2.sudoku.model;

import java.util.Arrays;

public class GridModel {
    private final Square[][] squares;
    private SudokuUtilities.SudokuLevel level;

    public GridModel(SudokuUtilities.SudokuLevel level) {
        squares = new Square[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        this.level = level;
    }

    private Square[][] copySquares(Square[][] squares) {
        Square[][] squaresCopy = new Square[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        for (int i = 0; i < SudokuUtilities.GRID_SIZE; i++) {
            for (int j = 0; j < SudokuUtilities.GRID_SIZE; j++) {
                Square square = squares[i][j];
                if (square != null) {
                    squaresCopy[i][j] = new Square(square.getCorrectNumber(), square.getSelectedNumber(), square.isChangeable());
                }
            }
        }
        return squaresCopy;
    }

    public Square[][] getSquares() {
        return copySquares(squares);
    }

    public SudokuUtilities.SudokuLevel getLevel() {
        return level;
    }

    public void setLevel(SudokuUtilities.SudokuLevel level) {
        this.level = level;
        // initNewGame();
    }

    public static String getRules() {
        String rules = "Rule 1: Each row and column must contain the numbers 1 to 9, without repetitions.\n";
        rules += "Rule 2: The digits can only occur once per block (the 9 3x3 blocks in the 9x9 grid)\n";
        rules += "Rule 3: The sum of every single row, column and 3x3 block must equal 45.\n";
        return rules;
    }

    @Override
    public String toString() {
        return "GridModel [squares: " + Arrays.toString(squares) + ", level: " + level + "]";
    }
}