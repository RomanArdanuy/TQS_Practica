package Pong.model;

public class Ball {
    private double x;
    private double y;
    private double radius;
    private double velocityX;
    private double velocityY;
    
    public Ball(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.velocityX = 0.0;
        this.velocityY = 0.0;
    }

    public boolean checkCollision(Paddle paddle) {
        boolean isVerticallyAligned = (y + radius >= paddle.getY() && 
                                     y - radius <= paddle.getY() + paddle.getHeight());
        boolean isHorizontallyAligned = (x + radius >= paddle.getX() && 
                                       x - radius <= paddle.getX() + paddle.getWidth());
        
        return isVerticallyAligned && isHorizontallyAligned;
    }
    
    public void move() {
        x += velocityX;
        y += velocityY;
    }
    
    public void reverseX() {
        velocityX = -velocityX;
    }
    
    public void reverseY() {
        velocityY = -velocityY;
    }
    
    // Getters and setters
    public double getX() { return x; }
    public double getY() { return y; }
    public double getRadius() { return radius; }
    public double getVelocityX() { return velocityX; }
    public double getVelocityY() { return velocityY; }
    public void setVelocityX(double velocityX) { this.velocityX = velocityX; }
    public void setVelocityY(double velocityY) { this.velocityY = velocityY; }
}