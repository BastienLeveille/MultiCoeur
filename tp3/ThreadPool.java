package fr.univnantes.multicore.tp3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
/**
 * @author Jenkov
 * utilisation et adaptation de la classe ThreadPool de Jenkov trouvable ici
 * http://tutorials.jenkov.com/java-concurrency/thread-pools.html
 */
public class ThreadPool {
	 private BlockingQueue taskQueue = null;
	    private List<PoolThread> threads = new ArrayList<PoolThread>();
	    private boolean isStopped = false;

	    public ThreadPool(int nbThread){
	        taskQueue = new BlockingQueue();

	        for(int i=0; i<nbThread; i++){
	            threads.add(new PoolThread(taskQueue));
	        }
	        for(PoolThread thread : threads){
	            thread.start();
	        }
	    }

	    public synchronized void  execute(ThreadPool tp, FutureTask<String> address) throws Exception{
	        if(this.isStopped) throw new IllegalStateException("ThreadPool is stopped");
	        this.taskQueue.enqueue(this, address);
	    }

	    public synchronized void stop(){
	        this.isStopped = true;
	        for(PoolThread thread : threads){
	           thread.doStop();
	        }
	    }
	    public void enqueueAddress(ThreadPool tp, String address) {
			FutureTask<String> future= new FutureTask<String>((Runnable) this, address);
			try {
				this.execute(this, future);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
}
