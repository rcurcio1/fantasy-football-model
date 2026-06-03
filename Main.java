import java.util.ArrayDeque;

public static void main(String[] args) {
    Queue<Drafter> draftOrder = new ArrayDeque<Drafter>();
    draftOrder.add(new Drafter(new SimpleDraftStrategy(), "Ross"));
    draftOrder.add(new Drafter(new OGDraftStrategy(2.0), "Joe"));
    draftOrder.add(new Drafter(new SimpleDraftStrategy(), "Caroline"));
    Draft draft = new Draft(draftOrder);
    draft.play(15);
}