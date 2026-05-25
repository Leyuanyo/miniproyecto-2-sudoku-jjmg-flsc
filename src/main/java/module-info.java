module com.example.miniproyecto_sudoku {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.miniproyecto_sudoku to javafx.fxml;
    opens com.example.miniproyecto_sudoku.controller to javafx.fxml;
    opens com.example.miniproyecto_sudoku.view to javafx.fxml;
    exports com.example.miniproyecto_sudoku;
}