//Harshil Ratnu(hratnu) and Shivali Singh(ssingh53)
import java.util.LinkedList;
import java.util.List;

public class vertex implements Comparable {
	private double longitude;
	private double latitude;
	private String id;
	private double distance = Double.MAX_VALUE;
	public boolean visited = false;
	private vertex parent = null;
	private List<edge> neighborIds = new LinkedList<edge>();
	public vertex(double longitude, double latitude, String id) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.id = id;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Point{longitude="+longitude+", latitude=" + latitude +", id=" + id + ", neighborIds="+ neighborIds + ", distance="
				+ distance + ", visited=" + visited + ", parent=" + parent+ "}"; 
	}
	public List<edge> getNeighborIds() {
		return neighborIds;
	}
	public void setNeighborIds(List<edge> neighborId) {
		this.neighborIds = neighborId;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public vertex getParent() {
		return parent;
	}
	public void setParent(vertex parent) {
		this.parent = parent;
	}
	@Override
	public int compareTo(Object node) {
		// TODO Auto-generated method stub
		//return ((Node) node).getDistance() >= distance ? 0 : -1;
		if (distance < ((vertex)node).distance) 
            return -1; 
        if (distance > ((vertex)node).distance) 
            return 1; 
        return 0; 
	}
}
