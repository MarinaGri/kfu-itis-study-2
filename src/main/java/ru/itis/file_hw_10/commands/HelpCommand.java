package ru.itis.file_hw_10.commands;

import java.nio.file.Path;

public class HelpCommand implements Command{
    @Override
    public void execute(String option, Path path) {
        if(option == null) {
            System.out.println("№1 cd: changes the current directory \n" +
                                "№2 ls: displays subdirectories and files of a directory \n" +
                                "№3 mkdir: creates a directory \n" +
                                "№4 rm: removes an empty directory or file \n" +
                                "№5 tree: displays all subdirectories and files of a directory in tree format \n" +
                                "№6 touch: changes the last access time of the file to the current one \n" +
                                "       if the file with this name does not exist, then creates it \n" +
                                "*to find out info about options of command enter \"help -[command name]\"");
        }
        else {
            switch (option){
                case("-cd"):
                case("-touch"):
                    System.out.println("has no options");
                    break;
                case("-ls"):
                    System.out.println("\"-l\" outputs the properties of subdirectories and files in the format: \n" +
                                        "d: if it is a directory, else \"-\" \n" +
                                        "r: if it can be opened for reading, else \"-\" \n" +
                                        "w: if it can be opened for writing, else \"-\" \n" +
                                        "x: if it is executable, else \"-\" \n" +
                                        "Owner \nSize \nTime of creation \nName");
                    break;
                case("-mkdir"):
                    System.out.println("\"-p\" additionally creates non-existent parent directories in the path");
                    break;
                case("-rm"):
                    System.out.println("\"-r\" deletes this directory along with its files and subdirectories");
                    break;
                case("-tree"):
                    System.out.println("\"-d\" displays only subdirectories of a directory in tree format");
                    break;
                default:
                    System.out.println("Invalid key! \nList of all command:");
                    execute(null, null);
            }
        }
    }
}
