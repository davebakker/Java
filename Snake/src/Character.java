import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics2D;

import java.util.ArrayList;

public class Character
{
    private final ArrayList<Rectangle> body;

    private final Color color;

    private String direction = "NULL";

    public boolean movement = false;

    public Character(Color color)
    {
        this.color = color; // sets the color
        body = new ArrayList<>(); // creates an array of rectangles instance
        CreateBody();
    }

    public void Draw(Graphics2D graphics)
    {
        graphics.setColor(color); // sets the color of the rectangle
        for (Rectangle rectangle : body) { graphics.fill(rectangle); } // fills each rectangle of the array of rectangles (body)
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