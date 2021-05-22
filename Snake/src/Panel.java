import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements ActionListener
{
    boolean focusable = true;

    Grid grid;
    Character character;
    Collectible collectible;
    Collision collision;
    UI ui;

    public Panel()
    {
        Vector2 vector = new Vector2(40, 80);

        int width = 720;
        int height = 680;

        final Color backgroundColor = new Color(170, 215, 81);
        final Color gridColor = new Color(0, 0, 0);
        final Color characterColor = new Color(71, 117, 235);
        final Color collectibleColor = new Color(231, 71, 29);

        // creates a grid, character, collectible, collision and ui instance
        grid = new Grid(vector.GetX(), vector.GetY(), width, height, Game.scale, gridColor);
        character = new Character(characterColor);
        collectible = new Collectible(vector.GetX(), vector.GetY(), width, height, Game.scale, collectibleColor, character);
        collision = new Collision(vector.GetX(), vector.GetY(), width, height, character, collectible);
        ui = new UI();

        this.setPreferredSize(new Dimension(Game.width, Game.height)); // sets the preferred size of panel's width and height
        this.setBackground(backgroundColor); // sets the background to a color
        this.setFocusable(focusable); // allows accessibility for focus (e.g. of the key event)
        this.addKeyListener(new Input()); // binds an input instance to the key listener

        SetTimer();
    }

    private void SetTimer()
    {
        Timer timer = new Timer(Game.delay, this); // creates a named timer instance
        timer.start(); // starts the timer
    }

    private void CheckCollision()
    {
        // checks the collision of the character, collectible, and border
        collision.CollisionCollectible();
        collision.CollisionBorder();
        collision.CollisionSelf();
    }

    private void Reset()
    {
        // resets the character, collectible and ui
        character.Reset();
        collectible.Reset();
        ui.Reset();

        Game.state = "RUN";
    }

    private void Draw(java.awt.Graphics graphics)
    {
        // enhanced switch statement
        switch (Game.state)
        {
            case "START" -> ui.TextField(graphics, "START"); // displays a start screen
            case "RUN" -> {
                // draws the instances
                grid.Draw(graphics);
                character.Draw((Graphics2D)graphics);
                collectible.Draw(graphics);
                ui.Interface(graphics);

                // gets the character input
                character.Input();

                // enables movement if the characters movement is true
                if(character.movement) { character.Movement(); }
            }
            case "PAUSE" -> ui.TextField(graphics, "PAUSE"); // display a pause screen
            case "DEFEAT" -> Reset(); // resets the application
        }
    }

    @Override // override annotation
    public void paintComponent(java.awt.Graphics graphics)
    {
        super.paintComponent(graphics); // allows this method do have its own behaviour but utilize in the original class
        Draw(graphics);
    }

    @Override // override annotation
    public void actionPerformed(ActionEvent event) // invoked automatically when a registered component gets clicked
    {
        CheckCollision();
        repaint();
    }
}
