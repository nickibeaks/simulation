
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
        // pre-simulation set-up
    	// generate board
        Random randomNum = new Random(); // random number generator for general use across simulation
        Scanner input = new Scanner(System.in); // user input for step forward in simulation
        int totalRows = 20; // number of rows on game board
        int totalColumns = 20; // number of columns on game board
        String[][] board = new String[totalRows][totalColumns]; // build the game board
        
        // generate rabbits
        ArrayList<Rabbit> rabbits = new ArrayList<Rabbit>(); // create an ArrayList to store Rabbits        
        for(int i = 0; i <= 4+randomNum.nextInt(4); i++) // for a random number of times between 1 and 10
        {
            rabbits.add(new Rabbit("R"+i)); // build a new rabbit
            rabbits.get(i).setID(i); // set this rabbits ID
        }        
        // place rabbits randomly around gameboard
        for(int i = 0; i < rabbits.size(); i++) // for the number of rabbits in the game, place them randomly around the game board
        {
            int row = randomNum.nextInt(totalRows); // random row
            int column = randomNum.nextInt(totalColumns); // random column
            rabbits.get(i).setRow(row); // store this rabbit's row in it's attributes
            rabbits.get(i).setColumn(column); // store this rabbit's column in it's attributes            
        }

        // generate foxes
        ArrayList<Fox> foxes = new ArrayList<Fox>(); // create an ArrayList to store Foxes        
        for(int i = 0; i < 1; i++) // for a random number of times
        {
            foxes.add(new Fox(" F"+i+" ")); // build a new fox
            foxes.get(i).setID(i); // set this foxes ID
        }
        // place foxes randomly around gameboard
        for(int i = 0; i < foxes.size(); i++) // for the number of foxes in the game, place them randomly around the game board
        {
            int row = randomNum.nextInt(totalRows); // generate a random number between 0 and number of rows
            int column = randomNum.nextInt(totalColumns); // generate a random number between 0 and number of columns
            foxes.get(i).setRow(row); // store this foxes row in it's attributes
            foxes.get(i).setColumn(column); // store this foxes column in it's attributes            
        }
        
        
        
        // simulation start
        int numSteps = 5; // number of steps to go into the simulation before stopping
        for(int step = 0; step < numSteps; step++)
        {
            // clear board
            for(int i = 0; i < board.length; i++)
            {
                for(int j = 0; j < board[0].length; j++)
                {
                    board[i][j] = null;
                }
            }
            
            // set up foxes on game board
            for(int i = 0; i < foxes.size(); i++)
            {
                board[foxes.get(i).getRow()][foxes.get(i).getColumn()] = foxes.get(i).getName(); // place foxes on game board
            }
            // set up rabbits on game board
            for(int i = 0; i < rabbits.size(); i++)
            {
                board[rabbits.get(i).getRow()][rabbits.get(i).getColumn()] = rabbits.get(i).getName(); // place rabbits on game board
            }
             
            // print out game board
            for(int i = 0; i < board.length; i++) // for the number of rows on the game board
            {
                for(int j = 0; j < board[0].length; j++) // for the number of columns on the game board
                {
                    if(board[i][j] == null)
                    {
                        System.out.print("|      |"); // print blank space if no object is present
                    }
                    else if(board[i][j].length() == 1)
                    {
                        System.out.print("|   "+board[i][j]+"  |"); // print object
                    }
                    else if(board[i][j].length() == 2)
                    {
                        System.out.print("|  "+board[i][j]+"  |"); // print object
                    }
                    else if(board[i][j].length() == 3)
                    {
                        System.out.print("|  "+board[i][j]+" |"); // print object
                    }
                    else if(board[i][j].length() == 4)
                    {
                        System.out.print("| "+board[i][j]+" |"); // print object
                    }
                }
                System.out.println();                
            }
            
            // press enter to initiate simulation step                
            
            System.out.println("Press enter to continue: "); // Ask user to press enter to move to next round
            input.nextLine();
            
            // pre-step set-up
            // set up the fox variables for this step
            boolean hunt = false; // variable to decide wether fox will hunt or not, set to false by default
            String foxName; // fox name
            int foxID = 0; // fox ID
            int foxRow = 0; // fox row
            int foxColumn = 0; // fox column
            int foxSight = 0; // fox sight
            ArrayList<Rabbit> rabbitsInSight = new ArrayList<Rabbit>(); // memory for rabbits within sight
            ArrayList<Rabbit> closestRabbit = new ArrayList<Rabbit>(); // memory for closest rabbit
            int closestDistance = 0;
      
            // set up the rabbits' variables for this step 
            String rabbitName; // rabbit name
            int rabbitID = 0; // rabbit ID
            int rabbitRow = 0; // rabbit row
            int rabbitColumn = 0; // rabbit column
            int rowDistance = 0; // rows rabbit is from fox
            int columnDistance = 0; // columns rabbit is from fox
            int totalDistance = 0; // total distance rabbit is from fox
            // closest rabbit's variables
            int closestRabbitID = 0;
            
            for(int foxCount = 0; foxCount < foxes.size(); foxCount++) // for all foxes, have fox search for rabbits
            {
                // current fox
            	foxName = foxes.get(foxCount).getName(); // fox name
                foxID = foxes.get(foxCount).getID(); // fox ID
                foxRow = foxes.get(foxCount).getRow(); // fox row
                foxColumn = foxes.get(foxCount).getColumn(); // fox column
                foxSight = foxes.get(foxCount).getSight()/10; // fox sight
                closestDistance = (foxSight*2)+1;

                
                System.out.println("Fox Sight: "+foxSight); // print foxes sight attribute value
                
                // current fox look for rabbits
                for(int rabbitCount = 0; rabbitCount < rabbits.size(); rabbitCount++) // for all rabbits
                {
                    // current rabbit
                	rabbitName = rabbits.get(rabbitCount).getName(); // rabbit name
                    rabbitID = rabbits.get(rabbitCount).getID(); // rabbit ID
                    rabbitRow = rabbits.get(rabbitCount).getRow(); // rabbit row
                    rabbitColumn = rabbits.get(rabbitCount).getColumn(); // rabbit column
                    rowDistance = Math.abs(rabbitRow - foxRow); // rows rabbit is from fox
                    columnDistance = Math.abs(rabbitColumn - foxColumn); // columns rabbit is from fox
                    totalDistance = Math.abs(rowDistance) + Math.abs(columnDistance); // total distance rabbit is from fox
                    
                    // check if rabbit is within sight of this fox
                    if(rowDistance <= foxSight & columnDistance <= foxSight) // if rabbit is within fox sight
                    {
                        // fox sees rabbit
                        rabbitsInSight.add(rabbits.get(rabbitCount)); // added to fox rabbits in sight memory
                        System.out.println("Fox sees "+rabbitName);
                    }
                    
                    // details on current rabbit's position relative to current fox
                    System.out.println(rabbitName+" is "+rowDistance+" rows and "+columnDistance+" columns from the fox"); // print the relative co-ordinate distance of the rabbit from the fox
                    
                    // check for closest rabbit
                    if(totalDistance < closestDistance) // if this rabbit is currently the closest rabbit to the fox
	                {
	                	closestRabbit.add(rabbits.get(rabbitCount)); // store this rabbit as the closest rabbit in the foxes memory
	                	closestDistance = totalDistance; // update the closest rabbit distance tracker with the new rabbits distance
	                	closestRabbitID = rabbitID;
	                }
                    
               	}       
                
        	}
            
            System.out.println("Fox sees "+rabbitsInSight.size()+" rabbits"); // print the number of rabbits within the foxes sight range
            if(rabbitsInSight.size() > 0) // if there are any rabbits witin the foxes sight range
            {
                System.out.println("The closest rabbit to the fox is "+closestRabbit.get(closestRabbit.size()-1).getName()); // print the name of the closest rabbit to the fox
                hunt = true;
            }
            
            if(hunt == true)
            {
            	// closest rabbit info
                String closestRabbitName = rabbits.get(closestRabbitID).getName(); // rabbit name
                int closestRabbitRow = rabbits.get(closestRabbitID).getRow(); // rabbit row
                int closestRabbitColumn = rabbits.get(closestRabbitID).getColumn(); // rabbit column
                int closestRabbitRowDistance = Math.abs(rabbitRow - foxRow); // rows rabbit is from fox
                int closestRabbitColumnDistance = Math.abs(rabbitColumn - foxColumn); // columns rabbit is from fox
                int closestRabbitTotalDistance = Math.abs(rowDistance) + Math.abs(columnDistance); // total distance rabbit is from fox                
                
                // get fox to chase the closest rabbit
                // chase along row
                if(foxRow < closestRabbitRow)
                {
                	foxRow = foxRow+1;
                }
                else if(foxRow > closestRabbitRow)
                {
                	foxRow = foxRow-1;
                }
                // chase along column
                if(foxColumn < closestRabbitColumn)
                {
                	foxColumn = foxColumn+1;
                }
                else if(foxColumn > closestRabbitColumn)
                {
                	foxColumn = foxColumn-1;
                }
                
                // update foxes position on the game board for the next step
                foxes.get(foxID).setRow(foxRow);
                foxes.get(foxID).setColumn(foxColumn);
                
                // if the fox reaches a cell with a rabbit on it, fox kills the rabbit
                if(foxes.get(foxID).getRow() == closestRabbitRow && foxes.get(foxID).getColumn() == closestRabbitColumn)
                {
                	rabbits.remove(closestRabbitID);
                }
            }
            
            
            

            
        }        
    }     
}
