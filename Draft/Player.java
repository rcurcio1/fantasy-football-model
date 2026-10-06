package Draft;

/*
    Player class representing a football player that can be drafted
*/
public class Player implements Comparable<Player> {
    private final String name;
    private final Position position;
    private final double projection;

    // Create a Player given their name, Position, and projected performance
    public Player(String name, Position position, double projection) {
        this.name = name;
        this.position = position;
        this.projection = projection;
    }

    // Create a Player given their name, String representing their position, and projected performance
    public Player(String name, String position, double projection) {
        this.name = name;
        this.position = Position.valueOf(position);
        this.projection = projection;
    }
    
    // Return this player's name
    public String getName() {
        return this.name;
    }

    // Return this player's position
    public Position getPosition() {
        return this.position;
    }

    // Return this player's projection
    public double getProjection() {
        return this.projection;
    }

    // Return whether this player is the same as the given player
    public Boolean equals(Player other) {
        return this.getName().equals(other.getName()) && this.getPosition() == other.getPosition();
    }

    // Represent this players position, name, and projections as a String
    public String toString() {
        return this.position.toString() + " " + this.name + ": " + String.valueOf(this.projection);
    }

    // Compare this player to the given player using their projection
    @Override
    public int compareTo(Player o) {
        return Double.compare(o.projection, this.projection);
    }
}
