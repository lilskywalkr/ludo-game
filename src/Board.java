import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;


public class Board extends JPanel implements MouseListener {

    public static final int SQUARE_SIZE = 50;
    public static final int CIRCLE_SIZE = SQUARE_SIZE;
    public static final int PAWN_SIZE = SQUARE_SIZE/2 + SQUARE_SIZE/10;
    public static final int DICE_SIZE = SQUARE_SIZE;
    public static final int SPACE_BETWEEN_CIRCLE = SQUARE_SIZE + SQUARE_SIZE/5;
    public static final int ROLLING_OFFSET = 1;
    public int diceValue = this.randomNumberGenerate();
    public static final int BIG_SQUARE_SIZE = 6*SQUARE_SIZE;
    public static final float STROKE_WIDTH = (float)SQUARE_SIZE/25;
    public static final int SHORT_HORIZONTAL_FIELDS = 3;
    public static final int SHORT_VERTICAL_FIELDS = 3;
    public static final int LONG_HORIZONTAL_FIELDS = 6;
    public static final int LONG_VERTICAL_FIELDS = 6;
    public static final int NUMBER_OF_FINAL_FIELDS = 5;
    public static final int NUMBER_OF_PAWNS = 4;
    public static final int LAST_FIELD_BEFORE_HOME = -1;
    public static final int PAWN_ON_BOARD = -2;
    public static final int NUMBER_WIDTH = 30;
    public static final int NUMBER_HEIGHT = 30;



    private static final int MAX_NUMBER_OF_MOVES = 55;
    public boolean isDiceRolled = false;
    public boolean hasMoved = false;
    public boolean oneMoreMove = false;
    public boolean wasColorChanged = false;

    public static final int MAXIMUM_ROLLED_VALUE = 6;
    private Color currentPlayerColor;
    LinkedHashMap<Color,LinkedList<Point>> baseFields;
    LinkedHashMap<Color,LinkedList<Point>> homeFields;
    LinkedList<User> users;
    LinkedList<Point> squares;


    public Board() {
        currentPlayerColor = Color.RED;
        setPreferredSize(new Dimension(BIG_SQUARE_SIZE*2+3*SQUARE_SIZE, BIG_SQUARE_SIZE*2+3*SQUARE_SIZE));
        generateSquares();
        initializeBases();
        addMouseListener(this);
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
        for(int i=0;i<SHORT_HORIZONTAL_FIELDS;i++){
            squares.add(new Point(BIG_SQUARE_SIZE + SQUARE_SIZE*i + SQUARE_SIZE/2,SQUARE_SIZE/2));
        }
        for(int i=0;i<LONG_VERTICAL_FIELDS;i++){
            squares.add(new Point(BIG_SQUARE_SIZE + SQUARE_SIZE*2 + SQUARE_SIZE/2,SQUARE_SIZE+SQUARE_SIZE*i + SQUARE_SIZE/2));
        }
        for(int i=0;i<LONG_HORIZONTAL_FIELDS;i++){
            squares.add(new Point(BIG_SQUARE_SIZE + SQUARE_SIZE*3 + SQUARE_SIZE*i + SQUARE_SIZE/2,SQUARE_SIZE*6 + SQUARE_SIZE/2));
        }
        for(int i = 0; i<SHORT_VERTICAL_FIELDS- 1; i++){
        squares.add(new Point(2*BIG_SQUARE_SIZE + SQUARE_SIZE*2 + SQUARE_SIZE/2,BIG_SQUARE_SIZE + SQUARE_SIZE*i + SQUARE_SIZE/2 + SQUARE_SIZE));
        }
        for(int i=0;i<LONG_HORIZONTAL_FIELDS;i++){
            squares.add(new Point(2*BIG_SQUARE_SIZE + SQUARE_SIZE + SQUARE_SIZE/2 - SQUARE_SIZE*i,BIG_SQUARE_SIZE + SQUARE_SIZE*2 + SQUARE_SIZE/2));
        }
        for(int i=0;i<LONG_VERTICAL_FIELDS;i++){
            squares.add(new Point(BIG_SQUARE_SIZE + 2*SQUARE_SIZE + SQUARE_SIZE/2,BIG_SQUARE_SIZE + SQUARE_SIZE*3 + SQUARE_SIZE/2 +  SQUARE_SIZE*i));
        }
        for(int i = 0; i<SHORT_HORIZONTAL_FIELDS- 1; i++){
            squares.add(new Point(BIG_SQUARE_SIZE + SQUARE_SIZE + SQUARE_SIZE/2 - SQUARE_SIZE*i,2*BIG_SQUARE_SIZE + SQUARE_SIZE*2 + SQUARE_SIZE/2));
        }
        for(int i=0;i<LONG_VERTICAL_FIELDS;i++){
            squares.add(new Point(BIG_SQUARE_SIZE + SQUARE_SIZE/2,2*BIG_SQUARE_SIZE + SQUARE_SIZE + SQUARE_SIZE/2 - SQUARE_SIZE*i));
        }
        for(int i=0;i<LONG_HORIZONTAL_FIELDS;i++){
            squares.add(new Point(BIG_SQUARE_SIZE - SQUARE_SIZE/2 - SQUARE_SIZE*i,BIG_SQUARE_SIZE + 2*SQUARE_SIZE + SQUARE_SIZE/2));
        }
        for(int i = 0; i<SHORT_VERTICAL_FIELDS- 1; i++){
            squares.add(new Point(SQUARE_SIZE/2,BIG_SQUARE_SIZE + SQUARE_SIZE + SQUARE_SIZE/2 - SQUARE_SIZE*i));
        }
        for(int i=0;i<LONG_HORIZONTAL_FIELDS;i++){
            squares.add(new Point(SQUARE_SIZE + SQUARE_SIZE/2 + SQUARE_SIZE*i,BIG_SQUARE_SIZE + SQUARE_SIZE/2));
        }
        for(int i = 0; i<LONG_HORIZONTAL_FIELDS- 1; i++){
            squares.add(new Point(BIG_SQUARE_SIZE + SQUARE_SIZE/2,BIG_SQUARE_SIZE - SQUARE_SIZE/2 - SQUARE_SIZE*i));
        }

    }

    public void initializeBases() {
        baseFields = new LinkedHashMap<>();
        homeFields = new LinkedHashMap<>();

        LinkedList<Point> redPoints = new LinkedList<>();
        LinkedList<Point> greenPoints = new LinkedList<>();
        LinkedList<Point> yellowPoints = new LinkedList<>();
        LinkedList<Point> bluePoints = new LinkedList<>();


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



        for (int i = 0; i < 5; i++) {
            redPoints.add(new Point((2*SQUARE_SIZE-SQUARE_SIZE/2)+SQUARE_SIZE*i,(BIG_SQUARE_SIZE+SQUARE_SIZE+SQUARE_SIZE/2)));
        }
        homeFields.put(Color.RED,redPoints);

        for (int i = 0; i < 5; i++) {
            greenPoints.add(new Point((BIG_SQUARE_SIZE+SQUARE_SIZE+SQUARE_SIZE/2),(2*SQUARE_SIZE-SQUARE_SIZE/2)+SQUARE_SIZE*i));
        }
        homeFields.put(Color.GREEN,greenPoints);

        for (int i = 0; i < 5; i++) {
            bluePoints.add(new Point((2*BIG_SQUARE_SIZE-SQUARE_SIZE/2+BIG_SQUARE_SIZE/3)-i*SQUARE_SIZE,(BIG_SQUARE_SIZE+SQUARE_SIZE+SQUARE_SIZE/2)));
        }
        homeFields.put(Color.BLUE,bluePoints);



        for (int i = 0; i < 5; i++) {
            yellowPoints.add(new Point((BIG_SQUARE_SIZE+SQUARE_SIZE/2+SQUARE_SIZE),(2*BIG_SQUARE_SIZE+SQUARE_SIZE/2+SQUARE_SIZE)-i*SQUARE_SIZE));
        }
        homeFields.put(Color.YELLOW,yellowPoints);

        initializeUsers();
    }
    public void initializeUsers() {
        users = new LinkedList<>();
        for (Map.Entry<Color, LinkedList<Point>> entry : baseFields.entrySet()) {
            users.add(
                    new User(entry.getKey(), entry.getValue(), squares)
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

        diceButton(g2d, 50 * 7, 50 * 8, 50, 50);
        drawRoundThingy(g2d, currentPlayerColor);
        drawPawns(g2d);
        diceThrow(g2d);

        if (areUserPawnsInHome(getUserByColor(currentPlayerColor))) {
            drawGameOver(g2d);
        }
        drawNumberOfPawns(g2d,getUserByColor(currentPlayerColor));
    }
    
    private void diceButton(Graphics2D g2d, int x, int y, int width, int height) {
        Color backgroundColor = Color.WHITE;
        String text = "Roll!";

        g2d.setColor(backgroundColor);
        g2d.fillRect(x, y, width, height);

        Font font = new Font("Arial", Font.BOLD, 16);
        Color textColor = Color.BLACK;
        g2d.setFont(font);
        g2d.setColor(textColor);

        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        int textX = x + (width - textWidth) / 2;
        int textY = y + (height - textHeight) / 2 + fm.getAscent();

        g2d.drawString(text, textX, textY);
        g2d.drawRect(x, y, width, height);
    }

    private void drawPawns(Graphics2D g2d) {
        for (User user : users) {
            LinkedList<Pawn> pawns = user.getPawns();
            for(Pawn pawn : pawns) {
                drawPawn(g2d, pawn.getColor(), pawn.getLocation().x, pawn.getLocation().y);
            }
        }
    }

    private void drawColorPart(Graphics2D g2d) {
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
            for (int i = 0; i < NUMBER_OF_FINAL_FIELDS; i++) {
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

    private void drawSquare(Color color,Graphics2D g2d,int x,int y,int size) {
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

        return random.nextInt(MAXIMUM_ROLLED_VALUE) + ROLLING_OFFSET;

    }

    private void diceThrow(Graphics2D g2d) {
        try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/kostka_" + this.diceValue + ".png")));
            Image diceIcon = icon.getImage();
            g2d.drawImage(diceIcon, BIG_SQUARE_SIZE + DICE_SIZE, BIG_SQUARE_SIZE + DICE_SIZE, DICE_SIZE, DICE_SIZE, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        Pawn pawn;
        int rectX = 50 * 7;
        int rectY = 50 * 8;
        int rectWidth = 50;
        int rectHeight = 50;

       for (User user: users) {
            if((pawn = user.getPawn(point))!=null && user.getColor().equals(currentPlayerColor)) {
                if (!hasMoved) {
                    if(movePawn(pawn, user)) {
                        hasMoved = true;
                        isDiceRolled = false;
                    }
                }
                repaint();
                return;
            }
        }

        if (isButtonClicked(point, rectX, rectY, rectWidth, rectHeight)) {

            if (!isDiceRolled) {
                if(diceValue == MAXIMUM_ROLLED_VALUE) {
                    diceValue = randomNumberGenerate();
                    isDiceRolled = true;
                    hasMoved = false;
                } else {
                    diceValue = randomNumberGenerate();
                    currentPlayerColor = getNextColor(currentPlayerColor);
                    if(userPawnsNumberInBase(getUserByColor(currentPlayerColor)) != 4 || diceValue == MAXIMUM_ROLLED_VALUE)
                        isDiceRolled = true;
                    hasMoved = false;
                }
            }
        }
        repaint();
    }

    private User getUserByColor(Color color) {
        for (User user : users) {
            if (user.getColor().equals(color)) {
                return user;
            }
        }

        return null;
    }

    public boolean isButtonClicked(Point clickedPosition, int btnX, int btnY, int btnWidth, int btnHeight) {
        return clickedPosition.x >= btnX && clickedPosition.x <= btnX + btnWidth && clickedPosition.y >= btnY && clickedPosition.y <= btnY + btnHeight;
    }

    private boolean areUserPawnsInHome(User user) {
        for (Pawn pawn : user.getPawns()) {
            if (pawn.getPositionInHome() < 0) {
                return false;
            }
        }

        return true;
    }

    private int userPawnsNumberInBase(User user) {
        int count = 0;

        LinkedList<Pawn> pawns = user.getPawns();
        LinkedList<Point> basefieldsPoints = baseFields.get(user.getColor());
        for (int i = 0; i < NUMBER_OF_PAWNS; i++) {
            if (pawns.get(i).getLocation().equals(basefieldsPoints.get(i)) || pawns.get(i).getPositionInHome()>=0 ) {
                count++;
            }
        }

        return count;
    }

    private void drawRoundThingy(Graphics2D g2d, Color color) {
        String text = getColorName(color);

        g2d.setColor(color);
        g2d.fillRect(BIG_SQUARE_SIZE + SQUARE_SIZE + 12, BIG_SQUARE_SIZE + (SQUARE_SIZE / 2) - 5, SQUARE_SIZE / 2, SQUARE_SIZE / 2);

        Font font = new Font("Arial", Font.BOLD, 10);
        Color textColor = Color.BLACK;
        g2d.setFont(font);
        g2d.setColor(textColor);

        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        int textX = BIG_SQUARE_SIZE + SQUARE_SIZE + 12 + (SQUARE_SIZE / 2 - textWidth) / 2;
        int textY = BIG_SQUARE_SIZE + (SQUARE_SIZE / 2 - textHeight) / 2 + fm.getAscent();

        g2d.drawString(text, textX, textY);
        g2d.drawRect(BIG_SQUARE_SIZE + SQUARE_SIZE + 12, BIG_SQUARE_SIZE + (SQUARE_SIZE / 2) - 5, SQUARE_SIZE / 2, SQUARE_SIZE / 2);
    }

    private void drawGameOver(Graphics2D g2d) {
        String message = "You won the game";

        g2d.setColor(currentPlayerColor);
        g2d.fillRect(BIG_SQUARE_SIZE, BIG_SQUARE_SIZE, BIG_SQUARE_SIZE / 2, BIG_SQUARE_SIZE / 2);

        Font font = new Font("Arial", Font.BOLD, 15);
        Color textColor = Color.BLACK;
        g2d.setFont(font);
        g2d.setColor(textColor);

        g2d.drawString(message, BIG_SQUARE_SIZE + 13, BIG_SQUARE_SIZE + SQUARE_SIZE + SQUARE_SIZE/2);
        g2d.drawRect(BIG_SQUARE_SIZE, BIG_SQUARE_SIZE, BIG_SQUARE_SIZE / 2, BIG_SQUARE_SIZE / 2);
    }

    private void drawNumberOfPawns(Graphics2D g2d, User user){
        int[] pawnsAndCords = calculateNumberOfPawnsInSquareAndCoords(user);
        if(pawnsAndCords[0] > 1){
            try {
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("assets/nr" + pawnsAndCords[0] + ".png")));
                Image pawnIcon = icon.getImage();
                g2d.drawImage(pawnIcon, pawnsAndCords[1], pawnsAndCords[2], NUMBER_WIDTH, NUMBER_HEIGHT, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private int[] calculateNumberOfPawnsInSquareAndCoords(User user) {
        int[] pawnsAndCoords = {0, 0, 0, 0, 0};
        List<Point> uniqueLocations = new ArrayList<>();

        for (int i = 0; i < NUMBER_OF_PAWNS; i++) {
            Pawn currentPawn = user.getPawns().get(i);
            Point currentLocation = currentPawn.getLocation();

            if (!uniqueLocations.contains(currentLocation)) {
                uniqueLocations.add(currentLocation);

                int sameLocationCount = 1;
                for (int j = i + 1; j < NUMBER_OF_PAWNS; j++) {
                    Pawn nextPawn = user.getPawns().get(j);
                    Point nextLocation = nextPawn.getLocation();

                    if (currentLocation.equals(nextLocation)) {
                        sameLocationCount++;
                    }
                }

                if (sameLocationCount > 1) {
                    pawnsAndCoords[0] = sameLocationCount;
                    pawnsAndCoords[1] = currentLocation.x;
                    pawnsAndCoords[2] = currentLocation.y;
                    break;
                }
            }
        }

        return pawnsAndCoords;
    }

    private boolean isClickedPawnInBase(Pawn pawn) {
        LinkedList<Point> basefieldsPoints = baseFields.get(currentPlayerColor);
        for (Point p : basefieldsPoints) {
            if(User.isCursorInBoundries(pawn,p))
                return true;
        }
        return false;
    }

    private LinkedList<Pawn> getPawnFromNextSquare(Pawn pawn,int step)
    {
        int counter = 0;
        LinkedList<Pawn> pawns = new LinkedList<>();
        int currentPosition = getSquareId(pawn);
        for (Color color : baseFields.keySet()) {
            if(!color.equals(pawn.getColor()) && users.get(counter).getColor().equals(color))
            {
                for (Pawn enemyPawn : users.get(counter).getPawns()) {
                    if (getSquareId(enemyPawn) == currentPosition + step && enemyPawn.getPositionInHome()<0)
                        pawns.add(enemyPawn);
                }
            }
            if(!pawns.isEmpty())
                break;
            counter++;
        }
        return pawns;
    }
    private boolean checkNextSquare(Pawn pawn, User currentUser,int step)
    {
        if(pawn.getPositionInHome() == PAWN_ON_BOARD)
        {
            LinkedList<Pawn> pawns = getPawnFromNextSquare(pawn,step);
            if(!pawns.isEmpty())
            {
                for (int i = 0; i < pawns.size(); i++) {
                    pawns.get(i).setNumberOfMoves(0);
                    pawns.get(i).setPositionInHome(PAWN_ON_BOARD);
                    pawns.get(i).setLocation(baseFields.get(pawns.get(i).getColor()).get(pawns.get(i).getPawnID()));
                }
            }
            return true;
        }
        else if(pawn.getPositionInHome() == LAST_FIELD_BEFORE_HOME)
        {
            for (Pawn currentUserPawn : currentUser.getPawns()) {
                if(currentUserPawn.getPositionInHome() == pawn.getPositionInHome()+diceValue)
                    return false;
            }
            return true;

        }

        return false;
    }


    public boolean movePawn(Pawn pawn, User currentUser) {
        if(isClickedPawnInBase(pawn) && diceValue == MAXIMUM_ROLLED_VALUE) {
            currentUser.moveOutOfBase(pawn);
            return true;
        }
        else if(pawn.getPositionInHome()>LAST_FIELD_BEFORE_HOME || (pawn.getPositionInHome() == LAST_FIELD_BEFORE_HOME && diceValue == MAXIMUM_ROLLED_VALUE))
            return true;
        else if(pawn.getPositionInHome() == LAST_FIELD_BEFORE_HOME)
        {
            if(checkNextSquare(pawn,currentUser,diceValue))
            {
                pawn.setPositionInHome(diceValue-1);
                pawn.setLocation(Pawn.setPawnPrintingValues(homeFields.get(pawn.getColor()).get(pawn.getPositionInHome())));
            }
            return true;
        }
        else if(!isClickedPawnInBase(pawn)) {

            int squareID = getSquareId(pawn) + diceValue;

            if(pawn.getNumberOfMoves()+diceValue >= MAX_NUMBER_OF_MOVES && pawn.getPositionInHome()==PAWN_ON_BOARD)
            {
                pawn.setNumberOfMoves(MAX_NUMBER_OF_MOVES);
                pawn.setPositionInHome(LAST_FIELD_BEFORE_HOME);
                pawn.setLocation(Pawn.setPawnPrintingValues(squares.get(currentUser.getEndField())));
                return true;
            }
            checkNextSquare(pawn,currentUser,diceValue);

            if(squareID > currentUser.getEndField())

            if(squareID >= squares.size())
                squareID -= squares.size();
            pawn.setLocation(Pawn.setPawnPrintingValues(squares.get(squareID)));
            pawn.setNumberOfMoves(pawn.getNumberOfMoves()+diceValue);
            return true;
        }
        return false;
    }

    private int getSquareId(Pawn pawn) {
        int i = 0;
        for (Point square : squares) {
            if(isPawnInSquare(square, pawn))
                break;
            ++i;
        }
        return i;
    }

    private boolean isPawnInSquare(Point square, Pawn pawn) {
        return square.x - SQUARE_SIZE/2 <= pawn.getLocation().x && square.x + SQUARE_SIZE/2 >= pawn.getLocation().x
                && square.y - SQUARE_SIZE/2 <= pawn.getLocation().y && square.y + SQUARE_SIZE/2 >= pawn.getLocation().y;
    }

    private Color getNextColor(Color color)
    {
        if (color.equals(Color.RED))
            return Color.GREEN;
        else if( color.equals(Color.GREEN))
            return Color.BLUE;
        else if(color.equals(Color.BLUE))
            return Color.YELLOW;
        else
            return Color.RED;
    }

    private String getColorName (Color color)
    {
        if (color.equals(Color.RED)) {
            return "RED";
        }
        else if( color.equals(Color.GREEN))
            return "GREEN";
        else if(color.equals(Color.YELLOW))
            return "YELLOW";
        else
            return "BLUE";
    }



    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
