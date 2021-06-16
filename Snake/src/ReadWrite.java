import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ReadWrite
{
    private final static String directory = "text";

    public ReadWrite() { CreateDirectory(); }

    // Creates a directory
    private void CreateDirectory()
    {
        Path path = Paths.get(directory);
        if (!Files.exists(path))
        {
            File file = new File(directory);
            if (file.mkdir()) { System.out.println("Successfully created the folder '" + directory + "'"); }
            else { System.out.println("ERROR: failed to create the folder '" + directory + "'"); }
        }
        else { System.out.println("(!) failed to create the folder '" + directory+ "', folder already exists"); }
    }

    // Creates a file (.txt) inside the directory
    public void CreateFile(String filename)
    {
        Path path = Paths.get(directory);
        if (Files.exists(path))
        {
            try
            {
                File file = new File(directory + File.separator + filename);
                boolean fileCreation = false;
                if (!file.exists()) { fileCreation = file.createNewFile(); WriteScore(filename, 0); }
                else System.out.println("(!) failed to create the file '" + filename + "', file already exists");
                if (fileCreation)  { System.out.println("Successfully created the file '" + filename + "'"); }
            }
            catch (IOException exception) { exception.printStackTrace(); }
        }
        else { System.out.println("ERROR: failed to create the file, directory doesn't exist"); }
    }

    // Reads and returns the score of the file (.txt)
    public static int ReadScore(String filename)
    {
        Scanner scanner = null;
        String data;

        try
        {
            File file = new File(directory + File.separator + filename);
            scanner = new Scanner(file);
        }
        catch (FileNotFoundException exception) { exception.printStackTrace(); }

        assert scanner != null;
        data = scanner.nextLine();
        return Integer.parseInt(data);
    }

    // Writes the score in the file (.txt)
    public static void WriteScore(String filename, int score)
    {
        try
        {
            FileWriter writer = new FileWriter(directory + File.separator + filename);
            writer.write(String.valueOf(score));
            writer.close();
        }
        catch (IOException exception) { exception.printStackTrace(); }
    }
}
