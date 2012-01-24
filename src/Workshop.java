import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ian hunt
 * @author christoffer rosen
 * @date 1/16/12
 */


public class Workshop {

    private ArrayBlockingQueue<Elf> elfQueue = new ArrayBlockingQueue<Elf>(3);
    
    private final ElfQueue<Elf> elvesWithProblems;
    private final CyclicBarrier warmingHut;
    private final Santa santa;
    private final LinkedList<Elf> elves = new LinkedList<Elf>();
    
    public Workshop() {

    	// will make all names wait until the last one arrives.
    	// the last one to arrive will go and notify santa. 
    	// then, all names threads are released.
    	warmingHut = new CyclicBarrier(SantaConstants.NUM_REINDEER,
    								new Runnable() {
    									public void run() {
    										// Notify Santa
    										notifySanta();
    									}
    	});

    	this.santa = new Santa(this);
    	this.elvesWithProblems = new ElfQueue<Elf>(
    			SantaConstants.ELF_COUNT_WORTH_SANTAS_ATTENTION, this.santa);
    }
    
    /**
     * Creates all names and starts them.
     */
    public void initReindeer(CountDownLatch start) {
    	for(int i = 0; i < SantaConstants.NUM_REINDEER; i++ ){
    		Reindeer reindeer = new Reindeer(Reindeer.names[i], warmingHut, start);
    		reindeer.start();
    	}
    	
    }
    
    /** 
     * Creates all the elves and start them.
     */
    public void initElves(CountDownLatch start) {
    	for(int i=0; i < SantaConstants.NUM_ELVES; i++ ) {
    		Elf elf = new Elf(this, santa, start);
            elves.add(elf);
            elf.start();

        }
    }
    
    private void notifySanta() {
    	// Santa.notify() - Notify Santa!
    	
    }

    public ElfQueue<Elf> getElfQueue() {
        return elvesWithProblems;
    }

    public void setElfQueue(ElfQueue<Elf> elfQueue) {
        this.elfQueue = elfQueue;
    }

    public CyclicBarrier getWarmingHut() {
        return warmingHut;
    }
}
