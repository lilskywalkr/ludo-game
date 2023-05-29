import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Board extends JPanel {

    public static final int SQUARE_SIZE = 50;
    public static final int CIRCLE_SIZE = SQUARE_SIZE;
    public static final int PAWN_SIZE = SQUARE_SIZE/2 + 5;
    public static final int DICE_SIZE = SQUARE_SIZE * 3;
    public static final int SPACE_BETWEEN_CIRCLE = SQUARE_SIZE + 10;
    public static final int BIG_SQUARE_SIZE = 6*SQUARE_SIZE;
    public static final float STROKE_WIDTH = (float)SQUARE_SIZE/25;
    public int random = 0;
    LinkedHashMap<Color,LinkedList<Point>> baseFields;
    LinkedList<User> users;
    LinkedList<Point> squares;

    public Board() {
        setPreferredSize(new Dimension(BIG_SQUARE_SIZE*2+3*SQUARE_SIZE, BIG_SQUARE_SIZE*2+3*SQUARE_SIZE));
        initializeBases();
        generateSquares();
    }
    public LinkedList<Point> setBaseCordinates (int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        LinkedList<Point> base = new LinkedList<>();
        base.add(new Point(x1, y1));
        base.add(new Point(x2, y2));
        base.add(new Point(x3, y3));
        base.add(new Point(x4, y4));
        return base;
    }
    public void generateSquares(){
        squares = new LinkedList<>();
        for(int i=0;i<3;i++){
            squares.add(new Point(BIG_SQUARE_SIZE + SQUARE_SIZE*i + SQUARE_SIZE/2,SQUARE_SIZE/2));
        }
        for(int i=0;i<6;i++){
            squares.add(new Point(BIG_SQUARE_SIZE + SQUARE_SIZE*2 + SQUARE_SIZE/2,SQUARE_SIZE+SQUARE_SIZE*i + SQUARE_SIZE/2));
        }
        for(int i=0;i<6;i++){
            squares.add(new Point(BIG_SQUARE_SIZE + SQUARE_SIZE*3 + SQUARE_SIZE*i + SQUARE_SIZE/2,SQUARE_SIZE*6 + SQUARE_SIZE/2));
        }
        for(int i=0;i<2;i++){
        squares.add(new Point(2*BIG_SQUARE_SIZE + SQUARE_SIZE*2 + SQUARE_SIZE/2,BIG_SQUARE_SIZE + SQUARE_SIZE*i + SQUARE_SIZE/2 + SQUARE_SIZE));
        }
        for(int i=0;i<6;i++){
            squares.add(new Point(2*BIG_SQUARE_SIZE + SQUARE_SIZE + SQUARE_SIZE/2 - SQUARE_SIZE*i,BIG_SQUARE_SIZE + SQUARE_SIZE*2 + SQUARE_SIZE/2));
        }
        for(int i=0;i<6;i++){
            squares.add(new Point(BIG_SQUARE_SIZE + 2*SQUARE_SIZE + SQUARE_SIZE/2,BIG_SQUARE_SIZE + SQUARE_SIZE*3 + SQUARE_SIZE/2 +  SQUARE_SIZE*i));
        }
        for(int i=0;i<2;i++){
            squares.add(new Point(BIG_SQUARE_SIZE + SQUARE_SIZE + SQUARE_SIZE/2 - SQUARE_SIZE*i,2*BIG_SQUARE_SIZE + SQUARE_SIZE*2 + SQUARE_SIZE/2));
        }
        for(int i=0;i<6;i++){
            squares.add(new Point(BIG_SQUARE_SIZE + SQUARE_SIZE/2,2*BIG_SQUARE_SIZE + SQUARE_SIZE + SQUARE_SIZE/2 - SQUARE_SIZE*i));
        }
        for(int i=0;i<6;i++){
            squares.add(new Point(BIG_SQUARE_SIZE - SQUARE_SIZE/2 - SQUARE_SIZE*i,BIG_SQUARE_SIZE + 2*SQUARE_SIZE + SQUARE_SIZE/2));
        }
        for(int i=0;i<2;i++){
            squares.add(new Point(SQUARE_SIZE/2,BIG_SQUARE_SIZE + SQUARE_SIZE + SQUARE_SIZE/2 - SQUARE_SIZE*i));
        }
        for(int i=0;i<6;i++){
            squares.add(new Point(SQUARE_SIZE + SQUARE_SIZE/2 + SQUARE_SIZE*i,BIG_SQUARE_SIZE + SQUARE_SIZE/2));
        }
        for(int i=0;i<5;i++){
            squares.add(new Point(BIG_SQUARE_SIZE + SQUARE_SIZE/2,BIG_SQUARE_SIZE - SQUARE_SIZE/2 - SQUARE_SIZE*i));
        }

    }
    public void initializeBases() {
        baseFields = new LinkedHashMap<>();
        baseFields.put(Color.RED, setBaseCordinates(SQUARE_SIZE + SQUARE_SIZE/2, SQUARE_SIZE + SQUARE_SIZE/2 - SQUARE_SIZE/10,
                4*SQUARE_SIZE - SQUARE_SIZE/10, SQUARE_SIZE + SQUARE_SIZE/2 - SQUARE_SIZE/10,
                SQUARE_SIZE + SQUARE_SIZE/2, 4*SQUARE_SIZE - SQUARE_SIZE/5,
                4*SQUARE_SIZE - SQUARE_SIZE/10, 4*SQUARE_SIZE - SQUARE_SIZE/5));
        baseFields.put(Color.GREEN, setBaseCordinates(2*BIG_SQUARE_SIZE-3*SQUARE_SIZE/2, SQUARE_SIZE + SQUARE_SIZE/2 - SQUARE_SIZE/10,
                2*BIG_SQUARE_SIZE+SQUARE_SIZE-SQUARE_SIZE/10, SQUARE_SIZE + SQUARE_SIZE/2 - SQUARE_SIZE/10,
                2*BIG_SQUARE_SIZE-3*SQUARE_SIZE/2, 4*SQUARE_SIZE - SQUARE_SIZE/5,
                2*BIG_SQUARE_SIZE+SQUARE_SIZE-SQUARE_SIZE/10, 4*SQUARE_SIZE - SQUARE_SIZE/5));
        baseFields.put(Color.YELLOW, setBaseCordinates(SQUARE_SIZE + SQUARE_SIZE/2, BIG_SQUARE_SIZE+7*PAWN_SIZE + SQUARE_SIZE/5,
                4*SQUARE_SIZE - SQUARE_SIZE/10, BIG_SQUARE_SIZE+7*PAWN_SIZE + SQUARE_SIZE/5,
                SQUARE_SIZE + SQUARE_SIZE/2, 2*BIG_SQUARE_SIZE+SQUARE_SIZE-SQUARE_SIZE/5,
                4*SQUARE_SIZE - SQUARE_SIZE/10, 2*BIG_SQUARE_SIZE+SQUARE_SIZE-SQUARE_SIZE/5));
        baseFields.put(Color.BLUE, setBaseCordinates(BIG_SQUARE_SIZE*2 - 3*SQUARE_SIZE/2, BIG_SQUARE_SIZE*2 - 8*SQUARE_SIZE/5,
                BIG_SQUARE_SIZE*2+SQUARE_SIZE*9/10, BIG_SQUARE_SIZE*2 - 8*SQUARE_SIZE/5,
                BIG_SQUARE_SIZE*2 - 3*SQUARE_SIZE/2, BIG_SQUARE_SIZE*2+SQUARE_SIZE*4/5,
                BIG_SQUARE_SIZE*2+SQUARE_SIZE*9/10, BIG_SQUARE_SIZE*2+SQUARE_SIZE*4/5));
        initializeUsers();
    }
    public void initializeUsers() {
        users = new LinkedList<>();
        for (Map.Entry<Color, LinkedList<Point>> entry : baseFields.entrySet()) {
            users.add(
                    new User(entry.getKey(), entry.getValue())
            );
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Stroke oldStroke = g2d.getStroke();

        //Duze kwadraty
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                drawSquare(Color.WHITE,g2d,(3*SQUARE_SIZE+BIG_SQUARE_SIZE)*i,(3*SQUARE_SIZE+BIG_SQUARE_SIZE)*j,BIG_SQUARE_SIZE);
            }
        }


        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 3; j++) {
                //Poziome
                drawSquare(Color.WHITE,g2d,SQUARE_SIZE*i,BIG_SQUARE_SIZE+SQUARE_SIZE*j,SQUARE_SIZE);

                //Pionowe
                drawSquare(Color.WHITE,g2d,BIG_SQUARE_SIZE+SQUARE_SIZE*j,SQUARE_SIZE*i,SQUARE_SIZE);
            }
        }

        drawColorPart(g2d);
        g2d.setStroke(oldStroke);

        drawPawns(g2d);
    }

    private void drawPawns(Graphics2D g2d) {
        for (User user : users) {
            LinkedList<Pawn> pawns = user.getPawns();
            for(Pawn pawn : pawns) {
                drawPawn(g2d, pawn.getColor(), pawn.getLocation().x, pawn.getLocation().y);
            }
        }
    }

    private void drawColorPart(Graphics2D g2d)
    {
        Color[] colors = {Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW};
        int startX,startY,moveX,moveY,centerOfBigSquareX,centerOfBigSquareY;

        for (Color color : colors) {
            if (color.equals(Color.RED)) {
                startX = SQUARE_SIZE;
                startY = BIG_SQUARE_SIZE+SQUARE_SIZE;
                moveX = SQUARE_SIZE;
                moveY = 0;
                centerOfBigSquareX = BIG_SQUARE_SIZE/2-CIRCLE_SIZE/2;
                centerOfBigSquareY = BIG_SQUARE_SIZE/2-CIRCLE_SIZE/2;
            } else if (color.equals(Color.GREEN)) {
                startX = BIG_SQUARE_SIZE+SQUARE_SIZE;
                startY = SQUARE_SIZE;
                moveX = 0;
                moveY = SQUARE_SIZE;
                centerOfBigSquareX = BIG_SQUARE_SIZE/2+BIG_SQUARE_SIZE + 3* SQUARE_SIZE-CIRCLE_SIZE/2;
                centerOfBigSquareY = BIG_SQUARE_SIZE/2-CIRCLE_SIZE/2;
            } else if (color.equals(Color.BLUE)) {
                startX = BIG_SQUARE_SIZE+3*SQUARE_SIZE;
                startY = BIG_SQUARE_SIZE+SQUARE_SIZE;
                moveX = SQUARE_SIZE;
                moveY = 0;
                centerOfBigSquareX = BIG_SQUARE_SIZE/2+BIG_SQUARE_SIZE + 3* SQUARE_SIZE-CIRCLE_SIZE/2;
                centerOfBigSquareY = BIG_SQUARE_SIZE/2+BIG_SQUARE_SIZE + 3* SQUARE_SIZE-CIRCLE_SIZE/2;
            } else if (color.equals(Color.YELLOW)) {
                startX = BIG_SQUARE_SIZE+SQUARE_SIZE;
                startY = BIG_SQUARE_SIZE+3*SQUARE_SIZE;
                moveX = 0;
                moveY = SQUARE_SIZE;
                centerOfBigSquareX = BIG_SQUARE_SIZE/2-CIRCLE_SIZE/2;
                centerOfBigSquareY = BIG_SQUARE_SIZE/2+BIG_SQUARE_SIZE + 3* SQUARE_SIZE-CIRCLE_SIZE/2;
            } else {
                throw new InputMismatchException();
            }


            // rysowanie pól końcowych
            for (int i = 0; i < 5; i++) {
                drawSquare(color,g2d,startX+i*moveX,startY+i*moveY,SQUARE_SIZE);
            }

            //rysowanie pól startowych
            for (int i = -1; i < 2; i+=2) {
                for (int j = -1; j < 2; j+=2) {
                    g2d.setColor(color);
                    g2d.fillOval(centerOfBigSquareX+i*SPACE_BETWEEN_CIRCLE, centerOfBigSquareY+j*SPACE_BETWEEN_CIRCLE, CIRCLE_SIZE, CIRCLE_SIZE);
                }
            }
        }

    }

    private void drawSquare(Color color,Graphics2D g2d,int x,int y,int size)
    {
        g2d.setStroke(new BasicStroke(STROKE_WIDTH));
        g2d.setColor(color);
        g2d.fillRect(x,y, size, size);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x,y, size, size);
    }

    private void drawPawn(Graphics2D g2d, Color color, int x, int y) {
        int colorInt;
        if (color.equals(Color.RED))
            colorInt = 2;
        else if(color.equals(Color.GREEN))
            colorInt = 1;
        else if(color.equals(Color.YELLOW))
            colorInt = 3;
        else
            colorInt = 0;
        try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/" + colorInt + ".png")));
            Image pawnIcon = icon.getImage();
            g2d.drawImage(pawnIcon, x, y, PAWN_SIZE, PAWN_SIZE, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int randomNumberGenerate() {
        Random random = new Random();

        return random.nextInt(6) + 1;
    }

    private void diceThrow(Graphics2D g2d) {
        this.random = this.randomNumberGenerate();

        try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/kostka_" + this.random + ".png")));
            Image diceIcon = icon.getImage();
            g2d.drawImage(diceIcon, BIG_SQUARE_SIZE, BIG_SQUARE_SIZE, DICE_SIZE, DICE_SIZE, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
