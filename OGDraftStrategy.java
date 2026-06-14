import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class OGDraftStrategy extends AbstractDraftStrategy {

    Map<String, List<Integer>> qValues;
    double Gamma;
    double Alpha;
    double Epsilon;

    public OGDraftStrategy(String filepath, double gamma, double alpha, double epsilon) {
        this.qValues = this.PopulateQValues(filepath);
        this.Gamma = gamma; //discount factor
        this.Alpha = alpha; //learning rate
        this.Epsilon = epsilon; //chance of random action
    } 

    private Map<String, List<Integer>> PopulateQValues(String filepath) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'PopulateQValues'");
    }

    @Override
    public Player draftPlayer(String me, List<Player> available, Queue<String> draftOrder, Map<String, List<Player>> drafted) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draftPlayer'");
    }

    private String getState(List<Player> drafted) {
        StringBuilder s = new StringBuilder();
        for (Player p: drafted) {
            s.append(p.getPosition().toString());
        }
        String original = s.toString();
        char[] chars = original.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;
    }
    
}
