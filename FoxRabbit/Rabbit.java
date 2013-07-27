/**
 * Rabbit class.
 * 
 * @author Shane Farrell 
 * @version 1.0
 * @date 07/07/13
 */
public class Rabbit extends Animal
{
    public static int numRabbits = 0;
    private int rabbitID;

    /**
     * Constructor for objects of class Rabbit
     */
    public Rabbit()
    {
        super("Rabbit");
        numRabbits = numRabbits+1;
        rabbitID = numRabbits;
    }
    
    /**
     * Constructor for objects of class Rabbit
     */
    public Rabbit(String newName)
    {
        super(newName, "Rabbit");
        numRabbits = numRabbits+1;
        rabbitID = numRabbits;
    }
    
    /**
     * Get rabbit ID
     * 
     * @param
     * @return int
     */
    public int getRabbitID()
    {
        return rabbitID;
    }
    
    /**
     * Get the number of rabbits in the scene
     * 
     * @param
     * @return int
     */
    public int getNumRabbits()
    {
        return numRabbits;
    }
}