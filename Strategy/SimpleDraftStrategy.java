package Strategy;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import Draft.Player;
import Visitor.StrategyVisitor;

public class SimpleDraftStrategy extends AbstractDraftStrategy {
    int Randomness;

    public SimpleDraftStrategy(int randomness) {
        this.Randomness = randomness;
    }

    @Override
    public Player draftPlayer(String me, List<Player> available, Queue<String> draftOrder,
            Map<String, List<Player>> drafted) {
        int index = (int) (Math.random() * this.Randomness);
        return available.get(index);
    }

    @Override
    public <R> R accept(StrategyVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void reset() {
        return;
    }
}