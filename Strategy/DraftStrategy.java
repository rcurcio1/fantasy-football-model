package Strategy;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import Draft.Player;
import Draft.Position;
import Visitor.StrategyVisitor;

public interface DraftStrategy {
    public Player draftPlayer(String me, List<Player> available, Queue<String> draftOrder, Map<String, List<Player>> drafted);
    public Player getBestPlayerAtPosition(Position position, List<Player> available);
    public double evaluateTeam(List<Player> list);
    public <R> R accept(StrategyVisitor<R> visitor);
    public void reset();
}
