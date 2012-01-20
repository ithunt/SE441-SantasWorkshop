import java.util.ArrayList;

/**
 * @author ian hunt
 * @date 1/16/12
 */
public class Main {
	
	//list of constants
	private static final int NUM_ELVES = 10;
	private static final int NUM_REINDEER = 9;
	private static final int INIT_DAYS_TO_XMAS = 364;
	private static final int ELF_COUNT_WORTH_SANTAS_ATTENTION = 3;
	private static final int MILLIS_PER_DAY = 15000;
	private static final double INIT_REINDEER_DELAY = INIT_DAYS_TO_XMAS * MILLIS_PER_DAY * .8;
	private static final double INIT_ELF_DELAY = INIT_DAYS_TO_XMAS * MILLIS_PER_DAY * .1;
    
    public static void main(String[] args)  {

        final ArrayList<Elf> elves = new ArrayList<Elf>();
        final ArrayList<Reindeer> reindeer = new ArrayList<Reindeer>();
        
        final Workshop workshop = new Workshop();
        final Santa santa = new Santa(workshop);
        
        for(int i=0; i < NUM_ELVES; i++ ) {
            elves.add(new Elf(workshop, santa));
        }
        
        for(int i=0; i < NUM_REINDEER; i++ ) {
            reindeer.add(new Reindeer(Reindeer.reindeer[i], santa, workshop));
        }
    	
    	
    }

}
