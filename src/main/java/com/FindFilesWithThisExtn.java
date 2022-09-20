package com;

import java.io.File;
import java.io.FilenameFilter;

public class FindFilesWithThisExtn {

    //specify the path (folder) where you want to search files
    private static final String fileLocation = "C:\\softwares\\github_projects";

    //extension you want to search for e.g. .png, .jpeg, .xml etc
    private static final String searchThisExtn = ".pom";

    public static void main(String args[]) {
        FindFilesWithThisExtn obj = new FindFilesWithThisExtn();
        obj.listFiles(fileLocation, searchThisExtn);
    }

    public void listFiles(String loc, String extn) {

        SearchFiles files = new SearchFiles(extn);

        File folder = new File(loc);

        if(folder.isDirectory()==false){
            System.out.println("Folder does not exists: " + fileLocation);
            return;
        }

        File[] listFiles = folder.listFiles();
        for(int z = 0; z < listFiles.length; z++){
            File fold = new File(String.valueOf(listFiles[z]));
            String[] ls = fold.list(files);
            System.out.println(ls);
        }

         String[] list = folder.list(files);
        if (list.length == 0) {
            System.out.println("There are no files with " + extn + " Extension");
            return;
        }

        for (String file : list) {
            String temp = new StringBuffer(fileLocation).append(File.separator)
                    .append(file).toString();
            System.out.println("file : " + temp);
        }
    }

    public class SearchFiles implements FilenameFilter {

        private String ext;

        public SearchFiles(String ext) {
            this.ext = ext;
        }

        @Override
        public boolean accept(File loc, String name) {
            if(name.lastIndexOf('.')>0)
            {
                // get last index for '.'
                int lastIndex = name.lastIndexOf('.');

                // get extension
                String str = name.substring(lastIndex);

                // matching extension
                if(str.equalsIgnoreCase(ext))
                {
                    return true;
                }
            }
            return false;

        }
    }
}
