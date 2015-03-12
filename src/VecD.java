

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings("hiding")
public class VecD <K ,Float> {
	
	private Class <K> type;
	private Set<K> domain;
	private HashMap<K,Float> codomain;
	private K [] domainKeys;

	@SuppressWarnings("unchecked")
	public VecD(Set<K> domain,HashMap<K,Float> codomain){
		this.domain = domain;
		this.codomain = codomain;
		this.domainKeys = this.domain.toArray((K[])new Object[0]);
	}
	
	public VecD(){
		this.domain = new TreeSet<K>();
		this.codomain = new HashMap<K,Float>();
		this.domain = domain;
		this.codomain = codomain;
		this.domainKeys = this.domain.toArray((K[])new Object[0]);
	}
	
	public VecD(Class <K> type){
		this.type = type;
		
	}
	private boolean sameClass(Object o){
		return o!=null && o.getClass() == type;
	}
	@Override
	public String toString() {
		return "VecD [ Domain =" + getDomain() + ", Codomain = "
				+ getCodomain() + "]";
	}
	private static void checkDomainEquality(VecD a,VecD b,String message){
		if( !a.domain.equals(b.getDomain()) )
			throw new java.lang.RuntimeException(message);
	}
	
	public void add(VecD <K,Float> a){
		checkDomainEquality(this, a, "cannot add vector "+this.domain+" and "+a.domain+ " ,not same domain" );
		for (K key:a.codomain.keySet()){
		
		}

	}
	
	
	public Set<K> getDomain() {
		return domain;
	}

	public void setDomain(Set<K> domain) {
		this.domain = domain;
	}

	public HashMap<K, Float> getCodomain() {
		return codomain;
	}

	public void setCodomain(HashMap<K, Float> codomain) {
		for (int i=0; i<domainKeys.length; i++){
			Float value = (Float) (codomain.containsKey(domainKeys[i])?codomain.get(domainKeys[i]):0);
			codomain.put(domainKeys[i], value);
		}
	}

	public void setValue(K key, Float value){
		this.domain.add(key);
		this.codomain.put(key, value);
	}
	
	public Number getValue(K key){
		if (!this.domain.contains(key))
			throw new java.lang.NullPointerException("no such element in your domain:"+key);
		return  (Number) this.codomain.get(key);
	}
	
	
	
	
	public static void main(String [] args){
		VecD v1 = new VecD();
		v1.setValue("A", 4.34f);
		v1.setValue("B", 564);
		v1.setValue("SA", 7);
		v1.setValue("s", 45);
		VecD v2 = new VecD();

		v2.setValue(10, 7);
		v2.setValue(1, 16);
		v2.setValue(4, 67);
		v2.setValue(7, 5);
		
		System.out.println(v1+"\n"+v2);
		System.out.println(v2.getValue(4));
	}

	

	
}
