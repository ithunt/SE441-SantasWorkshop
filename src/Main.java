import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

/**
 * @author ian hunt
 * @date 1/16/12
 */
public class Main {


    /**
     * Main Program Entry Point
     * Simulates a year in Santa's workshop
     * @param args not used
     */
    public static void main(String[] args)  {

        final Workshop workshop = new Workshop();
        final CountDownLatch start = new CountDownLatch(1);

        workshop.initElves(start);
        workshop.initReindeer(start);

        final Timer timer = new Timer();
        final TimerTask christmas = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Christmas!");
                synchronized (workshop) {
                    workshop.setChristmas(true);
                }
                workshop.getSanta().awaken();

            }
        };



        //Schedule task to run at christmas
        timer.schedule(christmas, SantaConstants.MILLIS_PER_DAY * SantaConstants.INIT_DAYS_TO_XMAS);

        //Go!
        start.countDown();

    }

}
