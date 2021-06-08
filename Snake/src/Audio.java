import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Audio
{
    private static final String directory = "audio";

    public static boolean enableAudio = true;

    public static Clip GetAudio(String filename)
    {
        File file = new File(directory + File.separator + filename);
        Clip clip = null;

        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException exception) { exception.printStackTrace(); }

        return clip;
    }

    public static void PlayAudio(Clip clip)
    {
        if (enableAudio)
        {
            clip.setFramePosition(0);
            clip.start();
        }
    }
}
