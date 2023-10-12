package lab4.alternativ2.sudoku.view;


import lab4.alternativ2.sudoku.model.GridModel;
import lab4.alternativ2.sudoku.model.Square;
import lab4.alternativ2.sudoku.model.SudokuUtilities;
import lab4.alternativ2.sudoku.model.io.SudokuFileIO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GridController {
    private final GridModel model;
    private SudokuView sudokuView;
    private GridView gridView;
    private final File sudokuFile;
    private static final String FILE_NAME = "game.sudoku";

    public GridController(GridModel model, SudokuView sudokuView, GridView gridView) {
        this.model = model;
        this.sudokuView = sudokuView;
        this.gridView = gridView;
        sudokuFile = new File(FILE_NAME);
    }

    public void handleLoadGame() {
        try {
            if (sudokuFile.exists()) {
                Square[][] squares = (Square[][]) SudokuFileIO.deSerializeFromFile(sudokuFile);
                model.setSquares(squares);
            }

        } catch (FileNotFoundException | ClassNotFoundException e) {
            System.out.println("Could not load projects from file, please check the data file.");
            System.out.println("Continuing with empty manager.");
        } catch (IOException ioe) {

        }
        gridView.updateGridView();
    }

    public void handleSaveGame() {
        try {
            if (sudokuFile.exists() || !sudokuFile.exists()) {
                Square[][] squaresToSave = model.getSquares();
                SudokuFileIO.serializeToFile(sudokuFile, squaresToSave);
            }
        } catch (IOException ioe) {

        }
        gridView.updateGridView();
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