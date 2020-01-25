//Harshil Ratnu(hratnu) and Shivali Singh(ssingh53)

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.Timer;

//class to display the main graph and directions on the graph
public class canvas extends JComponent {
	
Timer timer;
	@Override
	public void paintComponent(Graphics g) {
		
		if(StreetMap.mapName.equals("ur.txt")) {
		timer = new Timer(500, new callBack());
		}
		if(StreetMap.mapName.equals("monroe.txt")) {
			timer = new Timer(200, new callBack());
			}
		if(StreetMap.mapName.equals("nys.txt")) {
			timer = new Timer(20, new callBack());
			}
		timer.start();

	
	
		int w= getWidth();
		int h= getHeight();
		//to shift the graph according to the window size
		int paddingX,paddingY;
		paddingX=0;
		if( w> h) {
		paddingX = (w-h)/2;
		}
		paddingY=20;
		if( h>w) {
		paddingY= (h-w)/2;
		}
	
		//drawing the main graph
		Graphics2D g2 = (Graphics2D)g;
		int c= (w+500)/2000*(h+200)/1200;
		for (String key : Graph.Nodes.keySet()) {
			List<edge> adjList = Graph.Nodes.get(key).getNeighborIds();
			for (edge e : adjList) {
		
				g2.setColor(Color.green);
				g2.setStroke(new BasicStroke(1));
				g2.drawLine(
						(800-scaleLong(Graph.Nodes.get(key).getLongitude()))*(w+500)/2000*(h+200)/1200+paddingX,
						(800-scaleLat(Graph.Nodes.get(key).getLatitude()))*(w+500)/2000*(h+200)/1200+paddingY,
						(800-scaleLong(Graph.Nodes.get(e.endVId).getLongitude()))*(w+500)/2000*(h+200)/1200+paddingX,
						(800-scaleLat(Graph.Nodes.get(e.endVId).getLatitude()))*(w+500)/2000*(h+200)/1200+paddingY
						);
				
			}
		}
		
		if (StreetMap.found == true) {
		
			
			g.setColor(Color.blue);
			g.fillOval(Math.abs(800 - scaleLong(StreetMap.pathList.get(0).getLongitude()))*(w+500)/2000*(h+200)/1200+paddingX - 10,
					Math.abs(800 - scaleLat(StreetMap.pathList.get(0).getLatitude()))*(w+500)/2000*(h+200)/1200+paddingY - 10, 20, 20);
			g.setColor(Color.yellow);
			g.fillOval(Math.abs(800 - scaleLong(StreetMap.pathList.get(StreetMap.pathList.size()-1).getLongitude()))*(w+500)/2000*(h+200)/1200+paddingX-10,
					Math.abs(800- scaleLat(StreetMap.pathList.get(StreetMap.pathList.size()-1).getLatitude()))*(w+500)/2000*(h+200)/1200+paddingY-10, 20, 20);
			
			//drawing the directions
			for (int i = 0; i < StreetMap.pathList.size() - 1; i++) {
				
				vertex start = StreetMap.pathList.get(i);
				vertex end = StreetMap.pathList.get(i+1);
				g2.setColor(Color.white);
				g2.setStroke(new BasicStroke(6));
				int startX = Math.abs(800 - scaleLong(start.getLongitude()))*(w+500)/2000*(h+200)/1200+paddingX;	
				int startY = Math.abs(800 - scaleLat(start.getLatitude()))*(w+500)/2000*(h+200)/1200+paddingY;
				int endX = Math.abs(800 - scaleLong(end.getLongitude()))*(w+500)/2000*(h+200)/1200+paddingX;	
				int endY = Math.abs(800 - scaleLat(end.getLatitude()))*(w+500)/2000*(h+200)/1200+paddingY;	
				g2.drawLine(startX, startY, endX, endY);
				g.setColor(Color.red);
				g.fillOval(Math.abs(800 - scaleLong(markX))*(w+500)/2000*(h+200)/1200+paddingX - 10,
						Math.abs(800 - scaleLat(markY))*(w+500)/2000*(h+200)/1200+paddingY - 10, 20, 20);
				g.setColor(Color.MAGENTA);
				g.setFont(new Font("Courier New", Font.BOLD, 30));
				if (mark==0) {
					g.drawString("You have arrived at your destination.",w/2-400, 100);
					
					g.drawString("Total Distance-"+ (StreetMap.dist)*0.01+ " miles",w/2-350, h-100);		
				}
				
				
			}
		}
	}
	
	//to scale the graph
	public int scaleLong(double lon) {
	
		return (int)((lon - StreetMap.minLong)/(StreetMap.maxLong - StreetMap.minLong) * 800);

	}
	public int scaleLat(double lat) {

		return (int)((lat - StreetMap.minLat)/(StreetMap.maxLat - StreetMap.minLat) * 800);
	}
	
	
	//to keep an 
	int mark= StreetMap.pathList.size()-1;
	double markX,markY;
	public class callBack implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(StreetMap.DirectOrNot==true) {
			if(mark==0) {
				timer.stop();
				repaint();
				return;				
			}
			mark--;
			markX= StreetMap.pathList.get(mark).getLongitude();
			markY= StreetMap.pathList.get(mark).getLatitude();
			repaint();
			}	
		}
	}
	
	
}

