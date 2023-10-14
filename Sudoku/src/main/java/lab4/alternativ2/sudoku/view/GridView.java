package lab4.alternativ2.sudoku.view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lab4.alternativ2.sudoku.model.GridModel;
import lab4.alternativ2.sudoku.model.Square;
import lab4.alternativ2.sudoku.model.SudokuUtilities;

public class GridView {
    private final GridModel gridModel;
    private final GridController gridController;
    private TilePane numberPane;
    private final Label[][] numberSquares; // the tiles/squares to show in the ui grid

    public GridView(GridModel gridModel, GridController gridController) {
        this.gridModel = gridModel;
        this.gridController = gridController;
        numberSquares = new Label[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        initNumberSquares();
        makeNumberPane();
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

        EventHandler<MouseEvent> squareHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
                    for (int column = 0; column < SudokuUtilities.GRID_SIZE; column++) {
                        if (mouseEvent.getSource() == numberSquares[row][column]) {
                            // we got the row and column - now call the appropriate controller method, e.g.
                            gridController.handleSquares(row, column);
                            return;
                        }
                    }
                }
            }
        };
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++) {
                Label square = new Label(squareNumberToString(row, col)); // data from model
                square.setPrefWidth(32);
                square.setPrefHeight(32);
                square.setFont(font);
                square.setAlignment(Pos.CENTER);
                square.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;"); // css style
                //tile.setOnMouseClicked(tileClickHandler); // add your custom event handler
                // add new tile to grid
                square.addEventHandler(MouseEvent.MOUSE_CLICKED, squareHandler);
                numberSquares[row][col] = square;
            }
        }
    }

    private String squareNumberToString(int row, int column) {
        String stringNumber;
        Square[][] squares = gridModel.getSquares();
        int number = squares[row][column].getSelectedNumber();

        if (number == 0) {
            stringNumber = " ";
        } else {
            stringNumber = String.valueOf(number);
        }

        return stringNumber;
    }

    private final void makeNumberPane() {
        // create the root tile pane
        numberPane = new TilePane();
        numberPane.setPrefColumns(SudokuUtilities.SECTIONS_PER_ROW);
        numberPane.setPrefRows(SudokuUtilities.SECTIONS_PER_ROW);
        numberPane.setStyle(
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
                numberPane.getChildren().add(section);
            }
        }
    }
}