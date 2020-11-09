package worldofzuul;

<<<<<<< HEAD
public enum CommandWord
{
<<<<<<< HEAD
<<<<<<< HEAD
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), INVENTORY("inventory"),GET("get"),DROP("drop"),BUILD("build");
=======
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), INVENTORY("inventory"),GET("get"),DROP("drop");
>>>>>>> parent of 09c7fa1... Merge pull request #2 from ValonMorina/Cecilie
=======
    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), INVENTORY("inventory"),GET("get"),DROP("drop");
>>>>>>> parent of 09c7fa1... Merge pull request #2 from ValonMorina/Cecilie

=======
public enum CommandWord {
    GO("go"),
    QUIT("quit"),
    HELP("help"),
    UNKNOWN("?"),
    INVENTORY("inventory"),
    TAKE("take"),
    DROP("drop"),
    BUILD ("build");

    //Data Field
>>>>>>> parent of 0ba0549... Merge branch 'main' into Cecilie
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
