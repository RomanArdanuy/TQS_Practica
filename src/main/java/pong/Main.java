package pong;

import pong.controller.GameController;
import pong.model.GameModel;
import pong.view.GameView;
import pong.view.GameViewImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Canvas canvas = new Canvas(800, 600);
            GameModel model = new GameModel();
            GameView view = new GameViewImpl(canvas);
            GameController controller = new GameController(model, view);

            // Create scene and add canvas
            StackPane root = new StackPane(canvas);
            Scene scene = new Scene(root, 800, 600);

            // Handle keyboard input
            scene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.W) {
                    controller.setLeftPaddleVelocity(-5);
                }
                if (event.getCode() == KeyCode.S) {
                    controller.setLeftPaddleVelocity(5);
                }
                if (event.getCode() == KeyCode.UP) {
                    controller.setRightPaddleVelocity(-5);
                }
                if (event.getCode() == KeyCode.DOWN) {
                    controller.setRightPaddleVelocity(5);
                }
            });

            scene.setOnKeyReleased(event -> {
                if (event.getCode() == KeyCode.W || event.getCode() == KeyCode.S) {
                    controller.setLeftPaddleVelocity(0);
                }
                if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.DOWN) {
                    controller.setRightPaddleVelocity(0);
                }
            });

            // Game loop
            new AnimationTimer() {
                @Override
                public void handle(long now) {
                    controller.update();
                }
            }.start();

            // Set initial ball velocity
            model.getBall().setVelocityX(5);
            model.getBall().setVelocityY(5);

            // Show stage
            primaryStage.setTitle("Pong Game");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}