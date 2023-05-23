package Client;

import java.awt.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import javax.swing.JPanel;

public class Board extends JPanel {

    private static final int SQUARE_SIZE = 50;
    private static final int CIRCLE_SIZE = 50;
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

    public Point[] generateBoardCoordinates() {
        Point[] coordinates = new Point[56];
        coordinates[0] = new Point(7 * SQUARE_SIZE - SQUARE_SIZE / 2, 1 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[1] = new Point(8 * SQUARE_SIZE - SQUARE_SIZE / 2, 1 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[2] = new Point(9 * SQUARE_SIZE - SQUARE_SIZE / 2, 1 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[3] = new Point(9 * SQUARE_SIZE - SQUARE_SIZE / 2, 2 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[4] = new Point(9 * SQUARE_SIZE - SQUARE_SIZE / 2, 3 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[5] = new Point(9 * SQUARE_SIZE - SQUARE_SIZE / 2, 4 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[6] = new Point(9 * SQUARE_SIZE - SQUARE_SIZE / 2, 5 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[7] = new Point(9 * SQUARE_SIZE - SQUARE_SIZE / 2, 6 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[8] = new Point(9 * SQUARE_SIZE - SQUARE_SIZE / 2, 7 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[9] = new Point(10 * SQUARE_SIZE - SQUARE_SIZE / 2, 7 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[10] = new Point(11 * SQUARE_SIZE - SQUARE_SIZE / 2, 7 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[11] = new Point(12 * SQUARE_SIZE - SQUARE_SIZE / 2, 7 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[12] = new Point(13 * SQUARE_SIZE - SQUARE_SIZE / 2, 7 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[13] = new Point(14 * SQUARE_SIZE - SQUARE_SIZE / 2, 7 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[14] = new Point(15 * SQUARE_SIZE - SQUARE_SIZE / 2, 7 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[15] = new Point(15 * SQUARE_SIZE - SQUARE_SIZE / 2, 8 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[16] = new Point(15 * SQUARE_SIZE - SQUARE_SIZE / 2, 9 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[17] = new Point(14 * SQUARE_SIZE - SQUARE_SIZE / 2, 9 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[18] = new Point(13 * SQUARE_SIZE - SQUARE_SIZE / 2, 9 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[19] = new Point(12 * SQUARE_SIZE - SQUARE_SIZE / 2, 9 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[20] = new Point(11 * SQUARE_SIZE - SQUARE_SIZE / 2, 9 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[21] = new Point(10 * SQUARE_SIZE - SQUARE_SIZE / 2, 9 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[22] = new Point(9 * SQUARE_SIZE - SQUARE_SIZE / 2, 9 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[23] = new Point(9 * SQUARE_SIZE - SQUARE_SIZE / 2, 10 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[24] = new Point(9 * SQUARE_SIZE - SQUARE_SIZE / 2, 11 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[25] = new Point(9 * SQUARE_SIZE - SQUARE_SIZE / 2, 12 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[26] = new Point(9 * SQUARE_SIZE - SQUARE_SIZE / 2, 13 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[27] = new Point(9 * SQUARE_SIZE - SQUARE_SIZE / 2, 14 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[28] = new Point(9 * SQUARE_SIZE - SQUARE_SIZE / 2, 15 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[29] = new Point(8 * SQUARE_SIZE - SQUARE_SIZE / 2, 15 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[30] = new Point(7 * SQUARE_SIZE - SQUARE_SIZE / 2, 15 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[31] = new Point(7 * SQUARE_SIZE - SQUARE_SIZE / 2, 14 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[32] = new Point(7 * SQUARE_SIZE - SQUARE_SIZE / 2, 13 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[33] = new Point(7 * SQUARE_SIZE - SQUARE_SIZE / 2, 12 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[34] = new Point(7 * SQUARE_SIZE - SQUARE_SIZE / 2, 11 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[35] = new Point(7 * SQUARE_SIZE - SQUARE_SIZE / 2, 10 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[36] = new Point(7 * SQUARE_SIZE - SQUARE_SIZE / 2, 9 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[37] = new Point(6 * SQUARE_SIZE - SQUARE_SIZE / 2, 9 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[38] = new Point(5 * SQUARE_SIZE - SQUARE_SIZE / 2, 9 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[39] = new Point(4 * SQUARE_SIZE - SQUARE_SIZE / 2, 9 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[40] = new Point(3 * SQUARE_SIZE - SQUARE_SIZE / 2, 9 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[41] = new Point(2 * SQUARE_SIZE - SQUARE_SIZE / 2, 9 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[42] = new Point(1 * SQUARE_SIZE - SQUARE_SIZE / 2, 9 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[43] = new Point(1 * SQUARE_SIZE - SQUARE_SIZE / 2, 8 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[44] = new Point(1 * SQUARE_SIZE - SQUARE_SIZE / 2, 7 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[45] = new Point(2 * SQUARE_SIZE - SQUARE_SIZE / 2, 7 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[46] = new Point(3 * SQUARE_SIZE - SQUARE_SIZE / 2, 7 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[47] = new Point(4 * SQUARE_SIZE - SQUARE_SIZE / 2, 7 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[48] = new Point(5 * SQUARE_SIZE - SQUARE_SIZE / 2, 7 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[49] = new Point(6 * SQUARE_SIZE - SQUARE_SIZE / 2, 7 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[50] = new Point(7 * SQUARE_SIZE - SQUARE_SIZE / 2, 7 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[51] = new Point(7 * SQUARE_SIZE - SQUARE_SIZE / 2, 6 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[52] = new Point(7 * SQUARE_SIZE - SQUARE_SIZE / 2, 5 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[53] = new Point(7 * SQUARE_SIZE - SQUARE_SIZE / 2, 4 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[54] = new Point(7 * SQUARE_SIZE - SQUARE_SIZE / 2, 3 * SQUARE_SIZE - SQUARE_SIZE / 2);
        coordinates[55] = new Point(7 * SQUARE_SIZE - SQUARE_SIZE / 2, 2 * SQUARE_SIZE - SQUARE_SIZE / 2);
        return coordinates;
    }





}
