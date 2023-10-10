package lab4.alternativ2.sudoku.model;

import java.util.Arrays;

public class GridModel {
    private final Square[][] squares;
    private SudokuUtilities.SudokuLevel level;

    public GridModel() {
        squares = new Square[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        level = SudokuUtilities.SudokuLevel.MEDIUM;
    }

    private Square[][] copySquares(Square[][] squares) {
        Square[][] squaresCopy = new Square[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int column = 0; column < SudokuUtilities.GRID_SIZE; column++) {
                Square square = squares[row][column];
                if (square != null) {
                    squaresCopy[row][column] = new Square(square.getCorrectNumber(), square.getSelectedNumber(), square.isChangeable());
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

    public void initNewGame() {
        int[][][] numbers = SudokuUtilities.generateSudokuMatrix(level);
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int column = 0; column < SudokuUtilities.GRID_SIZE; column++) {
                int[] number = numbers[row][column];
                int selectedNumber = number[0];
                boolean changeable = selectedNumber == 0;
                squares[row][column] = new Square(number[1], selectedNumber, changeable);
            }
        }
    }

    public void setLevel(SudokuUtilities.SudokuLevel level) {
        this.level = level;

        initNewGame();
    }

    public void setSquare(int value, int row, int column) {
        Square square = squares[row][column];
        if (square.isChangeable()) {
            square.setSelectedNumber(value);
        }
    }

    public void clearSquare(int row, int column) {
        setSquare(0, row, column);
    }

    public void clearSquares() {
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int column = 0; column < SudokuUtilities.GRID_SIZE; column++) {
                clearSquare(row, column);
            }
        }
    }

    private int[] randomPosition() {
        return new int[] {(int) (Math.random() * 9) + 1, (int) (Math.random() * 9) + 1};
    }

    public void setCorrectSquare() {
        int[] position;
        int row;
        int column;
        Square square;
        do {
            position = randomPosition();
            row = position[0];
            column = position[1];
            square = squares[row][column];
        } while (!square.isChangeable());
        setSquare(square.getCorrectNumber(), row, column);
    }

    public boolean checkSquares() {
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int column = 0; column < SudokuUtilities.GRID_SIZE; column++) {
                Square square = squares[row][column];
                int selectedNumber = square.getSelectedNumber();
                if (selectedNumber != 0 && selectedNumber != square.getCorrectNumber() ) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String getRules() {
        String rules = "Rule 1: Each row and column must contain the numbers 1 to 9, without repetitions.\n";
        rules += "Rule 2: The digits can only occur once per block (the 9 3x3 blocks in the 9x9 grid).\n";
        rules += "Rule 3: The sum of every single row, column and 3x3 block must equal 45.\n";
        return rules;
    }

    @Override
    public String toString() {
        return "GridModel [squares: " + Arrays.toString(squares) + ", level: " + level + "]";
    }
}