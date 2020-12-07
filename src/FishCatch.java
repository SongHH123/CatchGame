import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FishCatch extends JFrame{
	private Vector<GameObject> objs = new Vector<GameObject>();
	
	public FishCatch() {
		setTitle("생선 먹기 게임!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		setResizable(false);
		
		GamePanel gamePanel = new GamePanel();
		add(gamePanel);
		
		setVisible(true);
		
	}
	
	
	class GamePanel extends JPanel {
		private ImageIcon backgroundIcon;
		int backgroundMode = 0;
		
		public GamePanel() {
			setLayout(null);
			backgroundIcon = new ImageIcon(Background.LOADING);
			customcursor();
			
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					backgroundMode += 1;
					if(backgroundMode == 1) {
						backgroundIcon = new ImageIcon(Background.EXPLAIN);
						repaint();
					}else if(backgroundMode == 2) {
						backgroundIcon = new ImageIcon(Background.PLAYING);
						repaint();
					}
				}
			});
			
			for(int i = 0; i<5; i++) {
				 try {
					Thread.sleep(1000);
					objs.add(new Trash(150, 150));
					add(objs.get(i));
				} catch (InterruptedException e) {
					return;
				}
			}
			 //Fish f = new Fish(100, 200);
			 //add(f);
			 
		}
		
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Image image = backgroundIcon.getImage();
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		}
		
		public void customcursor() {
			/*커서만드는중*/
			Toolkit tk = Toolkit.getDefaultToolkit();
			Image cursorimage=tk.getImage("image/foot.png");//커서로 사용할 이미지
			Point point=new Point(0,0);
		    //새로운 custom 커서(image cursor, Point hotSpot, String name)
			Cursor cursor=tk.createCustomCursor(cursorimage, point, "haha");
		    //page1이라는 패널에 커서를 설정.
			this.setCursor(cursor); 
		}
	}
	
	
	
}