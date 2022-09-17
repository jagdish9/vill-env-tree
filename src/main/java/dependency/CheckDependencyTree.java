package dependency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CheckDependencyTree {

    public static void main(String[] args) {
        String s1 = "C:\\softwares\\csv generated files\\10-June-2022\\abc_1.txt";
        System.out.println(isDependencyTreeFound(new File(s1)));
    }

    private static boolean isDependencyTreeFound(File file) {
        boolean isTreeFound = false;
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                if(line.contains("->") && line.contains("\" -> \"") && !line.contains("{") && !line.contains("}")) {
                    isTreeFound = true;
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("IOException in try block "+ e.getMessage());
        }
        return isTreeFound;
    }
}
