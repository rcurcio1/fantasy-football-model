public class Player {
    private final String name;
    private final Position position;
    private final double projection;


    public Player(String name, Position position, double projection) {
        this.name = name;
        this.position = position;
        this.projection = projection;
    }
    
    public String getName() {
        return this.name;
    }

    public Position getPosition() {
        return this.position;
    }

    public double getProjection() {
        return this.projection;
    }

    public Boolean equals(Player other) {
        return this.getName().equals(other.getName()) && this.getPosition() == other.getPosition();
    }
}
