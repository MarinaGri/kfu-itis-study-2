package ru.itis.file_hw_10.commandManagement;

import ru.itis.file_hw_10.commands.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandManager {
    private CommandParser parser;
    private final Map<String, Command> commands;

    public CommandManager(){
        commands = new HashMap<>();
        commands.put("cd", new CdCommand());
        commands.put("help", new HelpCommand());
        commands.put("ls", new LsCommand());
        commands.put("mkdir", new MkdirCommand());
        commands.put("rm", new RmCommand());
        commands.put("touch", new TouchCommand());
        commands.put("tree", new TreeCommand());
    }

    public boolean perform(String str){
        Pattern r = Pattern.compile("^\\s*$");
        Matcher m = r.matcher(str);
        if(m.matches()) return true;

        parser = new CommandParser();
        parser.parse(str);
        if(parser.getCommand() != null) {
            for (String key : commands.keySet()) {
                if (key.equals(parser.getCommand())) {
                    if(parser.getPath() == null) {
                        System.out.println("Error in path syntax");
                    }
                    else {
                        commands.get(key).execute(parser.getOption(), parser.getPath());
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
