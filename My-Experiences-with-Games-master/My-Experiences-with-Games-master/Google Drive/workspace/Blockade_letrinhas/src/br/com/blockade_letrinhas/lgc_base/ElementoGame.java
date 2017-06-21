package br.com.blockade_letrinhas.lgc_base;

import java.awt.Color;
import java.awt.Graphics2D;

public class ElementoGame {
	private int pos_x;
	private int pos_y;
	private int largura;
	private int altura;
	
	private int vel;
	private boolean ativo;
	private Color cor;
	
	public ElementoGame(){
	}
	
	public ElementoGame( int pos_x,  int pos_y, int largura, int altura){
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.largura = largura;
		this.altura = altura;
	}
	
	public void atualiza(){
	}
	
	public void desenha(Graphics2D g){
		g.setColor(cor);
		g.fillRect(pos_x, pos_y, largura, altura);
	}
	
	public int getPos_x() {
		return pos_x;
	}

	public void setPos_x(int pos_x) {
		this.pos_x = pos_x;
	}

	public int getPos_y() {
		return pos_y;
	}

	public void setPos_y(int pos_y) {
		this.pos_y = pos_y;
	}

	public int getLargura() {
		return largura;
	}

	public void setLargura(int largura) {
		this.largura = largura;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getVel() {
		return vel;
	}

	public void setVel(int vel) {
		this.vel = vel;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

}
