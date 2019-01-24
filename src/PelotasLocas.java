import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class PelotasLocas extends Juego {

	private static final int MINRADIO = 50;
	private static final int MAXRADIO = 200;
	private static final int MINVEL = 30;
	private static final int MAXVEL = 200;
	private static final Random r = new Random();
	
	private Pelota [] pelotas;
	
	public PelotasLocas(Lienzo lienzo, int numeroPelotas) {
		super(lienzo);
		pelotas = new Pelota[numeroPelotas];
		for (int i=0; i<pelotas.length; i++) {
			Color color;
			int radio;
			double x;
			double y;
			double dir;
			double vel;
			pelotas[i] = new Pelota(color, radio, x, y, dir, vel, lienzo.getSize());
		}
	}

	@Override
	public void siguiente(long ns) {
		for (int i=0; i<pelotas.length; i++)
			pelotas[i].mover(ns);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getLienzo().getWidth(), getLienzo().getHeight());
		for (int i=0; i<pelotas.length; i++)
			pelotas[i].paint(g);
	}

}
