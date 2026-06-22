package Visitor;

import Strategy.OGDraftStrategy;
import Strategy.SimpleDraftStrategy;

public interface StrategyVisitor<R> {
    R visit(SimpleDraftStrategy strategy);
    R visit(OGDraftStrategy strategy);
}
