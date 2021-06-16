import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;

import java.io.File;
import java.io.IOException;

public class Audio
{
    private static final String directory = "audio";

    public static boolean enableAudio = true;

    // Returns a clip with an audio file (.wav) which is sourced from a directory
    public static Clip GetAudio(String filename)
    {
        File file = new File(directory + File.separator + filename);
        Clip clip = null;

        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream); // enables the clip to acquire any required system resources and become operational
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) { exception.printStackTrace(); }

        return clip;
    }

    // Plays a clip
    public static void PlayAudio(Clip clip)
    {
        if (enableAudio)
        {
            clip.setFramePosition(0); // 'resets' the clip
            clip.start();
        }
    }
}