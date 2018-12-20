import java.awt.Color;
import java.awt.Graphics;

public class Pelota {

	private float vx;
	private float vy;
	private float x;
	private float y;
	private int diametro;
	
	public Pelota(int radio, float x, float y, float d, float v) {
		this.x = x - radio;
		this.y = y - radio;
		vx = v * (float) Math.cos(d);
		vy = v * (float) Math.sin(d);
		diametro = 2 * radio;
	}
	
	public void mover(long t) {
		float dx = (float) t * vx / 1000000000f;
		float dy = (float) t * vy / 1000000000f;
		x += dx;
		y += dy; 
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawOval((int) x, (int) y, diametro, diametro);
	}
	
}
