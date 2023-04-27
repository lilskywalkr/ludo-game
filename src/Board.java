import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Board extends JPanel implements MouseListener {

    private final Image redPawn;
    private final Image bluePawn;
    private final Image greenPawn;
    private final Image yellowPawn;
    public Board () {
        addMouseListener(this);
        redPawn = Toolkit.getDefaultToolkit().getImage("./assets/redpawn.png");
        bluePawn = Toolkit.getDefaultToolkit().getImage("./assets/bluePawn.png");
        greenPawn = Toolkit.getDefaultToolkit().getImage("./assets/greenPawn.png");
        yellowPawn = Toolkit.getDefaultToolkit().getImage("./assets/yellowPawn.png");
    }
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.ORANGE);
        Rectangle rect = new Rectangle(50, 50, 100, 100);
        g2d.fill(rect);
        g2d.setColor(Color.BLACK);
        g2d.draw(rect);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Point p = e.getPoint();
        System.out.println("x: " + p.x + " y: " + p.y);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //do nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //do nothing
    }
}
