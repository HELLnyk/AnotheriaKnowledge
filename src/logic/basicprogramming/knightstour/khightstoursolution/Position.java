package logic.basicprogramming.knightstour.khightstoursolution;

/**
 * @author hellnyk
 */
public class Position {

    private int x;
    private int y;
    private int kindOfMove;

    public Position(int x, int y, int kindOfMove) {
        this.x = x;
        this.y = y;
        this.kindOfMove = kindOfMove;
    }

    public Position() {
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getKindOfMove() {
        return kindOfMove;
    }

    public void setKindOfMove(int kindOfMove) {
        this.kindOfMove = kindOfMove;
    }
}
