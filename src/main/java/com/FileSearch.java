package com;

import org.apache.maven.model.License;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.*;
import java.util.*;

public class FileSearch {

    private String fileNameToSearch;
    private String fileExtensionToSearch;
    private String compFileNameToSearch;
    private Integer count;
    private List<String> result = new ArrayList<String>();
    private Map<String, File> compURL = new HashMap<>();

    public String getFileExtensionToSearch() {
        return fileExtensionToSearch;
    }

    public void setFileExtensionToSearch(String fileExtensionToSearch) {
        this.fileExtensionToSearch = fileExtensionToSearch;
    }

    public String getFileNameToSearch() {
        return fileNameToSearch;
    }

    private void setFileNameToSearch(String fileNameToSearch) {
        this.fileNameToSearch = fileNameToSearch;
    }

    private void setCompFileNameToSearch(String compFileNameToSearch){
        this.compFileNameToSearch = compFileNameToSearch;
    }

    public String getCompFileNameToSearch(){
        return compFileNameToSearch;
    }

    public List<String> getResult() {
        return result;
    }

    public Map<String, File> getCompURL(){
        return compURL;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public static void main(String[] args) throws IOException {
        FileSearch fileSearch = new FileSearch();
        //try different directory and filename
        fileSearch.searchDirectory(new File("C:\\softwares\\comp-test\\atmosphere"), "pom.xml", ".pom","url.txt");
        int count = fileSearch.getResult().size();
        if(count ==0){
            System.out.println("\nNo result found!");
        }else{
            System.out.println("\nFound " + count + " result!\n");
            for (String matched : fileSearch.getResult()){
                System.out.println("Found : " + matched);
                displayPomValues(matched);

                if(matched.contains(".pom")){
                    matched = matched.replaceAll(".pom", "");
                }
                else {
                    matched = matched.replaceAll("pom.xml", "");
                }
                for(Map.Entry<String, File> compUrl : fileSearch.getCompURL().entrySet()){
                    String compPath = compUrl.getKey().replace("url.txt", "");
                    if(matched.contains(compPath)){
                        try {
                            File compObj = compUrl.getValue();
                            Scanner compReader = new Scanner(compObj);
                            while (compReader.hasNextLine()) {
                                String data = compReader.nextLine();
                                if(!data.contains("scan done")) {
                                    System.out.println("text file content -> " + data);
                                }
                            }
                            compReader.close();
                        } catch (FileNotFoundException e) {
                            System.out.println("An error occurred.");
                            e.printStackTrace();
                        }
                    }
                }
            }
            fileSearch.markTextProcessed(fileSearch.getCompURL());
        }
    }

    private void markTextProcessed(Map<String, File> compURL) {
        for (Map.Entry<String, File> compUrl : compURL.entrySet()) {
            try {
                File compObj = compUrl.getValue();
                Scanner compReader = new Scanner(compObj);
                while (compReader.hasNextLine()) {
                    String data = compReader.nextLine();
                    System.out.println("text file content -> " + data);
                    writeToTextFile(data, compUrl.getValue());
                }
                compReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    private boolean checkDone(String path){
        boolean check = false;
        try {
            File compObj = new File(path);
            Scanner compReader = new Scanner(compObj);
            while (compReader.hasNextLine()) {
                String data = compReader.nextLine();
                System.out.println("text file content -> " + data);
                if(data.contains("scan done")){
                    check = true;
                }
            }
            compReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return check;
    }

    public void writeToTextFile(String data, File toWrite) {
        try {
            if(!data.contains("scan done")) {
                FileWriter myWriter = new FileWriter(toWrite);
                myWriter.write(data + " - " + "scan done");
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void searchDirectory(File directory, String fileNameToSearch, String fileExtensionToSearch, String compFileNameToSearch) {
        setFileNameToSearch(fileNameToSearch);
        setCompFileNameToSearch(compFileNameToSearch);
        setFileExtensionToSearch(fileExtensionToSearch);
        if (directory.isDirectory()) {
            search(directory, directory);
        } else {
            System.out.println(directory.getAbsoluteFile() + " is not a directory!");
        }
    }

    private void search(File file, File rootPath) {
        if (file.isDirectory()) {
          //  System.out.println("Searching directory ... " + file.getAbsoluteFile());
            //do you have permission to read this directory
            if (file != null && file.canRead()) {
                for (File temp : file.listFiles()) {
                    if (temp.isDirectory()) {
                       // System.out.println("parent -> "+file.getParent());
                        if(file.getParent() != null && file.getParent().equalsIgnoreCase(rootPath.getAbsolutePath())){
                            System.out.println(file.getAbsolutePath() +" -> "+file.getParent()+" -> "+rootPath.getAbsolutePath());
                        }

                        if(file.getParent() != null && file.getParent().equalsIgnoreCase(rootPath.getAbsolutePath())){
                            File pmFile = new File(temp, "pom.xml");
                            File txtFile = new File(temp, "url.txt");
                            File pmInRootPath = new File(rootPath, "pom.xml");
                            File txtInRootPath = new File(rootPath, "url.txt");
                            if(pmFile.exists() && !txtFile.exists()){
                                //create url.txt
                                createTextFile(txtFile);
                            }
                            else if(pmInRootPath.exists() && !txtInRootPath.exists()){
                                createTextFile(txtInRootPath);
                            }
                        }
                        search(temp, rootPath);
                    } else {
                       if(getCompFileNameToSearch().equals(temp.getName().toLowerCase())){
                            compURL.put(temp.getAbsoluteFile().toString(), new File(temp.getAbsoluteFile().toString()));
                        }
                        if (getFileNameToSearch().equals(temp.getName().toLowerCase())) {
                            result.add(temp.getAbsoluteFile().toString());
                        }
                        if (temp.getName().toLowerCase().contains(getFileExtensionToSearch())) {
                            result.add(temp.getAbsoluteFile().toString());
                        }
                    }
                }
            } else {
                System.out.println(file.getAbsoluteFile() + "Permission Denied");
            }
        }
    }

    private static void createTextFile(File newFile){
        try {
            if (!newFile.exists() && newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void displayPomValues(String path) {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        try {
            Model model = reader.read(new FileReader(path));
            if(model.getParent() != null) {
                System.out.println(model.getParent().getGroupId());
                System.out.println(model.getParent().getArtifactId());
            }
            else {
                System.out.println(model.getGroupId());
                System.out.println(model.getArtifactId());
            }

            if(model.getLicenses() != null && model.getLicenses().size() > 0){
                List<License> licenses = model.getLicenses();
                for(License license : licenses){
                    System.out.println(license.getName());
                }
            }

        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }
        catch(XmlPullParserException xpe){
            xpe.printStackTrace();
        }
    }

    public static Properties readPropertiesFile(String fileName) throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fis);
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }
}
