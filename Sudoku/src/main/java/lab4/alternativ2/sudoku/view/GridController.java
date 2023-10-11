package lab4.alternativ2.sudoku.view;


import lab4.alternativ2.sudoku.model.GridModel;
import lab4.alternativ2.sudoku.model.SudokuUtilities;

public class GridController {
    private final GridModel model;
    private SudokuView sudokuView;
    private GridView gridView;

    public GridController(GridModel model, SudokuView sudokuView, GridView gridView) {
        this.model = model;
        this.sudokuView = sudokuView;
        this.gridView = gridView;
    }

    public void handleLoadGame() {

    }

    public void handleSaveGame() {

    }

    public void handleNewGame() {
        model.initNewGame();
        gridView.updateGridView();
    }

    public void handleNewLevelEasy() {
        model.setLevel(SudokuUtilities.SudokuLevel.EASY);
        gridView.updateGridView();
    }

    public void handleNewLevelMedium() {
        model.setLevel(SudokuUtilities.SudokuLevel.MEDIUM);
        gridView.updateGridView();
    }

    public void handleNewLevelHard() {
        model.setLevel(SudokuUtilities.SudokuLevel.HARD);
        gridView.updateGridView();
    }

    public void handleStartOver() {
        model.clearSquares();
        gridView.updateGridView();
    }
}