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
        } else if(workshop.getElfQueue().size() == 
        		SantaConstants.ELF_COUNT_WORTH_SANTAS_ATTENTION) {
        	this.solveElvesProblems(); //problems solved
        }

    }
    
    private void solveElvesProblems(){
    	
    	synchronized (this){
    		ElfQueue<Elf> problemElves = workshop.getElfQueue();
    		Elf problemElf;
    		
    		for (int i= 0; 1<SantaConstants.ELF_COUNT_WORTH_SANTAS_ATTENTION; ++i){
    			try {
					problemElf = problemElves.take();
					problemElf.notify(); //problem solved!
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    		
    	}
    }
}
