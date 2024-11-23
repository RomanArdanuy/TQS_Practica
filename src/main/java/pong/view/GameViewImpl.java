package pong.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameViewImpl implements GameView {
    private Canvas canvas;
    private GraphicsContext gc;
    private double ballX, ballY;
    private double leftPaddleY, rightPaddleY;
    private int leftScore, rightScore;
    
    private static final double PADDLE_WIDTH = 20;
    private static final double PADDLE_HEIGHT = 100;
    private static final double BALL_SIZE = 20;

    public GameViewImpl(Canvas canvas) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        gc.setFont(Font.font(36));
    }

    @Override
    public void updateBallPosition(double x, double y) {
        this.ballX = x;
        this.ballY = y;
    }

    @Override
    public void updatePaddlePositions(double leftY, double rightY) {
        this.leftPaddleY = leftY;
        this.rightPaddleY = rightY;
    }

    @Override
    public void updateScore(int leftScore, int rightScore) {
        this.leftScore = leftScore;
        this.rightScore = rightScore;
    }

    @Override
    public void render() {
        // Clear canvas
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw center line
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(2);
        for (double y = 0; y < canvas.getHeight(); y += 30) {
            gc.strokeLine(canvas.getWidth()/2, y, canvas.getWidth()/2, y + 15);
        }

        // Draw paddles
        gc.setFill(Color.WHITE);
        gc.fillRect(50, leftPaddleY, PADDLE_WIDTH, PADDLE_HEIGHT);  // Left paddle
        gc.fillRect(canvas.getWidth() - 70, rightPaddleY, PADDLE_WIDTH, PADDLE_HEIGHT); // Right paddle

        // Draw ball
        gc.fillOval(ballX - BALL_SIZE/2, ballY - BALL_SIZE/2, BALL_SIZE, BALL_SIZE);

        // Draw score
        gc.fillText(leftScore + " " + rightScore, canvas.getWidth()/2 - 40, 50);
    }
}