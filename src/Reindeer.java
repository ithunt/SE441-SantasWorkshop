import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;


/**
 * @author ian hunt
 * @date 1/16/12
 *
 * Represents one of Santa's Reindeers.
 * Uses a CyclicBarrier to represent the warming hut. 
 */
public class Reindeer extends Thread {

    public final static String[] names =
        {"Dasher", "Dancer", "Prancer", "Vixen", "Comet", "Cupid", "Donner", "Blitzen", "Rudolph"};

    private final CyclicBarrier barrier;
    private final CountDownLatch start;
    

    public Reindeer(String name, CyclicBarrier barrier, CountDownLatch start) {
        super(name);
        this.barrier = barrier;
        this.start = start;
    }

    public void run() {
        
        try {
            start.await();
        } catch (InterruptedException ex) {

        }
    	
        try {
            //todo: proper wait time
            Thread.currentThread().wait(1000);
            barrier.wait();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
      



}
