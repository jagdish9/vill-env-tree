package com;

import java.io.File;

public class CheckListFiles {

    public static void main(String[] args) {
        File file = new File("C:\\softwares\\comp-test\\google\\google-ctf\\2017\\quals\\2017-re-food\\Food\\app\\src\\main\\jniLibs\\armeabi");
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[0]);
            }
        }
        else {
            System.out.println("file is not a directory");
        }
    }
}
