package Client;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Objects;
import javax.swing.*;

public class Board extends JPanel {

    private static final int SQUARE_SIZE = 50;
    private static final int CIRCLE_SIZE = 50;
    private static final int PAWN_SIZE = 30;
    private static final int SPACE_BETWEEN_CIRCLE = 60;
    private static final int BIG_SQUARE_SIZE = 300;
    private static final float STROKE_WIDTH = 2.0f;

    public Board() {
        setPreferredSize(new Dimension(BIG_SQUARE_SIZE*2+3*SQUARE_SIZE, BIG_SQUARE_SIZE*2+3*SQUARE_SIZE));
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

        // Red pawn
        drawPawn(g2d, 2, 75, 70);
        drawPawn(g2d, 2, 195, 70);
        drawPawn(g2d, 2, 75, 190);
        drawPawn(g2d, 2, 195, 190);

        // Green pawn
        drawPawn(g2d, 1, 525, 70);
        drawPawn(g2d, 1, 645, 70);
        drawPawn(g2d, 1, 525, 190);
        drawPawn(g2d, 1, 645, 190);

        // Yellow pawn
        drawPawn(g2d, 3, 75, 520);
        drawPawn(g2d, 3, 195, 520);
        drawPawn(g2d, 3, 75, 640);
        drawPawn(g2d, 3, 195, 640);

        // Blue pawn
        drawPawn(g2d, 0, 525, 520);
        drawPawn(g2d, 0, 645, 520);
        drawPawn(g2d, 0, 525, 640);
        drawPawn(g2d, 0, 645, 640);
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

    private void drawPawn(Graphics2D g2d, int color, int x, int y) {
        try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("../assets/" + color + ".png")));
            Image pawnIcon = icon.getImage();
            g2d.drawImage(pawnIcon, x, y, PAWN_SIZE, PAWN_SIZE, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
