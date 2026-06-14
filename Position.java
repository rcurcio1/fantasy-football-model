public enum Position {
    WR,
    RB,
    QB,
    K,
    DEF;

    public String toString() {
        switch(this) {
            case DEF:
                return "d";
            case K:
                return "k";
            case QB:
                return "q";
            case RB:
                return "r";
            case WR:
                return "w";
            default:
                return "";
        }
    }
}
