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
        if(workshop.getWarmingHut().isBroken() && workshop.isChristmas()) {
            hookUpReindeer();
        } else if(workshop.getProblemElfQueue().size() ==
                SantaConstants.ELF_COUNT_WORTH_SANTAS_ATTENTION) {
            this.solveElvesProblems();
        }


    }

    /**
     * Iterates through elfqueue and solves their problems (notify)
     */
    private void solveElvesProblems(){
        
        final BlockingQueue<Elf> problemElves = workshop.getProblemElfQueue();
    		Elf problemElf;
    		
    		for (int i=0; 1<SantaConstants.ELF_COUNT_WORTH_SANTAS_ATTENTION; ++i){
    			try {
						
					problemElf = problemElves.take();
					
					synchronized (problemElf) {
						
						problemElf.notify(); //problem solved!
					
    				}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    }

    /**
     * Prepare the sleigh with all the reindeer and go!
     */
    private void hookUpReindeer(){
    	for(Reindeer r : workshop.getReindeer()) {
            r.setLocation(Reindeer.ReindeerLocation.LOADING_SLEIGH);
        }
        //Deliver presents!
        sleigh.countDown();
    }
    
}
