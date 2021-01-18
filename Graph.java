import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    /* an array of linkedlists holding nodes */
    LinkedList<Node>[] routes;

    public Graph(int size) {
        routes = new LinkedList[size];
    }

    /* if null, make a new linkedlist and add head */
    public void addNode(int index, Node node) {
        if (routes[index]==null)
            routes[index] = new LinkedList<Node>();
        routes[index].add(node);
    }

    public void printMap(ArrayList<String> locations) {
        for (int i=0; i<locations.size(); i++) {
            System.out.println("Index: " + i + " Start: " + locations.get(i));
            System.out.println(Arrays.toString(routes[i].toArray()).replace("},", "},\n") + "\n");
        }
    }
}
