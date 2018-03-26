/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.javafx8.thridparty;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Demo class for StageSizer class.
 *
 * @author sai.dandem
 */
public class StageSizerDemo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane sp = new StackPane();
        sp.setPrefSize(500, 500);
        Scene sc = new Scene(sp);
        primaryStage.setScene(sc);
        primaryStage.show();
        
        StageSizer stageSizer = new StageSizer();
        stageSizer.setStage(primaryStage);
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
