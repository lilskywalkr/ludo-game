import java.awt.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class User {

    public static final int POWN_WIDTH = 36;
    public static final int POWN_HEIGHT = 50;
    private LinkedList<Pawn> pawns;
    private final Color color;
    public User(Color color, LinkedList<Point> baseFields) {
        this.color = color;
        initializePawns(baseFields);
    }

    public Color getColor() {
        return color;
    }

    public LinkedList<Pawn> getPawns() {
        return pawns;
    }
    public void initializePawns(LinkedList<Point> baseFields) {
        pawns = new LinkedList<>();
        for (Point p : baseFields) {
            pawns.add(new Pawn(new Point(p.x, p.y), color));
        }
    }

    public Pawn getPawn(Point point)
    {
        for (Pawn pawn : pawns) {
            if(isCursorInBoundries(pawn,point))
                return pawn;
        }
        return null;
    }

    private boolean isCursorInBoundries(Pawn pawn,Point point)
    {
        return point.x <= pawn.getLocation().x + POWN_WIDTH  && point.x >= pawn.getLocation().x  && point.y <=pawn.getLocation().y + POWN_HEIGHT  && point.y >= pawn.getLocation().y;
    }
}
