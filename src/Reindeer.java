import java.util.concurrent.BrokenBarrierException;
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

    public static enum ReindeerLocation {
        TROPICS, WARMING_HUT, LOADING_SLEIGH, DELIVERING
    }

    private final CyclicBarrier barrier;
    private final CountDownLatch start;
    private final CountDownLatch sleigh;
    private ReindeerLocation location;


    /**
     * Reindeer constructor
     * @param name this reindeers name (from Reindeer.names)
     * @param barrier Reindeer warming hut for their return from the tropics
     * @param sleigh Santa's Sleigh
     * @param start Start Signal
     */
    public Reindeer(String name, CyclicBarrier barrier, CountDownLatch sleigh, CountDownLatch start) {
        super(name);
        this.barrier = barrier;
        this.start = start;
        this.sleigh = sleigh;
        this.location = ReindeerLocation.TROPICS;
    }

    /**
     * Will wait a certain time until it will return to Santa's workshop.
     * When returned, it will wait at the warming hut until the last reindeer
     * has returned and notified Santa.
     */
    public void run() {

        //Wait for start signal
        try {
            start.await();
        } catch (InterruptedException ex) {

        }

        //Go chill in the tropics for a while
        try {
            //todo: proper wait time
            Thread.currentThread().wait(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        //Returned from topics, wait in warming hut
        location = ReindeerLocation.WARMING_HUT;
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        //All reindeer have arrived, prepare to deliver presents
        try {
            sleigh.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Christmas!
        this.location = ReindeerLocation.DELIVERING;


    }

    public ReindeerLocation getLocation() {
        return location;
    }

    public void setLocation(ReindeerLocation location) {
        this.location = location;
    }
}
