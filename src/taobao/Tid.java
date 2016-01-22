package taobao;

public class Tid {

	private long tid;

	private static Tid Tid = new Tid();
	public static Tid getInstance(){
		if(Tid==null){
			Tid = new Tid();
		}
		return Tid;
	}
	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}
	
}
