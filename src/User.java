import java.awt.*;
import java.util.LinkedList;

public class User {
    public static final int START_GREEN_FIELD = 2;
    public static final int START_BLUE_FIELD = 16;
    public static final int START_YELLOW_FIELD = 30;
    public static final int START_RED_FIELD = 44;
    public static final int END_GREEN_FIELD = 1;
    public static final int END_BLUE_FIELD = 15;
    public static final int END_YELLOW_FIELD = 29;
    public static final int END_RED_FIELD = 43;
    private LinkedList<Pawn> pawns;
    private final Color color;
    private LinkedList<Point> startFields;
    private LinkedList<Point> endFields;
    public User(Color color, LinkedList<Point> baseFields, LinkedList<Point> squares) {
        this.color = color;
        initializePawnsAndBasefields(baseFields);
        initializeFields(squares);
    }

    public Color getColor() {
        return color;
    }

    public LinkedList<Pawn> getPawns() {
        return pawns;
    }
    public void initializePawnsAndBasefields(LinkedList<Point> baseFields) {
        pawns = new LinkedList<>();
        for (Point p : baseFields) {
            pawns.add(new Pawn(new Point(p), color));
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
    public static boolean isCursorInBoundries(Pawn pawn,Point point) {
        return point.x <= pawn.getLocation().x + Pawn.PAWN_WIDTH && point.x >= pawn.getLocation().x  && point.y <=pawn.getLocation().y + Pawn.PAWN_HEIGHT && point.y >= pawn.getLocation().y;
    }
    public void initializeFields(LinkedList<Point> squares) {
        startFields = new LinkedList<>();
        endFields = new LinkedList<>();
        if (color.equals(Color.GREEN)) {
            Point square = squares.get(START_GREEN_FIELD);
            startFields.add(square);
            square = squares.get(END_GREEN_FIELD);
            endFields.add(square);
        }
        else if (color.equals(Color.BLUE)) {
            Point square = squares.get(START_BLUE_FIELD);
            startFields.add(square);
            square = squares.get(END_BLUE_FIELD);
            endFields.add(square);
        }
        else if (color.equals(Color.YELLOW)) {
            Point square = squares.get(START_YELLOW_FIELD);
            startFields.add(square);
            square = squares.get(END_YELLOW_FIELD);
            endFields.add(square);
        }
        else if(color.equals(Color.RED)){
            Point square = squares.get(START_RED_FIELD);
            startFields.add(square);
            square = squares.get(END_RED_FIELD);
            endFields.add(square);
        }
    }

    public void moveOutOfBase(Pawn pawn) {
        pawn.setLocation(Pawn.setPawnPrintingValues(startFields.get(0)));
    }
}
