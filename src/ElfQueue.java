import java.util.concurrent.ArrayBlockingQueue;

/**
 * 
 */

/**
 * @author McAfee
 *
 */
public class ElfQueue<E> extends ArrayBlockingQueue<E> {

	private Santa santa;
	
	
	public ElfQueue(int capacity, Santa santa) {
		super(capacity);
		this.santa=santa;
	}
	
	public void put(E someElf){
		try {
			super.put(someElf);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (this.remainingCapacity()==0){
			santa.awaken();
		}
	}

}
