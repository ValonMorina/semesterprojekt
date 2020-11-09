package worldofzuul;

import java.util.ArrayList;

public class Game
{
    private Parser parser;
    private Room currentRoom;
    Room townSquare, village, brimhavenTown, quarry, spring, forest, river, toilet, school; // Generates the objects
    ArrayList<Item> inventory = new ArrayList<Item>();
    String[] buildingSpring =  {"wood","pickaxe","pipes"};
    boolean build = true;


    public Game()   // Creates the object "Game"
    {
        createRooms();
        parser = new Parser();
    }


    private void createRooms()
    {
        // Declaring the rooms
        townSquare = new Room("in Town Square");
        village = new Room("in a village");
        brimhavenTown = new Room("in Brimhaven Town");
        quarry = new Room("now at the quarry");
        spring = new Room("now at the spring");
        forest = new Room("now in the forest");
        river = new Room("now at the river");
        toilet = new Room("now at the toilet");
        school = new Room("now at the school");


        // Initialise room exits
        townSquare.setExit("east", village);
        townSquare.setExit("south", river);
        townSquare.setExit("west", brimhavenTown);
        townSquare.setExit("north", school);

        village.setExit("west", townSquare);
        village.setExit("north", toilet);
        village.setExit("south", spring);

        brimhavenTown.setExit("east", townSquare);
        brimhavenTown.setExit("north", forest);
        brimhavenTown.setExit("south", quarry);

        quarry.setExit("north", brimhavenTown);
        quarry.setExit("east", river);

        spring.setExit("west", river);
        spring.setExit("north", village);

        forest.setExit("east", school);
        forest.setExit("south", brimhavenTown);

        river.setExit("north", townSquare);
        river.setExit("west", quarry);
        river.setExit("east", spring);

        toilet.setExit("west", school);
        toilet.setExit("south", village);

        school.setExit("west", forest);
        school.setExit("south", townSquare);
        school.setExit("east", toilet);


        currentRoom = townSquare;  // start game townSquare

        // Adds objects in inventory
        inventory.add(new Item("hammer"));
        inventory.add(new Item("map"));
        inventory.add(new Item("shovel"));
        inventory.add(new Item("bucket"));

        // Sets items in rooms
        forest.setItem(new Item("wood"));
        quarry.setItem(new Item("pickaxe"));
        quarry.setItem(new Item("stone"));
        quarry.setItem(new Item("iron"));
        quarry.setItem(new Item("concrete"));
        village.setItem(new Item("paper"));
        townSquare.setItem(new Item("nail"));
        brimhavenTown.setItem(new Item("pens"));
        brimhavenTown.setItem(new Item("pipes"));

    }

    public void play()  // Method we call to play the game
    {
        printWelcome();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() // Prints out the strings in the method printWelcome.
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) // processCommand takes two commands and operates on them.
    {
        boolean wantToQuit = false; // Declaring the variable wantToQuit

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        // Checking input
        if (commandWord == CommandWord.HELP)
        {
            printHelp();
        }
        else if (commandWord == CommandWord.GO)
        {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT)
        {
            wantToQuit = quit(command);
        }
        else if (commandWord == CommandWord.INVENTORY)
        {
            printInventory();
        }
        else if (commandWord == CommandWord.GET)
        {
            getItem(command);
        }
        else if (commandWord == CommandWord.DROP)
        {
            dropItem(command);

        }
        else if (commandWord == CommandWord.BUILD)
        {
            buildItem(command);

        }
        return wantToQuit;
    }



    // Build items
    private void buildItem(Command command)
    {
        if (!command.hasSecondWord())       // If there is no second word, we don't know what to build
        {
            System.out.println("Build what?");
            return;
        }

        String building = command.getSecondWord();
        switch (building)
        {
            case "spring":
            {
                for (int i = 0; i<buildingSpring.length;i++)
                {
                    for(int j = 0;j<inventory.size();j++)

                    {
                        if(build && inventory.get(j).description.equals(buildingSpring[i]))
                        {
                            System.out.println("You build the spring protection!");
                            build = false;

                        }

                    }
                }
            }
        }
    }


    private void dropItem(Command command) {
        if (!command.hasSecondWord()) {      // If there is no second word, we don't know what to drop
            System.out.println("Drop what?");
            return;
        }

        String item = command.getSecondWord();

        Item newItem = null;
        int index = 0;
        for (int i = 0; i<inventory.size(); i++) {
            if (inventory.get(i).getDescription().equals(item)) {
                newItem = inventory.get(i);
                index = i;
            }
        }

        if (newItem == null) {
            System.out.println("The item is not in your inventory");
        } else {
            inventory.remove(index);
            currentRoom.setItem(new Item(item));
            System.out.println("Dropped: " + item);
        }
    }

    private void printInventory() {
        String output = "";
        for(int i = 0; i<inventory.size();i++) {
            output += inventory.get(i).getDescription() + " ";
        }
        System.out.println("You are carrying: ");
        System.out.println(output);
    }

    private void getItem(Command command) {
        if (!command.hasSecondWord()) {      // If command does not has a second command, we don't know what to get
            System.out.println("Get what?");
            return;
        }

        String item = command.getSecondWord();

        // Try to leave the room
        Item newItem = currentRoom.getItem(item);

        if (newItem == null) {     // We can't go that direction
            System.out.println("The item is not here!");
        } else {
            inventory.add(newItem);
            currentRoom.removeItem(item);
            System.out.println("Picked up: " + item);
        }
    }


    // Printing the strings declared in the method printHelp.
    private void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around in Town Square.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {      // If command does not has a second command, we print "Go where?".
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();     // Sets direction

        Room nextRoom = currentRoom.getExit(direction);    // Checks if we can go that direction

        if (nextRoom == null) {     // We can't go that direction
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;     // We can go this direction
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false; // Does not quit
        }
        else {
            return true;    // Quits
        }
    }
}
