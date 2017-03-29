import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

// TODO Auto-generated method stub
public class Logica implements Observer {
	private PApplet app;
	
	private Comunicacion com;
	private int posX;
	private int posY;
	private int vel;
	private ArrayList<Dulce> dulces;
	private PImage vida;
	private int numDulce;
	private long tiempo = 200;
	private int puntaje;
	private int pantalla;
	private PImage inicio, instrucciones, juego, boton, control;
	private PImage ali;
	private Ali aliz;

	public Logica(PApplet app) {
		this.app = app;
		this.aliz= new Ali(app, app.width/2, app.height-165);
		this.com = new Comunicacion();
		Thread thread = new Thread(com);
		thread.start();
		com.addObserver(this);
		dulces = new ArrayList<Dulce>();
		numDulce = com.getDulce();
		init();
		inicio = app.loadImage("data/inicio.jpg");
		juego = app.loadImage("data/juego.jpg");
		instrucciones = app.loadImage("data/instrucciones.jpg");
		ali = app.loadImage("data/alitza.png");
		puntaje = 0;
	}

	public void pintar() {
		switch (pantalla) {
		case 0:
			app.image(inicio, 0, 0);
			break;
		case 1:
			app.image(instrucciones, 0, 0);
			break;
		case 2:
			app.imageMode(PConstants.CORNER);
			app.image(juego, 0, 0);
			
			aliz.pintar(app);
			
			app.fill(255);
			app.textSize(20);
			app.text(tiempo + "", 779, 40);
			app.text(puntaje + "", 800, 160);
			if (app.frameCount % 60 == 0) {// cuanta regresiva
				tiempo--;
			}

			for (int i = 0; i < dulces.size(); i++) {
				dulces.get(i).pintar(app);
				dulces.get(i).mover();
				if(app.dist(dulces.get(i).getPosX(), dulces.get(i).getPosY(), aliz.getX(), aliz.getY())<50){
					//System.out.println("se come el dulce");
					dulces.remove(i);
					puntaje += 20;
				}
			}
			break;
		}

	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("esto lo recibi: " + arg);
		if(arg instanceof String){
			if (((String) arg).equals("izq")) {
				aliz.setX(aliz.getX() - 50);
			}
			if (((String) arg).equals("der")) {
				aliz.setX(aliz.getX() + 50);
			}
			if (((String) arg).equals("ini")) {
				pantalla = 2;
			}
			if (((String) arg).equals("instrucciones")) {
				pantalla = 1;
			}
		}
	}

	public void init() {
		for (int i = 0; i < numDulce; i++) {
			int x = (int) app.random(20, app.width);
			System.out.println(x);
			int y = -20;
			dulces.add(new Dulce(app, x, y));
		}
	}

	public void keyPressed() {
		// TODO Auto-generated method stub
		if (app.keyCode == app.LEFT) {
			aliz.setX(aliz.getX() - 50);
		}
		if (app.keyCode == app.RIGHT) {
			aliz.setX(aliz.getX() + 50);
		}
	}

	public void mouse(int mouseX, int mouseY) {
		System.out.println(app.mouseX + " " + app.mouseY);
		// JUGAR
		if (app.mouseX > 105 && app.mouseX < 285 && app.mouseY > 442 && app.mouseY < 483 && pantalla == 0) {
			pantalla = 2;
		}
		// INSTRUCCIONES
		if (app.mouseX > 365 && app.mouseX < 545 && app.mouseY > 442 && app.mouseY < 480 && pantalla == 0) {
			pantalla = 1;
		}
		// DE INSTRUCCIONES A JUGAR
		if (app.mouseX > 769 && app.mouseX < 937 && app.mouseY > 612 && app.mouseY < 649 && pantalla == 1) {
			pantalla = 2;
		}
	}

}
