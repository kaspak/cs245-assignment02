# cs245-assignment02

## Notes: 
- I changed roads.csv and attractions.csv file types into .txt files due to some conflicts with my IDE.
- My graph is fully functioning and can be printed (I have made a print method for both the graph and hashmap for attractions list)
- Despite the algorithm being incomplete, I brainstormed and came up with some ideas to get to a working solution I plan to implement on my own. 

### My plan:
- using a set to track visted nodes
- using a map to form the shortest path route and keep track of distance/cost between potential shortest paths
- using a queue to look at adjacent vertices and compare costs


### About Roadtrip.java
- main driver class, but includes methods for reading and scanning given files and organizing roads.csv and attractions.csv into respective data structures.
- creates maps and tests given inputs for testing the shortest path algorithm (incomplete)

### About Node.java
- used to initalize the vertices for the graph
- each node contains information about the city (string), cost (miles/minutes), path and visited (boolean)

### About Graph.java
- used to connect and form relations between each destination (starting and ending cities) based on roads.csv
- includes (incomplete) method for finding the shortest path
