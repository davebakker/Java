import java.awt.Graphics2D;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Collectible
{
    private final Vector2 position;
    private final Character character;
    private BufferedImage image;

    private int collectibleType = 0;

    public Collectible(Character character)
    {
        position = new Vector2();

        this.character = character;

        SetType();
        SetImage();
        RandomLocation();
    }

    // Sets the type of collectible by a random number
    public void SetType()
    {
        Random random = new Random();
        collectibleType = random.nextInt(10 + 1) + 1;
    }

    // Sets the image by the type of collectible
    public void SetImage()
    {
        String file;

        switch (collectibleType)
        {
            case 1 | 2 -> file = "collectible_3.png";
            case 3 | 4 -> file = "collectible_2.png";
            default -> file = "collectible_1.png";
        }

        try { image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/" + file))); }
        catch (IOException exception) { exception.printStackTrace(); }
    }

    // Sets the score by the type of collectible
    public void SetScore()
    {
        switch (collectibleType)
        {
            case 1 | 2 -> { Game.IncrementScore(10); Panel.IncreaseDelay(); }
            case 3 | 4 -> Game.IncrementScore(5);
            default -> Game.IncrementScore(1);
        }
    }

    // Draws the collectible by a singular image
    public void Draw(Graphics2D graphics2D)
    {
        graphics2D.drawImage(image, position.GetX(), position.GetY(), Game.scale, Game.scale, null);
    }

    // Gets a random x and y value and checks for overlap with the character
    public void RandomLocation()
    {
        Random random = new Random();
        boolean overlap = true;

        // Continuously renews the
        while (overlap)
        {
            int width = Game.width;
            int height = Game.height;

            position.SetX(Game.x + random.nextInt(width / Game.scale) * Game.scale);
            position.SetY(Game.y + random.nextInt(height / Game.scale) * Game.scale);

            for (Rectangle rectangle : character.GetBody()) { if (position.GetX() != rectangle.x && position.GetY() != rectangle.y) { overlap = false; } }
        }
    }

    public void Reset() { RandomLocation(); Panel.ResetDelay(); }

    public int GetX() { return position.GetX(); }
    public int GetY() { return position.GetY(); }
}
