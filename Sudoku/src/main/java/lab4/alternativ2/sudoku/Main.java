package lab4.alternativ2.sudoku;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab4.alternativ2.sudoku.view.SudokuView;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        SudokuView sudokuView = new SudokuView(primaryStage);

        Scene scene = new Scene(sudokuView.getRootPane());
        primaryStage.setTitle("Sudoku");
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}