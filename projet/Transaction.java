package projet;

public interface Transaction {
	public void begin();
	public void try_to_commit() throws AbortException;
	public boolean isCommited();
}
