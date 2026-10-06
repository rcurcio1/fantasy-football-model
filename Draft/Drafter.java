package Draft;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import Strategy.DraftStrategy;

/*
    Drafter class representing a participant in a draft with a strategy and name
*/
public class Drafter {
    private final DraftStrategy strategy;
    private final String name;

    // Create a Drafter with the given strategy and name
    public Drafter(DraftStrategy strategy, String name) {
        this.strategy = strategy;
        this.name = name;
    }

    // Return the DraftStrategy of the Drafter
    public DraftStrategy getStrategy() {
        return this.strategy;
    }

    // Return the name of the Drafter
    public String getName() {
        return this.name;
    }    

    // Draft a Player using the Drafter's DraftStrategy
    public Player draftPlayer(List<Player> available, Queue<Drafter> draftOrder, Map<String, List<Player>> drafted) {
        return this.strategy.draftPlayer(this.name, available, null, drafted);
    }

    // Set the state of the DraftStrategy back to the initial state
    public void reset() {
        this.strategy.reset();
    }
}
