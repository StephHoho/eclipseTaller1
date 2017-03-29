import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Observable;

public class Comunicacion extends Observable implements Runnable {
	private final int puerto = 5000;
	private DatagramSocket socket;
	private final String ADDRESS = "224.2.2.2";
	private InetAddress ips;
	private int dulces;
	public String mensaje;

	public Comunicacion() {
		dulces = 0;

		try {
			socket = new DatagramSocket(puerto);
			ips = InetAddress.getLocalHost();
			System.out.println("mi ip es: " + ips);
			int uno = ips.toString().lastIndexOf(".");
			int dos = ips.toString().lastIndexOf("/");
			
			String todas = ips.toString().substring(dos + 1, uno + 1);
			System.out.println(todas);

			for (int i = 1; i < 10; i++) {// 255
				String direccionesFinal = todas + i;
				InetAddress direccionesFinales = InetAddress.getByName(direccionesFinal);
				dulces++;
				if (direccionesFinales.isReachable(10)) {
					System.out.println(direccionesFinales + ":reacheable");
					// dulces++;
				} else {
					System.out.println(direccionesFinales + ":not reacheable");
				}
			}
			System.out.println("total de dulces:" + dulces);

		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {

		while (true) {
			try {
				setChanged();
				notifyObservers(recibir());
				clearChanged();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();

			}

		}
	}
	
	public String recibir() {
		try {
			byte[] paquete = new byte[1000];
			DatagramPacket packet = new DatagramPacket(paquete, paquete.length);
			socket.receive(packet);
			mensaje = new String(packet.getData(), 0, packet.getLength());

			return mensaje;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public final int getDulce() {
		return dulces;
	}

	public final void setDulce(int dulces) {
		this.dulces = dulces;
	}
}
