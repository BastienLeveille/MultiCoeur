//Gaël Lodé Bastien Leveillé 

package fr.univnantes.multicore.tp2.unisex;

import java.util.LinkedList;
import java.util.Queue;

public class ParallelBathRoom implements Bathroom {
	private int maleplaces = 3;
	private int femaleplaces = 3;
	public ParallelBathRoom(int nbbath) {}
	@Override
	public void enter(Person person) throws InterruptedException {
		synchronized(this) { //même chose que public synchronized void p() 
			if (person.isMale()) {
				while(maleplaces<=0 || femaleplaces< 3) {wait();} 
				maleplaces--;
			}else{
				while(femaleplaces<=0 || maleplaces< 3) {wait();} 
				femaleplaces--;
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
