package gradle;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class soundPlayer {

    private Clip audioClip;
    private DataLine.Info info;
    private AudioFormat format;
    private InputStream is;
    private AudioInputStream audioStream;

    soundPlayer() {
         try {
            is = getClass().getClassLoader().getResourceAsStream("shooting-stars.wav");
            audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(is));

            format = audioStream.getFormat();

            info = new DataLine.Info(Clip.class, format);

            audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.open(audioStream);
                    
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
    }


    public void play(int turn_audio_on) {
        
        if(turn_audio_on == 1) {
                audioClip.start();
                audioClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        else if (turn_audio_on == 0){ 
            audioClip.stop();
            audioClip.close();
        }
        else if (turn_audio_on == 2) {
            audioClip.stop();
        }
       
    }

    
}
