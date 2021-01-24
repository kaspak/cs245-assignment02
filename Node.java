/* vertex=node, edge=connection to other nodes */

public class Node {
    private String city;
    private float miles;
    private float minutes;
    private boolean visited;
    private int path;

    public Node(String city, float miles, float minutes) {
        this.city = city;
        this.miles = miles;
        this.minutes = minutes;
        visited = false;
        path = -1;
    }

    public Node(String city, float miles, float minutes, int path) {
        this.city = city;
        this.miles = miles;
        this.minutes = minutes;
        visited = false;
        this.path = path;
    }

    public Node(Node node) {
        this.city = node.city;
        this.miles = node.miles;
        this.minutes = node.minutes;
        visited = false;
        this.path = node.path;
    }

    public float getCost() {
        if (miles==0 && minutes==0) {
            return 0;
        }
        return miles/(minutes/60);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public float getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public float getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setVisited() {
        visited = true;
    }

    public boolean isVisited() {
        return visited;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Node{" +
                "city='" + city + '\'' +
                ", miles=" + miles +
                ", minutes=" + minutes +
                ", visited=" + visited +
                ", Path=" + path +
                ", Cost=" + getCost() +
                '}';
    }

}

