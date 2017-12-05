package com.test.hashCode;

import java.util.HashSet;
import java.util.Set;

public class HashCodeTest {

	private int i;

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	@Override
	public int hashCode() {
		// return super.hashCode();
		return i % 10;
	}

	@Override
	public boolean equals(Object object) {
		// return super.equals(obj);
		if (object == null) {
			return false;
		}
		if (object == this) {
			return true;
		}
		if (!(object instanceof HashCodeTest)) {
			return false;
		}
		HashCodeTest other = (HashCodeTest) object;
		if (other.getI() == this.getI()) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		HashCodeTest t1 = new HashCodeTest();
		HashCodeTest t2 = new HashCodeTest();
		t1.setI(12);
		t2.setI(12);

		Set<HashCodeTest> set = new HashSet<HashCodeTest>();
		set.add(t1);
		set.add(t2);
		System.out.println(t1.hashCode() + "---" + t2.hashCode() + "-----" + (t1.hashCode() == t2.hashCode()));
		System.out.println(t1.equals(t2));
		System.out.println(set);

	}
}
