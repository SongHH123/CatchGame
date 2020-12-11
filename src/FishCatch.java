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
	private Vector<Fish> fishes = new Vector<Fish>(50);
	private Vector<Trash> trashes = new Vector<Trash>(20);
	private int score = 0;
	
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
		Thread trashThread;
		Thread fishGenerator;
		Thread fishMover;
		
		public GamePanel() {
			setLayout(null);
			backgroundIcon = new ImageIcon(Background.LOADING);
			customcursor();
			
			trashThread = new Thread(new TrashThread());
			fishGenerator = new Thread(new FishGenerator());
			fishMover = new Thread(new FishMover());
		
			
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
						trashThread.start();
						fishGenerator.start();
						fishMover.start();
					}
				}
			});
		}
		
		
		class FishGenerator implements Runnable {
			@Override
			public void run() {
				long i = 0;
				Fish newFish = null;
				while(true) {
					i++;
					int y = (int)(Math.random()*600);
					int typeOfFish = (int)(i % 11);
					
					if(typeOfFish == 0) {
						newFish = new TripleFish(y);
					}
					else if(typeOfFish % 2 == 1) {
						newFish = new FishFromLeft(y);
					}
					else{
						newFish = new FishFromRight(y);
					}
					
					fishes.add(newFish);
					add(newFish);
					revalidate();
					repaint();
					
					try {
						Thread.sleep(1000); // Generating Interval
					} catch (InterruptedException e) {
						return;
					}
					
				}
			}
		}
		
		
		class FishMover implements Runnable {
			@Override
			public void run() {
				int delay = 50;
				while(true) {
					for(int i = 0; i < fishes.size(); i++) {
						Fish f = fishes.get(i);
						
						// From Right
						if(f instanceof FishFromRight) {
							f.setLocation(f.getX() - 3, f.getY());		
						}
						else if(f instanceof FishFromLeft) {
							f.setLocation(f.getX() + 3, f.getY());
						}
						// Triple Fish
						else {
							f.setLocation(f.getX() + 5, f.getY());								
						}
						
					}
					
					if(score <= 500) {
						delay = 50;
					}
					else if(score <= 1000) {
						delay = 20;
					}
					else {
						delay = 8;
					}
					
					
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						return;
					}
				}
			}
		}
		
		
		class TrashThread implements Runnable {
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
						return;
					}
					
					
				}
			}
		}
		
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Image image = backgroundIcon.getImage();
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		}
		
		
		public void processClickedGameObject(GameObject target) {	
			//System.out.println(target.toString());
			
			remove(target);
			if(target instanceof TripleFish) {
				score += 30;
				fishes.remove(target);
			}
			else if(target instanceof Fish) {
				score += 10;
				fishes.remove(target);
			}
			else {
				if(score <100) {
					//게임종료
				}

			}
			
			//System.out.println(score);
			setTitle("현재 점수 : " + score);
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