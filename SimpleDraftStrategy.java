import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SimpleDraftStrategy extends AbstractDraftStrategy {

    @Override
    public Player draftPlayer(String me, List<Player> available, Queue<String> draftOrder,
            Map<String, List<Player>> drafted) {
        return available.get(0);
    }
}
