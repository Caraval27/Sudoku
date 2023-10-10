package lab4.alternativ2.sudoku.model;

//klassen ska tas bort sen
public class Main {
    public static void main(String[] args) {
        GridModel gridModel = new GridModel();
        gridModel.setLevel(SudokuUtilities.SudokuLevel.EASY);
        System.out.println(gridModel);
        Square[][] squares = gridModel.getSquares();
        String text = "";
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int column = 0; column < SudokuUtilities.GRID_SIZE; column++) {
                text += squares[row][column] + " ";
            }
            text += "\n";
        }
        System.out.println(text);

        gridModel.setSquare(6, 0, 0);
        gridModel.setSquare(7, 1, 0);
        gridModel.setSquare(8, 0, 1);
        gridModel.setSquare(3, 4, 0);
        gridModel.setCorrectSquare();
        System.out.println(gridModel);
        System.out.println(gridModel.checkSquares());
        gridModel.clearSquare(0,0);
        System.out.println(gridModel.checkSquares());
        gridModel.clearSquares();
        System.out.println(gridModel);
    }
}
