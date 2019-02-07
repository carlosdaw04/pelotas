import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class PelotasLocas extends Juego {

	private static final int MINVEL = 30;
	private static final int MAXVEL = 200;
	private static final Random r = new Random();

	private int tamRef;
	private int minRadio;
	private int maxRadio;
	
	private LinkedList<Pelota> pelotas;
	private ArrayList<Pelota> eliminadas;

	public PelotasLocas(Lienzo lienzo, int numeroPelotas) {
		super(lienzo);
		pelotas = new LinkedList<Pelota>();
		eliminadas = new ArrayList<Pelota>();

		tamRef = Math.max(lienzo.getWidth(), lienzo.getHeight());
		minRadio = (int) (tamRef * 0.02);
		maxRadio = (int) (tamRef * 0.10);

		for (int i = 0; i < numeroPelotas; i++) {
			pelotas.add(nuevaPelota());
		}
	}

	private Pelota nuevaPelota() {
		Color color = new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
		int radio = r.nextInt(maxRadio - minRadio + 1) + minRadio;
		int xymin = 10 + radio;
		int xmax = getLienzo().getWidth() - 10 - radio;
		int ymax = getLienzo().getHeight() - 10 - radio;
		int x = r.nextInt(xmax - xymin + 1) + xymin;
		int y = r.nextInt(ymax - xymin + 1) + xymin;
		double dir = r.nextDouble() * 2 * Math.PI;
		double vel = r.nextInt(MAXVEL - MINVEL + 1) + MINVEL;
		return new Pelota(color, radio, x, y, dir, vel, getLienzo().getSize());
	}
	
	@Override
	public void siguiente(long ns) {
		synchronized (pelotas) {
			Iterator<Pelota> i = pelotas.iterator();
			while (i.hasNext())
				i.next().mover(ns);
		}
		synchronized (eliminadas) {
			Iterator<Pelota> i = eliminadas.iterator();
			while (i.hasNext()) {
				Pelota p = i.next();
				if (p.desaparecer(ns))
					i.remove();
			}
		}
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getLienzo().getWidth(), getLienzo().getHeight());
		synchronized (pelotas) {
			Iterator<Pelota> i = pelotas.iterator();
			while (i.hasNext()) {
				i.next().paint(g);
			}
			g.setColor(Color.BLACK);
			g.drawString("Número de pelotas: " + pelotas.size(), 50, 50);
		}
		synchronized (eliminadas) {
			Iterator<Pelota> i = eliminadas.iterator();
			while (i.hasNext()) 
				i.next().paintDesaparecer(g);
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
		synchronized (pelotas) {
			if (d < p.getRadio()) {
				pelotas.remove(p);
				synchronized (eliminadas) {
					eliminadas.add(p);
				}
			}
			else
				pelotas.add(0, nuevaPelota());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
