package lab4.alternativ2.sudoku.view;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lab4.alternativ2.sudoku.model.*;
import lab4.alternativ2.sudoku.io.SudokuFileIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GridController {
    private final GridModel gridModel;
    private final SudokuView sudokuView;
    private File sudokuFile;
    private int selectedNumber;

    public GridController(GridModel gridModel, SudokuView sudokuView) {
        this.gridModel = gridModel;
        this.sudokuView = sudokuView;
    }

    private void checkGameFinished() {
        if (gridModel.allSquaresSelected()) {
            String header = "Level ";
            switch(gridModel.getLevel()) {
                case EASY -> header += "easy";
                case MEDIUM -> header += "medium";
                case HARD -> header += "hard";
            }
            header += " finished";
            String content = "Numbers selected are ";
            if (gridModel.checkSquares()) {
                content += "correct.";
            } else {
                content += "incorrect.";
            }
            sudokuView.showAlert(Alert.AlertType.INFORMATION, "Game", header, content);
        }
    }

    public void handleLoadGame(Stage stage) {
        String content;

        sudokuFile = sudokuView.getFileChooser().showOpenDialog(stage);
        try {
            if (sudokuFile.exists()) {
                Square[][] squares = SudokuFileIO.deSerializeFromFile(sudokuFile);
                gridModel.setSquares(squares);
            }
        } catch (FileNotFoundException | ClassNotFoundException ioException) {
            content = "Could not load sudoku game from file, please check the file.";
            sudokuView.showAlert(Alert.AlertType.ERROR, "Error", "File or class not found", content);
        } catch (IOException ioException) {
            content = "There is a problem with the de serialization from the file.";
            sudokuView.showAlert(Alert.AlertType.ERROR, "Error", "IO problem", content);
        }
        sudokuView.getGridView().updateGridView();
        sudokuView.getGridView().updateNumberSquaresFont();
        checkGameFinished();
    }

    public void handleSaveGame(Stage stage) {
        sudokuFile = sudokuView.getFileChooser().showSaveDialog(stage);
        try {
            Square[][] squares = gridModel.getSquares();
            SudokuFileIO.serializeToFile(sudokuFile, squares);
        } catch (IOException ioException) {
            String message = "There is a problem with the serialization to the file.";
            sudokuView.showAlert(Alert.AlertType.ERROR, "Error", "IO problem", message);
        }
    }

    public void handleNewGame() {
        gridModel.initNewGame();
        sudokuView.getGridView().updateGridView();
        sudokuView.getGridView().updateNumberSquaresFont();
    }

    public void handleSelectNewLevel(SudokuUtilities.SudokuLevel level) {
        gridModel.setLevel(level);
        sudokuView.getGridView().updateGridView();
        sudokuView.getGridView().updateNumberSquaresFont();
    }

    public void handleStartOver() {
        gridModel.clearSelectedSquares();
        sudokuView.getGridView().updateGridView();
    }

    public void handleGameRules() {
        sudokuView.showAlert(Alert.AlertType.INFORMATION, "Help", "Game rules", gridModel.getRules());
    }

    public void handleCheck() {
        String content = "Numbers selected so far are ";
        if (gridModel.checkSquares()) {
            content += "correct.";
        } else {
            content += "incorrect.";
        }
        sudokuView.showAlert(Alert.AlertType.INFORMATION, "Help", "Check", content);
    }

    public void handleHint() {
        try {
            gridModel.setCorrectSquare();
            checkGameFinished();
            sudokuView.getGridView().updateGridView();
        } catch (IllegalStateException illegalStateException) {
            String content = "Hints can not be provided when the game is finished.";
            sudokuView.showAlert(Alert.AlertType.WARNING, "Warning", "Invalid hint", content);
        }
    }

    public void handleNumbers(int selectedNumber) {
        this.selectedNumber = selectedNumber;
    }

    public void handleSquares(int row, int column) {
        try {
            gridModel.setSelectedSquare(selectedNumber, row, column);
        } catch (IllegalStateException illegalStateException) {
            String content = "Original numbers can not be cleared or replaced.";
            sudokuView.showAlert(Alert.AlertType.WARNING, "Warning", "Invalid square", content);
        }
        sudokuView.getGridView().updateGridView();
        checkGameFinished();
    }
}