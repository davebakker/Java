import java.awt.Graphics;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;

public class UI
{
    String startText = "PRESS 'SPACE' TO START";
    String pauseText = "PAUSED";
    String scoreText = "SCORE: ";
    String highScoreText = "HIGH SCORE: ";

    Color textColor = new Color(0, 0, 0);

    Font textFont = new Font("SansSerif", Font.BOLD, 32);

    public void Interface(Graphics graphics)
    {
        graphics.setColor(textColor); // sets the text color
        graphics.setFont(textFont); // sets the text font

        FontMetrics metrics = graphics.getFontMetrics(graphics.getFont()); // creates a font metric from the font

        // creates a width with the text and the size of the score, with use of the font metric
        int scoreWidth = metrics.stringWidth(scoreText + Game.GetScore());
        int highScoreWidth = metrics.stringWidth(highScoreText+ Game.GetHighScore());

        int offset = 25;

        // draws the text with the given parameters
        graphics.drawString(scoreText + Game.GetScore(), ((Game.width - scoreWidth) / 2) - (highScoreWidth / 2) - offset, graphics.getFont().getSize() + offset);
        graphics.drawString(highScoreText + Game.GetHighScore(), ((Game.width - highScoreWidth) / 2) + (scoreWidth / 2) + offset, graphics.getFont().getSize() + offset);
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
