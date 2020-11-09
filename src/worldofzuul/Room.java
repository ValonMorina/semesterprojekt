package worldofzuul;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


public class Room // Creates the class Room
{
    private String description; // Declares description
    private HashMap<String, Room> exits;    // ???
    ArrayList<Item> items = new ArrayList<Item>();


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
<<<<<<< HEAD
<<<<<<< HEAD
        returnString += "\nItems avaible in the room: \n";
        returnString += getRoomItems();
=======
            returnString += "\nItems avaible in the room: \n";
            returnString += getRoomItems();
>>>>>>> parent of 09c7fa1... Merge pull request #2 from ValonMorina/Cecilie
=======
            returnString += "\nItems avaible in the room: \n";
            returnString += getRoomItems();
>>>>>>> parent of 09c7fa1... Merge pull request #2 from ValonMorina/Cecilie

        return returnString;
    }

    public Room getExit(String direction) // Get a particular exit
    {
        return exits.get(direction);
    }


    public Item getItem(int index) {
        return items.get(index);
    }
    // Get items from the room
    public Item getItem(String itemName)
    {
        for(int i = 0; i<items.size(); i++)
        {
            if (items.get(i).getDescription().equals(itemName))
            {
                return items.get(i);
            }
        }
        return null;
    }

    // Remove items from the inventory
    public void removeItem(String itemName)
    {
        for(int i = 0; i<items.size(); i++)
        {
            if (items.get(i).getDescription().equals(itemName))
            {
                items.remove(i);
            }
        }
    }

    // Set a specifically item in a room
    public void setItem (Item newitem) {
        items.add(newitem);
    }

    // Get the descriptions of items in a room
    public String getRoomItems () {
        String output = "";
        for(int i = 0; i<items.size();i++) {
            output += items.get(i).getDescription() + " ";
        }
        return output;
    }

}

