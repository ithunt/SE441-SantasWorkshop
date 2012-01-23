/**
 * @author ian hunt
 * @date 1/16/12
 */
public class Elf extends Thread {
    
    private Workshop workshop;
    private Santa santa;

    public Elf(Workshop workshop, Santa santa) {
        this.workshop = workshop;
        this.santa = santa;
    }

    public void run() {
    	
    	
    	
        while (true) {
            try {
                Thread.currentThread().wait((long)(1000 + (Math.random()*2000)));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            synchronized (this) {
                try {
                    workshop.getElfQueue().put(this);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                if(workshop.getElfQueue().size() > 2) {
                    santa.notify();
                }
                //suspend
                //todo: sleep/wait when in queue, santa wakes him up

            }
        }

    }

}
