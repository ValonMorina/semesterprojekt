package worldofzuul;

public class Game
{
    private Parser parser;
    private Room currentRoom;


    public Game()   // Creates the object "Game"
    {
        createRooms();
        parser = new Parser();
    }


    private void createRooms()
    {
        Room townSquare, village, brimhavenTown, quarry, spring, forest, river, toilet, school; // Generates the objects

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
    }

    public void play()  // Method we call to play the game
    {
        printWelcome();

        //
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome() // Prints out the strings in the method printWelcome (line 46).
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
        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
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
