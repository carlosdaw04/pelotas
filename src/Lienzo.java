import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Lienzo extends JPanel {

	private Dimension d;
	private Thread t;
	private Pelota pelota;
	
	public Lienzo(int w, int h) {
		d = new Dimension(w, h);
		pelota = new Pelota(50, 100, 100, (float) Math.PI / 4, 100, d);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return d;
	}
	
	public void iniciarAnimacion() {
		t = new Thread(new Runnable() {
			@Override
			public void run() {
				long t0 = System.nanoTime(), t1, t;
				while(true) {
					t1 = System.nanoTime();
					t = t1 - t0;
					t0 = t1;
					pelota.mover(t);
					repaint();
				}
			}
		});
		t.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
//		g.fillRect(0, 0, getWidth(), getHeight());
		g.fillRect(0, 0, d.width, d.height);
		pelota.paint(g);
		g.setColor(Color.BLACK);
//		g.drawString("tiempo: " + t , 10, 10);
	}
}
