package lab4.alternativ2.sudoku.view;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lab4.alternativ2.sudoku.model.*;
import java.util.Objects;

public class GridView {
    private final SudokuModel sudokuModel;
    private final SudokuController sudokuController;
    private TilePane numberPane;
    private final Label[][] numberSquares; // the tiles/squares to show in the ui grid
    private final Font fontBold;
    private final Font fontNormal;

    public GridView(SudokuModel sudokuModel, SudokuController sudokuController) {
        this.sudokuModel = sudokuModel;
        this.sudokuController = sudokuController;
        numberSquares = new Label[SudokuUtilities.GRID_SIZE][SudokuUtilities.GRID_SIZE];
        fontBold = Font.font("Monospaced", FontWeight.BOLD, 20);
        fontNormal = Font.font("Monospaced", FontWeight.NORMAL, 20);
        initNumberSquares();
        createNumberPane();
    }

    // use this method to get a reference to the numberPane (called by some other class)
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

    public void updateNumberSquaresFont() {
        Square[][] squares = sudokuModel.getSquares();
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int column = 0; column < SudokuUtilities.GRID_SIZE; column++) {
                if (squares[row][column].isChangeable()) {
                    numberSquares[row][column].setFont(fontNormal);
                } else {
                    numberSquares[row][column].setFont(fontBold);
                }
            }
        }
    }

    // called by constructor (only)
    private final void initNumberSquares() {
        EventHandler<MouseEvent> squareHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
                    for (int column = 0; column < SudokuUtilities.GRID_SIZE; column++) {
                        if (mouseEvent.getSource() == numberSquares[row][column]) {
                            // we got the row and column - now call the appropriate controller method, e.g.
                            sudokuController.handleSquares(row, column);
                            return;
                        }
                    }
                }
            }
        };
        for (int row = 0; row < SudokuUtilities.GRID_SIZE; row++) {
            for (int col = 0; col < SudokuUtilities.GRID_SIZE; col++) {
                Label square = new Label(squareNumberToString(row, col));
                square.setPrefWidth(32);
                square.setPrefHeight(32);
                if(Objects.equals(square.getText(), " ")) {
                    square.setFont(fontNormal);
                } else {
                    square.setFont(fontBold);
                }
                square.setAlignment(Pos.CENTER);
                square.setStyle("-fx-border-color: black; -fx-border-width: 0.5px;"); // css style
                square.addEventHandler(MouseEvent.MOUSE_CLICKED, squareHandler);
                numberSquares[row][col] = square;
            }
        }
    }

    private String squareNumberToString(int row, int column) {
        String stringNumber;
        Square[][] squares = sudokuModel.getSquares(); // data from model
        int number = squares[row][column].getSelectedNumber();

        if (number == 0) {
            stringNumber = " ";
        } else {
            stringNumber = String.valueOf(number);
        }

        return stringNumber;
    }

    private final void createNumberPane() {
        // create the root tile pane
        numberPane = new TilePane();
        numberPane.setPrefColumns(SudokuUtilities.SECTIONS_PER_ROW);
        numberPane.setPrefRows(SudokuUtilities.SECTIONS_PER_ROW);
        numberPane.setStyle("-fx-border-color: black; -fx-border-width: 1.0px; -fx-background-color: white;");

        // create the 3*3 sections and add the number tiles
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