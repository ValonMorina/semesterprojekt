package worldofzuul;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Parser {
    private CommandWords commands; //Holds all valid command words
    private Scanner reader;


    //Constructor
    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        // We create a scanner with the parameter as inputLine (keyboard).
        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next(); //get first word
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next(); //get second word
            }
        }
        //Creates new command, which has word1 as first command word and word2 as second command word
        return new Command(commands.getCommandWord(word1), word2);
    }

    //Prints a list of valid command words
    public void showCommands()
    {
        commands.showAll();
    }
}
