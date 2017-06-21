package br.com.blockade_letrinhas.lgc_base;

import br.com.blockade_letrinhas.lgc_base.ElementoGame;

public class Updater extends Executor {
    private BackgroundStandart game;

    public Updater(BackgroundStandart game){
        this.game = game;
    }

    public void run() {
        this.game.update();
    }
}
