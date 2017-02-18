import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.Timer;

public class Game extends JFrame implements ActionListener {
	Model model;
	static Game g;
    //variables
	
    public static final int UPDATE_TIME = 2;
    public static final int DURATION = 1000;
 
    private Point primaryLocation;
    private long startTime;
    private Timer time;

	public Game() throws Exception {
		this.model = new Model();
		Controller controller = new Controller(this.model);
		View view = new View(this.model);
		this.addKeyListener(controller);// Adds to JPanel rather than Frame
		this.addMouseListener(controller);
		this.addMouseMotionListener(controller);
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		this.setSize(1920, 1080);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		new Timer(20, this).start(); // Indirectly calls actionPerformed at
										// regular intervals
	}

	public void actionPerformed(ActionEvent evt) {
		try {
			this.model.update();
			if(this.model.map1.boss.isSmashing){
				ShakingFrame s = new ShakingFrame(this);
				s.startShake();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		repaint(); // Indirectly calls View.paintComponent
	}     
  
	public static void main(String[] args) throws Exception{
		new Game();
		
	}
	
	public void restart() {
		try{
			
		}
		catch(Exception e){
			System.out.println("adskljfasdlkjf");
		}
	}
}
