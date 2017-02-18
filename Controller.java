import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.Timer;

class Controller implements KeyListener, ActionListener, MouseListener, MouseMotionListener {
	Model model;
	PauseMenu menu;

	private Timer time;

	Controller(Model m) throws IOException {
		this.model = m;
		menu = model.menu;
		time = new Timer(2, this);
		time.start();
	}

	// Hovered event for hover icons
	public void mouseMoved(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();

		// Hover event for pause button
		if (mouseX > menu.pauseButton().x && mouseX < menu.pauseButton().x + menu.pauseButton().width
				&& mouseY > menu.pauseButton().y && mouseY < menu.pauseButton().y + menu.pauseButton().height) {

			menu.isPauseButtonHovered = true;

		} else {
			menu.isPauseButtonHovered = false;
		}
		// Hover event for resume button
		if (mouseX > menu.resumeButton().x && mouseX < menu.resumeButton().x + menu.resumeButton().width
				&& mouseY > menu.resumeButton().y && mouseY < menu.resumeButton().y + menu.resumeButton().height) {

			menu.isResumeButtonHovered = true;

		} else {
			menu.isResumeButtonHovered = false;
		}
		// Hover event for settings button
		if (mouseX > menu.settingsButton().x && mouseX < menu.settingsButton().x + menu.settingsButton().width
				&& mouseY > menu.settingsButton().y
				&& mouseY < menu.settingsButton().y + menu.settingsButton().height) {

			menu.isSettingsButtonHovered = true;

		} else {
			menu.isSettingsButtonHovered = false;
		}
		// Hover event for exit button
		if (mouseX > menu.exitButton().x && mouseX < menu.exitButton().x + menu.exitButton().width
				&& mouseY > menu.exitButton().y && mouseY < menu.exitButton().y + menu.exitButton().height) {

			menu.isExitButtonHovered = true;

		} else {
			menu.isExitButtonHovered = false;
		}
		// Hover event for back button
		if (mouseX > menu.backButton().x && mouseX < menu.backButton().x + menu.backButton().width
				&& mouseY > menu.backButton().y && mouseY < menu.backButton().y + menu.backButton().height
				&& !menu.isGameStarted) {

			menu.isBackButtonHovered = true;

		} else {
			menu.isBackButtonHovered = false;
		}
	}

	// Press event for buttons
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX();
		int mouseY = e.getY();
		// This is the button for pause
		if (mouseX > menu.pauseButton().x && mouseX < menu.pauseButton().x + menu.pauseButton().width
				&& mouseY > menu.pauseButton().y && mouseY < menu.pauseButton().y + menu.pauseButton().height
				&& (!menu.isGamePaused && menu.isGameStarted)) {
			menu.isGamePaused = true;
			this.model.pause();
			menu.isPauseButtonClicked = true;
			menu.isMainMenuActive = true;
			menu.isButtonClicked = true;
			
		}
		if (mouseX > menu.resumeButton().x && mouseX < menu.resumeButton().x + menu.resumeButton().width
				&& mouseY > menu.resumeButton().y && mouseY < menu.resumeButton().y + menu.resumeButton().height
				&& (menu.isGamePaused || !menu.isGameStarted) && !menu.isSettingsOpened) {
			if (menu.isGamePaused)
				menu.isGamePaused = false;
			else
				menu.isGameStarted = true;
			menu.isMainMenuActive = true;
			menu.isPauseButtonClicked = false;
			menu.isButtonClicked = true;
			this.model.pause();
		}

		if (mouseX > menu.settingsButton().x && mouseX < menu.settingsButton().x + menu.settingsButton().width
				&& mouseY > menu.settingsButton().y && mouseY < menu.settingsButton().y + menu.settingsButton().height
				&& (menu.isGamePaused || !menu.isGameStarted) && !menu.isSettingsOpened) {
			menu.isMainMenuActive = false;
			menu.isSettingsOpened = true;
			menu.isBackButtonClicked = true;
			menu.isButtonClicked = true;
		}
		if (mouseX > menu.backButton().x && mouseX < menu.backButton().x + menu.backButton().width
				&& mouseY > menu.backButton().y && mouseY < menu.backButton().y + menu.backButton().height
				 && menu.isSettingsOpened) {
	
			menu.isMainMenuActive = true;
			menu.isSettingsOpened = false;
			menu.isBackButtonClicked = true;
			menu.isButtonClicked = true;
		}
		if (mouseX > menu.exitButton().x && mouseX < menu.exitButton().x + menu.exitButton().width
				&& mouseY > menu.exitButton().y && mouseY < menu.exitButton().y + menu.exitButton().height
				&& (menu.isGamePaused || !menu.isGameStarted) && !menu.isSettingsOpened && !menu.isBackButtonClicked) {
			System.exit(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_LEFT) {
			// System.out.println("LEFT ARROW!");
			this.model.stopLeftArrow();
		} else if (keyCode == KeyEvent.VK_RIGHT) {
			// System.out.println("RIGHT ARROW!");
			this.model.stopRightArrow();
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		// If the game is paused, then we shouldn't have a keylistener
		// for anything except the pause button
		if (!this.model.isPaused && this.model.person.DefaultHP > 0) {
			if (keyCode == KeyEvent.VK_LEFT) {
				// System.out.println("LEFT ARROW!");
				this.model.LeftArrow();
			} else if (keyCode == 87) {
				this.model.switchWeapon();
			} else if (keyCode == KeyEvent.VK_RIGHT) {
				// System.out.println("RIGHT ARROW!");
				this.model.RightArrow();
			} else if (keyCode == KeyEvent.VK_SHIFT) {
				// System.out.println("JUMP!");
				this.model.Jump();
			} else if (keyCode == KeyEvent.VK_UP) {
				// System.out.println("UP ARROW!");
				this.model.usePortal();
			} else if (keyCode == KeyEvent.VK_SPACE) {
				// System.out.println("UP ARROW!");
				try {
					this.model.Space();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
