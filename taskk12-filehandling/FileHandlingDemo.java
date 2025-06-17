import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class FileHandlingDemo {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java FileHandlingDmo <file_path>");
            return;
        }

        String filePath = args[0];

        System.out.println("----------Using BufferedReader-------------");
        // Better for large files (line-by-line processing)
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = br.readLine()) != null){
                System.out.println(line); // Read Line by Line
            }
        }catch(IOException e){
            System.out.println("Error reading with BufferedReder: " + e.getMessage());
        }

        System.out.println("\n ---------Using Files.readAllLines ---------");
        // It's ideal for small to moderately-sized text files.
        try{
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            lines.forEach(System.out::println);
        }catch(IOException e){
            System.out.println("Error reading with Files: "+ e.getMessage());
        }

        System.out.println("\n----------- Using Scanner ----------");
        try(Scanner sc = new Scanner(new File(filePath))){
            while (sc.hasNextLine()){
                System.out.println(sc.nextLine()); // Flexible, handles tokenizing
            }
            } catch (FileNotFoundException e){
                System.out.println("Error reading with Scanner: " + e.getMessage());
        }
        
    }
}
