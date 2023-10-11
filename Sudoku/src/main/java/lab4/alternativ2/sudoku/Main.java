package lab4.alternativ2.sudoku;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import lab4.alternativ2.sudoku.model.GridModel;
import lab4.alternativ2.sudoku.view.GridView;
import lab4.alternativ2.sudoku.view.SudokuView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridModel gridModel = new GridModel();
        GridView gridView = new GridView();
        SudokuView sudokuView = new SudokuView(gridModel);
        MenuBar menuBar = sudokuView.getMenuBar();

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridView.getNumberPane());

        VBox root = new VBox(menuBar, sudokuView, borderPane);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Sudoku");
        primaryStage.sizeToScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}