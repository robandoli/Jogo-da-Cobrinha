package br.com.blockade_letrinhas.lgc_base;

import java.awt.Graphics2D;

public abstract class BackgroundStandart {
	
	protected int altura, largura;
	
	public BackgroundStandart( int largura, int altura ){
		this.altura = altura;
		this.largura = largura;
	}
	
	public abstract void load();
	
	public abstract void unLoad();
	
	public abstract void update();
	
	public abstract void paint(Graphics2D g);

}
