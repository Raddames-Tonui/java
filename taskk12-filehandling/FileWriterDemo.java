import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {
    public static void main(String[] args) {
        String content = "This is sample content to write to a file in java.\n You managed to write.";

        File file = new File("output.txt");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(content);
            System.out.println("Written to file Successfully!");
        }catch(IOException e){
            System.out.println("Error writing to file: " + e.getMessage());
        }

    }
}
