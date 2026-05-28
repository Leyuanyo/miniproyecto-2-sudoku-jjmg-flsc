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

/**
 * Controller responsible for managing the main menu view.
 * Handles navigation between scenes, starting new games,
 * loading saved sessions, and displaying available options.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public class MainMenuController {

    /**
     * Button used to continue a previously saved game.
     */
    @FXML
    private Button continueGameButton;

    /**
     * Button used to open the "How To Play" view.
     */
    @FXML
    private Button howToPlayButton;

    /**
     * Button used to start a new game.
     */
    @FXML
    private Button playButton;

    /**
     * Label displaying the game title.
     */
    @FXML
    private Label titleLabel;

    /**
     * Manages saving and loading game sessions.
     */
    private final ISudokuGameSession gameSession;

    /**
     * Creates a new MainMenuController and initializes
     * the game session manager.
     */
    public MainMenuController() {
        this.gameSession = new SudokuGameSession();
    }

    /**
     * Initializes the controller and updates the visibility
     * of the continue button depending on saved sessions.
     */
    @FXML
    public void initialize() {
        continueGameButton.setVisible(gameSession.hasSavedSession());
    }

    /**
     * Handles the action of continuing a previously saved game.
     * Loads the saved board and switches to the game scene.
     *
     * @param event action event triggered by the continue button.
     */
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
            SudokuBoard savedBoard = gameSession.loadSession();
            controller.loadSession(savedBoard);
            GameStage.getPrimaryStage().setScene(new Scene(root));
            GameStage.getPrimaryStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action of opening the "How To Play" view.
     *
     * @param event action event triggered by the corresponding button.
     */
    @FXML
    void handleHowToPlay(ActionEvent event) {
        GameStage.loadScene("how-to-play-view.fxml");
    }

    /**
     * Handles the action of starting a new Sudoku game.
     * Clears any previous session and initializes a new board.
     *
     * @param event action event triggered by the play button.
     */
    @FXML
    void handlePlay(ActionEvent event) {
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