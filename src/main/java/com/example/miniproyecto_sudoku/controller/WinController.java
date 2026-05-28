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

/**
 * Controller responsible for managing the victory view displayed
 * when the player successfully completes the Sudoku board.
 * Handles navigation options such as returning to the main menu
 * or starting a new game.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public class WinController {

    /**
     * ImageView displaying the cat with a party hat.
     */
    @FXML
    private ImageView catPartyHatImageView;

    /**
     * Button used to return to the main menu.
     */
    @FXML
    private Button mainMenuButton;

    /**
     * Button used to start a new game.
     */
    @FXML
    private Button playAgainButton;

    /**
     * ImageView displaying the surprised cat image.
     */
    @FXML
    private ImageView surprisedCatImageView;

    /**
     * Label displaying the victory message.
     */
    @FXML
    private Label wonMessageLabel;

    /**
     * Label indicating that the player has won.
     */
    @FXML
    private Label youWonLabel;

    /**
     * Manages saving and clearing game sessions.
     */
    private final ISudokuGameSession gameSession;

    /**
     * Creates a new WinController and initializes
     * the game session manager.
     */
    public WinController() {
        this.gameSession = new SudokuGameSession();
    }

    /**
     * Handles the action of returning to the main menu.
     * Clears the current game session before changing the scene.
     *
     * @param event action event triggered by the menu button.
     */
    @FXML
    void handleMenu(ActionEvent event) {
        gameSession.clearSession();
        GameStage.loadScene("main-menu-view.fxml");
    }

    /**
     * Handles the action of starting a new Sudoku game.
     * Clears the current session, creates a new board,
     * and loads the game scene.
     *
     * @param event action event triggered by the play again button.
     */
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