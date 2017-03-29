import processing.core.PApplet;
import processing.core.PImage;

public class Dulce {
	private int posX, posY;
	private int vel;
	private PImage dulce, dulce1_1, dulce1_2, dulce1_3, dulce2_1, dulce2_2, dulce2_3, dulce3_1, dulce3_2, dulce3_3, dulce4_1, dulce4_2, dulce4_3;
	/*private PImage[] dulcesBajo = ;
	private PImage [] dulcesMedios= ;
	private PImage [] dulcesAltos= ;*/
	
	
	public Dulce(PApplet app, int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		vel = (int) app.random(1,2);
		dulce = app.loadImage("data/dulce1.png");
	}

	public void pintar(PApplet app) {
		// app.fill(0);
		app.image(dulce, posX, posY);
	}

	public void mover() {
		posY = posY + vel;
	}

	/**
	 * @return the posX
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public int getPosY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}
}
