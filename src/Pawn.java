import java.awt.*;

public class Pawn {
    private final Point location;
    private final Color color;

    public Pawn(Point location, Color color) {
        this.location = location;
        this.color = color;
    }
    public Color getColor() {
        return color;
    }
    public Point getLocation() {
        return location;
    }

    public void setLocation(int x, int y) {
        this.location.x = x;
        this.location.y = y;
    }
}
