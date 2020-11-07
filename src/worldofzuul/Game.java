package worldofzuul;

import java.util.ArrayList;

public class Game {
    //Data fields
    private Parser parser;
    private Room currentRoom;

    //Creates the room
    //Putting the rooms out here makes it possible to manipulate them when items are added and removed from the room
    Room townSquare, village, brimhavenTown, quarry, spring, forest, river, toilet, school;

    //This ArrayList contains the users items, picked up items will be stored here
    ArrayList<Item> inventory = new ArrayList<Item>();

    //Creates the game and initializes the map
    public Game() {
        createRooms(); //initializes the map
        parser = new Parser();
    }

    private void createRooms() {
        //Creates the room with a description
        townSquare = new Room("in Town Square");
        village = new Room("in a village");
        brimhavenTown = new Room("in Brimhaven Town");
        quarry = new Room("now at the quarry");
        spring = new Room("now at the spring");
        forest = new Room("now in the forest");
        river = new Room("now at the river");
        toilet = new Room("now at the toilet");
        school = new Room("now at the school");


        //Gives the room an exit command word paired with the new location the user will end up in if that word is used
        //This relates the different rooms to one another
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

        //Sets the currentRoom to townSquare, this is used when starting the game
        //currentRoom changes through out the game, always pointing at the room the user is currently in
        currentRoom = townSquare;

        //User inventory
        //here objects are created and added to the inventory, these will be in inventory from the start of the game
        inventory.add(new Item("shovel")); //add is a method defined with arrayList

        //Room inventory, sets items in the roomsm these can be picked up by the user
        townSquare.setRoomItem(new Item("paper"));
    }

    //Calls this method to play the game, it is a loop that runs until end of the game
    public void play() {
        printWelcome(); //Calls the printWelcome() method

        //Repeatedly reads commands from console and executes them until game is over
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        //Message when game is ended
        System.out.println("Thank you for playing.  Good bye.");
    }

    //This method prints the opening message for the user
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println(); //Empty line, creates space
        System.out.println(currentRoom.getLongDescription()); //Informs the user of the current room
    }

    //Processes (executes) the commands given by the user

    private boolean processCommand(Command command) {// processCommand takes two commands and operates on them.
        boolean wantToQuit = false; // Declaring the variable wantToQuit

        CommandWord commandWord = command.getCommandWord();

        //If invalid commandWord is given the following code is executed
        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false; //Keeps wantToQuit false
        }
        //If commandWord is help, execute method printHelp()
        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        //If commandWord is go, execute method goRoom(command)
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        //If commandWord is quit, the program executes the method quit(command) and sets the boolean variable wantToQuit
        //to the result of the quit(command) method
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        //If commandWord is inventory the program executes the method printInventory()
        else if (commandWord == CommandWord.INVENTORY) {
            printInventory();
        }
        else if (commandWord == CommandWord.TAKE) {
            takeItem(command);
        }
        return wantToQuit;
    }


    //Following method is activated by the commandWord help
    private void printHelp() {
        // Printing the strings declared in the method printHelp()
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around in Town Square.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    //Following method is activated by the commandWord go
    private void goRoom(Command command) {
        //If user only gives command word 'go', with no second command the following code is processed
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        //Stores second command in a String named direction
        String direction = command.getSecondWord();

        //Declares Room named nextRoom and checks if currentRoom has an exit named as the above stored variable direction
        Room nextRoom = currentRoom.getExit(direction);

        //if the user gives command word go and a second command that is invalid the following code is processed
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            //Sets variable nextRoom as currentRoom, this is what moves the user
            currentRoom = nextRoom;
            //Tells the user where they are now and the exits using method .getLongDescription()
            System.out.println(currentRoom.getLongDescription());
        }
    }

    //Following method is activated by the commandWord quit
    private boolean quit(Command command) {
        //If user writes a second command word after quit the following code is executed
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false; // Does not quit
        }
        //if only commandWord quit it written the following code is executed and the game ends
        else {
            return true; //Quits
        }
    }

    //This method is executed when commandWord inventory is written by user
    private void printInventory() {
        String output = ""; //these "" initializes the variable to be empty
        //Runs through the arrayList named inventory and prints every item in the list
        for (int i = 0; i < inventory.size(); i++) {
            //checks inventory for the current value of i, if something is stored there it is added to the String
            //variable 'output' and using the getDescription() method it is turned into a String
            output += inventory.get(i).getDescription() + " "; //" " at the end leaves a space between printed items
        }
        System.out.println("Your inventory contains: ");
        //if statement checks if inventory is empty by checking whether or not the value of 'output' has been changed
        if (output == "") {
            System.out.println("Nothing, go pick up some items"); //prints message if inventory is empty
        }
        else {
            System.out.println(output); //prints the inventory
        }
    }
    private void takeItem(Command command) {
        //If user only gives command word 'take', with no second command the following code is processed
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }
        //Stores second command word in a String named item
        String item = command.getSecondWord();

        //Declares Item named newtItem and checks if currentRoom has an item named as the above stored variable 'item'
        //if the 'item' called by second command word is in the room it is stored in the variable newItem
        Item newItem = currentRoom.getRoomItem(item);

        if (newItem == null) {
            System.out.println("That item is not in the room!");
        }
        else {
            //add to inventory
            inventory.add(newItem);
            //remove item from room
            currentRoom.removeRoomItem(item);
            System.out.println("You Picked up: " + item);
        }
    }
}
