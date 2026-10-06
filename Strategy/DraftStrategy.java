package Strategy;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import Draft.Player;
import Draft.Position;
import Visitor.StrategyVisitor;

// Interface representing a strategy that a drafter can use to draft their team
public interface DraftStrategy {
    // Returns the player that this strategy chooses to draft
    public Player draftPlayer(String me, List<Player> available, Queue<String> draftOrder, Map<String, List<Player>> drafted);
    // Returns the available player at the given position that has the highest projected score
    public Player getBestPlayerAtPosition(Position position, List<Player> available);
    // Evaluate the team's strength by rewarding drafter for having a starter at each position
    public double evaluateTeam(List<Player> list);
    // Accepts a visitor for differentiating between strategy sub-classes
    public <R> R accept(StrategyVisitor<R> visitor);
    // Resets the state of the strategy to prepare for a new draft
    public void reset();
}
