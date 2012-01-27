import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @author ian hunt
 * @date 1/16/12
 */
public class Santa {

    private final Workshop workshop;
    private final CountDownLatch sleigh;
    private boolean reindeersArrived;

    /**
     * Santa Constructor
     * @param workshop Santa's Workshop
     * @param sleigh Santa's sleigh for reindeer and presents (CountDownLatch)
     */
    public Santa(Workshop workshop, CountDownLatch sleigh) {
        this.workshop = workshop;
        this.sleigh = sleigh;
        reindeersArrived = false;
    }

    /**
     * Something requires Santa's Attention
     * If Reindeer arrived && isChristmas() -> presents
     *  else solve elf problems
     */
    public synchronized void awaken() {
        if(workshop.isChristmas() && workshop.reindeerReturned()) {
            hookUpReindeer();
        } else if(workshop.getProblemElfQueue().size() ==
                SantaConstants.ELF_COUNT_WORTH_SANTAS_ATTENTION) {
            this.solveElvesProblems();
            System.out.println("Santa done solving those pesky elf problems");
        }


    }

    /**
     * Iterates through elfqueue and solves their problems (notify)
     */
    private synchronized void solveElvesProblems() {
        for(Elf e : workshop.getProblemElfQueue()) {
            synchronized (e) {
                e.notify();
            }
        }
        workshop.getProblemElfQueue().clear();
    }

    /**
     * Prepare the sleigh with all the reindeer and go!
     */
    private void hookUpReindeer(){
    	for(Reindeer r : workshop.getReindeer()) {
            System.out.println("Santa harnessed " + r.getName() + " to the sleigh!");
            r.setLocation(Reindeer.ReindeerLocation.LOADING_SLEIGH);
        }
        //Deliver presents!
        System.out.println("HO HO HO MERRRRRY CHRISTMAS!");
        sleigh.countDown();


        //wait for each reindeer to finish
        for(Reindeer r : workshop.getReindeer()) {
            try {
                r.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);

    }
    
    /**
     * The last reindeer has arrived, inform Santa to not help any more elves.
     */
    public void reindeersArrived(){
    	reindeersArrived = true;
    }
    
    /**
     * Returns whether the last reindeer has arrived.
     * @return	boolean true/false determined if last reindeer has arrived or not.
     */
    public boolean haveReindeersArrived(){
    	return reindeersArrived;
    }
    
}
