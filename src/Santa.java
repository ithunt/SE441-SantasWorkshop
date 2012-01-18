/**
 * @author ian hunt
 * @date 1/16/12
 */
public class Santa extends Thread {

    private Workshop workshop;

    public Santa(Workshop workshop) {
        this.workshop = workshop;
    }

    public void run() {
        while(true) {
            try {
                Thread.currentThread().wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            if(workshop.getWarmingHutt().size() == 9) {
                //presents!
            } else if(workshop.getElfQueue().size() == 3) {
                workshop.getElfQueue().clear(); //problems solved
            }





            //check warming hutt size
                //if 9, presents time
            //check elf queue
                //if >=3, solve problems
        }
        
    }

    //if all reindeer are there -> deliver presents
    //
}
