package worldofzuul;

public enum CommandWord
{
<<<<<<< HEAD
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), INVENTORY("inventory"),GET("get"),DROP("drop"),BUILD("build");
=======
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), INVENTORY("inventory"),GET("get"),DROP("drop");
>>>>>>> parent of 09c7fa1... Merge pull request #2 from ValonMorina/Cecilie

    private String commandString;

    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    public String toString()
    {
        return commandString;
    }
}
