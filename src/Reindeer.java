import com.sun.xml.internal.ws.util.pipe.StandaloneTubeAssembler;

/**
 * @author ian hunt
 * @date 1/16/12
 */
public class Reindeer extends Thread {

    //todo: distribute these names to instantiations of reindeer
    public final static String[] reindeer =
            {"Dasher", "Dancer", "Prancer", "Vixen", "Comet", "Cupid", "Donner", "Blitzen", "Rudolph"};

    private Santa santa;
    private Workshop workshop;

    public Reindeer(String name, Santa santa, Workshop workshop) {
        super(name);
        this.santa = santa;
        this.workshop = workshop;
    }

    public void run() {
        try {
            //todo: proper wait time
            Thread.currentThread().wait(10000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        synchronized (this) {
            workshop.getWarmingHutt().add(this);
            if(workshop.getWarmingHutt().size() == 9) {
                santa.notify();
            }
        }
        //sleep for a random amount of time with large initial value
        //add yourself to warming hut
        // if after add, warming hut size == 9, notify santa
    }


}
