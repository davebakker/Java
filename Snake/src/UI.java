import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UI
{
    private final Color textColor = new Color(255, 255, 255, 255);
    private final Font textFont = new Font("SansSerif", Font.BOLD, 32);

    private BufferedImage image;

    // Sets the image
    private void SetImage(String file)
    {
        try { image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/" + file))); }
        catch (IOException exception) { exception.printStackTrace(); }
    }

    // Displays an interface by string (score, high score) and image (audio toggle)
    public void Interface(Graphics graphics)
    {
        graphics.setColor(textColor);
        graphics.setFont(textFont);

        FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());

        int xScore = 100 + (Game.scale - metrics.stringWidth(String.valueOf(Game.GetScore()))) / 2;
        int xHighScore = 220 + (Game.scale - metrics.stringWidth(String.valueOf(Game.GetHighScore()))) / 2;
        int y = 40 + ((Game.scale - metrics.getHeight()) / 2) + metrics.getAscent();

        graphics.drawString(String.valueOf(Game.GetScore()), xScore, y);
        graphics.drawString(String.valueOf(Game.GetHighScore()), xHighScore, y);

        if (Audio.enableAudio) { SetImage("audio_1.png"); }
        else { SetImage("audio_2.png"); }

        graphics.drawImage(image, 720, 40, Game.scale, Game.scale, null);
    }

    // Displays the text fields by string (start, pause)
    public void TextField(Graphics graphics, String type)
    {
        graphics.setColor(textColor);
        graphics.setFont(textFont);

        FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());

        String text = "";
        int width = 0;

        final String startText = "PRESS 'SPACE' TO START";
        final String pauseText = "PAUSED";

        switch (type)
        {
            case "START" -> { width = metrics.stringWidth(startText); text = startText; }
            case "PAUSE" -> { width = metrics.stringWidth(pauseText); text = pauseText; }
        }

        graphics.drawString(text, ((Game.windowWidth - width) / 2), (Game.windowHeight / 2));
    }

    public void Reset()
    {
        Game.SetHighScore();
        Game.ResetScore();
    }
}
