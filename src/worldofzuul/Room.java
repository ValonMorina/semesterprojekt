package worldofzuul;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


public class Room {
    //Data Fields
    private String description;
    private HashMap<String, Room> exits;

    //This is the ArrayList for the rooms, it stores items that can be found in the room
    ArrayList<Item> roomItem = new ArrayList<Item>();

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

    //return a String giving the room's exits and the items in the room if any
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        //shows items in the room
        returnString += "\nItems in the room:\n";
        returnString += getRoomItems();

        return returnString;
    }

    //Return the room that is reached if user goes from this room in the direction "direction"
    //If there is no room in that direction null is returned
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    //Picks up item from the room using index (int) as parameter (method overloading)
    public Item getRoomItem(int index) { //Item is the return type
        return roomItem.get(index);
    }


    //Picks up item from the room using a String as parameter (method overloading)
    public Item getRoomItem(String itemName) { //Item is the return type
        for (int i = 0; i < roomItem.size(); i++) {
            if (roomItem.get(i).getDescription().equals(itemName)) {
                return roomItem.get(i);
            }
        }
        //If there is no item with the given itemName, NULL is returned
        return null;
    }

    //Setter method: sets a particular Item in the room
    public void setRoomItem(Item newItem) {
        roomItem.add(newItem);
    }

    //Get a description of the items in a room
    public String getRoomItems() {
        String output = ""; //these "" initializes the variable to be empty
        //Runs through the arrayList named roomItem
        for (int i = 0; i < roomItem.size(); i++) {
            //checks inventory for the current value of i, if something is stored there it is added to the String
            //variable 'output' and using the getDescription() method it is turned into a String
            output += roomItem.get(i).getDescription() + " "; //" " at the end leaves a space between printed items
        }
        return output;
    }

    public void removeRoomItem(String itemName) {
        for (int i = 0; i < roomItem.size(); i++) {
            if (roomItem.get(i).getDescription().equals(itemName)) {
                roomItem.remove(i);
            }
        }
    }
}

