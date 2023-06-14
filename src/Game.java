import javax.swing.JFrame;

public class Game {

    public static void main(String[] args) {
        new MusicPlayer();
        JFrame frame = new JFrame("Ludo Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Board());
        frame.pack();
        frame.setVisible(true);
    }
}
