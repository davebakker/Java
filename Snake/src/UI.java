import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;

public class UI
{
    private final String startText = "PRESS 'SPACE' TO START";
    private final String pauseText = "PAUSED";

    private final Color textColor = new Color(255, 255, 255, 255);

    private final Font textFont = new Font("SansSerif", Font.BOLD, 32);

    public void Interface(Graphics graphics)
    {
        graphics.setColor(textColor); // sets the text color
        graphics.setFont(textFont); // sets the text font

        FontMetrics metrics = graphics.getFontMetrics(graphics.getFont()); // creates a font metric from the font

        int xScore = 80 + (Game.scale - metrics.stringWidth(String.valueOf(Game.GetScore()))) / 2;
        int xHighScore = 200 + (Game.scale - metrics.stringWidth(String.valueOf(Game.GetHighScore()))) / 2;
        int y = 40 + ((Game.scale - metrics.getHeight()) / 2) + metrics.getAscent();

        // draws the text with the given parameters
        graphics.drawString(String.valueOf(Game.GetScore()), xScore, y);
        graphics.drawString(String.valueOf(Game.GetHighScore()), xHighScore, y);
    }

    public void TextField(Graphics graphics, String type)
    {
        graphics.setColor(textColor); // sets the text color
        graphics.setFont(textFont); // sets the text font

        FontMetrics metrics = graphics.getFontMetrics(graphics.getFont()); // creates a font metric from the font

        String text = "";
        int width = 0;

        // enhanced switch statement
        switch (type)
        {
            // sets the width with the text, with use of the font metric, and sets the text
            case "START" -> { width = metrics.stringWidth(startText); text = startText; }
            case "PAUSE" -> { width = metrics.stringWidth(pauseText); text = pauseText; }
        }

        // draws the text in the middle of the screen with the given text width
        graphics.drawString(text, ((Game.width - width) / 2), (Game.height / 2)); // draws the text
    }

    public void Reset()
    {
        Game.SetHighScore();
        Game.SetScore();
    }
}
