package Restaurante;

import java.io.Serializable;

public final class Fecha implements Serializable, Comparable<Fecha>{

	int dd;
	int mm;
	int aa;
	private static final long serialVersionUID = 1L;
	
	public Fecha(int d, int m, int a) {
		dd = d;
		mm = m;
		aa = a;
	}
	
	public int getDd() {
		return dd;
	}
	public void setDd(int dd) {
		this.dd = dd;
	}
	public int getMm() {
		return mm;
	}
	public void setMm(int mm) {
		this.mm = mm;
	}
	public int getAa() {
		return aa;
	}
	public void setAa(int aa) {
		this.aa = aa;
	}
	
	@Override
	public int compareTo(Fecha ob) {
		if(this.aa > ob.aa){
			return 1;
		}
		else if(this.aa < ob.aa){
			return -1;
		}
		else if(this.mm > ob.mm){
			return 1;
		}
		else if(this.mm < ob.mm){
			return -1;
		}
		else if(this.dd > ob.dd){
			return 1;
		}
		else if(this.dd < ob.dd){
			return -1;
		}
		return 0;
	}	
}