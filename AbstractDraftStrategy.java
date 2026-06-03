import java.util.List;

public abstract class AbstractDraftStrategy implements DraftStrategy{

    @Override
    public Player getBestPlayerAtPosition(Position position, List<Player> available) {
        for (int i = 0; i < available.size(); i++) {
            if (available.get(i).getPosition() == position) {
                return available.get(i);
            }
        }
        return null;
    }
}
