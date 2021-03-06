//Harshil Ratnu(hratnu) and Shivali Singh(ssingh53)
public class edge {
	public String endVId;
	public double weight;
	public edge(String endVId, double weight) {
		this.endVId = endVId;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "("+endVId+", "+weight+")";
	}
	
	/**
	 * Uses the haversine formula to compute distance between two places
	 * @param lat1		start latitude
	 * @param lat2		end latitude
	 * @param long1		start longitude	
	 * @param long2		end longitude
	 * @return distance between the start and end in miles
	 */
	static double calculateWeight(double lat1, double lat2, double long1, double long2) {
		final double r = 3959; //radius of earth in miles
		double changeInLat = Math.toRadians(lat2 - lat1);
		double changeInLong = Math.toRadians(long2 - long1);
		double a = Math.sin(changeInLat/2) * Math.sin(changeInLat/2) + 
				Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
				Math.sin(changeInLong/2) * Math.sin(changeInLong/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return r * c;
	}


}
