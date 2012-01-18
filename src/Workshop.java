import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author ian hunt
 * @date 1/16/12
 */
public class Workshop {

    private ArrayBlockingQueue<Elf> elfQueue = new ArrayBlockingQueue<Elf>(3);
    private ArrayBlockingQueue<Reindeer> warmingHutt = new ArrayBlockingQueue<Reindeer>(9);

    public ArrayBlockingQueue<Elf> getElfQueue() {
        return elfQueue;
    }

    public void setElfQueue(ArrayBlockingQueue<Elf> elfQueue) {
        this.elfQueue = elfQueue;
    }

    public ArrayBlockingQueue<Reindeer> getWarmingHutt() {
        return warmingHutt;
    }

    public void setWarmingHutt(ArrayBlockingQueue<Reindeer> warmingHutt) {
        this.warmingHutt = warmingHutt;
    }
}
