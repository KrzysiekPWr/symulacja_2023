package gradle;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class soundPlayer implements LineListener {

    boolean playCompleted;

    public void play(boolean turn_audio_on) {
        
        playCompleted = turn_audio_on;
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream("shooting-stars.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(is));

            AudioFormat format = audioStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);

            Clip audioClip = (Clip) AudioSystem.getLine(info);

            audioClip.addLineListener(this);

            audioClip.open(audioStream);

            if(turn_audio_on == true)audioClip.start();
            else { 
                audioClip.stop();
                audioClip.close();
            }

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

    /**
     * Listens to the START and STOP events of the audio line.
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();

        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");

        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
        }
    }
}
