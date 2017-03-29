import java.net.InetAddress;
import java.net.UnknownHostException;

import processing.core.PApplet;

public class Main extends PApplet {
	PApplet app;
	Logica log;

	@Override
	public void settings() {
		// TODO Auto-generated method stub
		size(900, 600);
	}

	@Override
	public void setup() {
		background(255);
		app = this;
		log = new Logica(this);
	}

	@Override
	public void draw() {
		log.pintar();

	}

	public void keyPressed() {
		log.keyPressed();
	}

	public void mouseClicked() {
		log.mouse(mouseX, mouseY);
	}

	public static void main(String[] args) {
		PApplet.main("Main");
	}
}
