import javax.swing.JFrame;

public class Game
{
    public static final int width = 800;
    public static final int height = 800;

    public static final int scale = 40;
    public static int delay = 140;

    public static String state = "START";

    private static int score = 0;
    private static int highScore = 0;

    private static final String save = "highscore.txt";

    public Game()
    {
        new Frame("Game", JFrame.EXIT_ON_CLOSE, false, true);  // constructs a frame instance

        ReadWrite readWrite = new ReadWrite();
        readWrite.CreateFile(save);
        highScore = ReadWrite.ReadScore(save);
    }

    public static int GetScore() { return score; } // returns the score
    public static int GetHighScore() { return highScore; } // returns the high score

    public static void SetScore() { score = 0; } // resets the score
    public static void SetHighScore()
    {
        // sets the high score if the score is higher then the high score
        if (score > highScore)
        {
            highScore = score;
            ReadWrite.WriteScore(save, highScore);
        }
    }

    public static void IncrementScore(int amount) { Game.score += amount; SetHighScore(); } // increments the score and sets the high score
}