/**
 * @author ian hunt
 * @date 1/16/12
 */
public class Santa {

    private Workshop workshop;

    public Santa(Workshop workshop) {
        this.workshop = workshop;
    }


    public synchronized void awaken() {
        if(workshop.getWarmingHut().isBroken()) { //&& is christmas
            //presents!
        } else if(workshop.getElfQueue().size() == 3) {
            workshop.getElfQueue().clear(); //problems solved
        }

    }
}
