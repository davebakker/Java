import javax.swing.JFrame;

public class Frame extends JFrame
{
    public Frame(String title, int operation, boolean resizeable, boolean visible)
    {
        this.add(new Panel());

        this.setTitle(title);
        this.setDefaultCloseOperation(operation); // sets the event for using the close (X) button
        this.setResizable(resizeable);
        this.pack(); // packs the components within the window, based on the component’s preferred sizes
        this.setVisible(visible);
        this.setLocationRelativeTo(null); // sets the window to appear centered to the screen
    }
}