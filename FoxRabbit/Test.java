
/**
 * A place to test agent based modelling.
 * 
 * @author Shane Farrell 
 * @version 1.0
 * @date 24/06/13
 */
import java.util.*;
import java.lang.Math;

public class Test
{
    

    /**
     * Constructor for object
     */
    public Test()
    {
        
    }

    /**
     * Main method - general testing area for agent behaviour on 2D game board environment
     * 
     */
    public static void main(String args[])
    {                             
        // pre-simulation set-up*************************************************************************************************************************************************************************************
    	
    	// generate board
        Random randomNum = new Random(); // random number generator for general use across simulation
        Scanner input = new Scanner(System.in); // user input for step forward in simulation
        int totalRows = 20; // number of rows on game board
        int totalColumns = 20; // number of columns on game board
        int numSteps = 30; // number of steps to go into the simulation before stopping
        Animal[][] board = new Animal[totalRows][totalColumns]; // prepare the empty game board
        ArrayList<Rabbit> rabbits = new ArrayList<Rabbit>(); // storage for rabbits in the system
        ArrayList<Fox> foxes = new ArrayList<Fox>(); // storage for foxes in the system     
        
        
        // generate rabbits
        // randomly generate 4 to 8 rabbits
        for(int i = 0; i <= 4+randomNum.nextInt(4); i++)
        {
            rabbits.add(new Rabbit("R"+i)); // build a new rabbit
            rabbits.get(i).setID(i); // set this rabbits ID
        }        
        
        // give rabbits' random game board co-ords
        for(int i = 0; i < rabbits.size(); i++) // for the number of rabbits in the system, give them all random game board co-ords
        {
            int row = randomNum.nextInt(totalRows); // random row
            int column = randomNum.nextInt(totalColumns); // random column
            rabbits.get(i).setRow(row); // store this rabbit's row in it's attributes
            rabbits.get(i).setColumn(column); // store this rabbit's column in it's attributes            
        }
        
        
        // generate foxes
        // randomly generate a number of foxes (currently set to 1 fox for testing puposes)
        for(int i = 0; i < 1; i++)
        {
            foxes.add(new Fox("F"+i)); // build a new fox
            foxes.get(i).setID(i); // set this foxes ID
        }
        
        // give foxes random game board co-ords
        for(int i = 0; i < foxes.size(); i++) // for the number of foxes in the game, place them randomly around the game board
        {
            int row = randomNum.nextInt(totalRows); // generate a random number between 0 and number of rows
            int column = randomNum.nextInt(totalColumns); // generate a random number between 0 and number of columns
            foxes.get(i).setRow(row); // store this foxes row in it's attributes
            foxes.get(i).setColumn(column); // store this foxes column in it's attributes            
        }
        
        
        
        // simulation start******************************************************************************************************************************************************************************************
        
        for(int step = 0; step < numSteps; step++)
        {
            // set up empty game board
            for(int i = 0; i < board.length; i++)
            {
                for(int j = 0; j < board[0].length; j++)
                {
                    board[i][j] = null;
                }
            }
            
            // place foxes on game board using their stored co-ord attributes
            for(int i = 0; i < foxes.size(); i++)
            {
                board[foxes.get(i).getRow()][foxes.get(i).getColumn()] = foxes.get(i); // place fox on game board
            }
            // place rabbits on game board using their stored co-ord attributes
            for(int i = 0; i < rabbits.size(); i++)
            {
                board[rabbits.get(i).getRow()][rabbits.get(i).getColumn()] = rabbits.get(i); // place rabbit on game board
            }
            
            System.out.println("\nStep: "+(step+1));
            
            // press enter to initiate simulation step                            
            System.out.println("\nPress enter to print the game board for step "+(step+1)+": "); // Ask user to press enter to move to next round
            input.nextLine();
            
            // print out game board
            for(int i = 0; i < board.length; i++) // for the number of rows on the game board
            {
                for(int j = 0; j < board[0].length; j++) // for the number of columns on the game board
                {
                    if(board[i][j] == null)
                    {
                        System.out.print("|      |"); // print blank space if no object is present
                    }
                    else
                    {
                        System.out.print("|  "+board[i][j].getName()+"  |"); // if object with name consisting of 1 character is present, print this string
                    }
                }
                System.out.println();                
            }
            
            // press enter to initiate simulation step                            
            System.out.println("\nPress enter to see the info for step "+(step+1)+": "); // Ask user to press enter to move to next round
            input.nextLine();
            
            // pre-step set-up
            // set up the fox variables storage for this step
            boolean hunt = false; // variable to decide wether fox will hunt or not, set to true when fox has a rabbit in sight
            int foxRow = 0; // fox row
            int foxColumn = 0; // fox column
            int foxSight = 0; // fox sight
            int foxViewNorth = 0; // number of cells to the north the fox can see
            int foxViewSouth = 0; // number of cells to the south the fox can see
            int foxViewEast = 0; // number of cells to the east the fox can see
            int foxViewWest = 0; // number of cells to the west the fox can see
            Animal[] rabbitsInSight = new Animal[rabbits.size()]; // fox memory for rabbits within sight
            int numRabbitsInSight = 0;
            Animal[] closestRabbit = new Animal[rabbits.size()]; // fox memory for closest rabbit
            int closestDistance = 0;
      
            // set up the rabbits' variables storage for this step
            int rabbitRow = 0; // rabbit row
            int rabbitColumn = 0; // rabbit column
            int rowDistance = 0; // number of rows rabbit is from fox
            int columnDistance = 0; // number of columns rabbit is from fox
            int totalDistance = 0; // total distance rabbit is from fox
            
            
            // fox will begin looking for rabbits
            for(int foxCount = 0; foxCount < foxes.size(); foxCount++) // for all foxes, have fox search for rabbits
            {
            	// current fox attributes
                foxRow = foxes.get(foxCount).getRow(); // fox row
                foxColumn = foxes.get(foxCount).getColumn(); // fox column
                foxSight = foxes.get(foxCount).getSight()/10; // fox sight
                closestDistance = (foxSight*2)+1; // closest rabbit distance
                
                // calibrate vision radius for this fox
                foxViewNorth = foxRow - foxSight; 
                if(foxViewNorth < 0)
                {
                	foxViewNorth = 0;
                }
                foxViewSouth = foxRow + foxSight;
                if(foxViewSouth > totalRows-1)
                {
                	foxViewSouth = totalRows-1;
                }
                foxViewWest = foxColumn - foxSight;
                if(foxViewWest < 0)
                {
                	foxViewWest = 0;
                }
                foxViewEast = foxColumn + foxSight;
                if(foxViewEast > totalColumns-1)
                {
                	foxViewEast = totalColumns-1;
                }

                
                System.out.println("Fox Sight: "+foxSight); // print foxes sight attribute value
                
                // hunt set-up
                // check around foxes vision radius for rabbits
                for(int i = foxViewNorth; i <= foxViewSouth; i++) // for the foxes vision radius along the rows
                {
                    for(int j = foxViewWest; j <= foxViewEast; j++) // for the foxes vision radius along the columns
                    {
                    	if(board[i][j] != null) // if there is something in a cell, check if it's a rabbit
                    	{
                    		Class cls = board[i][j].getClass(); // check what type of object is in sight
                    		
                    		// rabbit attributes
                    		rabbitRow = board[i][j].getRow(); // rabbit row
                            rabbitColumn = board[i][j].getColumn(); // rabbit column
                            rowDistance = Math.abs(rabbitRow - foxRow); // rows rabbit is from fox
                            columnDistance = Math.abs(rabbitColumn - foxColumn); // columns rabbit is from fox
                            totalDistance = Math.abs(rowDistance) + Math.abs(columnDistance); // total distance rabbit is from fox
                        	
                        	if(cls.getName() == "Rabbit") 
                        	{
                        		rabbitsInSight[numRabbitsInSight] = board[i][j]; // found rabbit within vision radius
                        		numRabbitsInSight = numRabbitsInSight+1; // increment number of rabbits currently in vision radius by 1
                        		hunt = true;
                        		
                        		// find the closest rabbit to this fox
                        		if(totalDistance < closestDistance)
                        		{
                        			closestRabbit[0] = board[i][j]; // set the closest rabbit into fox memory
                        			closestDistance = totalDistance; // update the closest distance with this rabbit's distance
                        		}
                        	}
                    	}                	
                    }
                }
                    // finish hunt set-up          
                    // hunt
                    if(hunt == true)
                    {
                    	// print info about this foxes hunt set-up
                        // print out info on rabbits within vision radius of this fox            
                        System.out.print("Rabbits within sight of the fox: ");
                        for(int count = 0; count < numRabbitsInSight; count++)
                        {
                        	System.out.print(rabbitsInSight[count].getName()+", ");
                        }
                        
                        System.out.println();
                        
                        // print info on closest rabbit
                        if(closestRabbit.length > 0)
                        {
                        	System.out.println("The closest rabbit to the fox is: "+closestRabbit[0].getName());
                        }
                    	
                    	
                    	
                    	// rabbit attributes
                		rabbitRow = closestRabbit[0].getRow(); // rabbit row
                        rabbitColumn = closestRabbit[0].getColumn(); // rabbit column
                        rowDistance = Math.abs(rabbitRow - foxRow); // rows rabbit is from fox
                        columnDistance = Math.abs(rabbitColumn - foxColumn); // columns rabbit is from fox
                        totalDistance = Math.abs(rowDistance) + Math.abs(columnDistance); // total distance rabbit is from fox                
                        
                        // get fox to chase the closest rabbit
                        // chase along row
                        if(foxRow < rabbitRow)
                        {
                        	foxRow = foxRow+1;
                        }
                        else if(foxRow > rabbitRow)
                        {
                        	foxRow = foxRow-1;
                        }
                        // chase along column
                        if(foxColumn < rabbitColumn)
                        {
                        	foxColumn = foxColumn+1;
                        }
                        else if(foxColumn > rabbitColumn)
                        {
                        	foxColumn = foxColumn-1;
                        }
                        
                        // update foxes position on the game board for the next step
                        foxes.get(foxCount).setRow(foxRow);
                        foxes.get(foxCount).setColumn(foxColumn);
                        
                        // if the fox reaches a cell with a rabbit on it, fox kills the rabbit
                        if(foxes.get(foxCount).getRow() == rabbitRow && foxes.get(foxCount).getColumn() == rabbitColumn)
                        {
                        	rabbits.remove(closestRabbit[0]);
                        }
                    }
                    else // if fox has no rabbits within sight, move around
                    {
                    	if(foxRow <= 10)
                    	{
                    		foxes.get(foxCount).setRow(foxRow+1);
                    	}
                    	else
                    	{
                    		foxes.get(foxCount).setRow(foxRow-1);
                    	}
                    	
                    	if(foxColumn <= 10)
                    	{
                    		foxes.get(foxCount).setColumn(foxColumn+1);
                    	}
                    	else
                    	{
                    		foxes.get(foxCount).setColumn(foxColumn-1);
                    	}
                    }
                    
                }
        	}
          
        }        
    }     

