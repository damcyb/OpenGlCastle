package entities;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Music {

    private String fileName;
    public static Clip clip = null;

    public Music(String fileName) {
        this.fileName = fileName;
    }

    public void playSound() throws Exception{
        if(clip != null && clip.isOpen()) clip.close();
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/music/"+fileName+".wav").getAbsoluteFile());
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-15f);
        clip.start();
    }

    public void stop(){
        clip.stop();
    }
}
