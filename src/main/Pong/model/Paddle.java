package Pong.model;

public class Paddle {
    private double x;
    private double y;
    private double width;
    private double height;
    private double velocity;

    public Paddle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.velocity = 0.0;
    }

    public void move() {
        y += velocity;
    }

    // Getters and setters
    public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public double getVelocity() { return velocity; }
    public void setVelocity(double velocity) { this.velocity = velocity; }
}