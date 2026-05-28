package com.example.miniproyecto_sudoku.controller;

import com.example.miniproyecto_sudoku.controller.adapter.SudokuKeyAdapter;
import com.example.miniproyecto_sudoku.model.move.SudokuMove;
import com.example.miniproyecto_sudoku.model.board.SudokuBoard;
import com.example.miniproyecto_sudoku.model.generator.ISudokuBoardGenerator;
import com.example.miniproyecto_sudoku.model.generator.SudokuBoardGenerator;
import com.example.miniproyecto_sudoku.model.session.ISudokuGameSession;
import com.example.miniproyecto_sudoku.model.session.SudokuGameSession;
import com.example.miniproyecto_sudoku.model.validator.ISudokuValidator;
import com.example.miniproyecto_sudoku.model.validator.SudokuValidator;
import com.example.miniproyecto_sudoku.view.GameStage;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * Controller responsible for managing the Sudoku game view and its interactions.
 * Handles board generation, user input, hints, undo actions, session management,
 * and game validation.
 *
 * @author Juan José Morera Gómez
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 * @since 1.0
 */
public class GameController {

    /**
     * GridPane that contains the Sudoku board cells.
     */
    @FXML
    private GridPane gameGrid;

    /**
     * Button used to request a hint.
     */
    @FXML
    private ImageView hintButton;

    /**
     * Label used to display hint information.
     */
    @FXML
    private Label hintLabel;

    /**
     * Button used to return to the main menu.
     */
    @FXML
    private ImageView mainMenuButton;

    /**
     * Label associated with the menu button.
     */
    @FXML
    private Label menuLabel;

    /**
     * ImageView displaying the thinking cat image.
     */
    @FXML
    private ImageView thinkingCatImageView;

    /**
     * Button used to undo the last move.
     */
    @FXML
    private ImageView undoButton;

    /**
     * Label associated with the undo button.
     */
    @FXML
    private Label undoLabel;

    /**
     * Matrix containing all Sudoku board cells.
     */
    private TextField[][] cells = new TextField[6][6];

    /**
     * Current Sudoku board instance.
     */
    private SudokuBoard board;

    /**
     * Generator responsible for creating Sudoku boards.
     */
    private final ISudokuBoardGenerator boardGenerator;

    /**
     * Manages the current game session.
     */
    private final ISudokuGameSession gameSession;

    /**
     * Validator used to verify Sudoku rules and completion state.
     */
    private ISudokuValidator validator;

    /**
     * Adapter responsible for keyboard input handling.
     */
    private final SudokuKeyAdapter keyAdapter;

    /**
     * Row index of the currently selected cell.
     */
    private int selectedRow = -1;

    /**
     * Column index of the currently selected cell.
     */
    private int selectedCol = -1;

    /**
     * Creates a new GameController with initialized game components.
     */
    public GameController() {
        this.boardGenerator = new SudokuBoardGenerator();
        this.gameSession = new SudokuGameSession();
        this.keyAdapter = new SudokuKeyAdapter();
    }

    /**
     * Initializes the controller and configures input handlers.
     */
    @FXML
    public void initialize() {
        keyAdapter.setOnInput(this::processInput);
        keyAdapter.setOnClear(this::processClear);
    }

    /**
     * Handles the hint button action by revealing the solution value
     * for the first empty cell found on the board.
     *
     * @param event mouse event triggered by the hint button.
     */
    @FXML
    void handleHint(MouseEvent event) {
        for (int row = 0; row < SudokuBoard.SIZE; row++) {
            for (int col = 0; col < SudokuBoard.SIZE; col++) {
                if (board.getCell(row, col) == 0) {
                    int hint = board.getSolutionValue(row, col);
                    board.setCell(row, col, hint);
                    cells[row][col].setText(String.valueOf(hint));
                    cells[row][col].setStyle(
                            cells[row][col].getStyle()
                                    .replace("-fx-background-color: #EAD9B5; ",
                                            "-fx-background-color: #B3E5FC; ")
                                    .replace("-fx-background-color: #C8E6C9; ",
                                            "-fx-background-color: #B3E5FC; ")
                    );
                    hintLabel.setText("Pista ("
                            + (row + 1) + "," + (col + 1) + ")");
                    checkGameComplete();
                    return;
                }
            }
        }
        hintLabel.setText("Sin Pistas");
    }

    /**
     * Handles the action of returning to the main menu.
     * Saves the current session before changing the scene.
     *
     * @param event mouse event triggered by the menu button.
     */
    @FXML
    void handleMenu(MouseEvent event) {
        gameSession.saveSession(board);
        GameStage.loadScene("main-menu-view.fxml");
    }

    /**
     * Handles the undo action by restoring the previous board state.
     *
     * @param event mouse event triggered by the undo button.
     */
    @FXML
    void handleUndo(MouseEvent event) {
        SudokuMove undone = board.undoLastMove();
        if (undone != null) {
            int row = undone.getRow();
            int col = undone.getCol();
            int prev = undone.getPreviousValue();
            cells[row][col].setText(prev != 0 ? String.valueOf(prev) : "");
            cells[row][col].setStyle(
                    cells[row][col].getStyle()
                            .replace("-fx-background-color: #FFCDD2; ",
                                    "-fx-background-color: #EAD9B5; ")
                            .replace("-fx-background-color: #B3E5FC; ",
                                    "-fx-background-color: #EAD9B5; ")
            );
        }
    }

    /**
     * Builds and configures the visual Sudoku board.
     * Creates all cells, applies styles, and assigns event handlers.
     */
    private void buildBoard() {
        gameGrid.getChildren().clear();
        gameGrid.setFocusTraversable(false);
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                TextField tf = new TextField();
                tf.setPrefWidth(70);
                tf.setPrefHeight(70);
                tf.setAlignment(Pos.CENTER);

                String borderTop = (row == 0) ? "3px" : "0.5px";
                String borderLeft = (col == 0) ? "3px" : "0.5px";
                String borderRight = (col == 2 || col == 5) ? "3px" : "0.5px";
                String borderBottom = (row == 1 || row == 3 || row == 5) ? "3px" : "0.5px";

                tf.setStyle(
                        "-fx-border-color: #3D2B1F; " +
                                "-fx-border-width: " + borderTop + " " + borderRight + " " + borderBottom + " " + borderLeft + "; " +
                                "-fx-background-color: #EAD9B5; " +
                                "-fx-font-size: 20px; " +
                                "-fx-alignment: center;"
                );

                if (board.isFixed(row, col)) {
                    int value = board.getCell(row, col);
                    tf.setText(String.valueOf(value));
                    tf.setEditable(false);
                    tf.setFocusTraversable(false);
                    tf.setStyle(
                            "-fx-border-color: #3D2B1F; " +
                                    "-fx-border-width: " + borderTop + " " + borderRight + " " + borderBottom + " " + borderLeft + "; " +
                                    "-fx-background-color: #C4A882; " +
                                    "-fx-font-size: 20px; " +
                                    "-fx-alignment: center; " +
                                    "-fx-font-weight: bold;"
                    );
                } else {
                    final int r = row;
                    final int c = col;
                    tf.setEditable(false);

                    if (board.getCell(row, col) != 0) {
                        tf.setText(String.valueOf(board.getCell(row, col)));
                    }

                    tf.textProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue.length() > 1) {
                            tf.setText(oldValue);
                        }
                        if (!newValue.matches("[1-6]?")) {
                            tf.setText(oldValue);
                        }
                    });
                    tf.setOnMouseClicked(event -> {
                        selectedRow = r;
                        selectedCol = c;
                        keyAdapter.setSelectedCell(r, c);
                        highlightSelectedCell(r, c);
                        tf.requestFocus();
                    });

                    tf.setOnKeyPressed(keyAdapter::handle);
                }

                cells[row][col] = tf;
                gameGrid.add(tf, col, row);
            }
        }
    }

    /**
     * Highlights the currently selected cell and restores
     * the style of all other editable cells.
     *
     * @param row row of the selected cell.
     * @param col column of the selected cell.
     */
    private void highlightSelectedCell(int row, int col) {
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 6; c++) {
                if (!board.isFixed(r, c)) {
                    cells[r][c].setStyle(
                            cells[r][c].getStyle()
                                    .replace("-fx-background-color: #C8E6C9; ",
                                            "-fx-background-color: #EAD9B5; ")
                    );
                }
            }
        }
        cells[row][col].setStyle(
                cells[row][col].getStyle()
                        .replace("-fx-background-color: #EAD9B5; ",
                                "-fx-background-color: #C8E6C9; ")
        );
    }

    /**
     * Processes a value entered by the user into a cell.
     * Updates the board, validates the move, and changes the cell style.
     *
     * @param row row of the selected cell.
     * @param col column of the selected cell.
     * @param value value entered by the user.
     */
    private void processInput(int row, int col, int value) {
        if (board.isFixed(row, col)) return;

        board.setCell(row, col, value);
        cells[row][col].setText(String.valueOf(value));

        if (!validator.isMoveValid(row, col, value)) {
            cells[row][col].setStyle(
                    cells[row][col].getStyle()
                            .replace("-fx-background-color: #EAD9B5; ",
                                    "-fx-background-color: #FFCDD2; ")
                            .replace("-fx-background-color: #C8E6C9; ",
                                    "-fx-background-color: #FFCDD2; ")
            );
        } else {
            cells[row][col].setStyle(
                    cells[row][col].getStyle()
                            .replace("-fx-background-color: #FFCDD2; ",
                                    "-fx-background-color: #C8E6C9; ")
            );
            checkGameComplete();
        }
    }

    /**
     * Clears the value of a selected cell and restores its default style.
     *
     * @param row row of the selected cell.
     * @param col column of the selected cell.
     */
    private void processClear(int row, int col) {
        if (board.isFixed(row, col)) return;
        board.setCell(row, col, 0);
        cells[row][col].setText("");
        cells[row][col].setStyle(
                cells[row][col].getStyle()
                        .replace("-fx-background-color: #FFCDD2; ",
                                "-fx-background-color: #EAD9B5; ")
                        .replace("-fx-background-color: #B3E5FC; ",
                                "-fx-background-color: #EAD9B5; ")
        );
    }

    /**
     * Checks whether the Sudoku board has been completed successfully.
     * If completed, clears the current session and loads the win scene.
     */
    private void checkGameComplete() {
        if (validator.isBoardComplete()) {
            gameSession.clearSession();
            GameStage.loadScene("win-view.fxml");
        }
    }

    /**
     * Starts a new Sudoku game by generating a new board
     * and rebuilding the visual interface.
     */
    public void startNewGame() {
        board = boardGenerator.generateBoard();
        validator = new SudokuValidator(board);
        buildBoard();
    }

    /**
     * Loads a previously saved Sudoku session and rebuilds the board view.
     *
     * @param savedBoard saved Sudoku board to restore.
     */
    public void loadSession(SudokuBoard savedBoard) {
        this.board = savedBoard;
        this.validator = new SudokuValidator(board);
        buildBoard();
    }
}