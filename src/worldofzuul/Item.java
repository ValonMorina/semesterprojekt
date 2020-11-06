package worldofzuul;

public class Item {
    //Data field
    String description;

    //Constructor
    public Item(String description) {
        this.description = description;
    }

    //Getter method: returns description of the Item object
    public String getDescription() {
        return description;
    }
}
