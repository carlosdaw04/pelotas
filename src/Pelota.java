import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

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
	private AlphaComposite composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);
	
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
	
	public void mover(long lapso) {
		double dx = lapso * vx / 1000000000d;
		double dy = lapso * vy / 1000000000d;
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
	
	public boolean desaparecer(long lapso) {
		double alpha = composite.getAlpha();
		if (alpha > 0) {
			alpha -= (double) lapso / 2000000000d;
			if (alpha >= 0)
				composite = composite.derive((float) alpha);
		}
		return alpha <= 0;
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
	
	public void paintDesaparecer(Graphics g) {
		Composite aux = ((Graphics2D) g).getComposite();
		((Graphics2D) g).setComposite(composite);
		paint(g);
		((Graphics2D) g).setComposite(aux);
	}
	
}
