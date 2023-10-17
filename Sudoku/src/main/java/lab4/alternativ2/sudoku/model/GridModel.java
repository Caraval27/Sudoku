package lab4.alternativ2.sudoku.model;

public class GridModel {
    private final Square[][] squares;
    private SudokuUtilities.SudokuLevel level;

    public GridModel() {
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

    public Square[][] getSquares() {
        return copySquares(squares);
    }

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

    public void setLevel(SudokuUtilities.SudokuLevel level) {
        this.level = level;

        initNewGame();
    }

    public void setSelectedSquare(int value, int row, int column) {
        Square square = squares[row][column];
        if (square.isChangeable()) {
            square.setSelectedNumber(value);
        }
    }

    public void setSquares(Square[][] squares) {
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            System.arraycopy(squares[row], 0, this.squares[row], 0, SudokuUtilities.GRID_SIZE);
        }
    }

    public void clearSelectedSquares() {
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int column = 0; column < SudokuUtilities.GRID_SIZE; column++) {
                setSelectedSquare(0, row, column);
            }
        }
    }

    public void setCorrectSquare() {
        int row, column;
        Square square;
        do {
            row = SudokuUtilities.generateRandomNumber(SudokuUtilities.MAX_POSITION, SudokuUtilities.MIN_POSITION);
            column = SudokuUtilities.generateRandomNumber(SudokuUtilities.MAX_POSITION, SudokuUtilities.MIN_POSITION);
            square = squares[row][column];
        } while (!square.isChangeable() || square.getSelectedNumber() != 0);
        setSelectedSquare(square.getCorrectNumber(), row, column);
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

    public String getRules() {
        String rules = "Rule 1: Each row and column must contain the numbers 1 to 9, without repetitions.\n";
        rules += "Rule 2: The digits can only occur once per block (the 9 3x3 blocks in the 9x9 grid).\n";
        rules += "Rule 3: The sum of every single row, column and 3x3 block must equal 45.\n";
        return rules;
    }

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