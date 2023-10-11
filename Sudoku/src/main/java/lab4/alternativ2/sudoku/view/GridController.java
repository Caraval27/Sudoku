package lab4.alternativ2.sudoku.view;


import lab4.alternativ2.sudoku.model.GridModel;
import lab4.alternativ2.sudoku.model.SudokuUtilities;

public class GridController {
    private final GridModel model;
    private final SudokuView view;

    public GridController(GridModel model, SudokuView view) {
        this.model = model;
        this.view = view;
    }

    public void handleLoadGame() {

    }

    public void handleSaveGame() {

    }

    public void handleNewGame() {
        model.initNewGame();
    }

    public void handleNewLevelEasy() {
        model.setLevel(SudokuUtilities.SudokuLevel.EASY);
    }

    public void handleNewLevelMedium() {
        model.setLevel(SudokuUtilities.SudokuLevel.MEDIUM);
    }

    public void handleNewLevelHard() {
        model.setLevel(SudokuUtilities.SudokuLevel.HARD);
    }

    public void handleStartOver() {
        model.clearSquares();
    }
}