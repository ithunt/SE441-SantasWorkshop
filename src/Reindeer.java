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

    private final CyclicBarrier warminghut;
    private boolean attachedToSleigh;

    public Reindeer(String name, CyclicBarrier warminghut) {
        super(name);
        this.warminghut = warminghut;
        attachedToSleigh = false;
    }
    
    /**
     * Attach the reindeer to the sleigh.
     * Not thread safe, as only one object (Santa)
     * will actually be calling this.
     */
    public void attachToSleigh() {
    	attachedToSleigh = true;
    }

    /**
     * Will wait a certain time until it will return to Santa's workshop.
     * When returned, it will wait at the warming hut until the last reindeer
     * has returned and notified Santa.
     */
    public void run() {
    	
        try {
            //todo: proper wait time
            Thread.currentThread().wait(1000);
            warminghut.wait();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
      



}
