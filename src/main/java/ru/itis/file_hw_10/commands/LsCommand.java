package ru.itis.file_hw_10.commands;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class LsCommand implements Command {
    private final int NUM_OF_ROWS = 4;

    @Override
    public void execute(String option, Path path) {
        try {
            if (option == null) {
                printNames(path);
                System.out.println();
            }
            else {
                if ("-l".equals(option)) {
                    printAll(path);
                } else {
                    System.out.println("Invalid key: " + option);
                }
            }
        }
        catch (IOException ex) {
            System.out.println("Directory not found");
        }
    }

    private void printNames(Path path) throws IOException {
        int counter = 0;
        int maxLength = 0;
        DirectoryStream<Path> files = Files.newDirectoryStream(path);
        for (Path p : files){
            if(p.getFileName().toString().length() > maxLength){
                maxLength = p.getFileName().toString().length();
            }
        }
        DirectoryStream<Path> files2 = Files.newDirectoryStream(path);
        for(Path p : files2){
            if(counter == NUM_OF_ROWS){
                System.out.println();
                counter = 0;
            }
            System.out.printf("%-" + maxLength + "s ", p.getFileName());
            counter++;
        }
    }

    private void printAll(Path path) throws IOException {
        //count the length of file properties for even output
        long[] length = new long[3];
        DirectoryStream<Path> files = Files.newDirectoryStream(path);
        for(Path p: files){
            if(Files.getOwner(p).toString().length() > length[0]){
                length[0] = Files.getOwner(p).toString().length();
            }
            if(Files.size(p) > length[1]){
                length[1] = Files.size(p);
            }
            if(Files.getAttribute(p, "creationTime").toString().length() > length[2]){
                length[2] = Files.getAttribute(p, "creationTime").toString().length();
            }
        }
        length[1] = (int)(Math.log(length[1])/Math.log(10))+1;

        DirectoryStream<Path> files2 = Files.newDirectoryStream(path);
        for (Path p : files2) {
            System.out.print(Files.isDirectory(p)? "d": "-");
            System.out.print(Files.isReadable(p)? "r": "-");
            System.out.print(Files.isWritable(p)? "w": "-");
            System.out.print(Files.isExecutable(p)? "x ": "- ");
            System.out.printf("%-" + length[0] + "s ", Files.getOwner(p));
            System.out.printf("%" + length[1] + "s ", Files.size(p));
            System.out.printf("%-" + length[2] + "s ",  Files.getAttribute(p, "creationTime"));
            System.out.println(" " + p.getFileName());
        }
    }
}
