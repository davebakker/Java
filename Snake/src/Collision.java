import javax.sound.sampled.Clip;

public class Collision
{
    private final int x, y, width, height;
    private final Character character;
    private final Collectible collectible;

    private Clip collectClip;
    private Clip defeatedClip;

    public Collision(int x, int y, int width, int height, Character character, Collectible collectible)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.character = character;
        this.collectible = collectible;

        collectClip = Audio.GetAudio("Collect.wav");
        defeatedClip = Audio.GetAudio("Defeated.wav");
    }

    public void CollisionCollectible()
    {
        // checks of the if the x and the y of the character are equal to the x and the y of the collectible
        if (character.GetX() == collectible.GetX() && character.GetY() == collectible.GetY())
        {
            character.IncrementSize();
            collectible.SetScore();
            collectible.SetType();
            collectible.SetImage();
            collectible.RandomLocation();
            Audio.PlayAudio(collectClip);
        }
    }

    public void CollisionBorder()
    {
        // sets the state of the game to 'DEFEAT' if the x and the y of the character get pass the x and the y of the width and height
        if (character.GetX() < x || character.GetX() >= (x + width) || character.GetY() < y || character.GetY() >= (y + height))
        {
            Game.state = "DEFEAT";
            Audio.PlayAudio(defeatedClip);
        }
    }

    public void CollisionSelf()
    {
        // loops through the array of rectangles (body)
        for (int i = 1; i < character.GetBody().size(); ++i)
        {
            // checks if the x and the y of the first rectangle (head) are equal to the x and y of the rest of rectangles
            if (character.GetX() == character.GetBody().get(i).x && character.GetY() == character.GetBody().get(i).y)
            {
                Game.state = "DEFEAT";
                Audio.PlayAudio(defeatedClip);
            }
        }
    }
}
