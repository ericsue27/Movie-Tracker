package ui.tools;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SaveLoadSuccessSound {

    File file = new File("./data/SuccessSound.wav");

    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (LineUnavailableException e) {
                System.out.println("Error. Sound already playing.");
            }
        } catch (UnsupportedAudioFileException e) {
            System.out.println("Error. Unsupported audio file.");
        } catch (IOException e) {
            System.out.println("Error playing sound.");
        }
    }

}
