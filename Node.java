/* vertex=node, edge=connection to other nodes */

public class Node {
    private String city;
    private int miles;
    private int minutes;
    private boolean visited;

    public Node(String city, int miles, int minutes) {
        this.city = city;
        this.miles = miles;
        this.minutes = minutes;
        visited = false;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Node{" +
                "city='" + city + '\'' +
                ", miles=" + miles +
                ", minutes=" + minutes +
                ", visited=" + visited +
                '}';
    }
}

