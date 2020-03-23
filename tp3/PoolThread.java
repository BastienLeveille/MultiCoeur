package fr.univnantes.multicore.tp3;

import java.util.concurrent.Callable;

/**
 * @author Jenkov
 * utilisation et adaptation de la classe PoolThread de Jenkov trouvable ici
 * http://tutorials.jenkov.com/java-concurrency/thread-pools.html
 */
public class PoolThread extends Thread{
	private BlockingQueue taskQueue = null;
    private boolean       isStopped = false;

    public PoolThread(BlockingQueue queue){
        taskQueue = queue;
    }

    public void run(){
        while(!isStopped()){
            try{
                Runnable runnable = (Runnable) taskQueue.dequeue();
                runnable.run();
            } catch(Exception e){
                //log or otherwise report exception,
                //but keep pool thread alive.
            }
        }
    }

    public synchronized void doStop(){
        isStopped = true;
        this.interrupt(); //break pool thread out of dequeue() call.
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}

