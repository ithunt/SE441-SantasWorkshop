import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ian hunt
 * @date 1/16/12
 */
public class Elf extends Thread {
    
    private final Workshop workshop;
    private final Santa santa;
    private final CountDownLatch start;

    public Elf(Workshop workshop, Santa santa, CountDownLatch start) {
        this.workshop = workshop;
        this.santa = santa;
        this.start = start;
    }

    public void run() {

        try {
            start.await();
        } catch (InterruptedException ex) {}
    	
        while (true) {
            try {
            	//NEEDS TO TELL HOW LONG UNTIL XMAS!
                //this.sleep((long)(SantaConstants.INIT_ELF_DELAY + (Math.random());
            	this.sleep(0);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
			workshop.getElfQueue().put(this);
            
           try {
        	   //elf has a problem
        	   this.wait();
           } catch (InterruptedException e) {
        	   e.printStackTrace();
           }

        }

    }
    


}
