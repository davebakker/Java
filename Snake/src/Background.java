import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Background
{
    private final int rows, columns, scale, gridSize;
    private int gridIndex;

    private final BufferedImage[] images;

    private final int[] grid = new int[]
            {
                     4, 12,  4,  9, 12, 12,  4,  6, 11,  4,  6,  9,  8,  5,  4, 10, 10,  8, 12,  4,
                     8, 22,  7,  6, 21,  7,  6,  5,  8,  4,  4,  9, 11, 11,  9,  4, 12,  8, 10,  4,
                    23, 24, 23, 24, 23, 24, 23, 24, 23, 24, 23, 24, 23, 24, 23, 24, 23, 24, 23, 24,
                    15, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 17,
                    14,  1,  1,  1,  1,  3,  2,  1,  1,  1,  1,  1,  1,  1,  2,  1,  1,  1,  1, 18,
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

    public Background(int height, int width, int scale, JPanel panel)
    {
        panel.setBackground(new Color(129, 186, 68)); // sets the background to a color

        this.scale = scale;
        rows = height / scale; // sets the amount of rows by dividing the height by the scale
        columns = width / scale; // sets the amount of columns by dividing the width by the scale
        gridSize = rows * columns - 1;
        gridIndex = 0;

        int imageCount = 24 + 1;

        images = new BufferedImage[imageCount];

        for (int i = 1; i < imageCount; ++i) { SetImage(images,"tile_" + i + ".png", i); }
    }

    private void SetImage(BufferedImage[] array, String file, int index)
    {
        try { array[index] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/" + file))); }
        catch (IOException exception) { exception.printStackTrace(); }
    }

    public void Draw(Graphics2D graphics2D)
    {
        for (int c = 0; c < columns; ++c) { for (int r = 0; r < rows; ++r)
        {
                graphics2D.drawImage(images[grid[gridIndex]], (r * scale), (c * scale), scale, scale, null); // draws an image by x, y, width, height
                if (gridIndex == gridSize) { gridIndex = 0; } else { gridIndex++; } // resets the the grid index if the index equals the size of the grid, otherwise increments the index
        } }
    }
}
