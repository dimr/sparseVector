import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Avec <K> {

	private CustomKey<K> key;
	private Float value;
	private Set<CustomKey<K>> domain;
	private Map <CustomKey<K>,Float> codomain;
	private CustomKey<K>[] domainKeys;
	
	public Avec(Set<CustomKey<K>> domain,HashMap<CustomKey<K>,Float> codomain){
		this.domain = domain;
		this.codomain = codomain;
		this.domainKeys = this.domain.toArray((CustomKey<K>[])new Object[0]);
	}
	
	public Avec(){
		this.domain = new TreeSet<CustomKey<K>>();
		this.codomain = new HashMap<CustomKey<K>,Float>();
		this.domain = domain;
		this.codomain = codomain;
		//this.domainKeys = this.domain.toArray((K[])new Object[0]);
	}
	private void setDomain(Set<CustomKey<K>> domain) {
		this.domain = domain;
	}

	private Set<CustomKey<K>> getDomain() {
		return this.domain;
	}

	private void setCodomain(HashMap<CustomKey<K>, Number> codomain) {
		for (int i = 0; i < this.domainKeys.length; i++) {
			Number value = (codomain.containsKey(domainKeys[i]) ? codomain.get(domainKeys[i]) : 0);
			codomain.put(domainKeys[i], value);
		}

	}

	private Map<CustomKey<K>, Float> getCodomain() {
		return this.codomain;
	}

	public void setValue(CustomKey<K> position, Float i) {
		this.domain.add(position);
		this.codomain.put(position, i);
	}

	public Float getValue(Integer position) {
		if (!this.domain.contains(position))
			throw new java.lang.NullPointerException("no such element in your domain:" + position);
		return (Float) this.codomain.get(position);
	}

	private static void checkDomainEquality(Avec a, Avec b, String message) {
		if (!a.domain.equals(b.getDomain()))
			throw new java.lang.RuntimeException(message);
	}
	
	public void add(Avec a){
		checkDomainEquality(this, a, "cannot add vector " + this.domain	+ " and " + a.domain + " ,not same domain");
			
			
		
	}
	
	public static void main(String [] ar){
		HashMap<CustomKey<String>,Float> map = new HashMap<CustomKey<String>, Float>();
	}
	
}
