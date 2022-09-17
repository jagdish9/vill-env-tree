package dp;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

public class LineNumber {

    public static void main(String[] args) {
        LineNumber lineNumber = new LineNumber();
        String word = "\"Copyright (c)\n" +
                "2008 - 2022 Ilan Schnell; All Rights Reserved\"";
        String newFile = "C:\\softwares\\csv generated files\\24-August-2022\\tree2.txt";

        /*try {
            System.out.println(lineNumber.find(word, newFile));
        }
        catch(Exception e) {
            e.printStackTrace();
        }*/

        System.out.println(lineNumber.getOnlyLineNumber(newFile, word));
        System.out.println(lineNumber.getLineNumberByReadingTwoLines(newFile, word));
    }

    public ArrayList<Integer> find(String word, File text) throws IOException {
        LineNumberReader rdr = new LineNumberReader(new FileReader(text));
        ArrayList<Integer> results = new ArrayList<Integer>();
        try {
            int ch;
            while((ch = rdr.read())!=1) {
                System.out.print((char) ch);
            }
            String line = rdr.readLine();
            if (line.indexOf(word) >= 0) {
                results.add(rdr.getLineNumber());
            }
        } finally {
            rdr.close();
        }
        return results;
    }

    public void readLineNumber(String filename, String searchString) {
        try(LineNumberReader lineNumberReader
                    = new LineNumberReader(new FileReader(filename))) {

            //Print initial line number
         //   System.out.println("Line " + lineNumberReader.getLineNumber());

            //Setting initial line number
          //  lineNumberReader.setLineNumber(5);

            //Get current line number
         //   System.out.println("Line " + lineNumberReader.getLineNumber());

            //Read all lines now; Every read increase the line number by 1
            String line = null;
            while ((line = lineNumberReader.readLine()) != null) {
                if(line.contains(searchString)) {
                    System.out.println("Line " + lineNumberReader.getLineNumber() + ": " + line);
                    break;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int getOnlyLineNumber(String filename, String searchString) {
        int lineNumber = 0;
        try(LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filename))) {
            String line = null;
            while ((line = lineNumberReader.readLine()) != null) {
                if(line.contains(searchString)) {
                    lineNumber = lineNumberReader.getLineNumber();
                    break;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return lineNumber;
    }

    public int getLineNumberByReadingTwoLines(String filename, String searchString) {
        int lineNumber = 0;
        try(LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(filename))) {
            String line = null;
            while ((line = lineNumberReader.readLine()) != null) {
                if(line.contains(searchString)) {
                    lineNumber = lineNumberReader.getLineNumber();
                    break;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return lineNumber;
    }
}
