package fr.univnantes.multicore.tp3;

import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.Callable;

public class Task implements Callable<LinkedList<String>>{

	private static LinkedList<String> list;
	private String address;
	private static String explored;
	public Task (String addr) {
		address = addr;
		list = new LinkedList<String>();
	}
	@Override
	public LinkedList<String> call() throws Exception {
		explore(this.address);
		return this.list;
	}
	private static void explore(String address) {
		try {
			/*
			 *  Check that the page was not already explored and adds it
			 * TODO : the check and insertion must be atomic. Explain why. How to do it?
			 */
			if(!list.contains(address)) {
				list.add(address);
				// Parse the page to find matches and hypertext links
				ParsedPage page = Tools.parsePage(address);
				if(!page.matches().isEmpty()) {
					/* 
					 * TODO: Tools.print(page) is not thread safe...
					 */
					//Tools.print(page);
					// Recursively explore other pages
					for(String href : page.hrefs()) {
						explore(href);
					}
				}
			}
			for(String addr : list) {
				explored = explored + addr +"\n";
			}
		} catch (IOException e) {/*We could retry later...*/}
	}

}
