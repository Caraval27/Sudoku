package lab4.alternativ2.sudoku.model;

/**
 * Represents a grid with squares at a certain level.
 */
public class SudokuModel {
    private final Square[][] squares;
    private SudokuUtilities.SudokuLevel level;

    /**
     * Creates a new grid with 9*9 squares at the default level medium and initiates a new game.
     */
    public SudokuModel() {
        squares = new Square[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        level = SudokuUtilities.SudokuLevel.MEDIUM;
        initNewGame();
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

    /**
     * Gets a copy of the squares of this grid.
     * @return A copy of the squares of this grid.
     */
    public Square[][] getSquares() {
        return copySquares(squares);
    }

    /**
     * Gets the level of this grid.
     * @return The level of this grid.
     */
    public SudokuUtilities.SudokuLevel getLevel() {
        return level;
    }

    /**
     * Creates new squares, that differ from the previous squares, with correct numbers and originally selected numbers,
     * which also indicate whether they're changeable or not.
     */
    public void initNewGame() {
        int[][][] numbers;
        do {
            numbers = SudokuUtilities.generateSudokuMatrix(level);
        } while (squares[0][0] != null && numbers[0][0][1] == squares[0][0].getCorrectNumber());
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int column = 0; column < SudokuUtilities.GRID_SIZE; column++) {
                int[] number = numbers[row][column];
                int selectedNumber = number[0];
                boolean changeable = selectedNumber == 0;
                squares[row][column] = new Square(number[1], selectedNumber, changeable);
            }
        }
    }

    /**
     * Sets the level of this grid and initiates a new game.
     * @param level The level of this grid.
     */
    public void setLevel(SudokuUtilities.SudokuLevel level) {
        this.level = level;

        initNewGame();
    }

    /**
     * Sets the given value as the selected number of the square with the given row and column, if it's changeable.
     * @param value The given selected number of the square.
     * @param row The given row of the square.
     * @param column The given column of the square.
     * @throws IllegalStateException If the square is not changeable.
     */
    public void setSelectedSquare(int value, int row, int column) throws IllegalStateException {
        Square square = squares[row][column];
        if (!square.isChangeable()) {
            throw new IllegalStateException();
        }
        square.setSelectedNumber(value);
    }

    /**
     * Sets new squares of this grid.
     * @param squares The given new squares of this grid.
     */
    public void setSquares(Square[][] squares) {
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            System.arraycopy(squares[row], 0, this.squares[row], 0, SudokuUtilities.GRID_SIZE);
        }
    }

    /**
     * Clear the selected numbers of all squares that is changeable.
     */
    public void clearSelectedSquares() {
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int column = 0; column < SudokuUtilities.GRID_SIZE; column++) {
                if (squares[row][column].isChangeable()) {
                    setSelectedSquare(0, row, column);
                }
            }
        }
    }

    /**
     * Sets the correct value as the selected value of a random generated square that is not already selected.
     * @throws IllegalStateException If all squares are already selected.
     */
    public void setCorrectSquare() throws IllegalStateException {
        if (allSquaresSelected()) {
            throw new IllegalStateException();
        }
        int row, column;
        Square square;
        do {
            row = SudokuUtilities.generateRandomNumber(SudokuUtilities.MAX_POSITION, SudokuUtilities.MIN_POSITION);
            column = SudokuUtilities.generateRandomNumber(SudokuUtilities.MAX_POSITION, SudokuUtilities.MIN_POSITION);
            square = squares[row][column];
        } while (square.getSelectedNumber() != 0);
        setSelectedSquare(square.getCorrectNumber(), row, column);
    }

    /**
     * Checks if all squares that is selected have the correct value as the selected value.
     * @return If all squares that is selected have the correct value as the selected value.
     */
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

    /**
     * Gets rules of the game.
     * @return The rules of the game.
     */
    public String getRules() {
        String rules = "Rule 1: Each row and column must contain the numbers 1 to 9, without repetitions.\n";
        rules += "Rule 2: The digits can only occur once per block (the 9 3x3 blocks in the 9x9 grid).\n";
        rules += "Rule 3: The sum of every single row, column and 3x3 block must equal 45.\n";
        return rules;
    }

    /**
     * Checks if all squares are selected.
     * @return If all squares are selected.
     */
    public boolean allSquaresSelected() {
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int column = 0; column < SudokuUtilities.GRID_SIZE; column++) {
                if (squares[row][column].getSelectedNumber() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Converts the information of the grid to a string.
     * @return The information of the grid as a string.
     */
    @Override
    public String toString() {
        StringBuilder text = new StringBuilder("GridModel [squares:\n");
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int column = 0; column < SudokuUtilities.GRID_SIZE; column++) {
                text.append(squares[row][column]).append(" ");
            }
            text.append("\n");
        }
        text.append(", level: ").append(level).append("]");
        return text.toString();
    }
}