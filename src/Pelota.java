import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Pelota {

	private double vx;
	private double vy;
	private double x;
	private double y;
	private int diametro;
	private Dimension dim;
	
	public Pelota(int radio, double x, double y, double d, double v, Dimension dim) {
		this.x = x - radio;
		this.y = y - radio;
		vx = v * Math.cos(d);
		vy = v * Math.sin(d);
		diametro = 2 * radio;
		this.dim = dim;
	}
	
	public void mover(long t) {
		double dx = t * vx / 1000000000d;
		double dy = t * vy / 1000000000d;
		x += dx;
		y += dy;
		if (x <= 0 || x + diametro >= dim.width)
			vx *= -1;
		else if (y <= 0 || y + diametro >= dim.height)
			vy *= -1;
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawOval((int) x, (int) y, diametro, diametro);
	}
	
}
