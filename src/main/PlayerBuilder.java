package main;

public class PlayerBuilder {
    private int x;
    private int y;
    private int width;
    private int height;

    public PlayerBuilder setX(int x) {
        this.x = x;

        this.y = y;

        this.width = width;

        this.height = height;
        return this;
    }

    public Player createPlayer() {
        return new Player(x, y, width, height);
    }
}