import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author ian hunt
 * @author christoffer rosen
 * @date 1/16/12
 */


public class Workshop {

    private final BlockingQueue<Elf> problemElfQueue;
    private final CyclicBarrier warmingHut;
    private final CountDownLatch sleigh;

    private final Santa santa;

    private final List<Elf> elves = new ArrayList<Elf>();
    private final List<Reindeer> reindeer = new ArrayList<Reindeer>();

    private volatile boolean isChristmas = false;
    private volatile boolean reindeerReturned = false;

    /**
     * Workshop constructor
     * Creates warming hut as cyclicbarrier that notifies santa when full
     * Sleigh as CountDownLatch
     * Inits Santa
     * Builds Elf Problem Queue
     */
    public Workshop() {

    	// will make all names wait until the last one arrives.
    	// the last one to arrive will go and notify santa. 
    	// then, all names threads are released.
    	this.warmingHut = new CyclicBarrier(SantaConstants.NUM_REINDEER,
    								new Runnable() {
    									public void run() {
                                            reindeerReturned = true;
                                            System.out.println("All the reindeer have returned!");
    									}
    	});

        this.sleigh = new CountDownLatch(1);
        this.santa = new Santa(this, sleigh);

        this.problemElfQueue = new ArrayBlockingQueue<Elf>(SantaConstants.ELF_COUNT_WORTH_SANTAS_ATTENTION);
    }
    
    /**
     * Creates all reindeer and starts them.
     * @param start  program starting signal
     */
    public void initReindeer(CountDownLatch start) {
    	for(String reindeerName : SantaConstants.REINDEER_NAMES){
    		Reindeer r = new Reindeer(reindeerName, warmingHut, sleigh, start);
            reindeer.add(r);
    		r.start();
    	}
    	
    }
    
    /** 
     * Creates all the elves and start them.
     * @param start program starting signal
     */
    public void initElves(CountDownLatch start) {
    	for(int i=0; i < SantaConstants.NUM_ELVES; i++ ) {
    		Elf elf = new Elf(this, santa, start);
            elves.add(elf);
            elf.start();
        }
    }


    public CyclicBarrier getWarmingHut() {
        return warmingHut;
    }

    public BlockingQueue<Elf> getProblemElfQueue() {
        return problemElfQueue;
    }

    public CountDownLatch getSleigh() {
        return sleigh;
    }

    public List<Reindeer> getReindeer() {
        return reindeer;
    }

    public boolean isChristmas() {
        return isChristmas;
    }

    public void setChristmas(boolean christmas) {
        isChristmas = christmas;
    }

    public Santa getSanta() {
        return santa;
    }

    public boolean reindeerReturned() {
        return reindeerReturned;
    }
}
