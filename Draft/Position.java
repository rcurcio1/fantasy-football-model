package Draft;
public enum Position {
    WR,
    RB,
    QB,
    TE,
    K,
    D;

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
