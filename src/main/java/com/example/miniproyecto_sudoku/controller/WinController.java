package com.example.miniproyecto_sudoku.controller;

import com.example.miniproyecto_sudoku.model.session.ISudokuGameSession;
import com.example.miniproyecto_sudoku.model.session.SudokuGameSession;
import com.example.miniproyecto_sudoku.view.GameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;

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
        try {
            gameSession.clearSession();
            FXMLLoader loader = new FXMLLoader(
                    GameStage.class.getResource(
                            "/com/example/miniproyecto_sudoku/game-view.fxml"
                    )
            );
            Parent root = loader.load();
            GameController controller = loader.getController();
            controller.startNewGame();
            GameStage.getPrimaryStage().setScene(new Scene(root));
            GameStage.getPrimaryStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}