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
    private final File sudokuFile;
    private int selectedNumber;
    private static final String FILE_NAME = "game.sudoku";

    public GridController(GridModel gridModel, SudokuView sudokuView) {
        this.gridModel = gridModel;
        this.sudokuView = sudokuView;
        sudokuFile = new File(FILE_NAME);
    }

    private void checkGameFinished() {
        if (gridModel.AllSquaresSelected()) {
            String content = "Numbers selected are ";
            if (gridModel.checkSquares()) {
                content += "correct.";
            }
            else {
                content += "incorrect.";
            }
            sudokuView.showAlert(Alert.AlertType.INFORMATION, "Game", "Game finished", content);
        }
    }

    public void handleLoadGame() {
        String content;
        try {
            if (sudokuFile.exists()) {
                Square[][] squares = SudokuFileIO.deSerializeFromFile(sudokuFile);
                gridModel.setSquares(squares);
            }
        } catch (FileNotFoundException | ClassNotFoundException e) {
            content = "Could not load sudoku game from file, please check the data file.";
            sudokuView.showAlert(Alert.AlertType.ERROR, "Error", "File not found", content);
            System.out.println("Continuing with empty manager.");
        } catch (IOException ioe) {
            content = "There is a problem with the de serialization of the file.";
            sudokuView.showAlert(Alert.AlertType.ERROR, "Error", "IO problem", content);
        }
        sudokuView.getGridView().updateGridView();

        checkGameFinished();
    }

    public void handleSaveGame() {
        try {
            Square[][] squares = gridModel.getSquares();
            SudokuFileIO.serializeToFile(sudokuFile, squares);
        } catch (IOException ioe) {
            String message = "There is a problem with the serialization of the file.";
            sudokuView.showAlert(Alert.AlertType.ERROR, "Error", "IO problem", message);
        }
        sudokuView.getGridView().updateGridView();
    }

    public void handleNewGame() {
        gridModel.initNewGame();
        sudokuView.getGridView().updateGridView();
    }

    public void handleSelectNewLevel(SudokuUtilities.SudokuLevel level) {
        gridModel.setLevel(level);
        sudokuView.getGridView().updateGridView();
    }

    public void handleStartOver() {
        gridModel.clearSquares();
        sudokuView.getGridView().updateGridView();
    }

    public void handleGameRules() {
        sudokuView.showAlert(Alert.AlertType.INFORMATION, "Help", "Game rules", gridModel.getRules());
    }

    public void handleCheck() {
        String content = "Numbers selected so far are ";
        if (gridModel.checkSquares()) {
            content += "correct.";
        }
        else {
            content += "incorrect.";
        }
        sudokuView.showAlert(Alert.AlertType.INFORMATION, "Help", "Check", content);
    }

    public void handleHint() {
        gridModel.setCorrectSquare();
        sudokuView.getGridView().updateGridView();

        checkGameFinished();
    }

    public void handleNumbers(int selectedNumber) {
        this.selectedNumber = selectedNumber;
    }

    public void handleSquares(int row, int column) {
        gridModel.setSquare(selectedNumber, row, column);
        sudokuView.getGridView().updateGridView();

        checkGameFinished();
    }
}