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
    private final GridModel model;
    private final SudokuView sudokuView;
    private final File sudokuFile;
    private static final String FILE_NAME = "game.sudoku";

    public GridController(GridModel model, SudokuView sudokuView) {
        this.model = model;
        this.sudokuView = sudokuView;
        sudokuFile = new File(FILE_NAME);
    }

    public void handleLoadGame() {
        String message;
        try {
            if (sudokuFile.exists()) {
                Square[][] squares = (Square[][]) SudokuFileIO.deSerializeFromFile(sudokuFile);
                model.setSquares(squares);
            }
        } catch (FileNotFoundException | ClassNotFoundException e) {
            message = "Could not load sudoku game from file, please check the data file.";
            sudokuView.showAlert(Alert.AlertType.ERROR, "Error", "File not found", message);
            System.out.println("Continuing with empty manager.");
        } catch (IOException ioe) {
            message = "There is a problem with the de serialization of the file.";
            sudokuView.showAlert(Alert.AlertType.ERROR, "Error", "IO problem", message);
        }
        sudokuView.getGridView().updateGridView();
    }

    public void handleSaveGame() {
        try {
            if (sudokuFile.exists() || !sudokuFile.exists()) {
                Square[][] squaresToSave = model.getSquares();
                SudokuFileIO.serializeToFile(sudokuFile, squaresToSave);
            }
        } catch (IOException ioe) {
            String message = "There is a problem with the serialization of the file.";
            sudokuView.showAlert(Alert.AlertType.ERROR, "Error", "IO problem", message);
        }
        sudokuView.getGridView().updateGridView();
    }

    public void handleNewGame() {
        model.initNewGame();
        sudokuView.getGridView().updateGridView();
    }

    public void handleNewLevelEasy() {
        model.setLevel(SudokuUtilities.SudokuLevel.EASY);
        sudokuView.getGridView().updateGridView();
    }

    public void handleNewLevelMedium() {
        model.setLevel(SudokuUtilities.SudokuLevel.MEDIUM);
        sudokuView.getGridView().updateGridView();
    }

    public void handleNewLevelHard() {
        model.setLevel(SudokuUtilities.SudokuLevel.HARD);
        sudokuView.getGridView().updateGridView();
    }

    public void handleStartOver() {
        model.clearSquares();
        sudokuView.getGridView().updateGridView();
    }

    public void handleGameRules() {
        sudokuView.showAlert(Alert.AlertType.INFORMATION, "Help", "Game Rules", model.getRules());
    }
}
