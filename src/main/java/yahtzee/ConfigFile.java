package yahtzee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;



/**
 * The class that holds all methods in relation to reading and writing to the config file
 * 
 * @author Abraham Meshesha
 * @version 1.0
 */
public class ConfigFile {
    /**
     * Method reads from config file and if file dosnt exist it creates one with default vals

     * 
     */
    int[] readingFile() {
        File file = new File("src/main/resources/yahtzeeConfig.txt");
        int[] data = new int[3];



        if (!file.exists()) {
            writingFile(new int[] {6,5,3});
            
        }
        try {
            Scanner fileReader = new Scanner(file);
            int i = 0;
            while(fileReader.hasNextInt() && i < 3) {
                data[i] = fileReader.nextInt();
                i++;


            }
        }
        catch (FileNotFoundException e) {
            System.out.println("No data in file");
        }
        return data; 
    }

    /**
     * Method writes to config file 
     * 
     * @param newData This is an array of the new value the user choose to modify the config file with 
     */
    void writingFile(int[] newData) {
        File file = new File("src/main/resources/yahtzeeConfig.txt");

        try (PrintWriter fileWriter = new PrintWriter(file)) {

            for(int i = 0; i < 3; i++) {
                fileWriter.println(newData[i]);
            }

        }
        catch (FileNotFoundException e) {
            System.out.println("No File Exists");

        }

    }



}
