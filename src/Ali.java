import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Ali {
	private int posX, posY; 
	private int vel;
	private PImage ali;

	public Ali(PApplet app, int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		vel = 50;
		System.out.println("vel: "+vel);
		ali = app.loadImage("data/alitza.png");
	}

	public void pintar(PApplet app) {
		app.imageMode(PConstants.CENTER);
		app.image(ali, posX, posY);
	}

	public void mover() {
		posX = posX + vel;
	}

	public int getX() {
		return posX;
	}

	public void setX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public int getY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setY(int posY) {
		this.posY = posY;
	}
}
