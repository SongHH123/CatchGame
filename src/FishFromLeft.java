import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;

public class FishFromLeft extends Fish {

	public FishFromLeft(int y) {
		super(0, y);
	}
	
	@Override
	void draw(int x, int y) {
		ImageIcon fishFromLeft = new ImageIcon("image/fish2.png");
		Image scaledImage = fishFromLeft.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		fishFromLeft = new ImageIcon(scaledImage);
		
		this.setIcon(fishFromLeft);
		setSize(100, 100);
		
		setLocation(x, y);
	}
}
