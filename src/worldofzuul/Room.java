package worldofzuul;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


public class Room {
    //Data Fields
    private String description;
    private HashMap<String, Room> exits;

    //Constructor for room
    public Room(String description) {
        //String description is going to be the description of the new Room which is created using this constructor
        this.description = description;
        //Creates a HashMap with every new room, this is used to store exits using the method setExits()
        exits = new HashMap<String, Room>();
    }

    //Method used to create exits from a Room (roomName.exits() is used in the class Game)
    //String direction is going to be the command word used to perform the action (something like, "east", "west", "up")
    //Room neighbor is the name of the Room where the user ends up when a 'direction' is given
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor); //stores the exits from a room i a HashMap named exits
    }

    //Return the description of the room as a String
    //This description was created when the room was created using the constructor
    public String getShortDescription() {
        return description;
    }

    //Return a long description of the room in the form
        // You are 'in the village'
        // Exits: west south
    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    //return a String giving the room's exits
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    //Return the room that is reached if user goes from this room in the direction "direction"
    //If there is no room in that direction null is returned
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
}

