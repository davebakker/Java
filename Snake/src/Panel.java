import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics2D;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements ActionListener
{
    private static int delay = 140;

    private final Background background;
    private final Character character;
    private final Collectible collectible;
    private final Collision collision;
    private final UI ui;
    private final Grid grid;

    private static Timer timer;
    private static Clip startClip;

    public static boolean enableGrid = false;

    public Panel()
    {
        background = new Background(this);
        character = new Character();
        collectible = new Collectible(character);
        collision = new Collision(character, collectible);
        ui = new UI();
        grid = new Grid();

        startClip = Audio.GetAudio("Start.wav");

        this.setPreferredSize(new Dimension(Game.windowWidth, Game.windowHeight)); // sets the preferred size of the panel's width and height
        this.setFocusable(true); // allows accessibility for focus (e.g. of the key event)
        this.addKeyListener(new Input());

        SetTimer();
    }

    private void SetTimer()
    {
        timer = new Timer(delay, this);
        timer.start();
    }

    public static void IncreaseDelay() { if (delay > 80) { timer.setDelay(delay -= 15); } }

    public static void ResetDelay()
    {
        delay = 140;
        timer.setDelay(delay);
    }

    private void CheckCollision()
    {
        collision.CollisionCollectible();
        collision.CollisionBorder();
        collision.CollisionCharacter();
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
        Graphics2D graphics2D = (Graphics2D)graphics;

        switch (Game.state)
        {
            case "START" -> ui.TextField(graphics, "START");
            case "RUN" -> {
                background.Draw(graphics2D);
                character.Draw(graphics2D);
                collectible.Draw(graphics2D);
                ui.Interface(graphics);

                if (enableGrid) { grid.Draw(graphics); }

                character.Input();
                character.Movement();
            }
            case "PAUSE" -> ui.TextField(graphics, "PAUSE");
            case "DEFEAT" -> Reset();
        }
    }

    @Override
    public void paintComponent(java.awt.Graphics graphics)
    {
        super.paintComponent(graphics); // allows this method do have its own behaviour but utilize in the original class
        Draw(graphics);
    }

    @Override
    public void actionPerformed(ActionEvent event) // invoked automatically when a registered component gets clicked
    {
        CheckCollision();
        repaint();
    }
}
