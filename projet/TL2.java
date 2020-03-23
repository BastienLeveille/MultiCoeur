package projet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class TL2 implements Transaction {
	private	int birthDate;
	private ArrayList<Register> lrsT = new ArrayList<Register>();
	private ArrayList<Register> lwsT = new ArrayList<Register>();
	
public TL2() {}
@Override
public void begin() {
	// TODO Auto-generated method stub
	
}

@Override
public void try_to_commit() throws AbortException {
	// TODO Auto-generated method stub
	
}

@Override
public boolean isCommited() {
	// TODO Auto-generated method stub
	return false;
}


}
