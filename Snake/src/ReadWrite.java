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
    // DO A GET FILE FIRST BEFORE READING AND WRITING
    // STATIC FUNCTIONS DONT MAKE SENSE, NEED TO CREATE A DIRECTORY FIRST BEFORE READING/WRITING A FILE (BECAUSE THE FILE IS INSIDE THE DIR.)

    private static final String directory = "text";

    public ReadWrite() { CreateDirectory(directory); }

    private void CreateDirectory(String folder)
    {
        Path path = Paths.get(folder);
        if (!Files.exists(path))
        {
            File file = new File(folder);
            if (file.mkdir()) { System.out.println("Successfully created the folder '" + folder + "'"); }
            else { System.out.println("ERROR: failed to create the folder '" + folder + "'"); }
        }
        else { System.out.println("ERROR: failed to create the folder '" + folder + "', folder already exists"); }
    }

    public void CreateFile(String filename)
    {
        Path path = Paths.get(directory);
        if (Files.exists(path))
        {
            try
            {
                File file = new File(directory + File.separator + filename);
                if (!file.exists()) { file.createNewFile(); WriteScore(filename, 0); }
                else System.out.println("ERROR: failed to create the file '" + filename + "', file already exists");
            }
            catch (IOException exception) { exception.printStackTrace(); }
        }
        else { System.out.println("ERROR: failed to create the file, directory doesn't exist"); }
    }

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

        data = scanner.nextLine();
        return Integer.parseInt(data);
    }

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
