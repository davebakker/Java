import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Collectible
{
    private final int x, y, width, height, scale;

    private Vector2 position;

    private final Character character;

    private BufferedImage image;

    private boolean specialCollectible = true;

    public Collectible(int x, int y, int width, int height, int scale, Character character)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.character = character;

        SetType();
        SetImage();
        RandomLocation();
    }

    public void SetType()
    {
        Random random = new Random();
        int value = random.nextInt(10 + 1) + 1;

        if (value <= 1) { specialCollectible = true; }
        else {  specialCollectible = false; }
    }

    public void SetImage()
    {
        String file = "collectible_1.png";
        if (specialCollectible) { file = "collectible_2.png"; }

        try { image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/" + file))); }
        catch (IOException exception) { exception.printStackTrace(); }
    }

    public void Draw(Graphics2D graphics2D)
    {
        graphics2D.drawImage(image, position.GetX(), position.GetY(), scale, scale, null); // draws the image
    }

    public void RandomLocation()
    {
        Random random = new Random(); // creates a named random instance
        boolean overlap = true;

        while (overlap)
        {
            // sets the position (x and y) to a random location with the width, height and scale of the game
            position = new Vector2(x + random.nextInt(width / scale) * scale, y + random.nextInt(height / scale) * scale);

            // gets the individual rectangles of the array of rectangles (body)
            for (Rectangle rectangle : character.GetBody())
            {
                // sets overlap to true if the x and the y of the rectangle equals to the x and the y of the collectible
                if (position.GetX() == rectangle.x && position.GetY() == rectangle.y) { overlap = true; } else { overlap = false; break; }
            }
        }
    }

    public void SetScore()
    {
        int amount = 1;
        if (specialCollectible) { amount = 5; }

        Game.IncrementScore(amount);
    }

    public void Reset() { RandomLocation(); } // resets the collectible with a new random location

    public int GetX() { return position.GetX(); } // returns the x value
    public int GetY() { return position.GetY(); } // returns the y value
}
