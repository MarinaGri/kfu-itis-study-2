package ru.itis.file_hw_10.commands;

import java.nio.file.Path;

public interface Command {
    void execute(String option, Path path);
}
