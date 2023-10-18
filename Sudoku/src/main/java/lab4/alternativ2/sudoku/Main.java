package lab4.alternativ2.sudoku;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lab4.alternativ2.sudoku.view.SudokuView;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        SudokuView sudokuView = new SudokuView(stage);

        Scene scene = new Scene(sudokuView.getRootPane());
        stage.setTitle("Sudoku");
        stage.sizeToScene();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}