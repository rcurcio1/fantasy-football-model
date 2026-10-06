package Strategy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import Draft.Player;
import Draft.Position;
import Visitor.StrategyVisitor;

/*
    Strategy class that uses Q-Learning and knowledge of it's past draft picks to inform its next pick, 
    continuing to train the algorithm by updating the Q-values throughout the draft
*/

public class RosterKnowledgeDraftStrategy extends AbstractDraftStrategy {

    Map<String, double[]> qValues;
    String filePath;
    double Gamma;
    double Alpha;
    double Epsilon;
    String state;

    // Creates an instance of RosterKnowledgeDraftStrategy given the current Q-Values, and other hyperparameters
    // that will be used to train the model
    public RosterKnowledgeDraftStrategy(Map<String, double[]> qValues, double gamma, double alpha, double epsilon) {
        this.qValues = qValues;
        this.Gamma = gamma; //discount factor
        this.Alpha = alpha; //learning rate
        this.Epsilon = epsilon; //chance of random action
        this.state = "";
    }

    @Override
    public Player draftPlayer(String me, List<Player> available, Queue<String> draftOrder, Map<String, List<Player>> drafted) {
        double[] options = this.qValues.get(this.state);
        int action;
        if (options == null) {
            action = (int) Math.floor(Math.random() * 6);
            options = new double[6];
        }
        else if (Math.random() < Epsilon) {
            action = (int) Math.floor(Math.random() * 6);
        }
        else {
            action = getIndexOfBestAction(options);
        }
        Player drafting = this.getBestPlayerAtPosition(this.getPositionFromInt(action), available);
        String newState = this.getNextState(drafting.getPosition());
        double bestOfNextState = getBestOfNextState(newState);
        double reward = 0;
        if (newState.length() == 15) {
            reward = this.evaluateTeam(drafted.get(me));
        }
        options[action] += Alpha * (reward + Gamma * bestOfNextState - options[action]);
        this.qValues.put(state, options);
        this.state = newState;
        return drafting;
    }

    // Uses the Q-Values to determine the best action given the new state
    private double getBestOfNextState(String newState) {
        double[] nextActionSpace = this.qValues.get(newState);
        if (nextActionSpace == null) {
            return 0;
        }
        else {
            return nextActionSpace[this.getIndexOfBestAction(nextActionSpace)];
        }
    }

    // Converts integer to position
    private Position getPositionFromInt(int i) {
        switch(i) {
            case 0:
                return Position.D;
            case 1:
                return Position.K;
            case 2:
                return Position.QB;
            case 3:
                return Position.RB;
            case 4:
                return Position.TE;
            case 5:
                return Position.WR;
            default:
                throw new IllegalArgumentException("Cannot get Position from int: " + i);
        }
    }

    // Given the list of doubles representing the expected rewards, returns the index of the best reward
    private int getIndexOfBestAction(double[] options) {
        int index = 0;
        double max = options[0];
        for (int i = 1; i < options.length; i++) {
            if (options[i] > max) {
                max = options[i];
                index = i;
            }
        }
        return index;
    }

    // Updates the state given the most recent position drafted
    private String getNextState(Position p) {
        String unsortedState = this.state + p.toString();
        char[] chars = unsortedState.toCharArray();
        Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;
    }

    @Override
    public <R> R accept(StrategyVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public void reset() {
        this.state = "";
    }    
}
