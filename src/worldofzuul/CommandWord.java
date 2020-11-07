package worldofzuul;

public enum CommandWord {
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    UNKNOWN("?"),
    INVENTORY("inventory"),
    TAKE("take");

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
