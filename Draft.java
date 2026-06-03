import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Draft {
    private List<Player> available;
    private Queue<Drafter> draftOrder;
    private Map<String, List<Player>> drafted;

    public Draft(Queue<Drafter> draftOrder) {
        this.draftOrder = draftOrder;
        this.available = this.importPlayers();
        this.drafted = this.initializeDrafted();
    }

    private Map<String, List<Player>> initializeDrafted() {
        Map<String, List<Player>> drafted = new HashMap<>();
        for (Drafter drafter: this.draftOrder) {
            drafted.put(drafter.getName(), new ArrayList<Player>());
        }
        return drafted;
    }

    private List<Player> importPlayers() {
        List<Player> available = new ArrayList<Player>();
        available.sort(Comparator.comparing(Player::getProjection));
        return available;
    }
    
    public Boolean play(int rounds) {
        for (int i = 0; i < rounds * this.draftOrder.size(); i++) {
            Drafter drafter = this.draftOrder.remove();
            Player picked = drafter.draftPlayer(this.available, this.draftOrder, this.drafted);
            this.drafted.get(drafter.getName()).add(picked);
            this.available.remove(picked);
            this.draftOrder.add(drafter);
        }
        return true;
    }
}
