import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MusicPlayer extends JFrame {
    private Clip clip;
    private FloatControl volumeControl;
    private boolean isPlaying = false;

    public MusicPlayer() {
        JButton playButton = new JButton("Play Music");
        playButton.addActionListener(e -> playMusic());

        JButton stopButton = new JButton("Stop Music");
        stopButton.addActionListener(e -> stopMusic());

        JButton decreaseVolumeButton = new JButton("Decrease Volume");
        decreaseVolumeButton.addActionListener(e -> decreaseVolume());

        JButton increaseVolumeButton = new JButton("Increase Volume");
        increaseVolumeButton.addActionListener(e -> increaseVolume());

        add(playButton);
        add(stopButton);
        add(decreaseVolumeButton);
        add(increaseVolumeButton);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public void playMusic() {
        if (!isPlaying) {
            try {
                File musicFile = new File("src/assets/music.wav");
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
                clip = AudioSystem.getClip();
                clip.open(audioStream);

                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

                clip.loop(Clip.LOOP_CONTINUOUSLY);

                clip.start();
                isPlaying = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            isPlaying = false;
        }
    }

    public void decreaseVolume() {
        if (volumeControl != null) {
            float currentVolume = volumeControl.getValue();
            float decreaseAmount = -10f;

            float newVolume = currentVolume + decreaseAmount;
            volumeControl.setValue(newVolume);
        }
    }

    public void increaseVolume() {
        if (volumeControl != null) {
            float currentVolume = volumeControl.getValue();
            float increaseAmount = 10f;

            float newVolume = currentVolume + increaseAmount;
            volumeControl.setValue(newVolume);
        }
    }
}
