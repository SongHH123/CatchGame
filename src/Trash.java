import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Vector;

public class Trash extends GameObject {
	
	public Trash() {
		super(0, 0);
	}
	
	@Override
	void draw(int x, int y) {
		Vector<ImageIcon> images = new Vector<ImageIcon>(2);

		ImageIcon apple = new ImageIcon("image/trash_A.png");
		ImageIcon bone = new ImageIcon("image/trash_F.png");
		
		Image scaledAppleImage = apple.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		Image scaledBoneImage = bone.getImage().getScaledInstance(75, 75, Image.SCALE_SMOOTH);
		
		images.add(new ImageIcon(scaledAppleImage));
		images.add(new ImageIcon(scaledBoneImage));
		
		int selectionIdx = (int)(Math.random()*2);
		this.setIcon(images.get(selectionIdx));
		setSize(75, 75);
		
		x = (int)(Math.random()*900);
		y = (int)(Math.random()*600);
		setLocation(x, y);
		
	}
}
