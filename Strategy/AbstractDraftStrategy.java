package Strategy;

import Draft.Player;
import Draft.Position;

import java.util.List;

public abstract class AbstractDraftStrategy implements DraftStrategy{

    @Override
    public Player getBestPlayerAtPosition(Position position, List<Player> available) {
        for (int i = 0; i < available.size(); i++) {
            if (available.get(i).getPosition() == position) {
                return available.get(i);
            }
        }
        return available.get(available.size() - 1);
    }

    @Override
    public double evaluateTeam(List<Player> list) {
        int qb = 0;
        int wr = 0;
        int rb = 0;
        int te = 0;
        int flex = 0;
        int d = 0;
        int k = 0;
        double eval = 0;
        for (Player p : list) {
            switch(p.getPosition()) {
                case D:
                    if (d < 1) {
                        d++;
                        eval += p.getProjection();
                    }
                    else {
                        eval += (0.4 * p.getProjection());
                    }
                    break;
                case K:
                    if (k < 1) {
                        k++;
                        eval += p.getProjection();
                    }
                    else {
                        eval += (0.4 * p.getProjection());
                    }
                    break;
                case QB:
                    if (qb < 1) {
                        qb++;
                        eval += p.getProjection();
                    }
                    else {
                        eval += (0.4 * p.getProjection());
                    }
                    break;
                case RB:
                    if (rb < 2) {
                        rb++;
                        eval += p.getProjection();
                    }
                    else if (flex < 2) {
                        flex++;
                        eval += p.getProjection();
                    }
                    else {
                        eval += (0.4 * p.getProjection());
                    }
                    break;
                case TE:
                    if (te < 2) {
                        te++;
                        eval += p.getProjection();
                    }
                    else if (flex < 2) {
                        flex++;
                        eval += p.getProjection();
                    }
                    else {
                        eval += (0.4 * p.getProjection());
                    }
                    break;
                case WR:
                    if (wr < 2) {
                        wr++;
                        eval += p.getProjection();
                    }
                    else if (flex < 2) {
                        flex++;
                        eval += p.getProjection();
                    }
                    else {
                        eval += (0.4 * p.getProjection());
                    }
                    break;
                default:
                    break;
            } 
        }
        return eval;
    }
}
