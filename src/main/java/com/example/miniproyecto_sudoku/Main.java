package com.example.miniproyecto_sudoku;

import com.example.miniproyecto_sudoku.view.GameStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class of the Sudoku application.
 * Responsible for launching the JavaFX application
 * and initializing the primary stage.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application and loads
     * the main menu scene.
     *
     * @param primaryStage primary stage of the application.
     * @throws IOException if an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        GameStage.setPrimaryStage(primaryStage);
        GameStage.loadScene("main-menu-view.fxml");
    }
}