package com.example.miniproyecto_sudoku.controller;

import com.example.miniproyecto_sudoku.view.GameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainMenuController {

    @FXML
    private Button continueGameButton;

    @FXML
    private Button howToPlayButton;

    @FXML
    private Button playButton;

    @FXML
    private Label titleLabel;

    @FXML
    void handleContinueGame(ActionEvent event) {
        GameStage.loadScene("game-view.fxml");
    }

    @FXML
    void handleHowToPlay(ActionEvent event) {
        GameStage.loadScene("how-to-play-view.fxml");
    }

    @FXML
    void handlePlay(ActionEvent event) {
        GameStage.loadScene("game-view.fxml");
    }

}
