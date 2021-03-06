import java.util.concurrent.CountDownLatch;

/**
 * 
 * Represents an elf. Encounters problem after a certain amount of time, in which
 * he/she then will put himself in the problem queue. When three elves are in the 
 * problem queue, the elves will try to wake Santa and have him fix their
 * problems.
 * 
 * @author ian hunt
 * @author Patrick McAfee
 * @author Christoffer Rosen
 * @date 1/16/12
 */
public class Elf extends Thread {

    private final Workshop workshop;
    private final Santa santa;
    private final CountDownLatch start;
    private final String elfName;

    /**
     * Elf constructor
     * @param workshop Santa's Workshop
     * @param santa Santa (so he can be woken)
     * @param start Start Signal
     */
    public Elf(int elfNumber, Workshop workshop, Santa santa, CountDownLatch start) {
        this.workshop = workshop;
        this.santa = santa;
        this.start = start;
        this.elfName = "Elf "+elfNumber;
    }

    /**
     * Main elf running thread. Waits for start signal, then will have problems after
     * a certain amounts of time that requires the attension of Santa.
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

            System.out.println(this.elfName + " has a problem");

            try {
                //Add self to queue
                workshop.getProblemElfQueue().put(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (workshop.getProblemElfQueue().size() ==
                    SantaConstants.ELF_COUNT_WORTH_SANTAS_ATTENTION) {
                System.out.println(this.elfName + " was the last elf. Waking Santa");
                santa.awaken();
            } else {

                //elf now in queue, wait for problem to be resolved
                try {
                    synchronized (this) {
                        this.wait();
                        System.out.println(this.elfName + "'s problem solved. Back to making toys");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


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
