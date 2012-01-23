import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ian hunt
 * @date 1/16/12
 */
public class Elf extends Thread {
    
    private Workshop workshop;
    private Santa santa;
    private Lock hasProblem;

    public Elf(Workshop workshop, Santa santa) {
        this.workshop = workshop;
        this.santa = santa;
        this.hasProblem = new ReentrantLock();
    }

    public void run() {
    	
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
