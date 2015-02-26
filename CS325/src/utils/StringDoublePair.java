package utils;

public class StringDoublePair implements Comparable<StringDoublePair> {
	private String s;
	private double d;
	
	public StringDoublePair(String s,double d) {
		set(s,d);
	}
	
	public StringDoublePair(StringDoublePair p) {
		set(p.s,p.d);
	}
	
	public String getString() {
		return s;
	}
	
	public double getDouble() {
		return d;
	}
	
	public void set(String s,double d) {
		setString(s);
		setDouble(d);
	}
	
	public void setString(String s) {
		this.s=s;
	}
	
	public void setDouble(double d) {
		this.d=d;
	}
	
	@Override
	public int compareTo(StringDoublePair o) {
		return (int)Math.signum(d-o.d);
	}
}
