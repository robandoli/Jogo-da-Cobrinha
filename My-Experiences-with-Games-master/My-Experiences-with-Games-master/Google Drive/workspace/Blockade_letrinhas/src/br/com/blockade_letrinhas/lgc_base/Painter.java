package br.com.blockade_letrinhas.lgc_base;

import br.com.blockade_letrinhas.lgc_base.ElementoGame;

import javax.swing.*;
import java.awt.*;

public class Painter extends Executor {
    private final JPanel tela;
    private Graphics2D graphics;
    private BackgroundStandart game;
    private int janelaLargura;
    private int janelaAltura;

    public Painter(BackgroundStandart game, Graphics2D graphics, JPanel tela, int janelaLargura, int janelaAltura){
        this.game = game;
        this.graphics = graphics;
        this.tela=tela;
        this.janelaAltura = janelaAltura;
        this.janelaLargura = janelaLargura;
    }

    public void run(){
        graphics.setColor(Color.BLACK);
        
        graphics.fillRect(0, 0, janelaLargura, janelaAltura);
        game.paint(graphics);
        tela.repaint();
    }
}
