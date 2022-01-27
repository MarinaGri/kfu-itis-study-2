package ru.itis.file_hw_10.commands;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class TreeCommand implements Command{
    private int numOfDir;

    @Override
    public void execute(String option, Path path){
        numOfDir = countNumOfDir(path);
        System.out.println(path.getFileName().toString());
        try {
            if (option == null) {
                print(path, "-");
            } else {
                if ("-d".equals(option)) {
                    print(path, option);
                } else {
                    System.out.println("Invalid key: " + option);
                }
            }
        } catch (IOException ex) {
            System.out.println("Directory not found");
        }
    }

    //prints subdirectories and files (or only subdirectories) in tree format
    private void print(Path path, String option) throws IOException {
        DirectoryStream<Path> files = Files.newDirectoryStream(path);
        for (Path p : files) {
            if(!option.equals("-d") || Files.isDirectory(p)) {
                int space = countNumOfDir(p) - numOfDir;
                for (int i = 0; i < space; i++) {
                    System.out.print("      |");
                }
                System.out.print("_" + p.getFileName() + "\n");
            }
            if(Files.isDirectory(p)){
                print(p, option);
            }
        }
    }

    //counts number of directories in the path
    private int countNumOfDir(Path path){
        int counter = 0;
        Path p = path;
        while (p.getParent() != null) {
            p = p.getParent();
            counter++;
        }
        return counter;
    }
}
