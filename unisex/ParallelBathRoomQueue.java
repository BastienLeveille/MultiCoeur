//Gaël Lodé Bastien Leveillé 
package fr.univnantes.multicore.tp2.unisex;

import java.util.LinkedList;
import java.util.Queue;

public class ParallelBathRoomQueue implements Bathroom{
	private int maleplaces = 3;
	private int femaleplaces = 3;
	Queue q = new LinkedList<Person>();
	public ParallelBathRoomQueue(int nbbath) {}
	@Override
	public void enter(Person person) throws InterruptedException {
		synchronized(this) { //même chose que public synchronized void p() 
			q.add(person);
			if (person.isMale()) {
				while(maleplaces<=0 || femaleplaces< 3 || q.peek() != person) {wait();} 
				maleplaces--;
				q.remove();
			}else{
				while(femaleplaces<=0 || maleplaces< 3 || q.peek() != person) {wait();} 
				femaleplaces--;
				q.remove();
			}
		}
	}

	@Override
	public void leave(Person person) {
		synchronized(this) {
			if (person.isMale()) {
				maleplaces++;
			}else {
				femaleplaces++;
			}
			notifyAll();//on ne sait pas quelle est la prochaine personne à entrer
		}
	}
}
