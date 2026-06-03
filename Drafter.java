import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Drafter {
    private final DraftStrategy strategy;
    private final String name;

    public Drafter(DraftStrategy strategy, String name) {
        this.strategy = strategy;
        this.name = name;
    }

    public DraftStrategy getStrategy() {
        return this.strategy;
    }

    public String getName() {
        return this.name;
    }    

    public Player draftPlayer(List<Player> available, Queue<Drafter> draftOrder, Map<String, List<Player>> drafted) {
        return this.strategy.draftPlayer(this.name, available, null, drafted);
    }
}
