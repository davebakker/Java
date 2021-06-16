import java.awt.Graphics;
import java.awt.Color;

public class Grid
{
    private final int rows, columns;
    private final Color color;

    public Grid()
    {
        color = new Color(0, 0, 0);

        rows = Game.height / Game.scale;
        columns = Game.width / Game.scale;
    }

    // Draws the lines which are displayed as a grid
    public void Draw(Graphics graphics)
    {
        graphics.setColor(color);

        for(int r = 0; r< rows + 1; ++r) { graphics.drawLine(Game.x, Game.y + (r * Game.scale), Game.x + Game.width, Game.y + (r * Game.scale)); }
        for(int c = 0; c < columns + 1; ++c) { graphics.drawLine(Game.x + (c * Game.scale), Game.y,  Game.x + (c * Game.scale), Game.y + Game.height); }
    }
}
