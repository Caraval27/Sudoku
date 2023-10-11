package lab4.alternativ2.sudoku.view;

import lab4.alternativ2.sudoku.model.*;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GridView {
    private Label[][] numberSquares; // the tiles/squares to show in the ui grid
    private TilePane numberPane;
    private GridModel model;

    public GridView(GridModel model) {
        this.model = model;
        numberSquares = new Label[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        initNumberSquares();
        // ...
        numberPane = makeNumberPane();
        // ...
    }

    // use this method to get a reference to the number (called by some other class)
    public TilePane getNumberPane() {
        return numberPane;
    }

    public void updateGridView() {
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int column = 0; column < SudokuUtilities.GRID_SIZE; column++) {
                numberSquares[row][column].setText(squareNumberToString(row, column));
            }
        }
    }

    // called by constructor (only)
    private final void initNumberSquares() {
        Font font = Font.font("Monospaced", FontWeight.NORMAL, 20);

        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++) {
                Label tile = new Label(squareNumberToString(row, col)); // data from model
                tile.setPrefWidth(32);
                tile.setPrefHeight(32);
                tile.setFont(font);
                tile.setAlignment(Pos.CENTER);
                tile.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;"); // css style
                //tile.setOnMouseClicked(tileClickHandler); // add your custom event handler
                // add new tile to grid
                numberSquares[row][col] = tile;
            }
        }
    }

    private String squareNumberToString(int row, int column) {
        String stringNumber;
        Square[][] squares = model.getSquares();
        int number = squares[row][column].getSelectedNumber();

        if (number == 0) {
            stringNumber = " ";
        } else {
            stringNumber = String.valueOf(number);
        }

        return stringNumber;
    }

    private final TilePane makeNumberPane() {
        // create the root tile pane
        TilePane root = new TilePane();
        root.setPrefColumns(SudokuUtilities.SECTIONS_PER_ROW);
        root.setPrefRows(SudokuUtilities.SECTIONS_PER_ROW);
        root.setStyle(
                "-fx-border-color: black; -fx-border-width: 1.0px; -fx-background-color: white;");

        // create the 3*3 sections and add the number tiles
        TilePane[][] sections = new TilePane[SudokuUtilities.SECTIONS_PER_ROW][SudokuUtilities.SECTIONS_PER_ROW];
        int i = 0;
        for (int srow = 0; srow < SudokuUtilities.SECTIONS_PER_ROW; srow++) {
            for (int scol = 0; scol < SudokuUtilities.SECTIONS_PER_ROW; scol++) {
                TilePane section = new TilePane();
                section.setPrefColumns(SudokuUtilities.SECTION_SIZE);
                section.setPrefRows(SudokuUtilities.SECTION_SIZE);
                section.setStyle( "-fx-border-color: black; -fx-border-width: 0.5px;");

                // add number tiles to this section
                for (int row = 0; row < SudokuUtilities.SECTION_SIZE; row++) {
                    for (int col = 0; col < SudokuUtilities.SECTION_SIZE; col++) {
                        // calculate which tile and add
                        section.getChildren().add(numberSquares[srow * SudokuUtilities.SECTION_SIZE + row]
                                [scol * SudokuUtilities.SECTION_SIZE + col]);
                    }
                }

                // add the section to the root tile pane
                root.getChildren().add(section);
            }
        }

        return root;
    }
}