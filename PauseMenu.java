import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PauseMenu {

	// Get screen dimensions
	private int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

	// Create variables for images
	static Image menu;
	static Image back;
	static Image backHover;
	static Image exit;
	static Image exitHover;
	static Image playHover;
	static Image play;
	static Image settings;
	static Image settingsHover;
	static Image pause;
	static Image pauseHover;
	static Image pauseMenu;
	static Image pauseClicked;
	static Image resume;
	static Image resumeHover;
	static Image settingsMenu;

	// Boolean variables
	boolean isSettingsOpened = false;
	boolean isBackButtonClicked;
	boolean isMainMenuActive = true;
	boolean isGameStarted = false;
	boolean isGamePaused = false;
	boolean isPauseButtonHovered;
	boolean isResumeButtonHovered;
	boolean isPauseButtonClicked;
	boolean isExitButtonHovered;
	boolean isSettingsButtonHovered;
	boolean isBackButtonHovered;
	boolean isButtonClicked;

	// The width and height of buttons
	private int buttonWidth = 250;
	private int buttonHeight = 102;

	// Gets the x value of the buttons
	private int buttonX = (screenWidth / 2) - 100;

	// Sets pause position for just pause
	int pauseX = 170;
	int pauseY = 870;

	// Gets button's y-direction
	private int startButtonY = screenHeight - (screenHeight - ((45 * screenHeight) / 180));
	private int settingsButtonY = startButtonY + 150;
	private int exitButtonY = settingsButtonY + 150;
	private int backButtonY = screenHeight - 435;

	// Sets button to rectangle
	private Rectangle pauseButton = new Rectangle(pauseX, pauseY, buttonWidth, buttonHeight);
	private Rectangle resumeButton = new Rectangle(buttonX, startButtonY, buttonWidth, buttonHeight);
	private Rectangle settingsButton = new Rectangle(buttonX, settingsButtonY, buttonWidth, buttonHeight);
	private Rectangle exitButton = new Rectangle(buttonX, exitButtonY, buttonWidth, buttonHeight);
	private Rectangle backButton = new Rectangle(buttonX, backButtonY, buttonWidth, buttonHeight);

	// Get and set functions
	public Rectangle pauseButton() {
		return pauseButton;
	}

	public Rectangle resumeButton() {
		return resumeButton;
	}

	public Rectangle settingsButton() {
		return settingsButton;
	}

	public Rectangle exitButton() {
		return exitButton;
	}

	public Rectangle backButton() {
		return backButton;
	}

	public void setSettingsOpened(boolean is) {
		isSettingsOpened = is;
	}

	public void setBackClicked(boolean is) {
		isBackButtonClicked = is;
	}

	public boolean isMainMenuActive() {
		return isMainMenuActive;
	}

	// Pause menu constructor
	public PauseMenu() {
		try {
			menu = ImageIO.read(new File("mainMenu.png"));
			pause = ImageIO.read(new File("pause.png"));
			pauseHover = ImageIO.read(new File("pauseHover.png"));
			pauseMenu = ImageIO.read(new File("pauseMenu.png"));
			pauseClicked = ImageIO.read(new File("pauseClicked.png"));
			back = ImageIO.read(new File("back.png"));
			backHover = ImageIO.read(new File("backHover.png"));
			exit = ImageIO.read(new File("exit.png"));
			exitHover = ImageIO.read(new File("exitHover.png"));
			play = ImageIO.read(new File("play.png"));
			playHover = ImageIO.read(new File("playHover.png"));
			settings = ImageIO.read(new File("settings.png"));
			settingsHover = ImageIO.read(new File("settingsHover.png"));
			settingsMenu = ImageIO.read(new File("settingsMenu.png"));
			resume = ImageIO.read(new File("resume.png"));
			resumeHover = ImageIO.read(new File("resumeHover.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Draw function
	public void drawGameMenu(Graphics g) {
		// Draws Main menu before game starts
		if (isMainMenuActive) {
			isButtonClicked = false;
			isBackButtonClicked = false;
			if (!isGameStarted) {
				isMainMenuActive = true;
				g.drawImage(menu, 675, 160, null);
				if (!isResumeButtonHovered) {
					g.drawImage(play, buttonX, startButtonY, null);
				} else {
					g.drawImage(playHover, buttonX, startButtonY, null);
				}
				if (!isSettingsButtonHovered) {
					g.drawImage(settings, buttonX, settingsButtonY, null);
				} else {
					g.drawImage(settingsHover, buttonX, settingsButtonY, null);
				}
				if (!isExitButtonHovered) {
					g.drawImage(exit, buttonX, exitButtonY, null);
				} else {
					g.drawImage(exitHover, buttonX, exitButtonY, null);
				}
			}
			// Draws pause menu
			if (isGamePaused) {
				isButtonClicked = false;
				isBackButtonClicked = false;
				isMainMenuActive = true;
				g.drawImage(pauseMenu, 675, 160, null);
				if (!isResumeButtonHovered) {
					g.drawImage(resume, buttonX, startButtonY, null);

				} else {
					g.drawImage(resumeHover, buttonX, startButtonY, null);
				}

				if (!isSettingsButtonHovered) {
					g.drawImage(settings, buttonX, settingsButtonY, null);
				} else {
					g.drawImage(settingsHover, buttonX, settingsButtonY, null);
				}

				if (!isExitButtonHovered) {
					g.drawImage(exit, buttonX, exitButtonY, null);
				} else {
					g.drawImage(exitHover, buttonX, exitButtonY, null);
				}
			}
		}
		// Draws setting menu
		else {
			isButtonClicked = false;
			isBackButtonClicked = false;
			g.drawImage(settingsMenu, 650, 175, null);
			if (!isBackButtonClicked) {
				if (!isBackButtonHovered) {
					g.drawImage(back, buttonX, backButtonY, null);
				} else {
					g.drawImage(backHover, buttonX, backButtonY, null);
				}
			}

		}
		// Draws pause button after it is clicked on
		if (isPauseButtonClicked) {
			isButtonClicked = false;
			g.drawImage(pauseClicked, pauseX, pauseY, null);
		}
		// Draws pause button when it is hovered on
		else if (!isPauseButtonClicked && isGameStarted) {
			isButtonClicked = false;
			if (!isPauseButtonHovered) {
				g.drawImage(pause, pauseX, pauseY, null);

			} else {
				g.drawImage(pauseHover, pauseX, pauseY, null);
			}
		}

	}
}