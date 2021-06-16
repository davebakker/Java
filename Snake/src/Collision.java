import javax.sound.sampled.Clip;

public class Collision
{
    private final Character character;
    private final Collectible collectible;

    private final Clip collectClip;
    private final Clip defeatedClip;

    public Collision(Character character, Collectible collectible)
    {
        this.character = character;
        this.collectible = collectible;

        collectClip = Audio.GetAudio("Collect.wav");
        defeatedClip = Audio.GetAudio("Defeated.wav");
    }

    // Checks for the collision with the collectible
    public void CollisionCollectible()
    {
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

    // Checks for collision with the border of the playing area
    public void CollisionBorder()
    {
        int width = Game.width;
        int height = Game.height;

        if (character.GetX() < Game.x || character.GetX() >= (Game.x + width) || character.GetY() < Game.y || character.GetY() >= (Game.y + height))
        {
            Game.state = "DEFEAT";
            Audio.PlayAudio(defeatedClip);
        }
    }

    // Checks for collision with the character itself
    public void CollisionCharacter()
    {
        for (int i = 1; i < character.GetBody().size(); ++i)
        {
            if (character.GetX() == character.GetBody().get(i).x && character.GetY() == character.GetBody().get(i).y)
            {
                Game.state = "DEFEAT";
                Audio.PlayAudio(defeatedClip);
            }
        }
    }
}
