package com.example.miniproyecto_sudoku.controller;

import com.example.miniproyecto_sudoku.view.GameStage;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;

public class HowToPlayController {

    @FXML
    private Label howToPlayExitLabel;

    @FXML
    private Label howToPlayLabel;

    @FXML
    private TextArea howToPlayTextArea;

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