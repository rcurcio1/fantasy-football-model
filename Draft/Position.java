package Draft;
/*
    Position enum representing the six football positions in fantasy football
*/
public enum Position {
    WR,
    RB,
    QB,
    TE,
    K,
    D;

    // Return the one character long string representing this enum
    public String toString() {
        switch(this) {
            case D:
                return "d";
            case K:
                return "k";
            case QB:
                return "q";
            case RB:
                return "r";
            case WR:
                return "w";
            case TE:
                return "t";
            default:
                return "";
        }
    }
}
