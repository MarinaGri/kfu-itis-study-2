package ru.itis.file_hw_10.commands;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;

public class TouchCommand implements Command{
    @Override
    public void execute(String option, Path path) {
        File file = new File(path.toString());
        try {
            if(!file.createNewFile()) {
                FileTime fileTime = FileTime.fromMillis(System.currentTimeMillis());
                Files.setAttribute(path, "lastAccessTime", fileTime);
            }
        } catch (IOException e) {
            System.out.println("Error in path syntax");
        }
    }
}
