import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class LightningPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Point> thePoints;
	ArrayList<Point> bolt;
	Point start;
	Point end;
	int counter = 600;
	Random r;
	int point1, point2, point3, point4;
	Font font;
	int milseconds;
	int seconds;
	int min;

	LightningPanel() {
		r = new Random();
	}

	void update() {
		if (counter < 10) {

			point1 = r.nextInt(1980 - 200) + 200;
			point2 = r.nextInt(1000 - 500) + 500;
			point3 = r.nextInt(500 - 10) + 10;
			point4 = r.nextInt(50 - 20) + 20;
			start = new Point(point1, point4);
			end = new Point(point2, point3);
			bolt = Lightning.buildBolt(start, end);
			setPoints(bolt);
		} else if (counter >= 15) {
			start = new Point(1, 1);
			end = new Point(1, 1);
			bolt = Lightning.buildBolt(start, end);
			setPoints(bolt);
		}
		// Change speed of lightning
		if (counter == 800)
			counter = 0;

		counter++;
	}

	public void setPoints(ArrayList<Point> points) {
		this.thePoints = points;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(Color.yellow);
		g2d.setStroke(new BasicStroke(2));
		if (thePoints != null) {
			for (int i = 0; i < thePoints.size() - 1; i++) {
				int x1 = (int) thePoints.get(i).getX();
				int y1 = (int) thePoints.get(i).getY();
				int x2 = (int) thePoints.get(i + 1).getX();
				int y2 = (int) thePoints.get(i + 1).getY();
				g2d.drawLine(x1, y1, x2, y2);
			}
		}
	}
}