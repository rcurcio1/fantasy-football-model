package Visitor;

import Strategy.RosterKnowledgeDraftStrategy;
import Strategy.SimpleDraftStrategy;

public interface StrategyVisitor<R> {
    R visit(SimpleDraftStrategy strategy);
    R visit(RosterKnowledgeDraftStrategy strategy);
}
