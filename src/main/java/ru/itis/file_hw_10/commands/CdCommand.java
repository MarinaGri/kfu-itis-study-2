package ru.itis.file_hw_10.commands;

import java.nio.file.Files;
import java.nio.file.Path;

public class CdCommand implements Command {
    @Override
    public void execute(String option, Path path) {
        if(Files.isDirectory(path)) {
            System.setProperty("user.dir", path.toString());
        }
        else {
            System.out.println("The system cannot find the path specified");
        }
    }
}
