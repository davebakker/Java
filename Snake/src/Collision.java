public class Collision
{
    private final int x, y, width, height;
    private final Character character;
    private final Collectible collectible;

    public Collision(int x, int y, int width, int height, Character character, Collectible collectible)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.character = character;
        this.collectible = collectible;
    }

    public void CollisionCollectible()
    {
        // checks of the if the x and the y of the character are equal to the x and the y of the collectible
        if (character.GetX() == collectible.GetX() && character.GetY() == collectible.GetY())
        {
            character.IncrementSize(); // increments the size of the array of rectangle (body) of character
            collectible.RandomLocation(); // sets the collectible to a random location
            Game.IncrementScore(); // increments the score of the game
        }
    }

    public void CollisionBorder()
    {
        // sets the state of the game to 'DEFEAT' if the x and the y of the character get pass the x and the y of the width and height
        if (character.GetX() < x || character.GetX() >= (x + width) || character.GetY() < y || character.GetY() >= (y + height)) { Game.state = "DEFEAT"; }
    }

    public void CollisionSelf()
    {
        // loops through the array of rectangles (body) and sets the state of the game to 'DEFEAT' if the x and the y of the first rectangle (head) are equal to the x and y of the rest of rectangles
        for (int i = 1; i < character.GetBody().size(); ++i) { if (character.GetX() == character.GetBody().get(i).x && character.GetY() == character.GetBody().get(i).y) { Game.state = "DEFEAT"; } }
    }
}
