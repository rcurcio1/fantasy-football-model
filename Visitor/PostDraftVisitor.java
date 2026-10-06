package Visitor;

import Strategy.RosterKnowledgeDraftStrategy;
import Strategy.SimpleDraftStrategy;

public class PostDraftVisitor implements StrategyVisitor<Boolean> {

    @Override
    public Boolean visit(SimpleDraftStrategy strategy) {
        return false;
    }

    @Override
    public Boolean visit(RosterKnowledgeDraftStrategy strategy) {
        return true;
    }    
}
