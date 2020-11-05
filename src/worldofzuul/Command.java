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
    // Command is a object that holds two strings.
    private CommandWord commandWord;
    private String secondWord;


    public Command(CommandWord commandWord, String secondWord)
    {
        this.commandWord = commandWord;
        this.secondWord = secondWord;
    }

    // Returns the commandWord (the first word)
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    // Returns secondWord.
    public String getSecondWord()
    {
        return secondWord;
    }


    // Returns true if not understood
    public boolean isUnknown()
    {
        return (commandWord == CommandWord.UNKNOWN);
    }

    // Returns true if the command has a SecondWord
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}

