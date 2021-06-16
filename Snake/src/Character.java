import javax.imageio.ImageIO;

import java.awt.Rectangle;
import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Character
{
    private final ArrayList<Rectangle> body;
    private final BufferedImage[] images;

    private String direction = "NULL";

    public boolean movement = false;

    public Character()
    {
        body = new ArrayList<>();
        images = new BufferedImage[2];

        SetImage("character_head.png", 0);
        SetImage("character_body.png", 1);

        CreateBody();
    }

    // Gets an image by filename and adds it to the image array by index
    private void SetImage(String file, int index)
    {
        try { images[index] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/" + file))); }
        catch (IOException exception) { exception.printStackTrace(); }
    }

    // Creates a body of rectangles by size (with placement), in reverse
    private void CreateBody()
    {
        int size = 4;

        for (int i = size; i > 0; --i)
        {
            Rectangle rectangle = new Rectangle(Game.scale, Game.scale);

            rectangle.setLocation((Game.width / 2) + Game.x, (Game.height / 2 - (i * Game.scale) + Game.y));

            body.add(rectangle);
        }
    }

    // Returns a transform that has a rotation which is determined by key input
    private AffineTransform TransformHead(Rectangle rectangle)
    {
        AffineTransform transform = new AffineTransform();
        double rotation = 0;

        if (movement)
        {
            switch (direction)
            {
                case "UP", "NULL" -> rotation = 0;
                case "DOWN" -> rotation = 180;
                case "LEFT" -> rotation = -90;
                case "RIGHT" -> rotation = 90;
                default -> throw new IllegalStateException("Unexpected value: " + direction);
            }
        }

        int center = Game.scale / 2;

        transform.setToTranslation(rectangle.x, rectangle.y);
        transform.rotate(Math.toRadians(rotation), center, center);

        return transform;
    }

    // Sets the direction to the key input
    public void Input()
    {
        String input = Input.key;

        switch (input)
        {
            case "UP" -> { if (!direction.equals("DOWN")) { direction = input; } }
            case "DOWN" -> { if (!direction.equals("UP") && movement) { direction = input; } }
            case "LEFT" -> { if (!direction.equals("RIGHT")) { direction = input; } }
            case "RIGHT" -> { if (!direction.equals("LEFT")) { direction = input; } }
            case "NULL" -> direction = input;
        }
    }

    // Movement which is displayed creating a new rectangle, transforming this with the values of the first rectangle (0) in combination with the direction - adding this to the array and removing the last rectangle
    public void Movement()
    {
        Rectangle rectangle = new Rectangle(Game.scale, Game.scale);
        Rectangle head = body.get(0);

        if (!movement && direction.matches("UP|RIGHT|LEFT")) { movement = true; } // adding "DOWN" to the regex would allow the character to self-collide

        if (movement)
        {
            switch (direction)
            {
                case "UP" -> rectangle.setLocation(head.x, head.y - Game.scale);
                case "DOWN" -> rectangle.setLocation(head.x, head.y + Game.scale);
                case "LEFT" -> rectangle.setLocation(head.x - Game.scale, head.y);
                case "RIGHT" -> rectangle.setLocation(head.x + Game.scale, head.y);
            }

            body.add(0, rectangle);
            body.remove(body.size() - 1);
        }
    }

    // Increments the size of the character by creating a new rectangle, transforming this with the values of the last rectangle in combination with game's scale, and adding this to the array
    public void IncrementSize()
    {
        Rectangle rectangle = new Rectangle(Game.scale, Game.scale);
        Rectangle last = body.get(body.size() - 1);
        rectangle.setLocation(last.x * Game.scale, last.y * Game.scale);
        body.add(rectangle);
    }

    // Resets the character and input/movement
    public void Reset()
    {
        body.clear();
        CreateBody();

        movement = false;
        Input.key = "NULL";
    }

    // Draws the character by each individual rectangle to image, and in case of the first rectangle it display a different image
    public void Draw(Graphics2D graphics2D)
    {
        int index = 0;
        for (Rectangle rectangle : body)
        {
            if (index == 0)
            {
                graphics2D.drawImage(images[0], TransformHead(rectangle), null);
                index = 1;
            }
            else { graphics2D.drawImage(images[1], rectangle.x, rectangle.y, rectangle.width, rectangle.height, null); }
        }
    }

    public ArrayList<Rectangle> GetBody() { return body; }

    public int GetX() { return body.get(0).x; }
    public int GetY() { return body.get(0).y; }
}