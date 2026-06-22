package Visitor;

import Strategy.OGDraftStrategy;
import Strategy.SimpleDraftStrategy;

public class PostDraftVisitor implements StrategyVisitor<Boolean> {

    @Override
    public Boolean visit(SimpleDraftStrategy strategy) {
        return false;
    }

    @Override
    public Boolean visit(OGDraftStrategy strategy) {
        return true;
    }    
}
