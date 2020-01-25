//Harshil Ratnu(hratnu) and Shivali Singh(ssingh53)
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
//class to add a panel displaying information about start and vertex on the JFrame
public class info extends JPanel {

	
	public info() {
	
		setPreferredSize(new Dimension(100,100));

		this.setLayout(new GridLayout(1,2));

		JLabel origin= new JLabel("Origin: "+ StreetMap.origin);
		origin.setFont(new Font("Courier New", Font.BOLD, 24));
		origin.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(origin);
		
		JLabel dest= new JLabel("Destination: "+ StreetMap.dest);
		dest.setFont(new Font("Courier New", Font.BOLD, 24));
		dest.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(dest);
		

		
	}
	
}
