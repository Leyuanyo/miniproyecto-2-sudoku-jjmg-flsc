package com.example.miniproyecto_sudoku.controller;

import com.example.miniproyecto_sudoku.view.GameStage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

/**
 * Controller responsible for managing the "How To Play" view.
 * Configures the instructions interface and handles keyboard events
 * for returning to the main menu.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public class HowToPlayController {

    /**
     * Label used to display the exit instruction.
     */
    @FXML
    private Label howToPlayExitLabel;

    /**
     * Label used as the title of the "How To Play" view.
     */
    @FXML
    private Label howToPlayLabel;

    /**
     * Text area containing the game instructions.
     */
    @FXML
    private TextArea howToPlayTextArea;

    /**
     * Initializes the controller, configures the text area style,
     * and registers the ESC key event to return to the main menu.
     */
    @FXML
    public void initialize() {
        howToPlayTextArea.setStyle(
                "-fx-control-inner-background: #F5EDD6; " +
                        "-fx-text-fill: #3D2B1F; " +
                        "-fx-font-family: 'Berlin Sans FB'; " +
                        "-fx-font-size: 14px; " +
                        "-fx-background-color: transparent; " +
                        "-fx-border-color: transparent;"
        );

        Platform.runLater(() -> {
            GameStage.getPrimaryStage()
                    .getScene()
                    .setOnKeyPressed(event -> {
                        if (event.getCode() == KeyCode.ESCAPE) {
                            GameStage.loadScene("main-menu-view.fxml");
                        }
                    });
        });
    }
}