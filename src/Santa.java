import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @author ian hunt
 * @date 1/16/12
 */
public class Santa {

    private final Workshop workshop;
    private final CountDownLatch sleigh;

    /**
     * Santa Constructor
     * @param workshop Santa's Workshop
     * @param sleigh Santa's sleigh for reindeer and presents (CountDownLatch)
     */
    public Santa(Workshop workshop, CountDownLatch sleigh) {
        this.workshop = workshop;
        this.sleigh = sleigh;
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
    	synchronized (this){
    		System.out.println("HO HO HO MERRRRRY CHRISTMAS!");
    		sleigh.countDown();
    	

    		System.out.println("Christmas is over.");
    	}

        //wait for reindeer to go back to tropics
        for (Reindeer deer : workshop.getReindeer()){
        	try {
        		//notifyAll();
				deer.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
        
        
        System.exit(0);

    }
    
}
