import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class Background extends Sprite {

	static Image background;
	static Image background2;
	static Image layer;
	static Image layer2;
	static Image layer3;
	static Image layer4;
	static Image frontLayer = null;
	int mapNumber;
	int topLayer;


	Background(int imageNumber) throws IOException, FontFormatException {
		background = ImageIO.read(new File("background.png"));
		background2 = ImageIO.read(new File("background2.png"));
		layer = ImageIO.read(new File("map1backLayer.png"));
		layer2 = ImageIO.read(new File("map2backLayer.png"));
		layer3 = ImageIO.read(new File("map3backLayer.png"));
		layer4 = ImageIO.read(new File("map4backLayer.png"));
		this.mapNumber = imageNumber;

	}

	void draw(Graphics g) {

		if (mapNumber == 1) {
			g.drawImage(background, 0, 0, null);
			g.drawImage(layer, 0, 0, null);
		} else if (mapNumber == 2) {
			g.drawImage(background, 0, 0, null);
			g.drawImage(layer2, 0, -55, null);
		} else if (mapNumber == 3) {
			g.drawImage(background, 0, 0, null);
			g.drawImage(layer3, 0, 0, null);
		} else if (mapNumber == 4) {
			g.drawImage(background2, 0, 0, null);
			g.drawImage(layer4, 0, 0, null);
		}
	}

	@Override
	void update(LinkedList<Sprite> s) {
		// TODO Auto-generated method stub

	}

	@Override
	void collision(LinkedList<Sprite> s) {
		// TODO Auto-generated method stub

	}

}
