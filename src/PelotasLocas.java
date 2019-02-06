import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class PelotasLocas extends Juego {

	private static final int MINVEL = 30;
	private static final int MAXVEL = 200;
	private static final Random r = new Random();

	private ArrayList<Pelota> pelotas;

	public PelotasLocas(Lienzo lienzo, int numeroPelotas) {
		super(lienzo);
		pelotas = new ArrayList<Pelota>();

		int tamRef = Math.max(lienzo.getWidth(), lienzo.getHeight());
		int minRadio = (int) (tamRef * 0.02);
		int maxRadio = (int) (tamRef * 0.10);

		for (int i = 0; i < numeroPelotas; i++) {
			Color color = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
			int radio = r.nextInt(maxRadio - minRadio + 1) + minRadio;
			int xymin = 10 + radio;
			int xmax = lienzo.getWidth() - 10 - radio;
			int ymax = lienzo.getHeight() - 10 - radio;
			int x = r.nextInt(xmax - xymin + 1) + xymin;
			int y = r.nextInt(ymax - xymin + 1) + xymin;
			double dir = r.nextDouble() * 2 * Math.PI;
			double vel = r.nextInt(MAXVEL - MINVEL + 1) + MINVEL;
			pelotas.add(new Pelota(color, radio, x, y, dir, vel, lienzo.getSize()));
		}
	}

	@Override
	public void siguiente(long ns) {
		synchronized (pelotas) {
			Iterator<Pelota> i = pelotas.iterator();
			while (i.hasNext())
				i.next().mover(ns);
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getLienzo().getWidth(), getLienzo().getHeight());
		synchronized (pelotas) {
			Iterator<Pelota> i = pelotas.iterator();
			while (i.hasNext())
				i.next().paint(g);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		Pelota p = pelotas.get(pelotas.size() - 1);
		int xp = p.getX();
		int yp = p.getY();
		int xr = e.getX();
		int yr = e.getY();
		int d = (int) Point2D.distance(xp, yp, xr, yr);
		if (d < p.getRadio())
			synchronized (pelotas) {
				pelotas.remove(p);
			}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
