package lab4.alternativ2.sudoku.view;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import lab4.alternativ2.sudoku.model.*;
import lab4.alternativ2.sudoku.io.SudokuFileIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class represents the controller of the application.
 * The class contains all the methods that the EventHandlers in the view classes calls for.
 * Further, the methods uses the methods found in the model classes.
 * Therefor managing the interaction between the sudoku view and model.
 */
public class GridController {
    private final GridModel gridModel;
    private final SudokuView sudokuView;
    private File sudokuFile;
    private int selectedNumber;

    /**
     * Constructs a new instance of GridController and sets the gridModel and the sudokuView,
     * allowing the controller to manage the interactions between the sudoku view and model.
     *
     * @param gridModel the GridModel instance representing the sudoku grid model.
     * @param sudokuView the SudokuView instance representing the sudoku view.
     */
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

    /**
     * Handles the loading of a saved sudoku game from a file, sudokuFile.
     * A file dialog is opened to allow the user to choose a file to load, the contents of the file is then
     * deserialized and returned as a 2D array of Square objects. The GridModel and GridView are then updated.
     * An alert box is displayed if an error occurs during the reading or deserialization of a file.
     * Lastly the game is checked if it's finished or not, if it is an alert will show with the result.
     *
     * @param stage Stage object representing the primary stage of the application.
     */
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

    /**
     * Handles the saving of the current sudoku game state to a file, sudokuFile.
     * A file dialog is opened to allow the user to choose an existing file or create a new file to save to.
     * The 2D array of Square objects are then serialized and written to the file.
     * An alert box is displayed if an error occurs during the writing or serialization of a file.
     *
     * @param stage Stage object representing the primary stage of the application.
     */
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

    /**
     * Handles the start of a new game, a new game is initialized from the GridModel and the GridView is then updated.
     */
    public void handleNewGame() {
        gridModel.initNewGame();
        sudokuView.getGridView().updateGridView();
        sudokuView.getGridView().updateNumberSquaresFont();
    }

    /**
     * Handles the start of a new game with a new chosen difficulty level. The GridView is then updated.
     *
     * @param level enum SudokuLevel representing the new chosen difficulty level.
     */
    public void handleSelectNewLevel(SudokuUtilities.SudokuLevel level) {
        gridModel.setLevel(level);
        sudokuView.getGridView().updateGridView();
        sudokuView.getGridView().updateNumberSquaresFont();
    }

    /**
     * Handles the start over of the current game by clearing the sudoku game board of user induced numbers.
     * The GridView is then updated.
     */
    public void handleStartOver() {
        gridModel.clearSelectedSquares();
        sudokuView.getGridView().updateGridView();
    }

    /**
     * Handles the display, through an alert, of the sudoku game rules.
     */
    public void handleGameRules() {
        sudokuView.showAlert(Alert.AlertType.INFORMATION, "Help", "Game rules", gridModel.getRules());
    }

    /**
     * Handles the scan of all sudoku Squares and checks if the filled ones are correct or not.
     * An alert is then displayed with the corresponding message.
     */
    public void handleCheck() {
        String content = "Numbers selected so far are ";
        if (gridModel.checkSquares()) {
            content += "correct.";
        } else {
            content += "incorrect.";
        }
        sudokuView.showAlert(Alert.AlertType.INFORMATION, "Help", "Check", content);
    }

    /**
     * Handles the hint, an empty square is randomly chosen and filled with the correct number.
     * An IllegalStateException is caught if a hint is requested after all the squares have been filled,
     * an alert is then displayed.
     * Lastly if no exception was caught the game is, checked if it's finished or not,
     * if it is an alert will show with the result.
     */
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

    /**
     * Handles the selected number the user wants to place in a square.
     * @param selectedNumber the number the user has selected to be placed in a square.
     */
    public void handleNumbers(int selectedNumber) {
        this.selectedNumber = selectedNumber;
    }

    /**
     * Handles the squares, if the selected number can't be placed in the selected square an IllegalStateException
     * is caught and an alert with a warning is displayed. The GridModel is updated if the square can be changed.
     * The GridView is then updated.
     * Lastly the game is checked if it's finished or not, if it is an alert will show with the result.
     *
     * @param row integer representing the row of where the selected square is.
     * @param column integer representing the column of where the selected square is.
     */
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