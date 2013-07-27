
/**
 * Building human behaviour based characters
 * 
 * @author Shane Farrell 
 * @version 1.0
 * @date 19/06/13
 */
import java.util.*;

public class Human extends Animal
{       
    public static int numHumans = 0; // number of humans in a scene
    // primary personality traits
    private int openness, conscientiousness, extraversion, agreeableness, neuroticism;
    private int imagination, aestheticSensitivity, emotionalIntensity, curiosity; //openness
    private int selfDiscipline, carefulness, thoroughness, deliberation, recognitionDesire; // conscientiousness
    private int talkativeness, assertitiveness, gregariousness; // extraversion
    private int empathy, trustfulness, compliance, modesty; // agreeableness
    private int anxiousness, selfConsciousness, shyness; // neuroticism 
    // secondary personality traits
    private int bravery, dominance, timidness, perception;            
    /**
     * Default constructor for objects of class Human
     */
    public Human()
    {        
        super("Human");
        numHumans = numHumans+1;
        Random randomNum = new Random();
        // primary traits
        imagination = 30+randomNum.nextInt(40);
        aestheticSensitivity = 30+randomNum.nextInt(40);
        emotionalIntensity = 30+randomNum.nextInt(40);
        curiosity = 30+randomNum.nextInt(40);
        selfDiscipline = 30+randomNum.nextInt(40);
        carefulness = 30+randomNum.nextInt(40);
        thoroughness = 30+randomNum.nextInt(40);
        deliberation = 30+randomNum.nextInt(40);
        recognitionDesire = 30+randomNum.nextInt(40);
        talkativeness = 30+randomNum.nextInt(40);
        assertitiveness = 30+randomNum.nextInt(40);
        gregariousness = 30+randomNum.nextInt(40);
        empathy = 30+randomNum.nextInt(40);
        trustfulness = 30+randomNum.nextInt(40);
        compliance = 30+randomNum.nextInt(40);
        modesty = 30+randomNum.nextInt(40);
        anxiousness = 30+randomNum.nextInt(40);
        selfConsciousness = 30+randomNum.nextInt(40);
        shyness = 30+randomNum.nextInt(40);
        // seconday traits
        openness = (imagination+aestheticSensitivity+emotionalIntensity+curiosity)/4;
        conscientiousness = (selfDiscipline+carefulness+thoroughness+deliberation+recognitionDesire)/5;
        extraversion = (talkativeness+assertitiveness+gregariousness)/3;
        agreeableness = (empathy+trustfulness+compliance+modesty)/4;
        neuroticism = (anxiousness+selfConsciousness+shyness)/3;
        bravery = (recognitionDesire+assertitiveness+empathy+trustfulness)/4-(anxiousness+selfConsciousness)/2;
        dominance = (assertitiveness)-(empathy+trustfulness)/2;
        timidness = (compliance+shyness+anxiousness+selfConsciousness)/4-assertitiveness;
        perception = (imagination+curiosity)/2;                
    }
    
    /**
     * Constructor for objects of class Human that takes a name param
     */
    public Human(String newName)
    {        
        super(newName, "Human");
        numHumans = numHumans+1;
        Random randomNum = new Random();
        // primary traits
        imagination = 30+randomNum.nextInt(40);
        aestheticSensitivity = 30+randomNum.nextInt(40);
        emotionalIntensity = 30+randomNum.nextInt(40);
        curiosity = 30+randomNum.nextInt(40);
        selfDiscipline = 30+randomNum.nextInt(40);
        carefulness = 30+randomNum.nextInt(40);
        thoroughness = 30+randomNum.nextInt(40);
        deliberation = 30+randomNum.nextInt(40);
        recognitionDesire = 30+randomNum.nextInt(40);
        talkativeness = 30+randomNum.nextInt(40);
        assertitiveness = 30+randomNum.nextInt(40);
        gregariousness = 30+randomNum.nextInt(40);
        empathy = 30+randomNum.nextInt(40);
        trustfulness = 30+randomNum.nextInt(40);
        compliance = 30+randomNum.nextInt(40);
        modesty = 30+randomNum.nextInt(40);
        anxiousness = 30+randomNum.nextInt(40);
        selfConsciousness = 30+randomNum.nextInt(40);
        shyness = 30+randomNum.nextInt(40);
        // seconday traits
        openness = (imagination+aestheticSensitivity+emotionalIntensity+curiosity)/4;
        conscientiousness = (selfDiscipline+carefulness+thoroughness+deliberation+recognitionDesire)/5;
        extraversion = (talkativeness+assertitiveness+gregariousness)/3;
        agreeableness = (empathy+trustfulness+compliance+modesty)/4;
        neuroticism = (anxiousness+selfConsciousness+shyness)/3;
        bravery = (recognitionDesire+assertitiveness+empathy+trustfulness)/4-(anxiousness+selfConsciousness)/2;
        dominance = (assertitiveness)-(empathy+trustfulness)/2;
        timidness = (compliance+shyness+anxiousness+selfConsciousness)/4-assertitiveness;
        perception = (imagination+curiosity)/2;                
    }

    /**
     * Get number of humans in a scene
     * 
     * @param 
     * @return int 
     */
    public int getNumHumans()
    {
        return numHumans;
    }
    
    /**
     * Get imagination
     * 
     * @param 
     * @return int 
     */
    public int getImagination()
    {
        return imagination;
    }
    
    /**
     * Get aestheticSensitivity
     * 
     * @param 
     * @return int 
     */
    public int getAestheticSensitivity()
    {
        return aestheticSensitivity;
    }
    
    /**
     * Get emotionalIntensity
     * 
     * @param 
     * @return int 
     */
    public int getEmotionalIntensity()
    {
        return emotionalIntensity;
    }
    
    /**
     * Get curiosity
     * 
     * @param 
     * @return int  
     */
    public int getCuriosity()
    {
        return curiosity;
    }                
    
    /**
     * Check if this Human sees another Human
     * 
     * @param int, int
     * @return int
     */
    public int visualAwareness(int crowdSize, int proximity)
    {               
        int currentSight = getSight();
        if(getAlertness() < 20)
        {
            currentSight = 0;
        }
        
        int awareness = (getAlertness()+currentSight)/2-(proximity+crowdSize*3)/2;
        
        if(getAlertness() > 20 && getSight() > 5 & proximity <= 5)
        {
            System.out.println(getName()+" is extremely aware of you");
        }
        else if(awareness >= 80) 
        {
            System.out.println(getName()+" is extremely aware of you");
        }
        else if(awareness >= 60 && awareness < 80) 
        {
            System.out.println(getName()+" is very aware of you");
        }
        else if(awareness >=40 && awareness < 60) 
        {
            System.out.println(getName()+" is aware of you");
        }
        else if(awareness >=20 && awareness < 40)
        {
            System.out.println(getName()+" is barely aware of you");
        }
        else
        {
            System.out.println(getName()+" is not aware of you");
        }
        
        return awareness;
    }
    
    /**
     * Check dominance
     * 
     * @param
     * @return
     */
    public void dominance()
    {
        // check dominance
        if(dominance >= 80)
        {
            System.out.println(getName()+" is a complete bully. He will try to bully everybody around him that are weaker than him");
        }
        else if(dominance >= 60 && dominance < 80)
        {
            System.out.println(getName()+" is a bully. He will try to bully people that are weaker than him and have a timidness rating above 40.");
        }
        else if(dominance >= 40 && dominance < 60)
        {
            System.out.println(getName()+" is a bully. He will try to bully people that are weaker than him have a timidness rating above 60.");
        }
        else if(dominance >= 20 && dominance < 40)
        {
            System.out.println(getName()+" has bullying tendances. He will only try to bully people that are weaker than him and  have a timidness rating over 80.");
        }
        else
        {
            System.out.println(getName()+" is not a bully");
        }
    }
    
    /**
     * Status check
     * 
     * @param
     * @return
     */
    public void status()
    {
        dominance();
        alertness();
    }
}
