import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @author ian hunt
 * @date 1/16/12
 */
public class Main {
	
    
    public static void main(String[] args)  {

        final Workshop workshop = new Workshop();
        final CountDownLatch start = new CountDownLatch(1);

        workshop.initElves(start);
        workshop.initReindeer(start);

        

    	
    	
    }

}
