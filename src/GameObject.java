  
import javax.swing.*;

public abstract class GameObject extends JLabel{
	private Thread mover;
	private ImageIcon shape;
	private int x;
	private int y;
	
	public GameObject(int x, int y) {
		draw(x, y);
	}

	abstract void draw(int x, int y);
	
}