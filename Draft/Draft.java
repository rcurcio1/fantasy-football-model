package Draft;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
    Draft class for executing one draft with the given Drafters and Players
*/
public class Draft {
    private List<Player> available;
    private Queue<Drafter> draftOrder;
    private Map<String, List<Player>> drafted;

    // Create a Draft with the draft order and the list of available players
    public Draft(Queue<Drafter> draftOrder, List<Player> available) {
        this.draftOrder = draftOrder;
        this.available = available;
        this.drafted = this.initializeDrafted();
    }

    // Initialize the map of drafted players for each Drafter
    private Map<String, List<Player>> initializeDrafted() {
        Map<String, List<Player>> drafted = new HashMap<>();
        for (Drafter drafter: this.draftOrder) {
            drafted.put(drafter.getName(), new ArrayList<Player>());
        }
        return drafted;
    }

    // Play 1 draft with a given number of rounds
    public Boolean play(int rounds) {
        for (Drafter d : this.draftOrder) {
            d.reset();
        }
        for (int i = 0; i < rounds * this.draftOrder.size(); i++) {
            Drafter drafter = this.draftOrder.remove();
            Player picked = drafter.draftPlayer(this.available, this.draftOrder, this.drafted);
            this.drafted.get(drafter.getName()).add(picked);
            this.available.remove(picked);
            this.draftOrder.add(drafter);
        }
        return true;
    }

    // Return the map of drafted players
    public Map<String, List<Player>> getDrafted() {
        return this.drafted;
    }

    // Return the draft order
    public Queue<Drafter> getDrafters() {
        return this.draftOrder;
    }
}
