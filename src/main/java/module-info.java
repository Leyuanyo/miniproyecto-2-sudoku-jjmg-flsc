module com.example.miniproyecto_sudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.miniproyecto_sudoku to javafx.fxml;
    exports com.example.miniproyecto_sudoku;
}