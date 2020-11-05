package worldofzuul;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


public class Room // Creates the class Room
{
    private String description; // Declares description
    private HashMap<String, Room> exits;    // ???

    public Room(String description)  // Takes description as a parameter for the class Room.
    {
        this.description = description;    // Class variable = local variable
        exits = new HashMap<String, Room>();       // ???
    }

    public void setExit(String direction, Room neighbor) // Method to direct us to a room
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }

    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) // Get a particular exit
    {
        return exits.get(direction);
    }
}

