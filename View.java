import javax.swing.JPanel;
import java.awt.Graphics;
import java.io.IOException;

class View extends JPanel {
	Model model;

	View(Model m) throws IOException {
		this.model = m;
	}

	public void paintComponent(Graphics g) {
		//Draw the Person & Map, Map first so person overlaps platforms/Portals/Anything else in Map
		//Model Draw
		model.draw(g);
	}
}
