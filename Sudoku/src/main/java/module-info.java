module lab4.alternativ2.sudoku.model {
    requires javafx.controls;
    requires javafx.fxml;


    opens lab4.alternativ2.sudoku.model to javafx.fxml;
    exports lab4.alternativ2.sudoku.model;
}