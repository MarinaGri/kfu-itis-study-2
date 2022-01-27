package ru.itis.file_hw_10.commands;

import java.io.File;
import java.nio.file.Path;

public class MkdirCommand implements Command {
    @Override
    public void execute(String option, Path path) {
        File f = new File(path.toString());
        if(f.exists()){
            System.out.println("Subfolder or file " + path + " already exists");
        }
        else{
            if(option == null) {
                if (!f.mkdir()) {
                    System.out.println("Error in command syntax");
                }
            } else {
                if ("-p".equals(option)) {
                    while (!f.mkdir()) {
                        createDir(f);
                    }
                } else {
                    System.out.println("Invalid key: " + option);
                }
            }
        }
    }

    //recursive creation of missing parent directories
    private void createDir(File f){
        File file = f;
        if(file.mkdir()) return;
        file = file.getParentFile();
        createDir(file);
    }
}
