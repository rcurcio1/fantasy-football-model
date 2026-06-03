import java.util.List;
import java.util.Map;
import java.util.Queue;

public class OGDraftStrategy extends AbstractDraftStrategy {

    double index1;

    public OGDraftStrategy(double index1) {
        this.index1 = index1;
    } 

    @Override
    public Player draftPlayer(String me, List<Player> available, Queue<String> draftOrder, Map<String, List<Player>> drafted) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draftPlayer'");
    }
    
}
