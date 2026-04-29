import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Draft {
    private List<Player> available;
    private Queue<String> draftOrder;
    private Map<String, List<Player>> drafted;

    public Draft(Queue<String> draftOrder) {
        this.draftOrder = draftOrder;
        this.available = this.importPlayers();
        this.drafted = this.initializeDrafted();
    }

    private Map<String, List<Player>> initializeDrafted() {
        Map<String, List<Player>> drafted = new HashMap<>();
        for (String playerName: this.draftOrder) {
            drafted.put(playerName, new ArrayList<Player>());
        }
        return drafted;
    }

    private List<Player> importPlayers() {
        List<Player> available = new ArrayList<Player>();
        available.sort(Comparator.comparing(Player::getProjection));
        return available;
    }
    
    public Boolean play(int rounds) {
        DraftStrategy strat = new OGDraftStrategy(2.0);
        for (int i = 0; i < rounds * this.draftOrder.size(); i++) {
            String player = this.draftOrder.remove();
            Player picked = strat.draftPlayer(player, this.available, this.draftOrder, this.drafted);
            this.drafted.get(player).add(picked);
            this.available.remove(picked);
            this.draftOrder.add(player);
        }
        return true;
    }
}
