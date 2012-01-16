/**
 * @author ian hunt
 * @date 1/16/12
 */
public class Elf extends Thread {

    public void run() {
        while (true) {
            //work a bit
            try {
                //todo: random time (1-3 seconds)
                Thread.currentThread().wait(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            //ask for help
            //acquire help
        }

    }

}
