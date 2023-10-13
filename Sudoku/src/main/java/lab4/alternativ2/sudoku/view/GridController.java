package lab4.alternativ2.sudoku.view;


import javafx.scene.control.Alert;
import lab4.alternativ2.sudoku.model.GridModel;
import lab4.alternativ2.sudoku.model.Square;
import lab4.alternativ2.sudoku.model.SudokuUtilities;
import lab4.alternativ2.sudoku.model.io.SudokuFileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GridController {
    private final GridModel gridModel;
    private final SudokuView sudokuView;
    private final GridView gridView;
    private final File sudokuFile;
    private static final String FILE_NAME = "game.sudoku";

    public GridController(GridModel gridModel, SudokuView sudokuView, GridView gridView) {
        this.gridModel = gridModel;
        this.sudokuView = sudokuView;
        this.gridView = gridView;
        sudokuFile = new File(FILE_NAME);
    }

    public void handleLoadGame() {
        String message;
        try {
            if (sudokuFile.exists()) {
                Square[][] squares = (Square[][]) SudokuFileIO.deSerializeFromFile(sudokuFile);
                gridModel.setSquares(squares);
            }
        } catch (FileNotFoundException | ClassNotFoundException e) {
            message = "Could not load sudoku game from file, please check the data file.";
            sudokuView.showAlert(Alert.AlertType.ERROR, "Error", "File not found", message);
            System.out.println("Continuing with empty manager.");
        } catch (IOException ioe) {
            message = "There is a problem with the de serialization of the file.";
            sudokuView.showAlert(Alert.AlertType.ERROR, "Error", "IO problem", message);
        }
        gridView.updateGridView();
    }

    public void handleSaveGame() {
        try {
            if (sudokuFile.exists() || !sudokuFile.exists()) {
                Square[][] squares = gridModel.getSquares();
                SudokuFileIO.serializeToFile(sudokuFile, squares);
            }
        } catch (IOException ioe) {
            String message = "There is a problem with the serialization of the file.";
            sudokuView.showAlert(Alert.AlertType.ERROR, "Error", "IO problem", message);
        }
        gridView.updateGridView();
    }

    public void handleNewGame() {
        gridModel.initNewGame();
        gridView.updateGridView();
    }

    public void handleNewLevelEasy() {
        gridModel.setLevel(SudokuUtilities.SudokuLevel.EASY);
        gridView.updateGridView();
    }

    public void handleNewLevelMedium() {
        gridModel.setLevel(SudokuUtilities.SudokuLevel.MEDIUM);
        gridView.updateGridView();
    }

    public void handleNewLevelHard() {
        gridModel.setLevel(SudokuUtilities.SudokuLevel.HARD);
        gridView.updateGridView();
    }

    public void handleStartOver() {
        gridModel.clearSquares();
        gridView.updateGridView();
    }
}