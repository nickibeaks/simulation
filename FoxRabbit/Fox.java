
/**
 * Fox class.
 * 
 * @author Shane Farrell 
 * @version 1.0
 * @date 07/07/13
 */
public class Fox extends Animal
{
    public static int numFoxes = 0;
    private int foxID;

    /**
     * Constructor for objects of class Fox
     */
    public Fox()
    {
        super("Fox");
        numFoxes = numFoxes+1;
        foxID = numFoxes;
    }
    
    /**
     * Constructor for objects of class Fox
     */
    public Fox(String newName)
    {
        super(newName, "Fox");
        numFoxes = numFoxes+1;
        foxID = numFoxes;
    }
    
    /**
     * Get fox ID
     * 
     * @param
     * @return int
     */
    public int getFoxID()
    {
        return foxID;
    }
    
    /**
     * Hunt
     * 
     * @param
     * i - the hunted rabbit's row position
     * j - the hunted rabbit's column position
     * @return 
     */
    public void hunt(int i, int j)
    {
        
    }
}
