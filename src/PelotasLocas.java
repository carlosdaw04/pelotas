import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class PelotasLocas extends Juego {

	private static final int MINVEL = 30;
	private static final int MAXVEL = 200;
	private static final Random r = new Random();
	
	private Pelota [] pelotas;
	
	public PelotasLocas(Lienzo lienzo, int numeroPelotas) {
		super(lienzo);
		pelotas = new Pelota[numeroPelotas];
		
		int tamRef = Math.max(lienzo.getWidth(), lienzo.getHeight());
		int minRadio = (int) (tamRef * 0.02);
		int maxRadio = (int) (tamRef * 0.10);
		
		for (int i=0; i<pelotas.length; i++) {
			Color color = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
			int radio = r.nextInt(maxRadio - minRadio + 1) + minRadio;
			int xymin = 10 + radio;
			int xmax = lienzo.getWidth() - 10 - radio;
			int ymax = lienzo.getHeight() - 10 - radio;
			int x = r.nextInt(xmax - xymin + 1) + xymin;
			int y = r.nextInt(ymax - xymin + 1) + xymin;
			double dir = r.nextDouble() * 2 * Math.PI;
			double vel = r.nextInt(MAXVEL - MINVEL + 1) + MINVEL;
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
