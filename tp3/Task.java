package fr.univnantes.multicore.tp3;

import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentSkipListSet;

public class Task implements Callable<ConcurrentSkipListSet<String>>{

	   	private static ConcurrentSkipListSet<String> list; // ConcurrenteSkipListSet est thread safe
	    private volatile String address;
	    private static volatile ParsedPage page;
	public Task (String addr) {
		address = addr;
		list = new ConcurrentSkipListSet<String>();
	}
	@Override
	public ConcurrentSkipListSet<String> call() throws Exception {
		explore(this.address);
		return this.list;
	}
	private static void explore(String address) {
        try {
            /*
             *  Check that the page was not already explored and adds it
             * TODO : the check and insertion must be atomic. Explain why. How to do it?
             */
            int tailleList = list.size();
            list.add(address);

            if(tailleList < list.size()) {
                // Parse the page to find matches and hypertext links
                page = Tools.parsePage(address);
                if(!page.matches().isEmpty()) {
                    Tools.print(page);
                    // Recursively explore other pages
                    for(String href : page.hrefs()) {
                        explore(href);
                    }
                }
            }
            
           
        } catch (IOException e) {/*We could retry later...*/
        	}
    }
}
