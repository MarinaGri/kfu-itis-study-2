package ru.itis.file_hw_10.commandManagement;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {
    private final String commandReg = "([a-z0-9_]+)(?:\\s(-[a-z]+))?(?:\\s(.*)|($))";
    private final String pathReg = "[A-Za-zА-Яа-я\\s%/.:@&=+$,\\\\;-]+";
    private String command;
    private String option;
    private Path path;

    public void parse(String str) {
        Pattern r = Pattern.compile(commandReg);
        Matcher m = r.matcher(str);

        if (m.matches()) {
            if(m.find(0)){
                command = m.group(1).toLowerCase();
                option = m.group(2);
                path = createPath(m.group(3));
            }
        }
    }

    private Path createPath(String str){
        //if the path is not specified then the current one is used
        if(str == null){
            return Path.of(System.getProperty("user.dir"));
        }
        Pattern r = Pattern.compile(pathReg);
        Matcher m = r.matcher(str);
        if(m.matches()){
            try {
                path = Path.of(str);
                if(!path.isAbsolute()){
                    path = Path.of(System.getProperty("user.dir")).resolve(path);
                }
            }
            catch (InvalidPathException e){
                return null;
            }
            return path.normalize();
        }
        return null;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
}
