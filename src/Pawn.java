import java.awt.*;

public class Pawn {
    public static final int PAWN_WIDTH = 36;
    public static final int PAWN_HEIGHT = 50;
    private Point location;
    private final Color color;

    private int positionInHome;


    private int numberOfMoves;

    private int pawnID;

    public Pawn(Point location, Color color,int pawnID) {
        this.location = location;
        this.color = color;
        this.pawnID = pawnID;
        positionInHome = -2;
        numberOfMoves = 0;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public int getPawnID() {
        return pawnID;
    }

    public Color getColor() {
        return color;
    }
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point p) {
        this.location = p;
    }

    public int getPositionInHome() {
        return positionInHome;
    }

    public void setPositionInHome(int positionInHome) {
        this.positionInHome = positionInHome;
    }
    public static Point setPawnPrintingValues(Point from) {
        Point p = new Point();
        p.x = from.x - Pawn.PAWN_WIDTH/2;
        p.y = from.y - Pawn.PAWN_HEIGHT/3;
        return p;
    }
}
