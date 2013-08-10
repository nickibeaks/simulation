
/**
 * Animal class
 * 
 * @author Shane Farrell 
 * @version 1.0
 * @date 07/07/13
 */
import java.util.*;

public class Animal
{
    public static int numAgents = 0; // number of agents in a scene before newest built agent
    private String type, sex, name;
    private int ID, row, column;
    // primary states    
    private int alertness, hunger, anger, happiness, energy, health;    
    // senses
    private int hearing, sight, touch, taste, smell;
    // physical attributes
    private int height, weight, attractiveness, sexualOrientation, agility, strength, acceleration, speed; 

    /**
     * Constructor for objects of class Animal
     */
    public Animal(String newType)
    {
        numAgents = numAgents+1;
        type = newType;
        name = "John Doe";
        sex = "male";       
        
        Random randomNum = new Random();
        // primary states
        alertness = randomNum.nextInt(100);
        hunger = randomNum.nextInt(100);
        anger = randomNum.nextInt(100);
        happiness = randomNum.nextInt(100);
        energy = randomNum.nextInt(100)-hunger/2;
        health = 60+randomNum.nextInt(40);
        // primary senses
        hearing = randomNum.nextInt(100);
        sight = 60+randomNum.nextInt(40);
        touch = randomNum.nextInt(100);
        taste = randomNum.nextInt(100);
        smell = randomNum.nextInt(100);
        // physical attributes
        height = 48+randomNum.nextInt(32);
        weight = 80+randomNum.nextInt(220);
        attractiveness = randomNum.nextInt(100);
        sexualOrientation = randomNum.nextInt(1);
        agility = 30+randomNum.nextInt(70);
        strength = 30+randomNum.nextInt(70);
        acceleration = 30+randomNum.nextInt(70);
        speed = 30+randomNum.nextInt(70);                                   
    }
    
    /**
     * Constructor for objects of class Animal that takes a name and type for the animal
     * 
     * @param String
     * @return
     */
    public Animal(String newName, String newType)
    {
        numAgents = numAgents+1;
        Random randomNum = new Random();
        // primary states
        alertness = randomNum.nextInt(100);
        hunger = randomNum.nextInt(100);
        anger = randomNum.nextInt(100);
        happiness = randomNum.nextInt(100);
        energy = randomNum.nextInt(100)-hunger/2;
        health = 60+randomNum.nextInt(40);
        // primary senses
        hearing = randomNum.nextInt(100);
        sight = 60+randomNum.nextInt(40);
        touch = randomNum.nextInt(100);
        taste = randomNum.nextInt(100);
        smell = randomNum.nextInt(100);
        // physical attributes
        height = 48+randomNum.nextInt(32);
        weight = 80+randomNum.nextInt(220);
        attractiveness = randomNum.nextInt(100);
        sexualOrientation = randomNum.nextInt(1);
        agility = 30+randomNum.nextInt(70);
        strength = 30+randomNum.nextInt(70);
        acceleration = 30+randomNum.nextInt(70);
        speed = 30+randomNum.nextInt(70);
        // settings                               
        name = newName;
        type = newType;
        sex = "male";
    }
     
    /**
     * Get the type of animal
     * 
     * @param 
     * @return String 
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * Get number of agents in a scene
     * 
     * @param 
     * @return int 
     */
    public int getNumAgents()
    {
        return numAgents;
    }
    
    /**
     * Get name
     * 
     * @param 
     * @return String 
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Get agent ID
     * 
     * @param 
     * @return int 
     */
    public int getID()
    {
        return ID;
    }
    
    /**
     * Get the health of animal
     * 
     * @param 
     * @return int 
     */
    public int getHealth()
    {
        return health;
    }
    
    
    /**
     * Get row
     * 
     * @param 
     * @return int  
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * Get column
     * 
     * @param 
     * @return int  
     */
    public int getColumn()
    {
        return column;
    }
    
    /**
     * Get sight
     * 
     * @param
     * @return int
     */
    public int getSight()
    {
        return sight;    
    }
    
    /**
     * Get alertness
     * 
     * @param
     * @return int
     */
    public int getAlertness()
    {
        return alertness;    
    }
    
    /**
     * Set name
     * 
     * @param 
     * @return  
     */
    public void setName(String newName)
    {
        name = newName;
    }
    
    /**
     * Set the health of animal
     * 
     * @param int
     * @return 
     */
    public void setHealth(int newHealth)
    {
        health = newHealth;
    }
    
    /**
     * Set row
     * 
     * @param 
     * @return  
     */
    public void setRow(int newRow)
    {
        row = newRow;
    }
    
    /**
     * Set column
     * 
     * @param 
     * @return  
     */
    public void setColumn(int newColumn)
    {
        column = newColumn;
    }
    
    /**
     * Set agent ID
     * 
     * @param int
     * @return  
     */
    public void setID(int newID)
    {
        ID = newID;
    }
    
    /**
     * Check alertness
     * 
     * @param
     * @return
     */
    public void alertness()
    {
       // check alertness
        if(alertness >= 80)
        {
            System.out.println(getName()+" is completely alert");
        }
        else if(alertness >= 60 && alertness < 80)
        {
            System.out.println(getName()+" is alert");
        }
        else if(alertness >= 40 && alertness < 60)
        {
            System.out.println(getName()+" is fairly alert");
        }
        else if(alertness >= 30 && alertness < 40)
        {
            System.out.println(getName()+" is tiring");
        }
        else if(alertness >= 20 && alertness < 30)
        {
            System.out.println(getName()+" is drowzy");
        }
        else if(alertness >= 10 && alertness < 20)
        {
            System.out.println(getName()+" is in light sleep");
        }
        else if(alertness < 10)
        {
            System.out.println(getName()+" is in deep sleep");
        } 
    }

}
