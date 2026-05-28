package com.example.miniproyecto_sudoku;

import com.example.miniproyecto_sudoku.view.GameStage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        GameStage.setPrimaryStage(primaryStage);
        GameStage.loadScene("main-menu-view.fxml");    }
}