package pong.model;

public class GameModel {
    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private int leftScore;
    private int rightScore;
    private static final double SCREEN_WIDTH = 800;
    private static final double SCREEN_HEIGHT = 600;

    public GameModel() {
        // Initialize ball at center
        ball = new Ball(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 10);
        
        // Initialize paddles
        leftPaddle = new Paddle(50, SCREEN_HEIGHT/2 - 50, 20, 100);
        rightPaddle = new Paddle(SCREEN_WIDTH - 70, SCREEN_HEIGHT/2 - 50, 20, 100);
        
        // Initialize scores
        leftScore = 0;
        rightScore = 0;
    }

    public void update() {
        movePaddles();
        moveBall();
        checkCollisions();
        checkScore();
    }

    private void movePaddles() {
        leftPaddle.move();
        rightPaddle.move();
        
        // Keep paddles within screen bounds
        constrainPaddle(leftPaddle);
        constrainPaddle(rightPaddle);
    }

    private void constrainPaddle(Paddle paddle) {
        if (paddle.getY() < 0) {
            paddle.setVelocity(0);
            // Set Y to 0 if paddle goes above screen
        }
        if (paddle.getY() + paddle.getHeight() > SCREEN_HEIGHT) {
            paddle.setVelocity(0);
            // Set Y to keep paddle within screen bounds
        }
    }

    private void moveBall() {
        ball.move();
    }

    private void checkCollisions() {
        // Check paddle collisions
        if (ball.checkCollision(leftPaddle) || ball.checkCollision(rightPaddle)) {
            ball.reverseX();
        }
        
        // Check top and bottom wall collisions
        if (ball.getY() - ball.getRadius() <= 0 || 
            ball.getY() + ball.getRadius() >= SCREEN_HEIGHT) {
            ball.reverseY();
        }
    }

    private void checkScore() {
        // Ball passed left paddle
        if (ball.getX() - ball.getRadius() <= 0) {
            rightScore++;
            resetBall();
        }
        // Ball passed right paddle
        else if (ball.getX() + ball.getRadius() >= SCREEN_WIDTH) {
            leftScore++;
            resetBall();
        }
    }

    private void resetBall() {
        ball = new Ball(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 10);
        ball.setVelocityX(5);
        ball.setVelocityY(5);
    }

    // Getters
    public Ball getBall() { return ball; }
    public Paddle getLeftPaddle() { return leftPaddle; }
    public Paddle getRightPaddle() { return rightPaddle; }
    public int getLeftScore() { return leftScore; }
    public int getRightScore() { return rightScore; }
}