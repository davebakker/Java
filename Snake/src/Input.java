import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Input extends KeyAdapter implements KeyListener
{
    public static String key = "NULL";

    @Override // override annotation
    public void keyPressed(KeyEvent event)
    {
        // enhanced switch statement
        switch (event.getKeyCode()) // gets the key code of the key event
        {
            // sets the (movement) key to registered key event
            case KeyEvent.VK_UP, KeyEvent.VK_W  -> key = "UP";
            case KeyEvent.VK_DOWN, KeyEvent.VK_S  -> key = "DOWN";
            case KeyEvent.VK_LEFT, KeyEvent.VK_A -> key = "LEFT";
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> key = "RIGHT";

            case KeyEvent.VK_SPACE -> {
                if (Game.state.equals("PAUSE")) { Game.state = "RUN"; } else if (Game.state.equals("RUN")) { Game.state = "PAUSE"; } // pauses the game (toggle)
                if (Game.state.equals("START")) { Game.state = "RUN"; Panel.StartAudio(); } // starts the game
            }

            case KeyEvent.VK_G -> Panel.enableGrid = !Panel.enableGrid; // toggles between displaying the grid
            case KeyEvent.VK_M -> Audio.enableAudio = !Audio.enableAudio; // toggles between muting the audio

            case KeyEvent.VK_ESCAPE -> System.exit(0); // exits the application (exit success)
        }
    }
}
