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
	Vector<Trash> trashes = new Vector<Trash>(10);
	
	public FishCatch() {
		setTitle("���� �Ա� ����!");
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
		Thread trashGenerator;
		
		public GamePanel() {
			setLayout(null);
			backgroundIcon = new ImageIcon(Background.LOADING);
			customcursor();
			
			trashGenerator = new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i = 0; i<18; i++) {
						try {
							Thread.sleep(50);
							trashes.add(new Trash(150, 150));
							add(trashes.get(i));
							repaint();
						} catch (InterruptedException e) {
							return;
						}
					}
					
					// Change the location of trashes.
					while(true) {
						try {
							for(int i = 0; i < trashes.size(); i++) {
								Trash t = trashes.get(i);
								int x = (int)(Math.random()*10 -5);
								int y = (int)(Math.random()*4 -2);
								t.setLocation(t.getX() + x, t.getY() + y);
							}
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
			
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
						trashGenerator.start();
					}
				}
			});
			
			Fish f = new FishFromLeft(100, 100);
			add(f);
			
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
			/*Ŀ���������*/
			Toolkit tk = Toolkit.getDefaultToolkit();
			Image cursorimage=tk.getImage("image/foot.png");//Ŀ���� ����� �̹���
			Point point=new Point(0,0);
		    //���ο� custom Ŀ��(image cursor, Point hotSpot, String name)
			Cursor cursor=tk.createCustomCursor(cursorimage, point, "haha");
		    //page1�̶�� �гο� Ŀ���� ����.
			this.setCursor(cursor); 
		}
	}
	
	
	
}