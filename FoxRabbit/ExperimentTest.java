
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
        Animal[][] board = new Animal[totalRows][totalColumns]; // memory for game board animal overlay
        
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
        		board[animals.get(i).getRow()][animals.get(i).getColumn()] = animals.get(i); // place animal on game board at configured animal co-ords
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
            
            // begin animal turn
            // for each animal, assess its situation and make decisions
            for(int iD = 0; iD < animals.size(); iD++)
            {
       
            	// this animals' brain
            	// this animals self awareness*********************************************************************************************************************************************************************
            	String name = new String(animals.get(iD).getName()); // name of this
            	Class cls = animals.get(iD).getClass(); // class info of this
            	String type = new String(cls.getName()); // class name of this
            	int row = animals.get(iD).getRow(); // row of this
            	int column = animals.get(iD).getColumn(); // column of this
            	int sight = animals.get(iD).getSight()/10; // sight of this
            	int safeRow = 0; // if a predator is near this animal will look for the furthest row away from predator within this animals sight
            	int safeColumn = 0; // if a predator is near this animal will look for the furthest row away from predator within this animals sight
            	int furthestDistance = 0; // if a predator is near this animal will look for the cell that gets this animal furthest from predator
            	// **********************************************************************************************************************************************************************************************
            	
            	// short term memory of this animal**************************************************************************************************************************************************************
            	int otherRow = 0; // the row of the other animal currently in focus
            	int otherColumn = 0; // the column of the other animal currently in focus
            	Class otherCls; // class info of the other animal currently in focus
            	String otherType = new String(); // the type of the other animal currently in focus
            	int rowDistance = 0; // the row distance from this animal of the other animal currently in focus
            	int columnDistance = 0; // the column distance from this animal of the other animal currently in focus
            	int otherDistance = 0; // the total distance from this animal of the other animal currently in focus
            	// ***********************************************************************************************************************************************************************************************
            	
            	// long term memory of this animal*************************************************************************************************************************************************************
            	int closestPrey = ((sight*2)+1); // closest prey to this animal
            	int closestPredator = ((sight*2)+1); // closest predator to this animal
            	int preyID = 0; 
            	int predatorID = 0; 
            	int[] prey = new int[10];
            	int[] predators = new int[10];
            	int[] friends = new int[10];
            	int numPrey = 0;
            	int numPredators = 0;
            	int numFriends = 0;
            	boolean hunt = false;
            	boolean danger = false;
            	// *********************************************************************************************************************************************************************************************
            	
            	// this animals visual perimeters**************************************************************************************************************************************************************
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
                System.out.println("\nPress enter to see "+name+"'s self awareness"); // Ask user to press enter
                input.nextLine();
                System.out.println();
                
                // this animals' self awareness
            	System.out.println("Type: "+type);
            	System.out.println("Name: "+name);
            	System.out.println("Location: "+row+", "+column);
            	System.out.println("Sight: "+sight);
            	System.out.println("Perimeter: "+perimeterN+"N, "+perimeterS+"S, "+perimeterW+"W, "+perimeterE+"E");
            	System.out.println("Distance to Perimeter: "+perimeterDistanceN+", "+perimeterDistanceS+", "+perimeterDistanceW+", "+perimeterDistanceE);
            	System.out.println();
            	
            	// press enter to continue                           
                System.out.println("\nPress enter to see "+name+"'s awareness of prey and predators: "); // Ask user to press enter
                input.nextLine();
                System.out.println();
        		        	
            	// check perimeter for other animals******************************************************************************************************************************************************
            	for(int i = perimeterN; i <= perimeterS; i++)
            	{
            		for(int j = perimeterW; j <= perimeterE; j++)
            		{
            			if(board[i][j] != null)
            			{
            				// other animal info********************************************************************************************************************************************************
                        	int otherID = board[i][j].getID(); // ID
            				String otherName = new String(animals.get(otherID).getName()); // name
                        	otherCls = animals.get(otherID).getClass(); // class info
                        	otherType = otherCls.getName(); // class name
                        	otherRow = animals.get(otherID).getRow(); // row
                        	otherColumn = animals.get(otherID).getColumn(); // column
                        	rowDistance = Math.abs(row - otherRow);
                        	columnDistance = Math.abs(column - otherColumn);
                        	// to find total distance we need to take into account the diagnal direction
                        	if(rowDistance > columnDistance)
                        	{
                        		otherDistance = rowDistance;
                        	}
                        	else if(columnDistance > rowDistance)
                        	{
                        		otherDistance = columnDistance;
                        	}
                        	else
                        	{
                        		otherDistance = rowDistance;
                        	}
                        	// ****************************************************************************************************************************************************************************
                        	
                        	// this animals relative information on other animals it can see***************************************************************************************************************
                        	if(type.equals(otherType)) // if other animal is the same type of animal as this animal
                        	{
                        		friends[numFriends] = otherID; // friend
                        		numFriends++; // increment number of friends in this animals sight
                        	}
                        	else
                        	{
                        		if(type.equals("Rabbit") && otherType.equals("Fox")) // if this animal is a rabbit and other animal is a fox
                        		{
                        			predators[numPredators] = otherID; // predator
                        			numPredators++; // increment number of predators in this animals sight
                        			danger = true; // set this animal to danger mode
                        			System.out.println(name+" sees a "+otherType+" at cell "+otherRow+", "+otherColumn);
                        			System.out.println("The "+otherType+" is "+rowDistance+" rows and "+columnDistance+" columns away from this "+type);
                        			System.out.println("Total Distance: "+otherDistance);
                        			
                        			if(otherDistance < closestPredator)
                        			{
                        				predatorID = otherID;
                        				closestPredator = otherDistance;
                        			}
                        		}
                        		else if(type.equals("Fox") && otherType.equals("Rabbit")) // if this animal is a fox and other animal is a rabbit
                        		{
                        			prey[numPrey] = otherID; // prey
                        			numPrey++; // increment number of prey in this animals sight
                        			hunt = true; // set this animal to hunt mode
                        			System.out.println(name+" sees a "+otherType+" at cell "+otherRow+", "+otherColumn);
                        			System.out.println("The "+otherType+" is "+rowDistance+" rows aotherDistancend "+columnDistance+" columns away from this "+type);
                        			System.out.println("Total Distance: "+otherDistance);
                        			
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
            	if(danger == true) // if animal is in danger
            	{
            		// try to get out of danger
            		// closest predator info
            		otherCls = animals.get(predatorID).getClass();
            		otherType = otherCls.getName();
            		otherRow = animals.get(predatorID).getRow();
            		otherColumn = animals.get(predatorID).getColumn();
            		rowDistance = Math.abs(row - otherRow);
                	columnDistance = Math.abs(column - otherColumn);
                	// to find total distance we need to take into account the diagnal direction
                	if(rowDistance > columnDistance)
                	{
                		otherDistance = rowDistance;
                	}
                	else if(columnDistance > rowDistance)
                	{
                		otherDistance = columnDistance;
                	}
                	else
                	{
                		otherDistance = rowDistance;
                	}
                	
                	System.out.println(name+" is trying to escape from a "+otherType+" that is "+otherDistance+" cells away");
                	
                	// escape predator
                	// check all cells within 1 move of this animal and move to the first one that makes the distance between this animal and other animal greater than before
                	outerloop:
                	for(int i = row-1; i <= row+1; i++)
                	{
                		for(int j = column-1; j <= column+1; j++)
                		{
                			int newRowDistance = Math.abs(i - otherRow);
                			int newColumnDistance = Math.abs(j - otherColumn);
                			int newDistance = 0;
                			if(newRowDistance > newColumnDistance)
                			{
                				newDistance = newRowDistance;
                			}
                			else if(newColumnDistance > newRowDistance)
                			{
                				newDistance = newColumnDistance;
                			}
                			else
                			{
                				newDistance = newRowDistance;
                			}
                			
                			if(newDistance > otherDistance) // if new cell put this animal further away from other animal, move to new cell
                			{
                				animals.get(iD).setRow(i);
                				animals.get(iD).setColumn(j);
                				System.out.println(name+" will move to cell: "+i+", "+j+" to get further away from the other animal at a new distance of "+newDistance+" cells");
                				break outerloop;
                			}
                		}
                	}

            		
            	}
            	else if(danger == false && hunt == true) // if animal sees prey and is not in danger
            	{
            		// try to catch the prey
            		// closest prey info
            		otherCls = animals.get(preyID).getClass();
            		otherType = otherCls.getName();
            		otherRow = animals.get(preyID).getRow();
            		otherColumn = animals.get(preyID).getColumn();
            		rowDistance = Math.abs(row - otherRow);
                	columnDistance = Math.abs(column - otherColumn);
                	// to find total distance we need to take into account the diagnal direction
                	if(rowDistance > columnDistance)
                	{
                		otherDistance = rowDistance;
                	}
                	else if(columnDistance > rowDistance)
                	{
                		otherDistance = columnDistance;
                	}
                	else
                	{
                		otherDistance = rowDistance;
                	}
                	
                	System.out.println(name+" is trying to catch a "+otherType+" that is "+otherDistance+" cells away");
	
            		// chase prey
                	
            	}
            	else
            	{
            		System.out.println(name+" unaware of any prey or predators");
            	}
            	
            	// end of this animals turn
            	// press enter to continue
            	System.out.println("\nPress enter to go to next animal: ");
            	input.nextLine();
            	
            }
            
            // end of simulation step
            // press enter to continue                           
            System.out.println("\nPress enter to continue to next simulation step: "); // Ask user to press enter
            input.nextLine();
            System.out.println();
        }
        
        
        
        
    }        
}     

