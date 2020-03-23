package projet;


public class CTransaction<T> implements Transaction {

	Register<T> X, CX;
	Integer birthDate;
	@Override
	public void begin() {
		// TODO Auto-generated method stub

		CX = X;
		birthDate = clock;

	}

	@Override
	public void try_to_commit() throws AbortException {
		// TODO Auto-generated method stub
		//lock
		if(lrs.isLock()) {
			//libere les locks
			throw new AbortException();
		}

		for(x in lrs) {
			if (X.date > birthDate) {
				// libere les locks
				throw new AbortException();
			}
		}

		commitDate = clock.getAndIncrement();

		for (x in lws) {
			X.set(lcx.value, commitDate);
		}

		//unlock

	}

	@Override
	public boolean is_commited() {
		// TODO Auto-generated method stub
		return false;
	}

}
