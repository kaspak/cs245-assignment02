import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RoadTrip {
    /* string = name of attraction, integer = vertex number of the city */
    private HashMap<String, Integer> route;
    private ArrayList<String> locations;

    public RoadTrip() {
        route = new HashMap<String, Integer>();
        locations = new ArrayList<String>();
    }

    /* read file path */
    public static void getFileInfo(File input) {
        System.out.println("File Name: " + input.getName());
        System.out.println("Path: " + input.getAbsolutePath());
    }

    /* reads given file and returns size of total int of city location names */
    public void initLocationsList(File inputFile, Scanner scan) throws FileNotFoundException {
        scan = new Scanner(inputFile);
        String currentCity = "";

        /* if there is a next line, compare with current city and add to arraylist. */
        while (scan.hasNext()) {
            String[] location = scan.nextLine().split(",");

            if (!currentCity.equals(location[0])) {
                currentCity = location[0];
                locations.add(currentCity);
            }
        }
    }

    /* return Arraylist locations size */
    public int getLocationsSize() {
        return locations.size();
    }

    /* return Arraylist location, given an index */
    public String getLocation(int index) {
        return locations.get(index);
    }

    /* returns index if location is found in list, else returns -1 */
    public int findLocation(String location) {
        for (int i=0; i<locations.size(); i++) {
            if (locations.get(i).equals(location)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File roadsFile = new File(args[0]);
        Scanner scan = new Scanner(roadsFile);
        RoadTrip trip = new RoadTrip();

        /* read file path */
        RoadTrip.getFileInfo(roadsFile);

        /* store starting city locations of road.csv into an arraylist */
        trip.initLocationsList(roadsFile, scan);

        /* initalize map and start connecting vertices */
        int totalLocations = trip.getLocationsSize();
        Graph map = new Graph(totalLocations);

        while (scan.hasNext()) {
            /* get current line from file, roads.csv = start, end, miles, minutes */
            String[] road = scan.nextLine().split(",");
            int index = trip.findLocation(road[0]);

            /* if a matching location is found, add node of starting and ending city (undirected) */
            if (index >= 0 && index < totalLocations) {

                /* starting city = road[0], ending city = road[1] */
                map.addNode(index, new Node(road[1], Integer.parseInt(road[2]), Integer.parseInt(road[3])));

                /* starting city = road[1], ending city = road[0] */
                if (trip.findLocation(road[1]) >= 0)
                    map.addNode(trip.findLocation(road[1]), new Node(road[0], Integer.parseInt(road[2]), Integer.parseInt(road[3])));
            }
        }
        System.out.println("...");
        map.printMap(trip.locations);
    }
}
