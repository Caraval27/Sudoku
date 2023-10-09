module lab4.alternativ2.sudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens lab4.alternativ2.sudoku to javafx.fxml;
    exports lab4.alternativ2.sudoku;
}