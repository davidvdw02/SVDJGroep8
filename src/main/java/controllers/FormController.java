package controllers;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import views.FormView;

public class FormController implements Controller {

    FormView formView = new FormView();


    // FXML id's
    @FXML javafx.scene.control.Button nextButton;
    @FXML javafx.scene.control.Button previousButton;

    @FXML javafx.scene.control.Label nextLabel;
    @FXML javafx.scene.control.Label previousLabel;

    @FXML javafx.scene.control.Label moreInfoButton;
    @FXML javafx.scene.control.Button closeButton;
    @FXML ScrollPane moreInfoPane;

    @FXML WebView infoVideo;
    @FXML WebEngine webEngine;


    public void setStage(Stage primaryStage) {
        formView.setStage(primaryStage);
    }

    @FXML
    private void initialize() {

        // This sets the animation for the colors of the buttons.
        nextButton.setOnMouseEntered(e -> enterButton(nextButton, nextLabel));
        nextButton.setOnMouseExited(e -> exitButton(nextButton, nextLabel));

        previousButton.setOnMouseEntered(e -> enterButton(previousButton, previousLabel));
        previousButton.setOnMouseExited(e -> exitButton(previousButton, previousLabel));

        moreInfoButton.setOnMouseClicked(e -> showPopup());
        closeButton.setOnMouseClicked(e -> closePopup());

        infoVideo.setContextMenuEnabled(false);
        webEngine = infoVideo.getEngine();


    }

    private void background(Color bColor, Color tColor, Button button, javafx.scene.control.Label label) {
        button.setBackground(new Background(new BackgroundFill(bColor, CornerRadii.EMPTY, Insets.EMPTY)));
        button.setTextFill(tColor);
        BorderWidths svdjBorder = new BorderWidths(2, 2, 0, 0);
        Insets svdjInsets = new Insets(0, 0, 0, 10);
        label.setBorder(new Border(new BorderStroke(
                tColor,
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                svdjBorder,
                svdjInsets)));
    }

    private void enterButton(Button button, Label label) {
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(500));
                setInterpolator(Interpolator.EASE_OUT);
            }
            @Override
            protected void interpolate(double frac) {
                Color bColor = new Color(1, 1, 1, frac);
                Color tColor = new Color(0, 0, 0, frac);
                background(bColor, tColor, button, label);
            }
        };
        animation.play();
    }

    private void exitButton(Button button, Label label) {
        final Animation animation = new Transition() {
            {
                setCycleDuration(Duration.millis(500));
                setInterpolator(Interpolator.EASE_OUT);
            }
            @Override
            protected void interpolate(double frac) {
                Color bColor = new Color(1, 1, 1, 1 - frac);
                Color tColor = new Color(1, 1, 1, frac);
                background(bColor, tColor, button, label);
            }
        };
        animation.play();

    }

    private void showPopup() {
        moreInfoPane.setVisible(true);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), moreInfoPane);
        translateTransition.setByX(-620);
        translateTransition.play();

        // Example video, DELETE LATER
        webEngine.load("https://www.youtube.com/embed/WPyOl4Equpw");
        // ^^^^^^^^^^^^^^^^^^^^^^^^^^^
    }

    private void closePopup() {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), moreInfoPane);
        translateTransition.setByX(620);
        translateTransition.play();

        translateTransition.setOnFinished(e -> moreInfoPane.setVisible(false));

        webEngine.load("");
    }

}