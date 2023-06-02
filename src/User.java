import java.awt.*;
import java.util.LinkedList;

public class User {
    public static final int PAWN_WIDTH = 36;
    public static final int PAWN_HEIGHT = 50;
    public static final int START_GREEN_FIELD = 3;
    public static final int START_BLUE_FIELD = 17;
    public static final int START_YELLOW_FIELD = 31;
    public static final int START_RED_FIELD = 45;
    public static final int END_GREEN_FIELD = 1;
    public static final int END_BLUE_FIELD = 15;
    public static final int END_YELLOW_FIELD = 29;
    public static final int END_RED_FIELD = 43;
    private LinkedList<Pawn> pawns;
    private final Color color;
    private LinkedList<Point> startFields;
    private LinkedList<Point> endFields;
    private LinkedList<Point> basefieldsPoints;
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
        basefieldsPoints = new LinkedList<>();
        for (Point p : baseFields) {
            basefieldsPoints.add(new Point(p));
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

    private boolean isCursorInBoundries(Pawn pawn,Point point) {
        return point.x <= pawn.getLocation().x + PAWN_WIDTH && point.x >= pawn.getLocation().x  && point.y <=pawn.getLocation().y + PAWN_HEIGHT && point.y >= pawn.getLocation().y;
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



    public boolean isClickedPawnInBase(Pawn pawn) {
        for (Point p : basefieldsPoints) {
            if(isCursorInBoundries(pawn,p))
                return true;
        }
        return false;
    }


}
