//Harshil Ratnu(hratnu) and Shivali Singh(ssingh53)
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

public class StreetMap extends JFrame {
	static String mapName;
	static boolean DirectOrNot=false;
	static String origin;
	static String dest;
	static int dist;
	private static final long serialVersionUID = 6136394851913623525L;

	static boolean found = false;
	static double maxLat = 0;
	static double maxLong = 0;
	static double minLat = 400;
	static double minLong = 400;
	static List<vertex> pathList = new ArrayList<vertex>();
	public StreetMap() {
		setSize(1600, 1200);
		setTitle("Street Maps- HS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		getContentPane().setBackground(Color.black);
		add(new canvas(),BorderLayout.CENTER);
		add(new info(), BorderLayout.NORTH);
	}

	
	
	public static void main(String[] args) {
		Scanner scanner = null;
		String start = "";
		String end = "";
		mapName= args[0];
		File inputFile = new File(mapName);
		try {
			scanner = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			String[] lineData = line.split("\t");
			if(lineData[0].equals("i")) {
				Graph.Nodes.put(lineData[1], new vertex(Double.parseDouble(lineData[3]), Double.parseDouble(lineData[2]),
						lineData[1]));
				if (Math.abs(Double.parseDouble(lineData[2])) > Math.abs(maxLat)) {
					maxLat = Double.parseDouble(lineData[2]);
				}
				if (Math.abs(Double.parseDouble(lineData[3])) > Math.abs(maxLong)) {
					maxLong = Double.parseDouble(lineData[3]);
				}
				if (Math.abs(Double.parseDouble(lineData[2])) < Math.abs(minLat)) {
					minLat = Double.parseDouble(lineData[2]);
				}
				if (Math.abs(Double.parseDouble(lineData[3])) < Math.abs(minLong)) {
					minLong = Double.parseDouble(lineData[3]);
				}
			} else if (lineData[0].equals("r")) {
				double weight = edge.calculateWeight(Graph.Nodes.get(lineData[2]).getLatitude(), 
						Graph.Nodes.get(lineData[3]).getLatitude(), Graph.Nodes.get(lineData[2]).getLongitude(),
						Graph.Nodes.get(lineData[3]).getLongitude());
				Graph.Nodes.get(lineData[2]).getNeighborIds().add(new edge(lineData[3], weight));
				Graph.Nodes.get(lineData[3]).getNeighborIds().add(new edge(lineData[2], weight));
			}
		}

		if(args[1].equals("--show")) {
			
			try {
				if (args[2].equals("--directions")) {
					DirectOrNot=true;
					start = args[3];
					end = args[4];
					origin=start;
					dest=end;
					Graph.dijkstra(Graph.Nodes.get(start), Graph.Nodes.get(end));
					try {
						found = true;
						getPath(Graph.Nodes.get(start), Graph.Nodes.get(end));
					} catch (NullPointerException ex) {
						found = false;
						System.out.println("The nodes are not connected");
					}
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			}
			new StreetMap().setVisible(true);
		} else {
			DirectOrNot=true;
			try {
				if (args[2].equals("--show")) {
					start = args[3];
					end = args[4];
					new StreetMap().setVisible(true);
				} else {
					start = args[2];
					end = args[3];
				}
			} catch (ArrayIndexOutOfBoundsException ex) {

			}
			Graph.dijkstra(Graph.Nodes.get(start), Graph.Nodes.get(end));
			try {
				found = true;
				getPath(Graph.Nodes.get(start), Graph.Nodes.get(end));
			} catch (NullPointerException ex) {
				found = false;
				System.out.println("The nodes are not connected");
			}
		}
		if (found == true) {
			System.out.println("PATH: ");
			for (int i = pathList.size() - 1; i >= 0; i--) {
				System.out.println(pathList.get(i).getId() + " " + pathList.get(i).getDistance());
			}
		}
		if(DirectOrNot==true) {
		double d=pathList.get(0).getDistance();
		d=d*100;
		dist= (int) d;
		}

	}
	
	
	public static void getPath(vertex start, vertex end) throws NullPointerException {
		pathList.add(end);
		while(!pathList.contains(start)) {
			pathList.add(pathList.get(pathList.size()-1).getParent());
		}
	}

}
