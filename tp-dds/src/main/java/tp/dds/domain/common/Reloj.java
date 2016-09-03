package tp.dds.domain.common;

import java.util.Timer;
import java.util.TimerTask;

public class Reloj {

	private Reloj instance = null;

	public Reloj getInstance() {
		if (instance == null) {
			return new Reloj();
		}
		return instance;
	}

	Timer timer = new Timer();
	public int segundos;
	public boolean frozen;

	class Contador extends TimerTask {
		public void run() {
			segundos++;
			System.out.println("segundo: " + segundos);
		}
	}

	public void Contar() {
		this.segundos = 0;
		timer = new Timer();
		timer.schedule(new Contador(), 0, 1000);
	}

	public void Detener() {
		timer.cancel();
	}

	public int getSegundos() {
		return this.segundos;
	}

}
