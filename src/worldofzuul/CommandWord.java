package worldofzuul;

public enum CommandWord {
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    UNKNOWN("?");

    //Data Field
    private String commandString;

    //Constructor
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    public String toString()
    {
        return commandString;
    }
}
