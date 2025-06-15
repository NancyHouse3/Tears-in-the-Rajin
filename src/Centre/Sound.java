package Centre;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    URL soundURL[] =  new URL[30];

    public Sound() {

        soundURL[0] = getClass().getResource("/sound/somber_guitar.wav");
        soundURL[1] = getClass().getResource("/sound/item.wav");
        soundURL[2] = getClass().getResource("/sound/coke.wav");
        soundURL[3] = getClass().getResource("/sound/Kurt.wav");
        soundURL[4] = getClass().getResource("/sound/spawn.wav");
    }

    public void setFile(int i) {

        try {

            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip  = AudioSystem.getClip();
            clip.open(ais);

        }catch (Exception e) {
            System.err.println("failed to set song");
        }

    }
    public void play() {

        clip.start();
    }
    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {

        clip.stop();
    }
}
