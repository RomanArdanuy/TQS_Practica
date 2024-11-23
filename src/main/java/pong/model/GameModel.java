package pong.model;

public class GameModel {
    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private ScoreBoard scoreBoard;
    private GameLogger logger;
    public static final double SCREEN_WIDTH = 800;
    public static final double SCREEN_HEIGHT = 600;

    public GameModel() {
        // Initialize ball at center
        ball = new Ball(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 10);
        
        // Initialize paddles
        leftPaddle = new Paddle(50, SCREEN_HEIGHT/2 - 50, 20, 100);
        rightPaddle = new Paddle(SCREEN_WIDTH - 70, SCREEN_HEIGHT/2 - 50, 20, 100);
        
        // Initialize scoreBoard with mock (for now)
        this.scoreBoard = new MockScoreBoard();
        this.logger = null;  // Logger is optional in default constructor
    }

    public GameModel(ScoreBoard scoreBoard, GameLogger logger) {
        // Initialize ball at center
        ball = new Ball(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 10);
        
        // Initialize paddles
        leftPaddle = new Paddle(50, SCREEN_HEIGHT/2 - 50, 20, 100);
        rightPaddle = new Paddle(SCREEN_WIDTH - 70, SCREEN_HEIGHT/2 - 50, 20, 100);
        
        this.scoreBoard = scoreBoard;
        this.logger = logger;
        if (logger != null) {
            logger.logGameStart();
        }
    }
    public GameModel(ScoreBoard scoreBoard) {
        // Initialize ball at center
        ball = new Ball(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 10);
        
        // Initialize paddles
        leftPaddle = new Paddle(50, SCREEN_HEIGHT/2 - 50, 20, 100);
        rightPaddle = new Paddle(SCREEN_WIDTH - 70, SCREEN_HEIGHT/2 - 50, 20, 100);
        
        this.scoreBoard = scoreBoard;
        this.logger = null;  // No logger in this constructor
    }

    public void update() {
        if (!scoreBoard.isGameOver()) {
            movePaddles();
            moveBall();
            checkCollisions();
            checkScore();
        }
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
        if (ball.checkCollision(leftPaddle)) {
            ball.reverseX();
            if (logger != null) {
                logger.logCollision("leftPaddle");
            }
        } else if (ball.checkCollision(rightPaddle)) {
            ball.reverseX();
            if (logger != null) {
                logger.logCollision("rightPaddle");
            }
        }
        
        // Check top and bottom wall collisions
        if (ball.getY() - ball.getRadius() <= 0 || 
            ball.getY() + ball.getRadius() >= SCREEN_HEIGHT) {
            ball.reverseY();
            if (logger != null) {
                logger.logCollision("wall");
            }
        }
    }

    private void checkScore() {
        // Ball passed left paddle
        if (ball.getX() - ball.getRadius() <= 0) {
            scoreBoard.updateScore(scoreBoard.getLeftScore(), scoreBoard.getRightScore() + 1);
            if (logger != null) {
                logger.logScore("right", scoreBoard.getRightScore());
            }
            resetBall();
            if (scoreBoard.isGameOver() && logger != null) {
                logger.logGameEnd(scoreBoard.getWinner());
            }
        }
        // Ball passed right paddle
        else if (ball.getX() + ball.getRadius() >= SCREEN_WIDTH) {
            scoreBoard.updateScore(scoreBoard.getLeftScore() + 1, scoreBoard.getRightScore());
            if (logger != null) {
                logger.logScore("left", scoreBoard.getLeftScore());
            }
            resetBall();
            if (scoreBoard.isGameOver() && logger != null) {
                logger.logGameEnd(scoreBoard.getWinner());
            }
        }
    }

    private void resetBall() {
        ball = new Ball(SCREEN_WIDTH/2, SCREEN_HEIGHT/2, 10);
        ball.setVelocityX(5);
        ball.setVelocityY(5);
    }

    public void resetGame() {
        scoreBoard.reset();
        resetBall();
    }

    // Getters
    public Ball getBall() { return ball; }
    public Paddle getLeftPaddle() { return leftPaddle; }
    public Paddle getRightPaddle() { return rightPaddle; }
    public int getLeftScore() { return scoreBoard.getLeftScore(); }
    public int getRightScore() { return scoreBoard.getRightScore(); }
    public boolean isGameOver() { return scoreBoard.isGameOver(); }
    public String getWinner() { return scoreBoard.getWinner(); }
}