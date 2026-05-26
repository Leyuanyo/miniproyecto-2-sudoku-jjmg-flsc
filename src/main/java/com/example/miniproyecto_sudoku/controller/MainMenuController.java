package com.example.miniproyecto_sudoku.controller;

import com.example.miniproyecto_sudoku.model.board.SudokuBoard;
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
import java.io.IOException;

public class MainMenuController {

    @FXML private Button continueGameButton;
    @FXML private Button howToPlayButton;
    @FXML private Button playButton;
    @FXML private Label titleLabel;

    private final ISudokuGameSession gameSession;

    public MainMenuController() {
        this.gameSession = new SudokuGameSession();
    }

    @FXML
    public void initialize() {
        continueGameButton.setVisible(gameSession.hasSavedSession());
    }

    @FXML
    void handleContinueGame(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    GameStage.class.getResource(
                            "/com/example/miniproyecto_sudoku/game-view.fxml"
                    )
            );
            Parent root = loader.load();
            GameController controller = loader.getController();
            controller.loadSession(gameSession.loadSession());
            GameStage.getPrimaryStage().setScene(new Scene(root));
            GameStage.getPrimaryStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleHowToPlay(ActionEvent event) {
        GameStage.loadScene("how-to-play-view.fxml");
    }

    @FXML
    void handlePlay(ActionEvent event) {
        gameSession.clearSession();
        GameStage.loadScene("game-view.fxml");
    }
}