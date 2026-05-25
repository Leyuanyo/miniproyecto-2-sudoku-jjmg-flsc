package com.example.miniproyecto_sudoku.controller;

import com.example.miniproyecto_sudoku.view.GameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class WinController {

    @FXML
    private ImageView catPartyHatImageView;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button playAgainButton;

    @FXML
    private ImageView surprisedCatImageView;

    @FXML
    private Label wonMessageLabel;

    @FXML
    private Label youWonLabel;

    @FXML
    void handleMenu(ActionEvent event) {
        GameStage.loadScene("main-menu-view.fxml");
    }

    @FXML
    void handlePlayAgain(ActionEvent event) {
        GameStage.loadScene("game-view.fxml");
    }

}
