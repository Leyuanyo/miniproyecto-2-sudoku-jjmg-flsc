package com.example.miniproyecto_sudoku.controller;

import com.example.miniproyecto_sudoku.view.GameStage;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class GameController {

    @FXML private GridPane gameGrid;
    @FXML private ImageView hintButton;
    @FXML private Label hintLabel;
    @FXML private ImageView mainMenuButton;
    @FXML private Label menuLabel;
    @FXML private ImageView thinkingCatImageView;
    @FXML private ImageView undoButton;
    @FXML private Label undoLabel;

    private TextField[][] cells = new TextField[6][6];

    @FXML
    public void initialize() {
        buildBoard();
    }

    @FXML
    void handleHint(MouseEvent event) {

    }

    @FXML
    void handleMenu(MouseEvent event) {
        GameStage.loadScene("main-menu-view.fxml");
    }

    @FXML
    void handleUndo(MouseEvent event) {
    }

    private void buildBoard() {
        gameGrid.getChildren().clear();
        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                TextField tf = new TextField();
                tf.setPrefWidth(70);
                tf.setPrefHeight(70);
                tf.setAlignment(Pos.CENTER);

                String borderTop    = (row == 0) ? "3px" : "0.5px";
                String borderLeft   = (col == 0) ? "3px" : "0.5px";
                String borderRight  = (col == 2 || col == 5) ? "3px" : "0.5px";
                String borderBottom = (row == 1 || row == 3 || row == 5) ? "3px" : "0.5px";

                tf.setStyle(
                        "-fx-border-color: #3D2B1F; " +
                                "-fx-border-width: " + borderTop + " " + borderRight + " " + borderBottom + " " + borderLeft + "; " +
                                "-fx-background-color: #EAD9B5; " +
                                "-fx-font-size: 20px; " +
                                "-fx-alignment: center;"
                );

                cells[row][col] = tf;
                gameGrid.add(tf, col, row);
            }
        }
    }
}