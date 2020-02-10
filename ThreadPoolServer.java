package fr.univnantes.multicore.distanciel;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class ThreadPoolServer implements Server {
	ExecutorService threadpool;
	
	public ThreadPoolServer() {
		this.threadpool= Executors.newFixedThreadPool(20);
	}
	@Override
	public Future<Block> getBlock(Task task) {
		
				FutureTask<Block> future = new FutureTask<Block>(task);
				// Execute the future locally
				threadpool.submit(future);
				// Return the future
				return future;
	}

}