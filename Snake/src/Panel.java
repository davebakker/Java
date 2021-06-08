import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements ActionListener
{
    private final Background background;
    private final Character character;
    private final Collectible collectible;
    private final Collision collision;
    private final UI ui;
    private final Grid grid;

    private static Clip startClip;

    public static boolean enableGrid = false;

    public Panel()
    {
        int x = 40;
        int y = 160;
        int width = 720;
        int height = 600;

        Vector2 vector = new Vector2(x, y);

        background = new Background(Game.height, Game.width, Game.scale, this);
        character = new Character();
        collectible = new Collectible(vector.GetX(), vector.GetY(), width, height, Game.scale, character);
        collision = new Collision(vector.GetX(), vector.GetY(), width, height, character, collectible);
        ui = new UI();
        grid = new Grid(x, y, width, height, Game.scale, Color.black);

        startClip = Audio.GetAudio("Start.wav");

        this.setPreferredSize(new Dimension(Game.width, Game.height)); // sets the preferred size of panel's width and height
        this.setFocusable(true); // allows accessibility for focus (e.g. of the key event)
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
        character.Reset();
        collectible.Reset();
        ui.Reset();

        Game.state = "RUN";
    }

    public static void StartAudio() { Audio.PlayAudio(startClip); }

    private void Draw(java.awt.Graphics graphics)
    {
        Graphics2D graphics2D = (Graphics2D)graphics; // converts the graphics to graphics2D

        // enhanced switch statement
        switch (Game.state)
        {
            case "START" -> ui.TextField(graphics, "START"); // displays a start screen
            case "RUN" -> {
                background.Draw(graphics2D);
                character.Draw(graphics2D);
                collectible.Draw(graphics2D);
                ui.Interface(graphics);

                if (enableGrid) { grid.Draw(graphics); }

                character.Input();

                if(character.movement) { character.Movement(); }
            }
            case "PAUSE" -> ui.TextField(graphics, "PAUSE");
            case "DEFEAT" -> Reset();
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
