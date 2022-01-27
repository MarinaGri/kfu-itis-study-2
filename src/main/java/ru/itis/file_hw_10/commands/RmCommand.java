package ru.itis.file_hw_10.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class RmCommand implements Command{

    @Override
    public void execute(String option, Path path) {
        File file = new File(path.toString());
        if(!file.exists()){
            System.out.println("Subdirectory or file " + path + " does not exist");
        }
        else if(!file.delete()){
            if(option == null){
                System.out.println("Failed to delete " + file.getName());
            }

            //recursive deletion of all subdirectories and files of a given directory
            else if(option.equals("-r") && file.isDirectory()){
                DirectoryStream<Path> files;
                try {
                    files = Files.newDirectoryStream(path);
                    for (Path p : files) {
                        execute(option, p);
                    }
                    //delete empty directory
                    file.delete();
                } catch (IOException ex) {
                System.out.println("Failed to delete " + file.getName());
                }
            }
            else {
                System.out.println("Invalid key: " + option);
            }
        }
        if(path.toString().equals(System.getProperty("user.dir"))){
            System.setProperty("user.dir", path.getParent().toString());
        }
    }
}
