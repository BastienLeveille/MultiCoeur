package projet;


import java.time.LocalDateTime;
import java.util.Date;

public class MyRegister implements Register<Integer> {
	private int value;
	private Date date;
	public MyRegister(int x) {
		this.value = x;
		LocalDateTime now = LocalDateTime.now();
		
		this.date = new Date(now.getYear(),now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond());
	}
	@Override
	public Integer read(Transaction t) throws AbortException {
		return value;
	}

	@Override
	public void write(Transaction t, Integer v) throws AbortException {
				
	}
	public Date getDate() {
		return this.date;
	}

}
