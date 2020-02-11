package fr.univnantes.multicore.distanciel;


import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class TPJenkovServer implements Server {
	ExecutorService threadpool;
	BlockingQueue taskQueue = new LinkedBlockingDeque<Task>();
	public TPJenkovServer() {
		this.threadpool= Executors.newFixedThreadPool(10);
	}
	
	@Override
	public Future<Block> getBlock(Task task) {
		FutureTask<Block> future = new FutureTask<Block>(task);
		if(task.hasPriority()) {
			try {
				((LinkedBlockingDeque<Task>) taskQueue).putFirst(task);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				((LinkedBlockingDeque<Task>) taskQueue).putLast(task);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		while(taskQueue.peek() != task) {}
		taskQueue.remove(task);
		threadpool.submit(future);
		return future;
	}

}
