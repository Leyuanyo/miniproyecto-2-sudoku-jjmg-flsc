package com.example.miniproyecto_sudoku.controller;

import com.example.miniproyecto_sudoku.model.session.ISudokuGameSession;
import com.example.miniproyecto_sudoku.model.session.SudokuGameSession;
import com.example.miniproyecto_sudoku.view.GameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class WinController {

    @FXML private ImageView catPartyHatImageView;
    @FXML private Button mainMenuButton;
    @FXML private Button playAgainButton;
    @FXML private ImageView surprisedCatImageView;
    @FXML private Label wonMessageLabel;
    @FXML private Label youWonLabel;

    private final ISudokuGameSession gameSession;

    public WinController() {
        this.gameSession = new SudokuGameSession();
    }

    @FXML
    void handleMenu(ActionEvent event) {
        gameSession.clearSession();
        GameStage.loadScene("main-menu-view.fxml");
    }

    @FXML
    void handlePlayAgain(ActionEvent event) {
        gameSession.clearSession();
        GameStage.loadScene("game-view.fxml");
    }
}