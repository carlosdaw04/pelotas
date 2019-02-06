import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Pelota {

	private double vx;
	private double vy;
	private double x;
	private double y;
	private int radio;
	private int diametro;
	private Dimension dim;
	private Color color;
	private int xmax;
	private int ymax;
	private int rx;
	private int ry;
	
	public Pelota(Color color, int radio, int x, int y, double d, double v, Dimension dim) {
		this.color = color;
		this.radio = radio;
		this.x = x - radio;
		this.y = y - radio;
		vx = v * Math.cos(d);
		vy = v * Math.sin(d);
		diametro = 2 * radio;
		this.dim = dim;
		xmax = dim.width - diametro - 1;
		ymax = dim.height - diametro - 1;
		rx = dim.width + dim.width - diametro - diametro;
		ry = dim.height + dim.height - diametro - diametro;
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
		else if (x > xmax) {
			x = rx - x;
			vx *= -1;
		}
		if (y < 0) {
			y = Math.abs(dy) - y;
			vy *= -1;
		}
		else if (y > ymax) {
			y = ry - y;
			vy *= -1;
		}
	}
	
	public int getX() {
		return (int) x + radio;
	}
	
	public int getY() {
		return (int) y + radio;
	}
	
	public int getRadio() {
		return radio;
	}
	
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval((int) x, (int) y, diametro, diametro);
		g.setColor(Color.BLACK);
		g.drawOval((int) x, (int) y, diametro, diametro);
	}
	
}
