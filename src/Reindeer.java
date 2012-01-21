import java.util.concurrent.CyclicBarrier;


/**
 * @author ian hunt
 * @date 1/16/12
 */

/**
 * Represents one of Santa's Reindeers.
 * Uses a CyclicBarrier to represent the warming hut. 
 */
public class Reindeer extends Thread {
	private final CyclicBarrier barrier;
    

    public Reindeer(String name, CyclicBarrier barrier) {
        super(name);
        this.barrier = barrier;
    }

    public void run() {
    	
        try {
            //todo: proper wait time
            Thread.currentThread().wait(1000);
            barrier.wait();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
      



}
