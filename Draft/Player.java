package Draft;
public class Player implements Comparable<Player> {
    private final String name;
    private final Position position;
    private final double projection;


    public Player(String name, Position position, double projection) {
        this.name = name;
        this.position = position;
        this.projection = projection;
    }

    public Player(String name, String position, double projection) {
        this.name = name;
        this.position = Position.valueOf(position);
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

    public String toString() {
        return this.position.toString() + " " + this.name + ": " + String.valueOf(this.projection);
    }

    @Override
    public int compareTo(Player o) {
        return Double.compare(o.projection, this.projection);
    }
}
