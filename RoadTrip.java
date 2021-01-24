import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RoadTrip {
    /* string = name of attraction, integer = vertex number of the city */
    private HashMap<String, Integer> attractions;
    private ArrayList<String> locations;
    private final int TOTAL_LOCATIONS = 214;

    public RoadTrip() {
        this.attractions = new HashMap<String, Integer>();
        locations = new ArrayList<String>();
    }

    /* read file path */
    public static void getFileInfo(File input) {
        System.out.println("File Name: " + input.getName());
        System.out.println("Path: " + input.getAbsolutePath());
    }

    /* reads roads.txt file and adds starting cities to locations arraylist */
    public void initLocations(File rFile) throws FileNotFoundException {
        Scanner rScan = new Scanner(rFile);
        String currentCity = "";

        /* if there is a next line, compare with current city and add to arraylist. */
        while (rScan.hasNext()) {
            String[] location = rScan.nextLine().split(",");

            if (!currentCity.equals(location[0])) {
                currentCity = location[0];
                locations.add(currentCity);
            }
        }
    }

    /* reads attraction.txt file and adds attraction and index of location */
    public void initAttractions(File aFile, String[] places) throws FileNotFoundException {
        Scanner aScan = new Scanner(aFile);
        String[] attracts = new String[places.length];
        String[] attractsLocation = new String[places.length];
        int matches = places.length;

        /* split up each item in places into attraction, location */
        for (int i=0; i<places.length; i++) {
            String[] temp = places[i].split(",");
            attracts[i] = temp[0];
            attractsLocation[i] = temp[1];
        }

        /* if there is a next line, compare with current attraction and add to attractions. */
        while (aScan.hasNext() && matches>0) {
            String[] location = aScan.nextLine().split(",");
            int locationIndex = findLocation(location[1]);

            /* add attraction if matching and location (index) is found */
            for (int i=0; i<places.length; i++) {
                if (attracts[i].equals(location[0]) && locationIndex>=0) {
                    attractions.put(places[i], locationIndex);
//                    System.out.println("added: " + places[i] + " index: " + locationIndex);
                    matches--;
                }
            }
        }
    }

    /* reads road.txt file and creates a graph based on starting and ending cities */
    public void initMap(File rFile, Graph map) throws FileNotFoundException {
        Scanner rScan = new Scanner(rFile);
        while (rScan.hasNext()) {
            /* get current line from file, roads.csv = start, end, miles, minutes */
            String[] road = rScan.nextLine().split(",");
            int index = findLocation(road[0]);

            /* if a matching location is found, add node of starting and ending city (undirected) */
            if (index>=0 && index<getTOTAL_LOCATIONS()) {

                /* starting city = road[0], ending city = road[1] */
                map.addNode(index, new Node(road[1], Integer.parseInt(road[2]), Integer.parseInt(road[3])));

                /* starting city = road[1], ending city = road[0] */
                if (findLocation(road[1]) >= 0)
                    map.addNode(findLocation(road[1]), new Node(road[0], Integer.parseInt(road[2]), Integer.parseInt(road[3])));
            }
        }
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

    /* return ArrayList locations */
    public ArrayList<String> getLocations() {
        return locations;
    }

    public HashMap<String, Integer> getAttractions() {
        return attractions;
    }

    /* return ArrayList locations size */
    public int getTOTAL_LOCATIONS() {
        return TOTAL_LOCATIONS;
    }

    /* prints the hashmap with key=attraction, value=index */
    public void printAttractions() {
        System.out.println("Attractions & Index of Location " + attractions.toString().replace(", ", ", \n"));
    }

    public static void main(String[] args) throws FileNotFoundException {
        File rFile = new File(args[0]);
        File aFile = new File(args[1]);
        Scanner rScan = new Scanner(rFile);
        Scanner aScan = new Scanner(aFile);
        RoadTrip trip = new RoadTrip();

        String[] attractions = new String[] {
                "Grand Canyon,Grand Canyon AZ",
                "Paul Bunyon and Babe the Blue Ox,Bemidji MN",
                "The Empire State Building,New York NY"};

        /* print file path */
        RoadTrip.getFileInfo(rFile);
        RoadTrip.getFileInfo(aFile);

        /* store starting city locations of road.csv into an arraylist */
        trip.initLocations(rFile);

        Graph map = new Graph(trip.getTOTAL_LOCATIONS(),
                "San Francisco CA", trip.findLocation("San Francisco CA"),
                "Miami FL", trip.findLocation("Miami FL"));

        /* initialize map nodes, edges, and print map */
        trip.initMap(rFile, map);
//        map.printMap(trip.getLocations());

        /* initialize attractions hashmap and print list of attractions with key and value */
        trip.initAttractions(aFile, attractions);
//        trip.printAttractions();

        System.out.println(map.findShortestPath(trip.getAttractions()));
    }
}