import java.awt.Image;
import java.util.Vector;

import javax.swing.ImageIcon;

public class FishFromLeft extends Fish {

	public FishFromLeft(int x, int y) {
		super(x, y);
	}
	
	@Override
	void draw(int x, int y) {
		ImageIcon fishFR = new ImageIcon("image/fish2.png");
		Image scaledfishFRImage = fishFR.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		fishFR = new ImageIcon(scaledfishFRImage);
		
		this.setIcon(fishFR);
		setSize(100, 100);
		
		int _y = (int)Math.random()*600;
		setLocation(0, _y);
	}

}
