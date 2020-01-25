//Harshil Ratnu(hratnu) and Shivali Singh(ssingh53)
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {
	public static Map<String, vertex> Nodes = new HashMap<String, vertex>();
	static void dijkstra(vertex start, vertex end) {
		PriorityQueue<vertex> nextNode = new PriorityQueue<vertex>();
		start.setDistance(0);
		nextNode.add(start);
		while(nextNode.isEmpty() == false){
			vertex current = nextNode.poll();
			current.visited = true;
			List<edge> neighbors = current.getNeighborIds();
			for (edge e : neighbors) {
				if (Nodes.get(e.endVId).visited == false) {
					if (Nodes.get(e.endVId).getDistance() >
					current.getDistance() + e.weight) {
						Nodes.get(e.endVId).setDistance(
								current.getDistance() +	e.weight);
						Nodes.get(e.endVId).setParent(current);
						nextNode.add(Nodes.get(e.endVId));
						Nodes.get(e.endVId).visited = true;
					}
				}
			}
			if(end.visited == true) {
				StreetMap.found = true;
				System.out.println("Found");
				break;
			}
		}
	}
}
