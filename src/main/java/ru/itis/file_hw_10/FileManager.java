package ru.itis.file_hw_10;

import ru.itis.file_hw_10.commandManagement.CommandManager;

import java.util.Scanner;

public class FileManager extends AbstractApp{
    private Scanner scanner;
    private CommandManager commandManager;

    public FileManager(){
        super();
    }

    @Override
    protected void init() {
        scanner = new Scanner(System.in);
        commandManager = new CommandManager();
    }

    @Override
    protected void start() {
        String inputCommand;
        while (true){
            System.out.print(System.getProperty("user.dir") + ">");
            inputCommand = scanner.nextLine();
            if(!commandManager.perform(inputCommand)){
                System.out.println("\"" + inputCommand + "\" is not an internal or external\n" +
                        "command, executable program or batch file.");
            }
        }
    }
    public static void main(String[] args) {
               new FileManager();
    }
}
