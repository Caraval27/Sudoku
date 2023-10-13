package lab4.alternativ2.sudoku;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import lab4.alternativ2.sudoku.model.GridModel;
import lab4.alternativ2.sudoku.view.SudokuView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridModel gridModel = new GridModel();
        SudokuView sudokuView = new SudokuView(gridModel);

        VBox root = new VBox(sudokuView.getMenuBar(), sudokuView.getBorderPane());

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
