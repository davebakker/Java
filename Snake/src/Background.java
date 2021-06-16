import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Background
{
    private final int rows, columns, size;
    private int index = 0;

    private final BufferedImage[] images;

    private final int[] grid = new int[]
    {
            15, 16, 16, 16, 16, 16, 16, 17,  8, 12,  6,  4,  8,  5,  4,  9, 10, 15, 16, 17,
            14, 22,  1,  1, 21,  1,  1, 18,  4,  4,  4,  9, 11, 11,  9,  4, 12, 14,  1, 18,
            13, 20, 20, 20, 20, 20, 20, 19,  8,  4,  6,  9,  4,  5,  4, 10, 10, 13, 20, 19,
            23, 24, 23, 24, 23, 24, 23, 24, 23, 24, 23, 24, 23, 24, 23, 24, 23, 24, 23, 24,
            15, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 17,
            14,  1,  3,  1,  2,  1,  1,  1,  1,  1,  2,  1,  1,  1,  1,  3,  1,  1,  1, 18,
            14,  1,  1,  1,  1,  1,  1,  2,  1,  1,  1,  1,  1,  1,  1,  2,  1,  1,  3, 18,
            14,  1,  1,  2,  2,  1,  1,  1,  2,  1,  2,  1,  1,  1,  2,  1,  1,  1,  1, 18,
            14,  1,  1,  3,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  2,  1, 18,
            14,  1,  1,  1,  1,  1,  3,  1,  1,  3,  1,  1,  3,  1,  1,  1,  1,  1,  1, 18,
            14,  1,  1,  1,  1,  1,  3,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 18,
            14,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  2,  2,  2, 18,
            14,  1,  1,  3,  1,  3,  1,  1,  1,  1,  1,  1,  1,  1,  1,  3,  1,  1,  1, 18,
            14,  1,  1,  1,  1,  1,  2,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 18,
            14,  1,  1,  1,  1,  1,  1,  1,  1,  2,  1,  1,  1,  1,  1,  1,  1,  1,  1, 18,
            14,  1,  1,  1,  1,  1,  1,  1,  1,  3,  1,  1,  1,  1,  1,  1,  1,  1,  1, 18,
            14,  3,  1,  1,  1,  1,  2,  1,  1,  1,  1,  3,  1,  1,  1,  2,  1,  1,  1, 18,
            14,  1,  1,  2,  1,  1,  3,  1,  1,  2,  1,  1,  1,  1,  1,  1,  1,  1,  1, 18,
            14,  1,  3,  1,  1,  2,  1,  1,  1,  1,  2,  1,  1,  1,  1,  1,  1,  1,  1, 18,
            13, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 19
    };

    public Background(JPanel panel)
    {
        Color background = new Color(129, 186, 68);
        int imageCount = 25;

        panel.setBackground(background);

        rows = Game.windowWidth / Game.scale;
        columns = Game.windowHeight / Game.scale;
        size = rows * columns - 1;

        images = new BufferedImage[imageCount];

        for (int i = 1; i < imageCount; ++i) { SetImage(images,"tile_" + i + ".png", i); }
    }

    // Gets an image by filename and adds it to the image array by index
    private void SetImage(BufferedImage[] array, String file, int index)
    {
        try { array[index] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/" + file))); }
        catch (IOException exception) { exception.printStackTrace(); }
    }

    // Draws the background images
    public void Draw(Graphics2D graphics2D)
    {
        for (int c = 0; c < columns; ++c) { for (int r = 0; r < rows; ++r)
        {
                graphics2D.drawImage(images[grid[index]], (r * Game.scale), (c * Game.scale), Game.scale, Game.scale, null);
                if (index == size) { index = 0; } else { index++; }
        } }
    }
}
