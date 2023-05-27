import java.awt.*;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class User {
    private LinkedList<Pawn> pawns;
    private final Color color;
    public User(Color color, LinkedList<Point> baseFields) {
        this.color = color;
        initializePawns(baseFields);
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
}
