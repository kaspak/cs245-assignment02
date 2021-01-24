import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
    /* an array of linkedlists holding nodes */
    private LinkedList<Node>[] routes;
    private LinkedList<Node> shortestPath;
    private Node startNode;
    private Node endNode;
    private float distance;

    public Graph(int size, String start, int startPath, String end, int endPath) {
        routes = new LinkedList[size];
        shortestPath = new LinkedList<Node>();
        startNode = new Node(start, 0, 0, startPath);
        endNode = new Node(end, 0, 0, endPath);
        distance = 0;
    }

    /* if null, make a new linkedlist and add head */
    public void addNode(int index, Node node) {
        if (routes[index]==null)
            routes[index] = new LinkedList<Node>();
        routes[index].add(node);
    }


    public LinkedList<Node> findShortestPath(HashMap<String, Integer> attractions) {
        /* add node of starting city to shortestPath*/
        Node node = startNode;
        shortestPath.add(startNode);

        while(!node.getCity().equals(endNode.getCity())) {
            node.setVisited();
            node = shortestPath(routes[node.getPath()], node.getPath(), node);
            shortestPath.add(node);
        }
        return shortestPath;
    }

    public Node shortestPath(LinkedList<Node> routes, int path, Node node) {
        /* travNode is for traversing through linked list,
         * bestNode is the returned node with the lowest cost for the shortest path
         * lowCost is used to compare weights between nodes/vertices
         * lowNodeIndex is used to return the lowest cost node */
        Node travNode = routes.getFirst(), bestNode = null;
        float lowCost = travNode.getCost();
        int lowNodeIndex=0;

        for (int i=1; i<routes.size(); travNode=routes.get(i++)) {
            /* skip node if it's been visited (undirected graph) */
            if (!travNode.isVisited()) {

                /* mark visited and current path */
                travNode.setPath(node.getPath());

                /* find lowest cost vertex */
                if (travNode.getCost() < lowCost + distance) {
                    lowCost = travNode.getCost();
                    lowNodeIndex = i;
                }
            }
        }
        distance += lowCost;

        if (lowNodeIndex<0) {
            return null;
        }
        return new Node(routes.get(lowNodeIndex));
    }

    public void printMap(ArrayList<String> locations) {
        for (int i=0; i<locations.size(); i++) {
            System.out.println("Index: " + i + " Start: " + locations.get(i));
            System.out.println(Arrays.toString(routes[i].toArray()).replace("},", "},\n") + "\n");
        }
    }
}