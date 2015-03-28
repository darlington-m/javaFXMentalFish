/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Rectangle;

/**
 * Creates an instance of Player in the game. Utilises the Singleton Design Pattern,
 * meaning only one instance can be initialised at any given time.
 * 
 * @author Darlington Moyo
 */
public class Player {

	private static Player instance = null;
	private AudioClip sound;
	Image image;
	Rectangle r;
	double x, y, dx = 8, dy = 8;
	private String move;
	private MoveCommandIf moveR = new MoveRight();
	private MoveCommandIf moveL = new MoveLeft();
	private MoveCommandIf moveD = new MoveDown();
	private MoveCommandIf moveU = new MoveUp();
	int imageMoveNum;

	/**
	 * @param x
	 * @param y
	 */
	private Player(int x, int y) {

		sound = new AudioClip(Player.class.getResource(
				"resources/change-direction.wav").toExternalForm());
		image = new Image(AssignmentTemplate.class.getResource(
				"resources/head-down1.gif").toExternalForm());
		r = new Rectangle(x, y, image.getWidth(), image.getHeight());
	}

	/**
	 * Checks instance of Player is not null before before initialising one. If instance is not null,
	 * a null object is returned. 
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static Player getInstance(int x, int y) {
		if (instance == null) {
			instance = new Player(x, y);
		}
		return instance;
	}

	/**
	 * Depending on the value of "move", call respective command to change direction
	 * 
	 */
	public void move() {

		if (move == "right") {
			moveR.execute(this);
		} else if (move == "left") {
			moveL.execute(this);
		} else if (move == "down") {
			moveD.execute(this);
		} else if (move == "up") {
			moveU.execute(this);
		}
	}

	/**
	 * @return
	 */
	public AudioClip getSound() {
		return sound;
	}

	/**
	 * @param sound
	 */
	public void setSound(AudioClip sound) {
		this.sound = sound;
	}

	/**
	 * @return
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * @param image
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * @return
	 */
	public Rectangle getR() {
		return r;
	}

	/**
	 * @param r
	 */
	public void setR(Rectangle r) {
		this.r = r;
	}

	public double getX() {
		return x;
	}

	/**
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @return
	 */
	public double getDx() {
		return dx;
	}

	/**
	 * @param dx
	 */
	public void setDx(double dx) {
		this.dx = dx;
	}

	/**
	 * @return
	 */
	public double getDy() {
		return dy;
	}

	/**
	 * @param dy
	 */
	public void setDy(double dy) {
		this.dy = dy;
	}

	/**
	 * @return
	 */
	public String getMove() {
		return move;
	}

	/**
	 * @param move
	 */
	public void setMove(String move) {
		this.move = move;
	}

	/**
	 * @return
	 */
	public int getImageMoveNum() {
		return imageMoveNum;
	}

	/**
	 * @param imageMoveNum
	 */
	public void setImageMoveNum(int imageMoveNum) {
		this.imageMoveNum = imageMoveNum;
	}
}
