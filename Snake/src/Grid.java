import java.awt.Graphics;
import java.awt.Color;

public class Grid
{
    private final int x, y, width, height, scale;
    private final Color color;

    public Grid(int x, int y, int width, int height, int scale, Color color)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.color = color;
    }

    public void Draw(Graphics graphics)
    {
        graphics.setColor(color); // sets the grid color

        int rows = height / scale; // sets the amount of rows by dividing the height by the scale
        int columns = width / scale; // sets the amount of columns by dividing the width by the scale

        for(int r = 0; r< rows + 1; ++r) { graphics.drawLine(x, y + (r * scale), x + width, y + (r * scale)); } // creates rows of lines
        for(int c = 0; c < columns + 1; ++c) { graphics.drawLine(x + (c * scale), y,  x + (c * scale), y + height); } // creates columns of lines
    }
}
