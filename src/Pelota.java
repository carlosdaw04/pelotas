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
	private Color color;
	private int xmax;
	private int ymax;
	
	public Pelota(Color color, int radio, int x, int y, double d, double v, Dimension dim) {
		this.color = color;
		this.x = x - radio;
		this.y = y - radio;
		vx = v * Math.cos(d);
		vy = v * Math.sin(d);
		diametro = 2 * radio;
		this.dim = dim;
		xmax = dim.width - 1;
		ymax = dim.height - 1;
	}
	
	public void mover(long t) {
		double dx = t * vx / 1000000000d;
		double dy = t * vy / 1000000000d;
		x += dx;
		y += dy;
		if (x < 0) {
			x = Math.abs(dx) - x;
			vx *= -1;
		}
		else if (x + diametro > dim.width) {
			x = x - (x + diametro - xmax);
			vx *= -1;
		}
		if (y < 0) {
			y = Math.abs(dy) - y;
			vy *= -1;
		}
		else if (y + diametro > dim.height) {
			y = y - (y + diametro - ymax);
			vy *= -1;
		}
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval((int) x, (int) y, diametro, diametro);
		g.setColor(Color.BLACK);
		g.drawOval((int) x, (int) y, diametro, diametro);
	}
	
}
