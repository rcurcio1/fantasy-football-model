package Draft;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import Strategy.OGDraftStrategy;
import Strategy.SimpleDraftStrategy;
import Visitor.PostDraftVisitor;
import Visitor.StrategyVisitor;

public class DraftStarter {

    private List<Player> initialAvailable;
    private Map<String, double[]> qValues;
    private String qValueFilePath;
    private boolean withScoreLogging;
    private boolean withTeamLogging;
    private List<Double> scoreLog;
    
    public DraftStarter(String playerFilePath, String qValueFilePath, boolean withScoreLogging, boolean withTeamLogging) {
        this.initialAvailable = this.importPlayers(playerFilePath);
        this.qValues = this.populateQValues(qValueFilePath);
        this.qValueFilePath = qValueFilePath;
        this.withScoreLogging = withScoreLogging;
        this.withTeamLogging = withTeamLogging;
        this.scoreLog = new ArrayList<Double>();
    }

    public void start(int epoch) throws IOException {
        Queue<Drafter> draftOrder = new ArrayDeque<Drafter>();
        draftOrder.add(new Drafter(new SimpleDraftStrategy(20), "Ross"));
        draftOrder.add(new Drafter(new OGDraftStrategy(this.qValues, 0.8, .25, 0), "Joe"));
        draftOrder.add(new Drafter(new SimpleDraftStrategy(20), "Caroline"));

        StrategyVisitor<Boolean> visitor = new PostDraftVisitor();
        double strategyTotal = 0;
        for (int i = 0; i < epoch; i++) {
            List<Player> copyOfAvailable = new ArrayList<Player>(this.initialAvailable);
            Draft draft = new Draft(draftOrder, copyOfAvailable);
            draft.play(15);
            for (Drafter drafter : draft.getDrafters()) {
                if (drafter.getStrategy().accept(visitor)) {
                    strategyTotal = strategyTotal + drafter.getStrategy().evaluateTeam(draft.getDrafted().get(drafter.getName()));
                    this.tryLogTeam(drafter, draft);
                    if (i != 0 && i % 10000 == 0) {
                        scoreLog.add(strategyTotal / 10000);
                        strategyTotal = 0;
                    }
                }
            }
        }
        this.exportQValues();
        this.tryLogScore();
    }

    private List<Player> importPlayers(String playersFileName) {
        List<Player> available = new ArrayList<Player>();
        File playersFile = new File(playersFileName);
        try {
            Scanner fileScanner = new Scanner(playersFile);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] row = line.split(",");
                if (row.length == 3) {
                    String playerName = row[0];
                    String position = row[1];
                    double projection = Double.parseDouble(row[2]);
                    Player player = new Player(playerName, position, projection);
                    available.add(player);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Collections.sort(available);
        return available;
    }

    private Map<String, double[]> populateQValues(String filePath) {
        Map<String, double[]> qValues = new HashMap<String, double[]>();
        File playersFile = new File(filePath);
        try {
            Scanner fileScanner = new Scanner(playersFile);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] row = line.split(",");
                double[] actions = new double[6];
                for (int i = 1; i < 7; i++) {
                    actions[i-1] = Double.parseDouble(row[i]);
                }
                qValues.put(row[0], actions);
            }
            fileScanner.close();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return qValues;
    }

    private void exportQValues() {
        FileWriter myWriter;
        try {
            myWriter = new FileWriter(this.qValueFilePath);
            Set<String> keys = this.qValues.keySet();
            for (String key : keys) {
                StringBuilder output = new StringBuilder(key);
                double[] options = this.qValues.get(key);
                output.append(this.optionsToString(options));
                output.append("\n");
                myWriter.write(output.toString());
            }
            myWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String optionsToString(double[] options) {
        StringBuilder sb = new StringBuilder();
        for (double d : options) {
            sb.append(",").append(d);
        }
        return sb.toString();
    }

    private void tryLogScore() {
        if (withScoreLogging) {
            FileWriter myWriter;
            try {
                myWriter = new FileWriter("score.csv");
                for (int i = 0; i <this.scoreLog.size(); i++) {
                    myWriter.append(this.scoreLog.get(i).toString() + "\n");
                }
                myWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void tryLogTeam(Drafter drafter, Draft draft) {
        if (withTeamLogging) {
            FileWriter myWriter;
            try {
                myWriter = new FileWriter("team.log");
                myWriter.append(drafter.getName() + ": " + drafter.getStrategy().evaluateTeam(draft.getDrafted().get(drafter.getName())) + "\n");
                for (Player p : draft.getDrafted().get(drafter.getName())) {
                    myWriter.append("\t" + p + "\n");
                }
                myWriter.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
