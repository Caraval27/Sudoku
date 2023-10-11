package lab4.alternativ2.sudoku.view;


import lab4.alternativ2.sudoku.model.GridModel;

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

    public void handleSelectNewLevel() { // användaren måste få välja ny nivå --> 3 nya items
        //model.setLevel();
    }

    public void handleStartOver() {
        model.clearSquares();
    }

    public void handleGameRules() {

    }
}