import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;

public class TripleFish extends Fish {

	public TripleFish(int y) {
		super(0, y);
	}
	
	@Override
	void draw(int x, int y) {
		ImageIcon tripleFish = new ImageIcon("image/fish3.png");
		Image scaledImage = tripleFish.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		tripleFish = new ImageIcon(scaledImage);
		
		this.setIcon(tripleFish);
		setSize(100, 100);
		
		setLocation(x, y);
	}
}
