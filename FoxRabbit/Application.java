
/**
 * Main application
 * 
 * @author Shane Farrell 
 * @version 1.0
 * @date 19/06/13
 */
import java.util.*;

public class Application
{
    // instance variables - replace the example below with your own
    

    /**
     * Constructor for objects of class Application
     */
    public Application()
    {
        // initialise instance variables
        
    }

    /**
     * Main method
     * 
     * @param 
     * @return 
     */
    public static void main (String args[])
    {
        // prepare for user input
        Scanner input = new Scanner(System.in);
        // set the scene
        System.out.println(
        "You are dazed and confused. \n"
        +"Unaware of your location, you fill with fear and begin to feel extremely weak and nauseous. \n" 
        +"You look around with squinted, blurry eyes. They are painfully pierced by an unidentifiable light source. \n"
        +"As your eyes come in to focus, all of your other senses slowly start to fill in the scene. \n"
        +"The sound of hundreds of rush hour cars penetrate your ears causing you to cover them with your hands to prevent your ear drum erupting. \n"
        +"Police sirens can be heard deep in the background, below the pulsating noises of cars and people. \n"
        +"You are certain you are in a city, but is it a familiar city? \n"
        +"In the lack of natural light, neon lights engulf your peripheral vision as you focus in on the streaming traffic bussling past you in the heavy rain. \n"
        +"People are everywhere, heads covered with umberellas and brief cases. \n"
        +"They brush past you, hardly noticing you as they focus on getting out of the rain. \n\n" 
        );
        System.out.println("Make a decision: "); // ask player to make a decision
        // list the decisions
        System.out.println(
        "A: Ask a passer by for help. \n"
        +"B: Look for a quiet place to stand. \n"
        +"C: Push a passer by aggressively. \n"
        +"D: Run into traffic. \n"
        +"E: Look around to see if anybody notices you. \n"
        );
        System.out.println("To make a decision, input the corresponding letter and press enter: ");
        String choice = new String(input.nextLine());
        // use player choice to set the next scene
        if(choice.charAt(0)=='A' | choice.charAt(0)=='a')
        {
            System.out.println("\nYou desperately plead with a stranger for help. \n"
            +"A woman in her mid-twenties clutches her purse tighter and looks at you with worry as she speeds past. \n"
            +"A man dressed in a formal business suit notices the situation and approaches you. \n"
            +"\"Are you ok\" the man says to you. More out of protection for the woman than concern for you. \n"
            );
            System.out.println("Make a decision: "); // ask player to make a decision
            // list the decisions
            System.out.println(
            "A: Tell the man you don't know who you are or where you are. \n"
            +"B: Shout after the woman for help. \n"
            +"C: Ignore both the man and woman. Look around for another person \n"
            +"D: Ask the man for help. \n"
            +"E: Back away from the man and prepare to run. \n"
            );
            System.out.println("To make a decision, input the corresponding letter and press enter: ");
            choice = new String(input.nextLine());
            // use player choice to set the next scene
                if(choice.charAt(0)=='A' | choice.charAt(0)=='a')
                {
                    System.out.println("\nYou tell the man you don't know who you are or where you are. \n"
                    +"He looks at you in shock. \n"
                    +"\"How did you get here?\" he asks in a bemused tone. \n"
                    +"\"Did you have an accident\" he adds. \n"
                    );
                    System.out.println("Make a decision: "); // ask player to make a decision
                    // list the decisions
                    System.out.println(
                    "A: \"I don't know how I got here.\" \n"
                    +"B: \"I don't know who I am or where I am! How the fuck would I know how I got here!\" \n"
                    +"C: Ignore the man. \n"
                    +"D: Ask for help. \n"
                    +"E: Look around to see if anybody else notices you. \n"
                    );
                    System.out.println("To make a decision, input the corresponding letter and press enter: ");
                    choice = new String(input.nextLine());
                    // use player choice to set the next scene
                }
                
        }
        
    }
}
