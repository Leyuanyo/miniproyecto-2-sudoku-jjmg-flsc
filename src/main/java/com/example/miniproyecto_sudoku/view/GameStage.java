package com.example.miniproyecto_sudoku.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Utility class responsible for managing the main application stage
 * and handling scene transitions within the Sudoku application.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public class GameStage {

    /**
     * Main stage of the JavaFX application.
     */
    private static Stage primaryStage;

    /**
     * Returns the primary stage of the application.
     *
     * @return primary application stage.
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Configures the primary stage of the application,
     * including title, icon, and resize behavior.
     *
     * @param stage primary JavaFX stage.
     */
    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Sudoku");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(
                new Image(GameStage.class.getResourceAsStream(
                        "/com/example/miniproyecto_sudoku/images/game-icon.png"
                ))
        );
    }

    /**
     * Loads and displays a new scene from the specified FXML file.
     *
     * @param fxmlName name of the FXML file to load.
     */
    public static void loadScene(String fxmlName) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    GameStage.class.getResource(
                            "/com/example/miniproyecto_sudoku/" + fxmlName
                    )
            );
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}