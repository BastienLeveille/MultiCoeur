package projet;

public class Main {

	public static void main(String[] args) {
		Register<Integer> cpt = new MyRegister(0);
		Transaction transaction = new TL2();
		//transaction.addRegister(cpt);
		try {
			transaction.begin();
			int x = cpt.read(transaction);
			int xx = x+ 1;
			cpt.write(transaction, xx);;
			transaction.try_to_commit();
		} catch (AbortException e) {
			e.printStackTrace();
		}

	}

}
