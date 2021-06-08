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

    private String direction = "NULL";

    public boolean movement = false;

    private final BufferedImage[] images;

    public Character()
    {
        body = new ArrayList<>(); // creates an array of rectangles instance
        images = new BufferedImage[2];

        SetImage("character_head.png", 0);
        SetImage("character_body.png", 1);

        CreateBody();
    }

    private void SetImage(String file, int index)
    {
        try { images[index] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/" + file))); }
        catch (IOException exception) { exception.printStackTrace(); }
    }

    private AffineTransform Rotate(Rectangle rectangle)
    {
        String direction = Input.key;

        AffineTransform transform = images[0].createGraphics().getTransform();

        double rotation = 0;
        int width = images[0].getWidth() / 2;
        int height = images[0].getHeight() / 2;

        switch (direction)
        {
            case "UP" -> rotation = 0;
            case "DOWN" -> rotation = 180;
            case "LEFT" -> rotation = -90;
            case "RIGHT" -> rotation = 90;
        }

        transform.setToTranslation(rectangle.x, rectangle.y);
        transform.rotate(Math.toRadians(rotation), width, height);

        return transform;
    }

    public void Draw(Graphics2D graphics2D)
    {
        int index = 0;
        for (Rectangle rectangle : body)
        {
            if (index == 0) {  graphics2D.drawImage(images[0], Rotate(rectangle), null); index++; }
            else { graphics2D.drawImage(images[1], rectangle.x, rectangle.y, rectangle.width, rectangle.height, null); }
        }
    }

    public void Input()
    {
        String input = Input.key;

        if (input.matches("DOWN|UP|RIGHT|LEFT")) { movement = true; } // sets the movement to true if either of the (movement) input is registered

        // enhanced switch statement
        switch (input)
        {
            // sets the direction to the (movement) key
            case "UP" -> { if (!direction.equals("DOWN")) { direction = input; } }
            case "DOWN" -> { if (!direction.equals("UP")) { direction = input; } }
            case "LEFT" -> { if (!direction.equals("RIGHT")) { direction = input; } }
            case "RIGHT" -> { if (!direction.equals("LEFT")) { direction = input; } }
            case "NULL" -> direction = input;
        }
    }

    public void Movement()
    {
        Rectangle head = body.get(0); // gets the first rectangle (head) of the array (body)
        Rectangle rectangle = new Rectangle(Game.scale, Game.scale); // creates a rectangle with the scale of the game

        // enhanced switch statement
        switch (direction)
        {
            // sets the x and y of the rectangle with the x and the y of the other rectangle (head) and adding the scale of the game, with the given direction
            case "UP" -> rectangle.setLocation(head.x, head.y - Game.scale);
            case "DOWN" -> rectangle.setLocation(head.x, head.y + Game.scale);
            case "LEFT" -> rectangle.setLocation(head.x - Game.scale, head.y);
            case "RIGHT" -> rectangle.setLocation(head.x + Game.scale, head.y);
            case "NULL" -> rectangle.setLocation(head.x, head.y);
        }

        body.add(0, rectangle); // adds the rectangle to the first index of the array (body)
        body.remove(body.size() - 1); // removes the last rectangle of the array (body)
    }

    private void CreateBody()
    {
        int size = 4;

        // loops throughout the size in reverse
        for (int i = size; i > 0; --i)
        {
            Rectangle rectangle = new Rectangle(Game.scale, Game.scale); // creates a rectangle with the scale of the game
            rectangle.setLocation(Game.width / 2, (Game.height / 2 - (i * Game.scale))); // sets the location of the rectangle in the center of the screen with the scale of the game
            body.add(rectangle); // adds the rectangle to the array (body)
        }
    }

    public void IncrementSize()
    {
        Rectangle rectangle = new Rectangle(Game.scale, Game.scale); // creates a rectangle
        Rectangle last = body.get(body.size() - 1); // gets the last rectangle of the array of rectangles (body)
        rectangle.setLocation(last.x * Game.scale, last.y * Game.scale); // sets the location of the rectangle to x and y of the last rectangle and adding the scale of game
        body.add(rectangle); // adds the rectangle to the array of rectangles (body)
    }

    public void Reset()
    {
        body.clear(); // clears the array of rectangles
        CreateBody(); // creates a new array of rectangles

        movement = false; // sets the movement to false
        Input.key = "NULL"; // sets the key to 'NULL'
    }

    public ArrayList<Rectangle> GetBody() { return body; } // returns the array of rectangles (body)

    public int GetX() { return body.get(0).x; } // returns the x value of the first rectangle
    public int GetY() { return body.get(0).y; } // returns the y value of the first rectangle
}