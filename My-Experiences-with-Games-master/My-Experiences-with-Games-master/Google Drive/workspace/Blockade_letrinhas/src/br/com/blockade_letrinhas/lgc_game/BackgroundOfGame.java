package br.com.blockade_letrinhas.lgc_game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import br.com.blockade_letrinhas.lgc_base.BackgroundStandart;
import br.com.blockade_letrinhas.lgc_base.ElementoGame;
import br.com.blockade_letrinhas.lgc_base.Util;
import br.com.blockade_letrinhas.lgc_game.BackgroundOfGame.Estado;


public class BackgroundOfGame extends BackgroundStandart{
	
	enum Estado{
		JOGANDO, GANHOU, PERDEU
	}
	
	private static final int LARGURA_ELEMENTO = 25;
	private static final int RASTRO_INICIAL = 5;
	
	private int dir_x, dir_y;

	private boolean moveu;

	private int temporizador = 0;

	private int contadorRastro = RASTRO_INICIAL;

	private ElementoGame fruta;

	private ElementoGame serpente;

	private ElementoGame[] level;

	private ElementoGame[] rastros;
	
	private Random rand = new Random();

	private int dificuldade = 10;

	private int contadorNivel = 0;

	private Estado estado = Estado.JOGANDO;

	public BackgroundOfGame(int largura, int altura) {
		super(largura, altura);
	}

	@Override
	public void load() {
		
		// define a direção do objeto
		dir_y = 1;
		rastros = new ElementoGame[dificuldade + RASTRO_INICIAL];
		
		fruta = new ElementoGame(0, 0, LARGURA_ELEMENTO, LARGURA_ELEMENTO);
		fruta.setCor(Color.PINK);
		
		serpente = new ElementoGame(0, 0, LARGURA_ELEMENTO, LARGURA_ELEMENTO);
		serpente.setAtivo(true);
		serpente.setCor( Color.YELLOW);
		serpente.setVel( 4 );
		
		for (int i = 0; i < rastros.length; i++) {
			rastros[ i ] = new ElementoGame(serpente.getPos_x(), serpente.getPos_y(), LARGURA_ELEMENTO, LARGURA_ELEMENTO);
			rastros[ i ].setCor(Color.GREEN);
			rastros[ i ].setAtivo(true);
		}
		
		char[][] levelSelecionado = Level.levels[2];
		level = new ElementoGame[ levelSelecionado.length * 5];
		
		for (int linha = 0; linha < levelSelecionado.length; linha++) {
			for (int coluna = 1; coluna < levelSelecionado[0].length; coluna++) {
				
				if(levelSelecionado[linha][coluna] != ' '){
					
					ElementoGame e = new ElementoGame();
					e.setAtivo(true);
					e.setCor(Color.LIGHT_GRAY);
					
					e.setPos_x(coluna * LARGURA_ELEMENTO);
					e.setPos_y(linha * LARGURA_ELEMENTO);
					
					e.setAltura(LARGURA_ELEMENTO);
					e.setLargura(LARGURA_ELEMENTO);
					
					level[ contadorNivel++ ] = e;
					
				}
			}
		}
	}

	@Override
	public void unLoad() {
		fruta = null;
		rastros = null;
		serpente = null;
	}

	@Override
	public synchronized void update() {
		
		if ( estado != Estado.JOGANDO){
			return;
		}
		
		if(!moveu){
			
			if(dir_y != 0){
				
				if (Game.controleTecla[Game.Tecla.ESQUERDA.ordinal()]) {
					dir_x = -1;

				} else if (Game.controleTecla[Game.Tecla.DIREITA.ordinal()]){
					dir_x = 1;
				}
				
				if (dir_x != 0){
					dir_y = 0;
					moveu = true;
				}
				
			} else if (dir_x !=0){
				if (Game.controleTecla[Game.Tecla.CIMA.ordinal()]){
					dir_y = -1;
				
				} else if (Game.controleTecla[Game.Tecla.BAIXO.ordinal()]){
					dir_y = 1;

				}
				
				if (dir_y != 0){
					dir_x = 0;
					moveu = true;
				}
			}
		}
		
		if (temporizador >= 20) {
			temporizador = 0;
			moveu = false;
			
			// variavel que armazena a posição da fila
			int x = serpente.getPos_x();
			int y = serpente.getPos_y();
			
			serpente.setPos_x( serpente.getPos_x() + LARGURA_ELEMENTO * dir_x );
			serpente.setPos_y( serpente.getPos_y() + LARGURA_ELEMENTO * dir_y );
			
			if( Util.saiu(serpente, largura, altura)){
				serpente.setAtivo(false);
				estado = Estado.PERDEU;
				
			} else {
				// colisão com o cenario
				for (int i = 0; i < contadorNivel; i++) {
					if( Util.colide(serpente, level[ i ])){
						serpente.setAtivo(false);
						estado = Estado.PERDEU;
						break;
					}
				}
				
				// colisão com o rastro
				for (int i = 0; i < contadorRastro; i++) {
					if(Util.colide(serpente, rastros[ i ])){
						serpente.setAtivo(false);
						estado = Estado.PERDEU;
						break;
					}
				}
			}
			
			if (Util.colide(fruta, serpente)) {
				// adiciona uma pausa
				temporizador = -10;
				contadorRastro++;
				fruta.setAtivo(false);
				
				if (contadorRastro == rastros.length) {
					serpente.setAtivo(false);
					estado = Estado.GANHOU;
				}
			}
			
			for (int i = 0; i < contadorRastro; i++) {
				ElementoGame rastro = rastros[ i ];
				int tx = rastro.getPos_x();
				int ty = rastro.getPos_y();
				
				// o rastro utiliza a posição da serpente
				rastro.setPos_x(x);
				rastro.setPos_y(y);
				
				x = tx;
				y = ty;				
			}
		} else {
			temporizador += serpente.getVel();
		}
		
		//adiciona fruta
		if (estado == Estado.JOGANDO && !fruta.isAtivo()) {
			int x = rand.nextInt( largura / LARGURA_ELEMENTO);
			int y = rand.nextInt( altura / LARGURA_ELEMENTO);
			
			fruta.setPos_x( x * LARGURA_ELEMENTO );
			fruta.setPos_y( y * LARGURA_ELEMENTO );
			fruta.setAtivo(true);
			
			// colisao com a serpente
			if (Util.colide(fruta, serpente)) {
				fruta.setAtivo(false);
				return;
			}
			
			// colisao com o rastro
			for (int i = 0; i < contadorRastro; i++) {
				if(Util.colide(fruta, rastros[ i ])){
					fruta.setAtivo(false);
					return;
				}
			}
			
			// colisao com o cenario
			for (int i = 0; i < contadorNivel; i++) {
				if(Util.colide(fruta, level[ i ])){
					fruta.setAtivo(false);
					return;
				}
			}
		}
		
	}

	@Override
	public synchronized void  paint(Graphics2D g) {
		
		if(fruta.isAtivo()){
			fruta.desenha(g);
		}
		
		for(ElementoGame e : level){
			if( e == null )
				break;
			
			e.desenha(g);
		}
		
		for (int i = 0; i < contadorRastro; i++) {
			rastros[ i ].desenha(g);
		}
		
		serpente.desenha(g);
		
	}
}
