package fr.univnantes.multicore.tp3;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import fr.univnantes.multicore.distanciel.Block;
/**
 * @author Jenkov
 * utilisation et adaptation du BlockingQueue de Jenkov trouvable ici
 * tutorials.jenkov.com/java-concurrency/blocking-queues.html
 */
public class BlockingQueue {

	private final static LinkedList<FutureTask<String>> explored = new LinkedList<FutureTask<String>>();

	  public BlockingQueue(){}


	  public synchronized void enqueue(ThreadPool tp, FutureTask<String> address)  throws InterruptedException, IOException, ExecutionException  {
		  if(!explored.contains(address)) {
				explored.add(address);
				// Parse the page to find matches and hypertext links
				ParsedPage page = Tools.parsePage(address.get());
				if(!page.matches().isEmpty()) {
					/* 
					 * TODO: Tools.print(page) is not thread safe...
					 */
					Tools.print(page);
					// Recursively explore other pages
					for(String href : page.hrefs()) { 
						tp.enqueueAddress(tp, href);
					}
				}
		  }
	  }


	  public synchronized FutureTask<String> dequeue()
	  throws InterruptedException{
	   /* while(this.explored.size() == 0){
	      wait();
	    }
	    notifyAll();*/

	    return (FutureTask<String>)this.explored.remove(0);
	  }

}