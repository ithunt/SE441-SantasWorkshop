import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ian hunt
 * @author christoffer rosen
 * @date 1/16/12
 */


public class Workshop {

    private ArrayBlockingQueue<Elf> elfQueue = new ArrayBlockingQueue<Elf>(3);
    
    private final ArrayList<Elf> elves = new ArrayList<Elf>();
    private final CyclicBarrier warmingHut;
    private final Santa santa;
    
    public Workshop() {
    	
    	// will make all names wait until the last one arrives.
    	// the last one to arrive will go and notify santa. 
    	// then, all names threads are released.
    	warmingHut = new CyclicBarrier(SantaConstants.NUM_ELVES,
    								new Runnable() {
    									public void run() {
    										// Notify Santa
    										notifySanta();
    									}
    	});
    	
    	this.santa = new Santa(this);
    	initReindeer();
    	initElves();
    }
    
    /**
     * Creates all names and starts them.
     */
    private void initReindeer() {
    	for(int i = 0; i < SantaConstants.NUM_REINDEER; i++ ){
    		Reindeer reindeer = new Reindeer(Reindeer.names[i], warmingHut);
    		reindeer.start();
    	}
    	
    }
    
    /** 
     * Creates all the elves and start them.
     */
    private void initElves() {
    	for(int i=0; i < SantaConstants.NUM_ELVES; i++ ) {
    		Elf elf = new Elf(this, santa);
            elves.add(elf);
            elf.start();
            
        }
    }
    
    public void notifySanta() {
    	// Santa.notify() - Notify Santa!
    	
    }

    public ArrayBlockingQueue<Elf> getElfQueue() {
        return elfQueue;
    }

    public void setElfQueue(ArrayBlockingQueue<Elf> elfQueue) {
        this.elfQueue = elfQueue;
    }

    public CyclicBarrier getWarmingHut() {
        return warmingHut;
    }
}
