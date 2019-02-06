import java.awt.Graphics;
import java.awt.event.MouseListener;

public abstract class Juego implements MouseListener{

	private Lienzo lienzo;
	
	public Juego(Lienzo lienzo) {
		this.lienzo = lienzo;
	}

	public Lienzo getLienzo() {
		return lienzo;
	}
	
	public abstract void siguiente(long ns);
	
	public abstract void render(Graphics g);
	
}
