import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Vector;

public class Trash extends GameObject {
	
	public Trash(int x, int y) {
		super(x, y);
	}
	
	@Override
	void draw(int x, int y) {
		Vector<ImageIcon> images = new Vector<ImageIcon>(2);

		ImageIcon apple = new ImageIcon("image/trash_A.png");
		ImageIcon bone = new ImageIcon("image/trash_F.png");
		
		Image scaledAppleImage = apple.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		Image scaledBoneImage = bone.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		
		images.add(new ImageIcon(scaledAppleImage));
		images.add(new ImageIcon(scaledBoneImage));
		
		int selectionIdx = (int)(Math.random()*2);
		this.setIcon(images.get(selectionIdx));
		setSize(50,50);
		setLocation(x, y);
	}
}
