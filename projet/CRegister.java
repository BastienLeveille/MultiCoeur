package projet;
import java.util.ArrayList;

public class CRegister<T> implements Register<T> {
	T value;
	Date localDate;

	public Registre<T> (){
		value = null;
		localDate = null;
	}


	@Override
	public T read(Transaction t) throws AbortException {
		// TODO Auto-generated method stub
		if (value == null || localDate == null)
		return null;
	}

	@Override
	public void write(Transaction t, T v) throws AbortException {
		// TODO Auto-generated method stub
		if (X)

	}

}
