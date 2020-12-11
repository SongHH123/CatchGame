import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;

public class FishFromRight extends Fish {

	public FishFromRight(int y) {
		super(800, y);
	}
	
	@Override
	void draw(int x, int y) {
		ImageIcon fishFromRight = new ImageIcon("image/fish1.png");
		Image scaledImage = fishFromRight.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		fishFromRight = new ImageIcon(scaledImage);
		
		this.setIcon(fishFromRight);
		setSize(100, 100);
		
		setLocation(x, y);
	}
}
