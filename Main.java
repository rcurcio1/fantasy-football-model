import java.io.IOException;

import Draft.DraftStarter;

public static void main(String[] args) {
    DraftStarter starter = new DraftStarter("players.csv", "QValues.csv", true, false);
    try {
        starter.start(10000000);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}