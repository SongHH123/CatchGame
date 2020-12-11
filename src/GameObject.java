  
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public abstract class GameObject extends JLabel{
	private int x;
	private int y;
	private boolean isClicked = false;
	
	public GameObject(int x, int y) {
		draw(x, y);		
		
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {		
				isClicked = true;
				FishCatch.GamePanel gamePanel = (FishCatch.GamePanel)getParent();
				gamePanel.processClickedGameObject((GameObject)e.getSource());
			}
			
		});
	}

	abstract void draw(int x, int y);
	
}