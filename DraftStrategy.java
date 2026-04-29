import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface DraftStrategy {
    public Player draftPlayer(String me, List<Player> available, Queue<String> draftOrder, Map<String, List<Player>> drafted);
}
