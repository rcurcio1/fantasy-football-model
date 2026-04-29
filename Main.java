import java.util.ArrayDeque;

public static void main(String[] args) {
    Queue<String> draftOrder = new ArrayDeque<String>();
    draftOrder.add("Ross");
    draftOrder.add("Joe");
    draftOrder.add("Caroline");
    Draft draft = new Draft(draftOrder);
    draft.play(15);
}