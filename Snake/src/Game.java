import javax.swing.JFrame;

public class Game
{
    public static final int x = 40;
    public static final int y = 200;
    public static final int width = 720;
    public static final int height = 560;
    public static final int windowWidth = 800;
    public static final int windowHeight = 800;

    public static final int scale = 40;

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

    public static int GetScore() { return score; }
    public static int GetHighScore() { return highScore; }

    public static void ResetScore() { score = 0; }
    public static void SetHighScore()
    {
        if (score > highScore)
        {
            highScore = score;
            ReadWrite.WriteScore(save, highScore);
        }
    }

    public static void IncrementScore(int amount) { score += amount; SetHighScore(); }
}