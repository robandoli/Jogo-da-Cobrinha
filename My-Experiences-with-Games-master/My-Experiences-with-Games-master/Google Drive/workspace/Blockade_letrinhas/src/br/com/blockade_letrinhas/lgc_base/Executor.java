package br.com.blockade_letrinhas.lgc_base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class Executor implements Runnable {
    public void runAtRate(long interval){
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this,0,interval, TimeUnit.MILLISECONDS);
    }
}
