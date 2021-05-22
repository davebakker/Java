import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

import java.util.Random;

public class Collectible
{
    private final int x, y, width, height, scale;

    private Vector2 position;

    private final Color color;

    private final Character character;

    public Collectible(int x, int y, int width, int height, int scale, Color color, Character character)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.color = color;
        this.character = character;

        RandomLocation();
    }

    public void Draw(Graphics graphics)
    {
        graphics.setColor(color); // sets the color of the collectible
        graphics.fillRect(position.GetX(), position.GetY(), scale, scale); // fills the rectangle (collectible)
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

    public void Reset() { RandomLocation(); } // resets the collectible with a new random location

    public int GetX() { return position.GetX(); } // returns the x value
    public int GetY() { return position.GetY(); } // returns the y value
}
