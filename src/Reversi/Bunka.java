package Reversi;

public class Bunka {
    private char x;
    private char c;
    private int y;

    public Bunka(char x, int y, char c) {
        this.x = x;
        this.y = y;
        this.c = c;
    }
    public Bunka(){};


    public char getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getCh() {
        return c;
    }

    public void setPosition(char x, char c, int y) {
        this.x = x;
        this.y=y;
        this.c=c;
    }
}
