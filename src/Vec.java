

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@SuppressWarnings("hiding")
public class Vec {

	private Set<Integer> domain;
	private HashMap<Integer, Number> codomain;
	private Integer[] domainKeys;

	// private Object [] codomainKeys;

	public Vec() {
		this.domain = new HashSet<Integer>();
		this.codomain = new HashMap<Integer, Number>();

	}

	public Vec(Set<Integer> domain, HashMap<Integer, Number> codomain) {

		this.domain = new HashSet<Integer>();
		this.domain = domain;
		domainKeys = this.domain.toArray(new Integer[0]);

		this.codomain = new HashMap<Integer, Number>();
		this.codomain = codomain;
		setCodomain(codomain);
	}

	/*
	 * NA GINEI
	 */
	// public Vec (int [] domain, float [] values){
	// Number [] ints = removeDuplicates(domain);
	// this.domain = new HashSet<Integer>();
	// this.codomain = new HashMap<Integer,Number>();
	// for (int i=0; i<this.domain.size(); i++){
	// this.codomain.put(this.domain.iterator().next(), values[i]);
	// }
	//
	// }

	private void setDomain(Set<Integer> domain) {
		this.domain = domain;
	}

	private Set<Integer> getDomain() {
		return this.domain;
	}

	private void setCodomain(HashMap<Integer, Number> codomain) {
		for (int i = 0; i < this.domainKeys.length; i++) {
			Number value = (codomain.containsKey(domainKeys[i]) ? codomain.get(domainKeys[i]) : 0);
			codomain.put(domainKeys[i], value);
		}

	}

	private HashMap<Integer, Number> getCodomain() {
		return this.codomain;
	}

	public void setValue(Integer position, Number i) {
		this.domain.add(position);
		this.codomain.put(position, i);
	}

	public Float getValue(Integer position) {
		if (!this.domain.contains(position))
			throw new java.lang.NullPointerException("no such element in your domain:" + position);
		return (Float) this.codomain.get(position);
	}

	private static void checkDomainEquality(Vec a, Vec b, String message) {
		if (!a.domain.equals(b.getDomain()))
			throw new java.lang.RuntimeException(message);
	}

	public void add(Vec a) {
		checkDomainEquality(this, a, "cannot add vector " + this.domain
				+ " and " + a.domain + " ,not same domain");
		for (Integer key : a.codomain.keySet()) {
			Float v = this.codomain.get(key).floatValue()* a.codomain.get(key).floatValue();
			this.codomain.put(key, v);
		}

	}

	public static Vec add(Vec a, Vec b) {
		String errorMessage = "cannot add vector " + a.domain + " and "
				+ b.domain + " ,not same domain";
		checkDomainEquality(a, b, errorMessage);
		Vec result = new Vec(a.domain, new HashMap<Integer, Number>());
		for (int i = 0; i < a.domain.size(); i++) {
			Float value = a.codomain.get(i).floatValue()+ b.codomain.get(i).floatValue();
			result.codomain.put(i, value);
		}
		return result;
	}

	public void mult(Number a) {
		for (Integer i : this.codomain.keySet()) {
			float k = this.codomain.get(i).floatValue() * a.floatValue();
			this.codomain.put(i, k);
		}
	}

	/**
	 * returns the dot product between 2 vectors sqrt(a.x*b.x+a.y*b.y)
	 * 
	 * @param a
	 * @param b
	 * @throw throws RuntimeException when a and b have different domains
	 * @return dot product as Float
	 * @see www.youtube.com/watch?v=ANvGnRQDzM4}
	 */
	public static Float dot(Vec a, Vec b) {
		String errorMessage = "cannot add vector " + a.domain + " and "
				+ b.domain + " ,not same domain";
		checkDomainEquality(a, b, errorMessage);
		ArrayList<Number> temp = new ArrayList<Number>();
		for (Integer key : a.codomain.keySet()) {
			Float value = a.codomain.get(key).floatValue() * b.codomain.get(key).floatValue();
			temp.add(value);
		}
		return sum(temp);
	}

	/**
	 * sqrt(x^2+y^2)
	 * 
	 * @param Vec
	 * @return Float
	 */
	public static Float vec_length(Vec a) {
		ArrayList<Number> temp = new ArrayList<Number>();
		for (Number n : a.codomain.values())
			temp.add(n.doubleValue() * n.doubleValue());
		return (float) Math.sqrt(sum(temp));
	}

	/**
	 * Vec/vec_length
	 */
	public void normalize() {
		for (Integer key : this.codomain.keySet()) {
			this.codomain.put(key,this.codomain.get(key).floatValue() / Vec.vec_length(this));
		}
	}

	/**
	 * 
	 * @param a
	 *            the vector that you want to normalize
	 * @return new Vec
	 */
	public static Vec normalized(Vec a) {
		Vec normed = new Vec(new HashSet<Integer>(a.domain),new HashMap<Integer, Number>(a.codomain));
		normed.normalize();
		return normed;

	}

	private static Float sum(ArrayList<Number> ints) {
		Float result = 0.0f;
		for (int i = 0; i < ints.size(); i++)
			result += ints.get(i).floatValue();
		return result;
	}

	public static float degreesToRadians(float degrees) {
		return (float) (degrees * Math.PI / 180);
	}

	public static float radiansToDegrees(float radians) {
		return (float) ((radians * 180) / Math.PI);
	}

	/**
	 * return float in Degrees!
	 * 
	 * @param a
	 * @param b
	 * @return float
	 */
	public static float angleBetween(Vec a, Vec b) {
		if (a.equals(b))
			throw new java.lang.RuntimeException(
					"Cannot find angle for vectors a:" + a.toString()
							+ " and b:" + b.toString() + " are equal");
		return radiansToDegrees((float) Math.acos(dot(a, b)
				/ (vec_length(a) * vec_length(b))));

	}

	@Override
	public String toString() {
		return "domain:" + this.domain + " codomain:" + this.codomain;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codomain == null) ? 0 : codomain.hashCode());
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vec))
			return false;
		Vec other = (Vec) obj;
		if (codomain == null) {
			if (other.codomain != null)
				return false;
		} else if (!codomain.equals(other.codomain))
			return false;
		if (domain == null) {
			if (other.domain != null)
				return false;
		} else if (!domain.equals(other.domain))
			return false;
		return true;
	}

	public static void main(String[] args) {
		Vec v1 = new Vec();
		v1.setValue(0, 234);
		v1.setValue(1, 564);
		v1.setValue(3, 7);
		v1.setValue(7, 45);
		Vec v2 = new Vec();

		v2.setValue(0, 37);
		v2.setValue(1, 16);
		v2.setValue(3, 567);
		v2.setValue(7, 5);
		System.out.println(v1 + "\n" + v2);
		v1.add(v2);
		System.out.println(Vec.angleBetween(v1, v2));
		System.out.println(Vec.dot(v1, v2));
		Set <Integer> a = new HashSet<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);
		a.add(5);
		a.add(34);
		HashMap <Integer,Number> b = new HashMap<Integer, Number>();
		b.put(1, 24);
		b.put(2, 3456);
		b.put(3, 90);
		b.put(6, 53);
		Vec v3 = new Vec(a,b);
		System.out.println("\n"+v3);
	}

}
