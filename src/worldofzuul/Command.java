/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of two parts: a CommandWord and a string
 * (for example, if the command was "take map", then the two parts
 * are TAKE and "map").
 *
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the CommandWord is UNKNOWN.
 *
 * If the command had only one word, then the second word is <null>.
 *
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */

package worldofzuul;

public class Command
{
    //Data fields
    private CommandWord commandWord;
    private String secondWord;

    //Constructor to create a command object
    //First and second word must be provided, but one or both can be null.
    //Null should be used to indicate that this was a command that the game does not recognize
    public Command(CommandWord commandWord, String secondWord) {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    // Return the command word (the first word) of this command
    //If the word is not understood null is returned
    public CommandWord getCommandWord() {
        return commandWord;
    }

    // Return the second command word of this command
    //If there is no second word it returns null
    public String getSecondWord() {
        return secondWord;
    }


    // Returns true if the command is not understood
    public boolean isUnknown() {
        return (commandWord == CommandWord.UNKNOWN);
    }

    // Returns true if the command has a SecondWord
    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}

