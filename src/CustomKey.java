
public class CustomKey <K>{

	private K t;
	
	
	public CustomKey(K t){
		setT(t);
	}
	
	
	public void setT(K t){
		this.t=t;
	}
	
	public K getT(){
		return this.t;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((t == null) ? 0 : t.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CustomKey))
			return false;
		CustomKey other = (CustomKey) obj;
		if (t == null) {
			if (other.t != null)
				return false;
		} else if (!t.equals(other.t))
			return false;
		return true;
	}
	
	
}
