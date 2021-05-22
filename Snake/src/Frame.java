import javax.swing.JFrame;

public class Frame extends JFrame
{
    public Frame(String title, int operation, boolean resizeable, boolean visible)
    {
        this.add(new Panel()); // creates a panel instance

        this.setTitle(title); // sets the window title
        this.setDefaultCloseOperation(operation); // sets the event for using the close (X) button
        this.setResizable(resizeable); // sets the ability to resize the window
        this.pack(); // packs the components within the window, based on the component’s preferred sizes
        this.setVisible(visible); // sets the window to be visible
        this.setLocationRelativeTo(null); // sets the window to appear centered to the screen
    }
}