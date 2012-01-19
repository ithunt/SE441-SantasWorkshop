import java.util.ArrayList;

/**
 * @author ian hunt
 * @date 1/16/12
 */
public class Main {
	
	//list of constants
	public static final int NUM_ELVES = 10;
	public static final int NUM_REINDEER = 9;
	public static final int INIT_DAYS_TO_XMAS = 364;
	public static final int ELF_COUNT_WORTH_SANTAS_ATTENTION = 3;
	public static final int MILLIS_PER_DAY = 15000;
	public static final double INIT_REINDEER_DELAY = INIT_DAYS_TO_XMAS * MILLIS_PER_DAY * .8;
    
    public static void main(String[] args)  {

        final ArrayList<Elf> elves = new ArrayList<Elf>();
        final ArrayList<Reindeer> reindeer = new ArrayList<Reindeer>();
        
        final Workshop workshop = new Workshop();
        final Santa santa = new Santa(workshop);
        
        for(int i=0; i < NUM_ELVES; i++ ) {
            elves.add(new Elf(workshop, santa));
        }
        
        for(int i=0; i < NUM_REINDEER; i++ ) {
            reindeer.add(new Reindeer("", santa, workshop));
        }
    	
    	
    }

}
