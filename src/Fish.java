import java.awt.Image;
import java.util.Vector;
import javax.swing.ImageIcon;

public class Fish extends GameObject{

	public Fish(int x, int y) {
		super(x, y);
	}

	@Override
	void draw(int x, int y) {
		Vector<ImageIcon> images = new Vector<ImageIcon>(3);
		ImageIcon fishFL = new ImageIcon("image/fish1.png");		
		Image scaledfishFLImage = fishFL.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon fishFR = new ImageIcon("image/fish2.png");
		Image scaledfishFRImage = fishFR.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		ImageIcon tripleFish = new ImageIcon("image/fish3.png");
		Image scaledtripleFishImage = tripleFish.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);

		images.add(new ImageIcon(scaledfishFLImage));
		images.add(new ImageIcon(scaledfishFRImage));
		images.add(new ImageIcon(scaledtripleFishImage));
		
		int selectionIdx = (int)(Math.random()*2);
		int randomY = (int)(Math.random()*600);
		this.setIcon(images.get(selectionIdx));
		
		setSize(100,100);
		if(selectionIdx == 0)	setLocation(870, randomY);
		else	setLocation(-70, randomY);
	}

}
