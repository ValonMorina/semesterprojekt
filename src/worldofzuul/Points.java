package worldofzuul;

public class Points {
    //data field
    private int score; //Encapsulation

    //no-arg constructor
    public Points(){
    }

    //getter method
    public int getScore() {
        return score;
    }

    //setter method
    public void setScore(int points) {
        this.score += points;
    }
}
