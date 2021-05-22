public class Vector2
{
    private int x;
    private int y;

    public Vector2()
    {
        x = 0;
        y = 0;
    }

    public Vector2(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int GetX() { return x; } // returns the x value
    public int GetY() { return y; } // returns the y value

    public void SetX(int x) { this.x = x; } // sets the x value
    public void SetY(int y) { this.y = y; } // sets the y value
}
