
/**
 * A place to test agent based modelling.
 * 
 * @author Shane Farrell 
 * @version 1.0
 * @date 24/06/13
 */
import java.util.*;
import java.lang.Math;

public class ExperimentTest
{
    

    /**
     * Constructor for object
     */
    public ExperimentTest()
    {
        
    }

    /**
     * Main method - general testing area for agent behaviour on 2D game board environment
     * 
     */
    public static void main(String args[])
    {                             
        // pre-simulation set-up**********************************************************************************************************************************************************************************    	
    	// configure 2D game board
        int totalRows = 20; // number of rows on game board
        int totalColumns = 20; // number of columns on game board
    		
    	// simulation utilities
    	Random randomNum = new Random(); // random number generator for general use across simulation
        Scanner input = new Scanner(System.in); // user input for step forward in simulation
        ArrayList<Animal> animals = new ArrayList<Animal>(); // memory for all animals in the system
        int numAnimals = 0; // counter for the total number of animals in the system
        int numRabbits = 0; // counter for number of rabbits in the system
        int numFoxes = 0; // counter for number of foxes in the system
        Animal[][] board = new Animal[totalRows][totalColumns]; // memory for game board
        
        // configure simulation
        int numSteps = 2; // number of steps to go into the simulation before stopping
        // minimum and maximum numbers of animals to generate into the system at the beginning of simulation 
        int minRabbits = 2; 
        int maxRabbits = 8; 
        int minFoxes = 1; 
        int maxFoxes = 2;
        // *******************************************************************************************************************************************************************************************************      
        
        // generate animals***************************************************************************************************************************************************************************************
        // randomly generate a number of rabbits using the simulation configured max and min rabbit numbers as parameters
        for(int i = 0; i <= minRabbits+randomNum.nextInt(maxRabbits-minRabbits); i++)
        {           
        	animals.add(new Rabbit("R"+i)); // build a new rabbit
        	animals.get(i).setID(i);
            numRabbits++; // increment number of rabbits in the system
            numAnimals++; // increment number of animals in the system
        }
        
        // randomly generate a number of foxes using the simulation configured max and min foxes numbers as parameters
        for(int i = 0; i <= randomNum.nextInt(maxFoxes); i++)
        {
        	animals.add(new Fox("F"+i)); // build a new fox
        	animals.get(animals.size()-1).setID(animals.size()-1);
        	numFoxes++; // increment number of foxes in the system
        	numAnimals++; // increment number of animals in the system
        } 
        // ******************************************************************************************************************************************************************************************************
        
        // configure generated animals' attributes***************************************************************************************************************************************************************
        // give animals random game board co-ords and store them as attributes
        for(int i = 0; i < animals.size(); i++)
        {
        	animals.get(i).setRow(randomNum.nextInt(totalRows));
        	animals.get(i).setColumn(randomNum.nextInt(totalColumns));
        }
        // ******************************************************************************************************************************************************************************************************
        
        // start simulation step*********************************************************************************************************************************************************************************
        for(int step = 0; step < numSteps; step++)
        {
        	// set up empty game board***************************************************************************************************************************************************************************
        	for(int i = 0; i < board.length; i++) // for all rows
        	{
        		for(int j = 0; j < board[0].length; j++) // for all columns
        		{
        			board[i][j] = null; // clear cell
        		}
        	}
        	// **************************************************************************************************************************************************************************************************
        	
        	// place animals on the board using their stored co-ord attributes***********************************************************************************************************************************
        	for(int i = 0; i < animals.size(); i++)
        	{
        		board[animals.get(i).getRow()][animals.get(i).getColumn()] = animals.get(i); // place animal on game board at it's configured co-ords
        	}     	
        	// **************************************************************************************************************************************************************************************************
        	
        	System.out.println("\nStep: "+(step+1));
            
            // press enter to initiate simulation step                            
            System.out.println("\nPress enter to print the game board for step "+(step+1)+": "); // Ask user to press enter to move to next round
            input.nextLine();
            
            // print out game board******************************************************************************************************************************************************************************
            for(int i = 0; i < board.length; i++) // for the number of rows on the game board
            {
                for(int j = 0; j < board[0].length; j++) // for the number of columns on the game board
                {
                    if(board[i][j] == null)
                    {
                        System.out.print("|  |"); // print blank space if no object is present
                    }
                    else
                    {
                        System.out.print("|"+board[i][j].getName()+"|"); // if there is an object in the cell, print out the name of the object
                    }
                }
                System.out.println();                
            }
            // **************************************************************************************************************************************************************************************************
            
            // begin animal AI
            // for each animal, assess its situation and make decisions
            for(int iD = 0; iD < animals.size(); iD++)
            {
       
            	// this animal brain*****************************************************************************************************************************************************************************
            	// this animals info
            	String name = new String(animals.get(iD).getName()); // name
            	Class cls = animals.get(iD).getClass(); // class info
            	String type = new String(cls.getName()); // class name
            	int row = animals.get(iD).getRow(); // row
            	int column = animals.get(iD).getColumn(); // column
            	int sight = animals.get(iD).getSight()/10; // sight
            	// other animal info
            	int safeRow = 0;
            	int safeColumn = 0;
            	int furthestDistance = 0;
            	int closestPrey = ((sight*2)+1);
            	int closestPredator = ((sight*2)+1);
            	int preyID = 0;
            	int predatorID = 0;
            	int otherRow = 0;
            	int otherColumn = 0;
            	String otherType = new String();
            	int rowDistance = 0;
            	int columnDistance = 0;
            	int otherDistance = 0;
            	String[] prey = new String[10];
            	String[] predators = new String[10];
            	String[] friends = new String[10];
            	int numPrey = 0;
            	int numPredators = 0;
            	int numFriends = 0;
            	boolean hunt = false;
            	boolean danger = false;
            	// *********************************************************************************************************************************************************************************************
            	
            	// this animal visual perimeters**************************************************************************************************************************************************************
            	int perimeterN = row - sight; // furthest cell this animal can see to the north
            	if(perimeterN < 0)
            	{
            		perimeterN = 0;
            	}
            	
            	int perimeterS = row + sight; // furthest cell this animal can see to the south
            	if(perimeterS > totalRows-1)
            	{
            		perimeterS = totalRows-1;
            	}
            	
            	int perimeterW = column - sight; // furtherst cell this animal can see to the west
            	if(perimeterW < 0)
            	{
            		perimeterW = 0;
            	}
            	
            	int perimeterE = column + sight; // furtherst cell this animal can see to the east
            	if(perimeterE > totalColumns-1)
            	{
            		perimeterE = totalColumns-1;
            	}
            	
            	// number of cells this animal can see in each direction
            	int perimeterDistanceN = Math.abs(perimeterN - row);
            	int perimeterDistanceS = Math.abs(perimeterS - row);
            	int perimeterDistanceW = Math.abs(perimeterW - column);
            	int perimeterDistanceE = Math.abs(perimeterE - column);
            	// ******************************************************************************************************************************************************************************************
            	
            	// press enter to continue                           
                System.out.println("\nPress enter to see "+name+"'s attributes"); // Ask user to press enter
                input.nextLine();
                System.out.println();
                
            	System.out.println("Type: "+type);
            	System.out.println("Name: "+name);
            	System.out.println("Location: "+row+", "+column);
            	System.out.println("Sight: "+sight);
            	System.out.println("Perimeter: "+perimeterN+"N, "+perimeterS+"S, "+perimeterW+"W, "+perimeterE);
            	System.out.println("Distance to Perimeter: "+perimeterDistanceN+", "+perimeterDistanceS+", "+perimeterDistanceW+", "+perimeterDistanceE);
            	System.out.println();
            	
            	// press enter to continue                           
                System.out.println("\nPress enter to see "+name+"'s thoughts: "); // Ask user to press enter
                input.nextLine();
                System.out.println();
        		        	
            	// check perimeter for other animals******************************************************************************************************************************************************
            	for(int i = perimeterN; i <= perimeterS; i++)
            	{
            		for(int j = perimeterW; j <= perimeterE; j++)
            		{
            			if(board[i][j] != null)
            			{
            				// other animal attributes****************************************************************************************************************************************************
                        	int otherID = board[i][j].getID(); // ID
            				String otherName = new String(animals.get(otherID).getName()); // name
                        	Class otherCls = animals.get(otherID).getClass(); // class info
                        	otherType = otherCls.getName(); // class name
                        	otherRow = animals.get(otherID).getRow(); // row
                        	otherColumn = animals.get(otherID).getColumn(); // column
                        	rowDistance = Math.abs(row - otherRow);
                        	columnDistance = Math.abs(column - otherColumn);
                        	otherDistance = Math.abs(rowDistance)+Math.abs(columnDistance);
                        	// ****************************************************************************************************************************************************************************
                        	
                        	// this animals relative information on other animals it can see***************************************************************************************************************
                        	if(type.equals(otherType)) // if other animal is the same type of animal as this animal
                        	{
                        		friends[numFriends] = otherName; // friend
                        		numFriends++; // increment number of friends in this animals sight
                        	}
                        	else
                        	{
                        		if(type.equals("Rabbit") && otherType.equals("Fox")) // if this animal is a rabbit and other animal is a fox
                        		{
                        			predators[numPredators] = otherName; // predator
                        			numPredators++; // increment number of predators in this animals sight
                        			danger = true; // set this animal to danger mode
                        			System.out.println(name+" sees "+otherName);
                        			System.out.println(otherName+" Location: "+otherRow+", "+otherColumn);
                        			
                        			if(otherDistance < closestPredator)
                        			{
                        				predatorID = otherID;
                        				closestPredator = otherDistance;
                        			}
                        		}
                        		else if(type.equals("Fox") && otherType.equals("Rabbit")) // if this animal is a fox and other animal is a rabbit
                        		{
                        			prey[numPrey] = otherName; // prey
                        			numPrey++; // increment number of prey in this animals sight
                        			hunt = true; // set this animal to hunt mode
                        			
                        			if(otherDistance < closestPrey)
                        			{
                        				preyID = otherID;
                        				closestPrey = otherDistance;
                        			}
                        		}
                        		
                        	}
                        	// *****************************************************************************************************************************************************************************                        	
            			}
            			
            		}
            	}
            	
            	// this animals state of mind
            	if(danger == true)
            	{
            		// try to get out of danger
            		// closest predator info
            		otherRow = animals.get(predatorID).getRow();
            		otherColumn = animals.get(predatorID).getColumn();
            		rowDistance = Math.abs(row - otherRow);
                	columnDistance = Math.abs(column - otherColumn);
                	otherDistance = Math.abs(rowDistance)+Math.abs(columnDistance);
                	
                	// find safe cell (cell furthest away from closest predator)
            		for(int i = perimeterN; i < perimeterS; i++)
            		{
            			for(int j = perimeterW; j < perimeterE; j++)
            			{
            				if(Math.abs(i-otherRow) > rowDistance)
            				{
            					safeRow = i;
            				}
            				
            				if(Math.abs(j-otherColumn) > columnDistance)
            				{
            					safeColumn = j;
            				}
            			}
            		}

            		
            	}
            	else if(danger == false && hunt == true)
            	{
            		// try to catch the prey
            		// closest prey info
            		otherRow = animals.get(preyID).getRow();
            		otherColumn = animals.get(preyID).getColumn();
            		rowDistance = Math.abs(row - otherRow);
                	columnDistance = Math.abs(column - otherColumn);
                	otherDistance = Math.abs(rowDistance)+Math.abs(columnDistance);
	
            		if(row > otherRow)
            		{
            			row = row-1;
            		}
            		else
            		{
            			row = row+1;
            		}
            		
            		if(column > otherColumn)
            		{
            			column = column-1;
            		}
            		else
            		{
            			column = column+1;
            		}
            	}
            	else
            	{
            		System.out.println(name+" is just wandering the field without a care");
            	}
            	
            	// end of this animals turn
            	
            	
            }
            
            // press enter to continue                           
            System.out.println("\nPress enter to continue to next simulation step: "); // Ask user to press enter
            input.nextLine();
            System.out.println();
        }
        
        
        
        
    }        
}     

