/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sai.javafx8.thridparty;

/**
 * Class to restore window sizing.
 *
 * @ref https://pupeno.com/2017/12/20/restoring-window-sizes-in-javafx/
 * @author sai.dandem
 */
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import lombok.Data;

@Data
public class StageSizer {
    private static int MINIMUM_VISIBLE_WIDTH = 100;

    private static int MINIMUM_VISIBLE_HEIGHT = 50;

    private static int MARGIN = 50;

    private Boolean maximized;

    private Number x;

    private Number y;

    private Number width;

    private Number height;

    public void setStage(Stage stage) {
        // First, restore the size and position of the stage.
        resizeAndPosition(stage);
        // If the stage is not visible in any of the current screens, relocate it the primary screen.
        if (isWindowIsOutOfBounds(stage)) {
            moveToPrimaryScreen(stage);
        }
        // And now watch the stage to keep the properties updated.
        watchStage(stage);
    }

    private void resizeAndPosition(Stage stage) {
        if (getX() != null) {
            stage.setX((Double)getX());
        }
        if (getY() != null) {
            stage.setY((Double)getY());
        }
        if (getWidth() != null) {
            stage.setWidth((Double)getWidth());
        }
        if (getHeight() != null) {
            stage.setHeight((Double)getHeight());
        }
        if (getMaximized() != null) {
            stage.setMaximized(getMaximized());
        }
    }

    private boolean isWindowIsOutOfBounds(Stage stage) {
        boolean windowIsOutOfBounds = true;
        for (Screen screen : Screen.getScreens()) {
            Rectangle2D bounds = screen.getVisualBounds();
            if (bounds.getMinX() < stage.getX() && stage.getX() + MINIMUM_VISIBLE_WIDTH < bounds.getMaxX()
                    && bounds.getMinY() < stage.getY() && stage.getY() + MINIMUM_VISIBLE_HEIGHT < bounds.getMaxY()) {
                windowIsOutOfBounds = false;
                break;
            }
        }
        return windowIsOutOfBounds;
    }

    private void moveToPrimaryScreen(Stage stage) {
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        stage.setX(bounds.getMinX() + MARGIN);
        stage.setY(bounds.getMinY() + MARGIN);
        stage.setWidth(bounds.getWidth() - MARGIN * 2);
        stage.setHeight(bounds.getHeight() - MARGIN * 2);
    }

    private void watchStage(Stage stage) {
        // Get the current values.
        setX(stage.getX());
        setY(stage.getY());
        setWidth(stage.getWidth());
        setHeight(stage.getHeight());
        setMaximized(stage.isMaximized());
        // Watch for future changes.
        stage.xProperty().addListener((observable, old, x) -> setX(x));
        stage.yProperty().addListener((observable, old, y) -> setY(y));
        stage.widthProperty().addListener((observable, old, width) -> setWidth(width));
        stage.heightProperty().addListener((observable, old, height) -> setHeight(height));
        stage.maximizedProperty().addListener((observable, old, maximized) -> setMaximized(maximized));
    }
}
