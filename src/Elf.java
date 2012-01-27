import java.util.concurrent.CountDownLatch;

/**
 * @author ian hunt
 * @date 1/16/12
 */
public class Elf extends Thread {

    private final Workshop workshop;
    private final Santa santa;
    private final CountDownLatch start;

    /**
     * Elf constructor
     * @param workshop Santa's Workshop
     * @param santa Santa (so he can be woken)
     * @param start Start Signal
     */
    public Elf(Workshop workshop, Santa santa, CountDownLatch start) {
        this.workshop = workshop;
        this.santa = santa;
        this.start = start;
    }

    /**
     * Main elf running thread. Waits for start signal, has problems till christmas
     */
    public void run() {

        try {
            start.await();
        } catch (InterruptedException ex) {
        }

        while (true) {

            try {
                synchronized (this) {
                    this.wait(getRandomWaitTime());
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            //System.out.println(this.getName() + " has a problem");

            try {
                //Add self to queue
                workshop.getProblemElfQueue().put(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (workshop.getProblemElfQueue().size() ==
                    SantaConstants.ELF_COUNT_WORTH_SANTAS_ATTENTION && !santa.haveReindeersArrived()) {
                System.out.println(this.getName() + " was the third elf. Waking Santa");
                santa.awaken();
            } else {

                //elf now in queue, wait for problem to be resolved
                try {
                    synchronized (this) {
                        this.wait();
                    }
                } catch (InterruptedException e) {
                	//swallow interruption exception
                    
                }
            }
            //System.out.println(this.getName() + " problem solved. Back to making toys");


            if (this.isInterrupted()) break;

        } //end while loop

    }

    /**
     * Helper function for random number in range low-high using formula:
     *          Min + (long)(Math.random() * ((Max - Min) + 1))
     *          from StackOverflow
     * @return random wait time (millis) in range from SantaConstants
     */
    public static long getRandomWaitTime() {
        return SantaConstants.ELF_PROBLEM_MILLIS_LOW +
                (long)(Math.random() * ((SantaConstants.ELF_PROBLEM_MILLIS_HIGH -
                        SantaConstants.ELF_PROBLEM_MILLIS_LOW) + 1));

    }

}
